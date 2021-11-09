package com.example.sql.esquemas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OperacionesCRUD extends SQLiteOpenHelper {

    public OperacionesCRUD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.Esquema.CREAR_TABLA_USUARIO);
        db.execSQL(Asignatura.Esquema.CREAR_TABLA_ASIGNATURA);
        db.execSQL(UsrAsig.Esquema.CREAR_TABLA_USUARIO_ASIGNATURA);
        db.execSQL(Calificacion.Esquema.CREAR_TABLA_CALIFICACION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(User.Esquema.BORRAR_TABLA_USUARIO);
        db.execSQL(Asignatura.Esquema.BORRAR_TABLA_ASIGNATURA);
        db.execSQL(UsrAsig.Esquema.BORRAR_TABLA_USUARIO_ASIGNATURA);
        db.execSQL(Calificacion.Esquema.BORRAR_TABLA_CALIFICACION);

    }

    public long insertTabla(ContentValues columnas_valores_insertar, String nombre_tabla){
        long id_reg_insertado = 0;

        try{

            SQLiteDatabase baseDatos = this.getWritableDatabase();
            id_reg_insertado = baseDatos.insert(nombre_tabla,null, columnas_valores_insertar);

        }catch (Exception e){

            System.out.println("Error:"+e.getMessage());
        }
        return id_reg_insertado;
    }
}
