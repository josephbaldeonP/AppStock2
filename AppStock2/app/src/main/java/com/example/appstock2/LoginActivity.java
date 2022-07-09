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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
     private TextView register;
     private EditText editTextEmail, editTextPass;
     private Button signIn;

     private FirebaseAuth mAuth;
     private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register=(TextView)findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn=(Button) findViewById(R.id.loginbtn);
        signIn.setOnClickListener(this);

        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        editTextPass=(EditText) findViewById(R.id.editTextPassword);

        progressBar=(ProgressBar) findViewById(R.id.progresbar);

        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.loginbtn:
                userLogin();
                break;
        }
    }

    private void userLogin(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPass.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Email invalido");
            editTextEmail.requestFocus();
            return;

        }

        if(email.isEmpty()){

            editTextEmail.setError("El Email es requerido");
            editTextEmail.requestFocus();
            return;

        }
        if(password.isEmpty()){

            editTextPass.setError("La contraseña es requerido");
            editTextPass.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                        startActivity(new Intent(LoginActivity.this,HomeScreen.class));
                    progressBar.setVisibility(View.GONE);

                }else{

                    Toast.makeText(LoginActivity.this,"¡Error al ingresar! Comprueba tus datos", Toast.LENGTH_SHORT).show();

                }
            }
        });




    }
}