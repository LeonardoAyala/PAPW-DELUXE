package com.example;

import java.util.Date;

public class Articulo_Categoria {
    private Integer ID_CategoriaArticulo;   
    private Integer ID_Categoria;   
    private Integer ID_Articulo;   

    public Integer getId() {return ID_CategoriaArticulo;}  

    public void setIdCategoria(Integer ID_Categoria) {this.ID_Categoria = ID_Categoria;}   
    public Integer getIdCategoria() {return ID_Categoria;}     

    public void setIdArticulo(Integer ID_Articulo) {this.ID_Articulo = ID_Articulo;}   
    public Integer getIdArticulo() {return ID_Articulo;} 
}