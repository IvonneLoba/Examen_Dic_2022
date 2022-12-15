package com.example.examen_dic_2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv1;
    private List<Contactos>contactos;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        List<Contactos> contactos = new ArrayList<Contactos>();
    }

    private Adaptador_Contactos adaptador_contactos;

    Volley_Singleton requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv1 = findViewById(R.id.rv1);

        requestQueue = Volley_Singleton.getInstance(this);
        Mostrar();

        Adaptador_Contactos adaptador_contactos = new Adaptador_Contactos(contactos, MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        rv1.setLayoutManager(linearLayoutManager);
        rv1.setAdapter(adaptador_contactos);
    }

    private void Mostrar() {
        String url = "https://ramiro.uttics.com/api/contactos";
        JsonObjectRequest requiere = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Gson gson = new Gson();
                Resp_Obj co = gson.fromJson(response.toString(),Resp_Obj.class);

                contactos = Resp_Obj.data;
                rv1.setAdapter(adaptador_contactos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.addToRequestQue(requiere);
    }
}