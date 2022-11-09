package com.example.ejercicio01simulacro.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio01simulacro.Constantes;
import com.example.ejercicio01simulacro.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CigarrilloAdapter extends RecyclerView.Adapter<CigarrilloAdapter.CigarrilloVH> {

    private List<String> objects;
    private int resource; //fila
    private Context context;
    private int totalDineroAhorrado;


    public CigarrilloAdapter(List<String> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
        this.totalDineroAhorrado = 0;
    }

    public List<String> getObjects() {
        return objects;
    }

    @NonNull
    @Override
    public CigarrilloVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(context).inflate(resource, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        todoView.setLayoutParams(lp);

        return new CigarrilloVH(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull CigarrilloVH holder, int position) {
        String cigarillo = objects.get(position);
        holder.txtCigarrillo.setText("Cigarillo Real");

        holder.btnEliminarCigarrillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objects.remove(cigarillo);
                notifyItemRemoved(holder.getAdapterPosition());
                if (getItemCount() == 0){
                    totalDineroAhorrado += Constantes.DINEROAHORRADOPORPAQUETE;
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
                    Toast.makeText(context, numberFormat.format(totalDineroAhorrado), Toast.LENGTH_SHORT).show();
                    crearNuevoPaquete();
                }
            }
        });
    }

    private void crearNuevoPaquete() {
        for (int i = 0; i < Constantes.TAMANYOPAQUETE; i++) {
            objects.add("cigarrillo ");
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    public class CigarrilloVH extends RecyclerView.ViewHolder{

        TextView txtCigarrillo;
        ImageButton btnEliminarCigarrillo;

        public CigarrilloVH(@NonNull View itemView) {
            super(itemView);
            txtCigarrillo = itemView.findViewById(R.id.txtCigarrilloMain);
            btnEliminarCigarrillo = itemView.findViewById(R.id.btnEliminarCigarrilloMain);

        }
    }
}




