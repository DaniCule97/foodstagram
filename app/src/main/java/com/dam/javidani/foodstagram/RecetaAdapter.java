package com.dam.javidani.foodstagram;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>{

    private ArrayList<Receta> data;

    public RecetaAdapter(ArrayList<Receta> data) {
        this.data = data;
    }

    @SuppressLint("ResourceType")
    @Override
    public RecetaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecetaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.xml.item_receta, parent, false));
    }

    @Override
    public void onBindViewHolder(RecetaViewHolder holder, int position) {
        Receta receta = data.get(position);
        // holder.imgMusica.setImageResource(musica.getImagen());
        holder.tvNombre.setText(receta.getNombre());
        // holder.tvAutor.setText(receta.getAutor());
        holder.tvDescripcion.setText(receta.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecetaViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre, tvAutor, tvDescripcion;

        public RecetaViewHolder(View itemView){
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tv_nombre);
            // tvAutor = itemView.findViewById(R.id.tv_autor);
            tvDescripcion = itemView.findViewById(R.id.tv_descripcion);
        }
    }
}
