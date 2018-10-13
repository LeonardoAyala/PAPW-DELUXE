package com.example;

import java.util.Date;

public class Carrito {
    private Integer ID_Carrito;   
    private Integer ID_Usuario;   
    private Integer ID_Articulo;   
    private Integer unidades;   

    public Integer getId() {return ID_Carrito;}  

    public void setIdUsuario(Integer ID_Usuario) {this.ID_Usuario = ID_Usuario;}   
    public Integer getIdUsuario() {return ID_Usuario;}     

    public void setIdArticulo(Integer ID_Articulo) {this.ID_Articulo = ID_Articulo;}   
    public Integer getIdArticulo() {return ID_Articulo;} 

    public void setUnidades(Integer unidades) {this.unidades = unidades;}   
    public Integer getUnidades() {return unidades;} 
}