package com.example.sql.adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql.R;
import com.example.sql.esquemas.Asignatura;
import com.example.sql.esquemas.OperacionesCRUD;
import com.example.sql.esquemas.User;
import com.example.sql.objetos.asignaturaVO;

import java.util.ArrayList;

public class AdapterAsignatura extends RecyclerView.Adapter<AdapterAsignatura.AsignaturaHolder>{

    private ArrayList<asignaturaVO> listaAsignatura;

    public AdapterAsignatura(ArrayList<asignaturaVO> listaAsignaturaDesplegar){
        listaAsignatura = listaAsignaturaDesplegar;
    }

    @NonNull
    @Override
    public AdapterAsignatura.AsignaturaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asignatura,parent,false);

        AsignaturaHolder holder = new AsignaturaHolder(item);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAsignatura.AsignaturaHolder holder, int position) {

        asignaturaVO item = listaAsignatura.get(position);

        holder.codigo.setText(item.getCodigo());
        holder.descripcion.setText(item.getDescripcion());
        holder.borrar.setId(item.getId_asignatura());
        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String condicion = "id_asignatura=?";
                String valores[] = {""+item.getId_asignatura()};
                int cant_regs_eliminados=0;

                OperacionesCRUD instancia = new OperacionesCRUD(v.getContext(), "BDTEST",null,9);
                cant_regs_eliminados = instancia.borrarRegistro(Asignatura.Esquema.TABLA_ASIGNATURA,condicion,valores);

                if(cant_regs_eliminados>0){
                    Toast.makeText(v.getContext(),"La asignatura a sido eliminada",Toast.LENGTH_SHORT).show();
                    AdapterAsignatura.this.listaAsignatura.remove(holder.getAdapterPosition());
                }else{
                    Toast.makeText(v.getContext(),"Error al eliminar la asignatura",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return listaAsignatura.size();
    }

    public class AsignaturaHolder extends RecyclerView.ViewHolder {
        public TextView codigo,descripcion;
        public ImageButton borrar;

        public AsignaturaHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.codigo2);
            descripcion = itemView.findViewById(R.id.descripcion2);
            borrar = itemView.findViewById(R.id.btnBorrar2);
        }
    }
}
