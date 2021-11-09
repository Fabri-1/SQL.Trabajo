package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sql.esquemas.OperacionesCRUD;
import com.example.sql.esquemas.User;

public class MainActivity extends AppCompatActivity {

    EditText nombre,paterno,materno,direccion,email,edad,genero,telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.nombre);
        paterno = findViewById(R.id.apePaterno);
        materno = findViewById(R.id.apeMaterno);
        direccion = findViewById(R.id.direccion);
        email = findViewById(R.id.email);
        edad = findViewById(R.id.edad);
        genero = findViewById(R.id.genero);
        telefono = findViewById(R.id.telefono);
    }

    public void insertUser(View v){
        OperacionesCRUD instacia = new OperacionesCRUD(this, "BDTEST", null, 4);

        ContentValues datosUsuario = new ContentValues();
        datosUsuario.put(User.Esquema.NOMBRE, String.valueOf(nombre));
        datosUsuario.put(User.Esquema.APEPATERNO, String.valueOf(paterno));
        datosUsuario.put(User.Esquema.APEMATERNO, String.valueOf(materno));
        datosUsuario.put(User.Esquema.DIRECCION, String.valueOf(direccion));
        datosUsuario.put(User.Esquema.EMAIL, String.valueOf(email));
        datosUsuario.put(User.Esquema.EDAD, String.valueOf(edad));
        datosUsuario.put(User.Esquema.GENERO, String.valueOf(genero));
        datosUsuario.put(User.Esquema.TELEFONO, String.valueOf(telefono));

        long id_insertada = instacia.insertTabla(datosUsuario, User.Esquema.TABLA_NAME);

        Toast.makeText(this,"ID"+id_insertada, Toast.LENGTH_SHORT).show();

    }
    public void next(View v){
        Intent i=new Intent(this,MainActivity2.class);
        startActivity(i);

    }

}