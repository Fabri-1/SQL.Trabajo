package com.example.sql.adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql.ListaUsuarios;
import com.example.sql.MainActivity3;
import com.example.sql.R;
import com.example.sql.esquemas.OperacionesCRUD;
import com.example.sql.esquemas.User;
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

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.UsuarioHolder holder, int position) {

        userVO item = listaUser.get(position);

        if(item.getGenero().toUpperCase().equals("MASCULINO")){
            holder.avatar.setImageResource(R.drawable.avatar_mas);
        }else{
            holder.avatar.setImageResource(R.drawable.avatar_fem);
        }
        holder.nombre.setText(item.getId_usuario()+": "+item.getNombre()+" "+item.getApePaterno()+" "+item.getApeMaterno());
        holder.correo.setText(item.getEmail());
        //boton editar
        holder.editar.setId(item.getId_usuario());
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editarUsuario = new Intent(v.getContext(), MainActivity3.class);

                editarUsuario.putExtra("id",item.getId_usuario());
                editarUsuario.putExtra("nom",item.getNombre().toString());
                editarUsuario.putExtra("apepaterno",item.getApePaterno().toString());
                editarUsuario.putExtra("apematerno",item.getApeMaterno().toString());
                editarUsuario.putExtra("email",item.getEmail().toString());
                editarUsuario.putExtra("edad",item.getEdad());
                editarUsuario.putExtra("telefono",item.getTelefono());
                editarUsuario.putExtra("direccion",item.getDireccion());
                editarUsuario.putExtra("genero",item.getGenero().toString());
                v.getContext().startActivity(editarUsuario);
            }
        });
        //Boton eliminar
        holder.eliminar.setId(item.getId_usuario());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String condicion = "id_usuario=?";
                String valores[] = {""+item.getId_usuario()};
                int cant_regs_eliminados=0;

                OperacionesCRUD instancia = new OperacionesCRUD(v.getContext(), "BDTEST",null,9);
                cant_regs_eliminados = instancia.borrarRegistro(User.Esquema.TABLA_NAME,condicion,valores);

                if(cant_regs_eliminados>0){
                    Toast.makeText(v.getContext(),"El usuario a sido eliminado",Toast.LENGTH_SHORT).show();
                    AdapterUser.this.listaUser.remove(holder.getAdapterPosition());
                    AdapterUser.this.notifyDataSetChanged();
                }else{
                    Toast.makeText(v.getContext(),"Error al eliminar el usuario",Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.detalle.setId(item.getId_usuario());
        holder.detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ListaUsuarios.class);
                i.putExtra("id_usuario",item.getId_usuario());
                v.getContext().startActivity(i);
            }
        });

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
