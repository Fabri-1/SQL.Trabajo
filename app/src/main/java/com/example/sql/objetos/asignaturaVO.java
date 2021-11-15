package com.example.sql.objetos;

public class asignaturaVO {
    private int id_asignatura=0;
    private String codigo="";
    private String descripcion="";

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public asignaturaVO(int id_asignatura, String codigo, String descripcion) {
        this.id_asignatura = id_asignatura;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public asignaturaVO(){
    }

    public String getCodigo() {
        this.codigo=codigo;
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
