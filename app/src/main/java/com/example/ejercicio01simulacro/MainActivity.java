package com.example.ejercicio01simulacro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.ejercicio01simulacro.adapters.CigarrilloAdapter;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ejercicio01simulacro.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<String> paqueteList;
    private RecyclerView.LayoutManager layoutManager;
    private CigarrilloAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        crearPaquete();

        adapter = new CigarrilloAdapter(paqueteList, R.layout.cigarrillo_model_view, MainActivity.this);
        binding.contentMain.contenedor.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void crearPaquete() {
        paqueteList = new ArrayList<>();
        for (int i = 0; i < Constantes.TAMANYOPAQUETE; i++) {
            paqueteList.add("cigarrillo ");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.notifyDataSetChanged();
        outState.putSerializable(Constantes.LISTA, paqueteList);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<String> temp = (ArrayList<String>) savedInstanceState.getSerializable(Constantes.LISTA);
        paqueteList.addAll(temp);
        adapter.notifyItemRangeInserted(0, paqueteList.size());
    }


}