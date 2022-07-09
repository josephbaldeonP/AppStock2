package com.example.appstock2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {

  Context context;
  ArrayList<Product> list;
  private DatabaseReference database;
    public Adaptador(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          Product producto=  list.get(position);
          holder.modelo.setText(producto.getModelo().toString());
        holder.precio.setText(producto.getPrecio().toString());
        holder.stock.setText(producto.getStock().toString());
        Picasso.get().load("https://www.pikpng.com/pngl/m/492-4928302_licuadora-png-licuadora-black-and-decker-clipart.png").into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogplus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();

                dialogplus.show();



            }
        });

    }

    @Override
    public int getItemCount() {
       return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

     TextView modelo,precio,stock;
     ImageView img;
     Button edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            modelo=itemView.findViewById(R.id.modelo);
            precio=itemView.findViewById(R.id.precio);
            stock=itemView.findViewById(R.id.stock);
            img=itemView.findViewById(R.id.img1);
            edit=itemView.findViewById(R.id.btnedit);
        }
    }
}
