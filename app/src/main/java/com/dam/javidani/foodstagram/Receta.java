package com.dam.javidani.foodstagram;

public class Receta {

    private String autor;
    private String nombre;
    private String descripcion;
    private String realizacion;
    private int valoracion;

    public Receta(String autor, String nombre, String descripcion, String realizacion, int valoracion){
        this.autor = autor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.realizacion = realizacion;
        this.valoracion = valoracion;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRealizacion(String realizacion) {
        this.realizacion = realizacion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRealizacion() {
        return realizacion;
    }
}
