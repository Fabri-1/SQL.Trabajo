package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sql.esquemas.Asignatura;
import com.example.sql.esquemas.OperacionesCRUD;
import com.example.sql.objetos.asignaturaVO;

import java.util.ArrayList;
import java.util.List;

public class ListaAsignaturaPorUsuario extends AppCompatActivity {

    private Spinner spinnerAsignaturas;
    private RecyclerView listaAsignaturas;
    private int id_usuario;
    private RecyclerView.LayoutManager manejador;
    private RecyclerView.Adapter adaptadorRecycler;
    ArrayList<asignaturaVO> dataAsignaturas=new ArrayList<>();
    private OperacionesCRUD instancia;
    List<String> dataSpinner = new ArrayList<>();
    ArrayAdapter adaptadorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asignatura_por_usuario);

        if(null!= this.getIntent()){
            if(null != this.getIntent().getExtras()){
                Bundle parametrosEntrada = this.getIntent().getExtras();
                id_usuario = parametrosEntrada.getInt("id_usuario");


            }
        }//fin if

        spinnerAsignaturas = findViewById(R.id.spinner);
        listaAsignaturas = findViewById(R.id.recyclerAsig);
        instancia = new OperacionesCRUD(this,"BDTEST",null,9);

        llenarSpinner();
    }

    public void llenarSpinner(){
        String[] columnas= Asignatura.Esquema.ALLCOLUMNAS;

        String condicion= Asignatura.Esquema.ID_ASIGNATURA+" not int (select id_asignatura from usuario_asignatura where id_usuario = ?)";

        String valoresFiltro[] = {String.valueOf(id_usuario)};

        List<ContentValues> asignaturasNoAsociadas = instancia.obtenerDatos(columnas,condicion,valoresFiltro,Asignatura.Esquema.TABLA_ASIGNATURA);

        if(asignaturasNoAsociadas == null){
            Toast.makeText(this,"No se obtuvieron registros de asignaturas asociadas al usuario",Toast.LENGTH_LONG).show();
        }else{
            for(int i=0; i < asignaturasNoAsociadas.size(); i++){
                ContentValues auxiliar = asignaturasNoAsociadas.get(i);
                String opcion = "";
                for(String key: auxiliar.keySet()){
                    opcion+=auxiliar.get(key).toString()+":";
                }//fin for interno
                dataSpinner.add(opcion);
            }//fin for
        }
        adaptadorSpinner = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,dataSpinner);
        spinnerAsignaturas.setAdapter(adaptadorSpinner);
    }
}