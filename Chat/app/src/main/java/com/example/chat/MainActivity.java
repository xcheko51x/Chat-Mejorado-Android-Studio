package com.example.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario, etContrasena;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsuario.getText().toString().isEmpty() || etContrasena.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Se deben llenar los dos campos", Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_LONG).show();
                    login();
                }
            }
        });
    }

    public void login() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_LOGIN),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("usuario");

                            JSONObject objeto = jsonArray.getJSONObject(0);

                            Usuario usuario = new Usuario(
                                    objeto.getString("usuario"),
                                    objeto.getString("contrasena"),
                                    objeto.getString("nombre")
                            );

                            Intent intent = new Intent(MainActivity.this, Chats.class);
                            intent.putExtra("usuario", usuario);
                            etUsuario.setText("");
                            etContrasena.setText("");
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(MainActivity.this, "ERROR EN LA CONEXIÓN", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor

                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("usuario", etUsuario.getText().toString());
                parametros.put("contrasena", etContrasena.getText().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
