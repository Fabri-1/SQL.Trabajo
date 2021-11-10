package com.example.sql.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql.R;
import com.example.sql.objetos.userVO;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UsuarioHolder> {

    private ArrayList<userVO> listaUser;

    public AdapterUser(ArrayList<userVO> listaUsuariosDesplegar) {
        listaUser = listaUsuariosDesplegar;
    }

    @NonNull
    @Override
    public AdapterUser.UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);

        UsuarioHolder holder = new UsuarioHolder(item);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.UsuarioHolder holder, int position) {

        userVO item= listaUser.get(position);

        if(item.getGenero().toUpperCase().equals("MASCULINO")){
            holder.avatar.setImageResource(R.drawable.avatar_mas);
        }else{
            holder.avatar.setImageResource(R.drawable.avatar_fem);
        }
        holder.nombre.setText(item.getId_usuario()+": "+item.getNombre()+" "+item.getApePaterno()+" "+item.getApeMaterno());
        holder.correo.setText(item.getEmail());
        holder.editar.setId(item.getId_usuario());
        holder.eliminar.setId(item.getId_usuario());
        holder.detalle.setId(item.getId_usuario());
    }

    @Override
    public int getItemCount() {
        return listaUser.size();
    }

    public static class UsuarioHolder extends RecyclerView.ViewHolder {
        public ImageView avatar;
        public TextView nombre, correo;
        public ImageButton eliminar,detalle,editar;

        public UsuarioHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            nombre = itemView.findViewById(R.id.nombre2);
            correo = itemView.findViewById(R.id.correo);
            eliminar = itemView.findViewById(R.id.btnBorrar);
            detalle = itemView.findViewById(R.id.btnDetalles);
            editar = itemView.findViewById(R.id.btnEditar);
        }
    }
}
