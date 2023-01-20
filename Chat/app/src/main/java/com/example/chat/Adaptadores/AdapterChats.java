package com.example.chat.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chat.ChatEspecifico;
import com.example.chat.ChatGrupal;
import com.example.chat.R;
import com.example.chat.Usuario;

import java.util.ArrayList;

public class AdapterChats extends RecyclerView.Adapter<AdapterChats.ViewHolderChats> {

    Context contexto;
    Usuario usuario;
    ArrayList<Usuario> listaUsuarios;

    public AdapterChats(Context contexto, Usuario usuario, ArrayList<Usuario> listaUsuarios) {
        this.contexto = contexto;
        this.usuario = usuario;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public AdapterChats.ViewHolderChats onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_chats, null, false);
        return new ViewHolderChats(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChats.ViewHolderChats viewHolderChats, final int i) {
        viewHolderChats.tvChatUsuario.setText(listaUsuarios.get(i).getNombre());

        viewHolderChats.tvChatUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listaUsuarios.get(i).getNombre().equals("TODOS")) {
                    //Toast.makeText(v.getContext(), "CHAT GRUPAL", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), ChatGrupal.class);
                    intent.putExtra("usuario", usuario);
                    contexto.startActivity(intent);
                } else {
                    //Toast.makeText(v.getContext(), usuario.getNombre()+" , "+listaUsuarios.get(i).getNombre(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), ChatEspecifico.class);
                    intent.putExtra("usuario", usuario);
                    intent.putExtra("usuarioDestino", listaUsuarios.get(i));
                    contexto.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class ViewHolderChats extends RecyclerView.ViewHolder {

        TextView tvChatUsuario;

        public ViewHolderChats(@NonNull View itemView) {
            super(itemView);

            tvChatUsuario = itemView.findViewById(R.id.tvChatUsuario);
        }
    }
}
