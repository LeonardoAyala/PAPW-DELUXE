package com.example;

public class Categoria {
    private Integer ID_Categoria;   
    private String nombre_categoria;   
    
    public Integer getId() {return ID_Categoria;}  
    public void setId(Integer ID_Categoria){this.ID_Categoria = ID_Categoria;}

    public void setNombre(String nombre_categoria) {this.nombre_categoria = nombre_categoria;}   
    public String getNombre() {return nombre_categoria;}      

}