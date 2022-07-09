package com.example.aplicacionzapgest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import com.example.aplicacionzapgest.recycler.AdaptadorTitulares;
import com.example.aplicacionzapgest.recycler.Titular;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView recView;
    private ArrayList<Titular> datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        //inicialización de la lista de datos de ejemplo
        datos = new ArrayList<Titular>();
        /*for(int i=0; i<25; i++)
            datos.add(new Titular("Articulo " + i, "Subtítulo item " + i));*/
        datos.add(new Titular("Articulo 1", "lavadora  Precio: s/.500"));
        datos.add(new Titular("Articulo 2", "televisor  Precio: s/.1500"));
        datos.add(new Titular("Articulo 3", "licuadora  Precio: s/.90"));
        datos.add(new Titular("Articulo 4", "radio  Precio: s/.50"));

        //Inicialización RecyclerView
        recView = (RecyclerView) findViewById(R.id.RecView);
        recView.setHasFixedSize(true);
        final AdaptadorTitulares adaptador = new AdaptadorTitulares(datos);
        recView.setAdapter(adaptador);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Pulsando el elemento " +

                        recView.getChildAdapterPosition(v),Snackbar.LENGTH_SHORT).show();
            }
        });
        recView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
    }
}