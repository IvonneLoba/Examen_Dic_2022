package com.example.examen_dic_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adaptador_Contactos extends RecyclerView.Adapter<Adaptador_Contactos.Adaptador_ContactosHolder> {
    List<Contactos>contactos;
    Button btnNum;
    Context c;
    Activity activity;

    public Adaptador_Contactos(List<Contactos> contactos, MainActivity mainActivity) {
        this.contactos = contactos;
        this.activity = mainActivity;
    }

    @NonNull
    @Override
    public Adaptador_Contactos.Adaptador_ContactosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        c=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adaptador_contactos, parent, false);
        return new Adaptador_ContactosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador_Contactos.Adaptador_ContactosHolder holder, int position) {
        holder.imprimir(position);
    }

    public Adaptador_Contactos(List<Contactos>contactos){this.contactos = contactos;}

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public class Adaptador_ContactosHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView Nombre;
        ImageView ivurl;

        public Adaptador_ContactosHolder(@NonNull View itemView) {
            super(itemView);

            Nombre = itemView.findViewById(R.id.Nombre);
            btnNum = itemView.findViewById(R.id.btnNum);
            ivurl = itemView.findViewById(R.id.ivurl);
        }

        public void imprimir(int position) {
            Nombre.setText("nombre: " + contactos.get(position).getClass());
            btnNum.setText("numero: " + contactos.get(position).getClass());
            btnNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(" numero: " + btnNum.getText()));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) ==
                            PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 255);
                    } else {
                        activity.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }
}