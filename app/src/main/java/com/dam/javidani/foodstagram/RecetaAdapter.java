package com.dam.javidani.foodstagram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
    private Context mContext;
    public RecetaAdapter(Context context, ArrayList<Receta> data) {
        this.data = data;
        this.mContext = context;
    }

    @SuppressLint("ResourceType")
    @Override
    public RecetaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecetaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.xml.item_receta, parent, false));
    }

    @Override
    public void onBindViewHolder(RecetaViewHolder holder, final int position) {
        Receta receta = data.get(position);
        // holder.imgMusica.setImageResource(musica.getImagen());
        holder.tvNombre.setText(receta.getNombre());
        holder.tvAutor.setText(receta.getAutor());
        holder.tvDescripcion.setText(receta.getDescripcion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RecetaActivity.class);
                intent.putExtra("autor", data.get(position).getAutor());
                intent.putExtra("nombre", data.get(position).getNombre());
                mContext.startActivity(intent);
            }
        });
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
            tvAutor = itemView.findViewById(R.id.tv_autor);
            tvDescripcion = itemView.findViewById(R.id.tv_descripcion);
        }
    }
}
