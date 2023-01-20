package com.example.chat;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chat.Adaptadores.AdapterChats;
import com.example.chat.Adaptadores.AdapterMensajes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ChatEspecifico extends AppCompatActivity {

    Usuario usuario;
    Usuario usuarioDestino;

    ArrayList<Mensaje> listaMensajes = new ArrayList<Mensaje>();

    RecyclerView rvMensajes;

    EditText etTexto;

    Button btnEnviar, btnRecargar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_especifico);

        usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
        usuarioDestino = (Usuario) getIntent().getExtras().getSerializable("usuarioDestino");
        getSupportActionBar().setTitle(usuario.getNombre());

        rvMensajes = findViewById(R.id.rvMensajes);
        rvMensajes.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        etTexto = findViewById(R.id.etTexto);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnRecargar = findViewById(R.id.btnRecargar);

        obtenerMensajes();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etTexto.getText().toString().isEmpty()) {
                    Toast.makeText(ChatEspecifico.this, "Se te olvido escribir el mensaje.", Toast.LENGTH_LONG).show();
                } else {
                    enviarMensaje();
                }
            }
        });

        btnRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerMensajes();
            }
        });
    }

    private void ejecutar() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                obtenerMensajes();
                handler.postDelayed(this, 5000);
            }
        }, 5000);
    }

    public void enviarMensaje() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_ENVIAR_MENSAJE),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores
                        Toast.makeText(ChatEspecifico.this, response, Toast.LENGTH_LONG).show();
                        obtenerMensajes();
                        etTexto.setText("");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(ChatEspecifico.this, "ERROR EN LA CONEXIÓN", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor

                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("usuario", usuario.getUsuario());
                parametros.put("usuarioDestino", usuarioDestino.getUsuario());
                parametros.put("mensaje", etTexto.getText().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void obtenerMensajes() {
        listaMensajes.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_OBTENER_MENSAJES),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("mensajes");

                            for(int i = 0 ; i < jsonArray.length() ; i++) {
                                JSONObject objeto = jsonArray.getJSONObject(i);

                                Mensaje mensaje = new Mensaje(
                                        objeto.getString("idMensaje"),
                                        objeto.getString("mensaje"),
                                        objeto.getString("usuarioOrigen"),
                                        objeto.getString("usuarioDestino")
                                );

                                listaMensajes.add(mensaje);
                            }
                            rvMensajes.smoothScrollToPosition (listaMensajes.size());
                            AdapterMensajes adaptador = new AdapterMensajes(ChatEspecifico.this, usuario, listaMensajes);
                            rvMensajes.setAdapter(adaptador);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(ChatEspecifico.this, "ERROR EN LA CONEXIÓN", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor

                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("usuario", usuario.getUsuario());
                parametros.put("usuarioDestino", usuarioDestino.getUsuario());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
