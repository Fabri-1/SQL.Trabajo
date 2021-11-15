package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sql.adaptadores.AdapterUser;
import com.example.sql.esquemas.OperacionesCRUD;
import com.example.sql.esquemas.User;
import com.example.sql.objetos.userVO;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarios extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager manejador;
    private RecyclerView.Adapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        OperacionesCRUD instancia= new OperacionesCRUD(this,"BDTEST",null,9);

        String columnasObtener[] = User.Esquema.ALLCOLUMNAS;
        String condicion = "";
        String valores[] = {};

        List<ContentValues> userObtenidos = instancia.obtenerDatos(columnasObtener, condicion, valores, User.Esquema.TABLA_NAME);

        ArrayList<userVO> listaUserObtenidos = new ArrayList<>();

        if(null == userObtenidos){
            Toast.makeText(this,"Error al botener usuarios", Toast.LENGTH_SHORT).show();
        }else{
            for(int i=0; i < userObtenidos.size();i++){
                ContentValues auxiliar = userObtenidos.get(i);
                userVO usuario= new userVO();

                for(String key: auxiliar.keySet()){
                    switch(key.toString()){
                        case User.Esquema.ID:
                            usuario.setId_usuario(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.NOMBRE:
                            usuario.setNombre((auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.APEPATERNO:
                            usuario.setApePaterno((auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.APEMATERNO:
                            usuario.setApeMaterno((auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.EMAIL:
                            usuario.setEmail((auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.DIRECCION:
                            usuario.setDireccion((auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.GENERO:
                            usuario.setGenero((auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.TELEFONO:
                            usuario.setTelefono(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.EDAD:
                            usuario.setEdad(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                    }
                }
                listaUserObtenidos.add(usuario);

            }
            RecyclerView lista = findViewById(R.id.listUser);
            lista.setHasFixedSize(true);

            manejador = new LinearLayoutManager(this);
            adaptador = new AdapterUser(listaUserObtenidos);
            lista.setLayoutManager(manejador);
            lista.setAdapter(adaptador);
        }

    }

    public void agregarUsuario(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void asignaturas(View v){
        Intent i = new Intent(this,ListaAsignaturas.class);
        startActivity(i);
    }

}