package com.example;

public class Articulo {
    private Integer ID_Articulo;   
    private String nombre_articulo;   
    private String descripcion;   
    private float precio;
    private Integer unidades;   

    /*
    private File imagen_1;
    private File imagen_2;    
    private File imagen_3;
    private File video;
    */

    private Integer publico;
    private Integer activo;
    private Integer vistas;
    private Integer oferta;
    private Integer ID_Usuario;
    
    public Integer getId() {return ID_Articulo;}  

    public void setNombre(String nombre_articulo) {this.nombre_articulo = nombre_articulo;}   
    public String getNombre() {return nombre_articulo;}      

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}   
    public String getDescripcion() {return descripcion;}

    public void setPrecio(float precio) {this.precio = precio;}   
    public float getPrecio() {return precio;}       

    public void setUnidades(Integer unidades) {this.unidades = unidades;}   
    public Integer getUnidades() {return unidades;}  

    public void setPublico(Integer publico) {this.publico = publico;}   
    public Integer getPublico() {return publico;}  

    public void setActivo(Integer activo) {this.activo = activo;}   
    public Integer getActivo() {return activo;}  

    public void setVistas(Integer vistas) {this.vistas = vistas;}   
    public Integer getVistas() {return vistas;}  

    public void setOferta(Integer oferta) {this.oferta = oferta;}   
    public Integer getOferta() {return oferta;}  

    public void setIdUsuario(Integer ID_Usuario) {this.ID_Usuario = ID_Usuario;}   
    public Integer getIdUsuario() {return ID_Usuario;}  
}