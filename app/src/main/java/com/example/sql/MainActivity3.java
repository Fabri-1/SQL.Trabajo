package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sql.esquemas.OperacionesCRUD;
import com.example.sql.esquemas.User;

public class MainActivity3 extends AppCompatActivity {

    private EditText nom,apat,amat,email,edad,tele,dire;
    private RadioButton mas,fem;

    private int id_user_entrada=0;
    private int edad_user_entrada=0;
    private int telefono_user_entrada=0;
    private String nom_user_entrada="";
    private String apep_user_entrada="";
    private String apem_user_entrada="";
    private String email_user_entrada="";
    private String dire_user_entrada="";
    private String genero_user_entrada="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        if(null!=this.getIntent()){
            if(null!=this.getIntent().getExtras()){
                Bundle parametrosEntrada = this.getIntent().getExtras();
                id_user_entrada = parametrosEntrada.getInt("id");
                edad_user_entrada = parametrosEntrada.getInt("edad");
                telefono_user_entrada = parametrosEntrada.getInt("telefono");
                nom_user_entrada = parametrosEntrada.getString("nom");
                apep_user_entrada = parametrosEntrada.getString("apepaterno");
                apem_user_entrada = parametrosEntrada.getString("apematerno");
                email_user_entrada = parametrosEntrada.getString("email");
                dire_user_entrada = parametrosEntrada.getString("direccion");
                genero_user_entrada = parametrosEntrada.getString("genero");

            }
        }

        nom = findViewById(R.id.nom);
        nom.setText(nom_user_entrada);

        apat = findViewById(R.id.pat);
        apat.setText(apep_user_entrada);

        amat = findViewById(R.id.mat);
        amat.setText(apem_user_entrada);

        email = findViewById(R.id.correo2);
        email.setText(email_user_entrada);

        edad = findViewById(R.id.edad2);
        edad.setText(""+edad_user_entrada);

        tele = findViewById(R.id.telefono2);
        tele.setText(""+telefono_user_entrada);

        dire = findViewById(R.id.direccion2);
        dire.setText(dire_user_entrada);

        mas = findViewById(R.id.mas2);
        fem = findViewById(R.id.fem2);

        if(genero_user_entrada.toUpperCase().equals("MASCULINO")){
            mas.setChecked(true);
            fem.setChecked(false);
        }else{
            mas.setChecked(false);
            fem.setChecked(true);
        }

    }

    public void volver (View v){
        Intent i = new Intent(this,ListaUsuarios.class);
        startActivity(i);
    }

    public void editarUsuario(View v){
        OperacionesCRUD instancia = new OperacionesCRUD(this,"BDTEST",null,9);
        ContentValues datosNuevosUsuarios = new ContentValues();
        datosNuevosUsuarios.put("nombre",nom.getText().toString());
        datosNuevosUsuarios.put("apePaterno",apat.getText().toString());
        datosNuevosUsuarios.put("apeMaterno",amat.getText().toString());
        datosNuevosUsuarios.put("email",email.getText().toString());
        datosNuevosUsuarios.put("edad",edad.getText().toString());
        datosNuevosUsuarios.put("direccion",dire.getText().toString());

        if(mas.isChecked()){
            datosNuevosUsuarios.put("genero",mas.getText().toString());
        }
        if(fem.isChecked()){
            datosNuevosUsuarios.put("genero",fem.getText().toString());
        }

        String codicion = "id_usuario=?";
        String valores[] = {id_user_entrada+""};
        int cantidad_actualizados = 0;
        cantidad_actualizados = instancia.actualizarRegistro(datosNuevosUsuarios,
                codicion,valores, User.Esquema.TABLA_NAME);

        if(cantidad_actualizados > 0){
            Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Error actualizando usuario", Toast.LENGTH_LONG).show();
        }

    }




}