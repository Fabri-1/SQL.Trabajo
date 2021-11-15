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

public class MainActivity extends AppCompatActivity {

    private EditText nombre, paterno, materno, direccion, email, edad, telefono;
    private RadioButton mas, fem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.nombre2);
        paterno = findViewById(R.id.apePaterno);
        materno = findViewById(R.id.apeMaterno);
        direccion = findViewById(R.id.direccion);
        email = findViewById(R.id.email);
        edad = findViewById(R.id.edad);
        mas = findViewById(R.id.mas);
        fem = findViewById(R.id.fem);
        telefono = findViewById(R.id.telefono);
    }

    public void insertUser(View v) {
        OperacionesCRUD instacia = new OperacionesCRUD(this, "BDTEST", null, 9);

        ContentValues datosUsuario = new ContentValues();
        datosUsuario.put(User.Esquema.NOMBRE, nombre.getText().toString());
        datosUsuario.put(User.Esquema.APEPATERNO, paterno.getText().toString());
        datosUsuario.put(User.Esquema.APEMATERNO, materno.getText().toString());
        datosUsuario.put(User.Esquema.DIRECCION, direccion.getText().toString());
        datosUsuario.put(User.Esquema.EMAIL, email.getText().toString());
        datosUsuario.put(User.Esquema.EDAD, Integer.parseInt(edad.getText().toString()));
        if(fem.isChecked()){
            datosUsuario.put(User.Esquema.GENERO, fem.getText().toString());
        }else if(mas.isChecked()) {
            datosUsuario.put(User.Esquema.GENERO, mas.getText().toString());
        }
        datosUsuario.put(User.Esquema.TELEFONO, Integer.parseInt(telefono.getText().toString()));

        long id_insertada = instacia.insertTabla(datosUsuario, User.Esquema.TABLA_NAME);

        Toast.makeText(this, "ID" + id_insertada, Toast.LENGTH_SHORT).show();

    }

    public void next(View v) {
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);

    }

    public void next2(View v){
        Intent i= new Intent (this, ListaUsuarios.class);
        startActivity(i);
    }



}