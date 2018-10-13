package com.example;

import java.util.Date;

public class Comentario {
    private Integer ID_Comentario;   
    private Integer ID_Usuario;   
    private Integer ID_Articulo;   
    private Date fechaComentario;   
    private String comentario;      

    public Integer getId() {return ID_Comentario;}  

    public void setIdUsuario(Integer ID_Usuario) {this.ID_Usuario = ID_Usuario;}   
    public Integer getIdUsuario() {return ID_Usuario;}     

    public void setIdArticulo(Integer ID_Articulo) {this.ID_Articulo = ID_Articulo;}   
    public Integer getIdArticulo() {return ID_Articulo;} 

    public void setFecha(Date fechaComentario) {this.fechaComentario = fechaComentario;}   
    public Date getFecha() {return fechaComentario;}     

    public void setComentario(String comentario) {this.comentario = comentario;}   
    public String getComentario() {return comentario;}      

}