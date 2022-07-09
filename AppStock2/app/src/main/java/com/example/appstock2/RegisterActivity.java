package com.example.appstock2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
     private TextView banner, registerUser;
     private EditText editTextFullName, editTextEmail, editTextPass;
     private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();

        banner=(TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser=(Button)findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFullName=(EditText) findViewById(R.id.fullName);
        editTextEmail=(EditText) findViewById(R.id.email);
        editTextPass=(EditText) findViewById(R.id.password);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.banner:
                startActivity(new Intent(this, HomeScreen.class));
                break;

            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser(){
          String email=editTextEmail.getText().toString();
        String password=editTextPass.getText().toString();
        String fullName=editTextFullName.getText().toString();

        if(fullName.isEmpty()){

            editTextFullName.setError("El Nombre completo es requerido");
            editTextFullName.requestFocus();
            return;

        }
        if(email.isEmpty()){

            editTextEmail.setError("El Email es requerido");
            editTextEmail.requestFocus();
            return;

        }

        if(password.isEmpty()){

            editTextPass.setError("la contraseña es requerida");
            editTextPass.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Email invalido");
            editTextEmail.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Usuario user=new Usuario(fullName,email);

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                     if(task.isSuccessful()){
                                         Toast.makeText(RegisterActivity.this,"Usuario registrado", Toast.LENGTH_SHORT).show();
                                         progressBar.setVisibility(View.GONE);
                                     }else {
                                         Toast.makeText(RegisterActivity.this,"Error al registrar usuario Intentalo de nuevo",Toast.LENGTH_LONG).show();
                                         progressBar.setVisibility(View.GONE);
                                     }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this,"Error al registrar usuario",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });






    }
}