package com.example.sql.esquemas;

import android.provider.BaseColumns;

public class UsrAsig {
    public static abstract class Esquema implements BaseColumns {

        public static final String TABLA_USUARIO_ASIGNATURA = "usuario_asignatura";

        public static final String ID_USER_ASIG = "id_user_asig";
        public static final String ID = "id_usuario";
        public static final String ID_ASIGNATURA = "id_asignatura";

        public static final String CREAR_TABLA_USUARIO_ASIGNATURA = "CREATE TABLE "+TABLA_USUARIO_ASIGNATURA+"("+ID_USER_ASIG+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ID+" INTEGER,"+ID_ASIGNATURA+" INTEGER)";

        public static final String BORRAR_TABLA_USUARIO_ASIGNATURA = "DROP TABLE IF EXISTS "+TABLA_USUARIO_ASIGNATURA;
    }
}
