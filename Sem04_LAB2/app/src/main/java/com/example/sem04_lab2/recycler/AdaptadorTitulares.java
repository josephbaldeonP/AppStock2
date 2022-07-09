package com.example.sem04_lab2.recycler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.sem04_lab2.R;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class AdaptadorTitulares
        extends RecyclerView.Adapter<AdaptadorTitulares.TitularesViewHolder>
        implements View.OnClickListener {
    private ArrayList<Titular> datos;
    private View.OnClickListener listener;
    public AdaptadorTitulares(ArrayList<Titular> datos) {
        this.datos = datos;
    }
    public static class TitularesViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitulo;
        private TextView txtSubtitulo;
        public TitularesViewHolder(View itemView) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.LblTitulo);
            txtSubtitulo = (TextView) itemView.findViewById(R.id.LblSubTitulo);
        }
        public void bindTitular(Titular t) {
            txtTitulo.setText(t.getTitulo());
            txtSubtitulo.setText(t.getSubtitulo());
        }
    }
    @Override
    public int getItemCount() {
        return datos.size();
    }
    @Override
    public void onBindViewHolder(@NonNull TitularesViewHolder holder, int position) {
        Titular item = datos.get(position);
        holder.bindTitular(item);
    }
    @Override
    public TitularesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_titular, parent, false);
        itemView.setOnClickListener(this);
        TitularesViewHolder tvh = new TitularesViewHolder(itemView);
        return tvh;
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }
}
