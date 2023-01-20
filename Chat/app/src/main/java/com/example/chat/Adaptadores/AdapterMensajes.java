package com.example.chat.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat.Mensaje;
import com.example.chat.R;
import com.example.chat.Usuario;

import java.util.ArrayList;

public class AdapterMensajes extends RecyclerView.Adapter<AdapterMensajes.ViewHolderMensajes> {

    Context contexto;
    Usuario usuario;
    ArrayList<Mensaje> listaMensajes;

    public AdapterMensajes(Context contexto, Usuario usuario, ArrayList<Mensaje> listaMensajes) {
        this.contexto = contexto;
        this.usuario = usuario;
        this.listaMensajes = listaMensajes;
    }

    @NonNull
    @Override
    public AdapterMensajes.ViewHolderMensajes onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_mensaje, null, false);
        return new AdapterMensajes.ViewHolderMensajes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMensajes.ViewHolderMensajes viewHolderMensajes, int i) {

        if(usuario.getUsuario().equals(listaMensajes.get(i).getUsuarioOrigen())) {
            viewHolderMensajes.tvNombreUsuario.setGravity(Gravity.LEFT);
            viewHolderMensajes.tvMensaje.setGravity(Gravity.LEFT);
            viewHolderMensajes.tvNombreUsuario.setTextColor(Color.BLUE);
            viewHolderMensajes.tvNombreUsuario.setText(listaMensajes.get(i).getUsuarioOrigen());
            viewHolderMensajes.tvMensaje.setText(listaMensajes.get(i).getMensaje());
        } else {
            viewHolderMensajes.tvNombreUsuario.setGravity(Gravity.RIGHT);
            viewHolderMensajes.tvMensaje.setGravity(Gravity.RIGHT);
            viewHolderMensajes.tvNombreUsuario.setTextColor(Color.RED);
            viewHolderMensajes.tvNombreUsuario.setText(listaMensajes.get(i).getUsuarioOrigen());
            viewHolderMensajes.tvMensaje.setText(listaMensajes.get(i).getMensaje());
        }
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    public class ViewHolderMensajes extends RecyclerView.ViewHolder {

        TextView tvNombreUsuario, tvMensaje;

        public ViewHolderMensajes(@NonNull View itemView) {
            super(itemView);

            tvNombreUsuario = itemView.findViewById(R.id.tvNombreUsuario);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
        }
    }
}
