package com.example.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chat.Adaptadores.AdapterChats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Chats extends AppCompatActivity {

    ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    RecyclerView rvChats;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
        getSupportActionBar().setTitle(usuario.getNombre());

        rvChats = findViewById(R.id.rvChats);
        rvChats.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        obtenerChats();

    }

    public void obtenerChats() {
        listaUsuarios.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_USUARIOS),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("usuarios");

                            for(int i = 0 ; i < jsonArray.length() ; i++) {
                                JSONObject objeto = jsonArray.getJSONObject(i);

                                Usuario usuario = new Usuario(
                                        objeto.getString("usuario"),
                                        objeto.getString("contrasena"),
                                        objeto.getString("nombre")
                                );

                                listaUsuarios.add(usuario);
                            }

                            AdapterChats adaptador = new AdapterChats(Chats.this, usuario, listaUsuarios);
                            rvChats.setAdapter(adaptador);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(Chats.this, "ERROR EN LA CONEXIÓN", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor

                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("usuario", usuario.getUsuario().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
