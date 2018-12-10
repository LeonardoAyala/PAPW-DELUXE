package com.example;

import java.util.Date;

public class Compra {
    private Integer ID_Compra;   
    private Integer ID_Usuario;   
    private Integer ID_Articulo;   
    private Integer valoracion;   

    public Integer getId() {return ID_Compra;}  
    public void setId(Integer ID_Compra) {this.ID_Compra = ID_Compra;}   

    public void setIdUsuario(Integer ID_Usuario) {this.ID_Usuario = ID_Usuario;}   
    public Integer getIdUsuario() {return ID_Usuario;}     

    public void setIdArticulo(Integer ID_Articulo) {this.ID_Articulo = ID_Articulo;}   
    public Integer getIdarticulo() {return ID_Articulo;} 

    public void setValoracion(Integer valoracion) {this.valoracion = valoracion;}   
    public Integer getValoracion() {return valoracion;} 
}