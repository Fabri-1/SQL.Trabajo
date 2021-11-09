package com.example.sql.esquemas;

import android.provider.BaseColumns;

public class Calificacion {
    public static abstract class Esquema implements BaseColumns {
        public static final String TABLA_CALIFICACION = "calificacion";

        public static final String ID_CALIFICACION = "id_calificacion";
        public static final String ID = "id_usuario";
        public static final String ID_ASIGNATURA = "id_asignatura";
        public static final String FECHA = "fecha";
        public static final String CALIFICACION = "calificacion";

        public static final String CREAR_TABLA_CALIFICACION = "CREATE TABLE " + TABLA_CALIFICACION + "(" + ID_CALIFICACION + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ID + " INTEGER," + ID_ASIGNATURA + " INTEGER," + FECHA + " TEXT," + CALIFICACION + " TEXT)";

        public static final String BORRAR_TABLA_CALIFICACION = "DROP TABLE IF EXISTS "+TABLA_CALIFICACION;
    }
}
