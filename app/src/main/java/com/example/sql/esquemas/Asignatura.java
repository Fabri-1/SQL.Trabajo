package com.example.sql.esquemas;

import android.provider.BaseColumns;

public class Asignatura {
    public static abstract class Esquema implements BaseColumns {
        public static final String TABLA_ASIGNATURA = "asignatura";
        public static final String ID_ASIGNATURA = "id_asignatura";
        public static final String CODIGO = "codigo";
        public static final String DESCRIPCION = "descripcion";

        public static final String[] ALLCOLUMNAS = {ID_ASIGNATURA,CODIGO,DESCRIPCION};

        public static final String CREAR_TABLA_ASIGNATURA = "CREATE TABLE " + TABLA_ASIGNATURA + "(" + ID_ASIGNATURA + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CODIGO + " TEXT," + DESCRIPCION + " TEXT)";

        public static final String BORRAR_TABLA_ASIGNATURA = "DROP TABLE IF EXISTS "+TABLA_ASIGNATURA;
    }

}
