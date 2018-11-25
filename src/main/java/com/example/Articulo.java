package com.example;

public class Articulo {
    private Integer ID_Articulo;   
    private String nombreArticulo;   
    private String descripcion;   
    private float precio;
    private Integer unidades;   

    private byte[] imagen_1;
    private byte[] imagen_2;    
    private byte[] imagen_3;
    private byte[] video;

    private Integer publico;
    private Integer activo;
    private Integer vistas;
    private Integer oferta;
    private Integer ID_Usuario;
    
    public Integer getId() {return ID_Articulo;}  
    public void setId(Integer ID_Articulo) {this.ID_Articulo = ID_Articulo;}

    public void setImagen_1(byte[] imagen_1) {this.imagen_1 = imagen_1;}   
    public byte[] getImagen_1() {return imagen_1;}   

    public void setImagen_2(byte[] imagen_2) {this.imagen_2 = imagen_2;}   
    public byte[] getImagen_2() {return imagen_2;}   

    public void setImagen_3(byte[] imagen_3) {this.imagen_3 = imagen_3;}   
    public byte[] getImagen_3() {return imagen_3;}       

    public void setVideo(byte[] video) {this.video = video;}   
    public byte[] getVideo() {return video;}    

    public void setNombre(String nombreArticulo) {this.nombreArticulo = nombreArticulo;}   
    public String getNombre() {return nombreArticulo;}      

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