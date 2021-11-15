package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sql.adaptadores.AdapterAsignatura;
import com.example.sql.adaptadores.AdapterUser;
import com.example.sql.esquemas.Asignatura;
import com.example.sql.esquemas.OperacionesCRUD;
import com.example.sql.objetos.asignaturaVO;

import java.util.ArrayList;
import java.util.List;

public class ListaAsignaturas extends AppCompatActivity {

    private RecyclerView.LayoutManager manejador;
    private RecyclerView.Adapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asignaturas);

        OperacionesCRUD instancia= new OperacionesCRUD(this,"BDTEST",null,9);

        String columnasObtener[] = Asignatura.Esquema.ALLCOLUMNAS;
        String condicion = "";
        String valores[] = {};

        List<ContentValues> asignaturasObtenidas = instancia.obtenerDatos(columnasObtener, condicion, valores, Asignatura.Esquema.TABLA_ASIGNATURA);

        ArrayList<asignaturaVO> listaAsignaturaObtenidas = new ArrayList<>();

        if(null == asignaturasObtenidas){
            Toast.makeText(this,"Error al botener usuarios", Toast.LENGTH_SHORT).show();
        }else{
            for(int i = 0; i < asignaturasObtenidas.size();i++){
                ContentValues auxiliar = asignaturasObtenidas.get(i);
                asignaturaVO asignatura = new asignaturaVO();

                for(String key: auxiliar.keySet()){
                    switch (key.toString()){
                        case Asignatura.Esquema.ID_ASIGNATURA:
                            asignatura.setId_asignatura(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case Asignatura.Esquema.CODIGO:
                            asignatura.setCodigo((auxiliar.get(key).toString()));
                            break;
                        case Asignatura.Esquema.DESCRIPCION:
                            asignatura.setDescripcion((auxiliar.get(key).toString()));
                            break;
                    }
                }
                listaAsignaturaObtenidas.add(asignatura);
            }
            RecyclerView lista = findViewById(R.id.listAsignaturas);
            lista.setHasFixedSize(true);

            manejador = new LinearLayoutManager(this);
            adaptador = new AdapterAsignatura(listaAsignaturaObtenidas);
            lista.setLayoutManager(manejador);
            lista.setAdapter(adaptador);
        }
    }

    public void agregarAsignatura (View v){
        Intent i = new Intent(this,MainActivity2.class);
        startActivity(i);
    }

    public void usuarios(View v){
        Intent i = new Intent(this,ListaUsuarios.class);
        startActivity(i);
    }
}