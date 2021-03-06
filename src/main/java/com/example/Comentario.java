package com.example;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Comentario {
    private Integer ID_Comentario;   
    private Integer ID_Usuario;   
    private Integer ID_Articulo;   
    private Date fechaComentario;   
    private String comentario;      

    public Integer getId() {return ID_Comentario;}  
    public void setId(Integer ID_Comentario) {this.ID_Usuario = ID_Comentario;}   

    public void setIdUsuario(Integer ID_Usuario) {this.ID_Usuario = ID_Usuario;}   
    public Integer getIdUsuario() {return ID_Usuario;}     

    public void setIdArticulo(Integer ID_Articulo) {this.ID_Articulo = ID_Articulo;}   
    public Integer getIdArticulo() {return ID_Articulo;} 

    public void setFecha(Date fechaComentario) {this.fechaComentario = fechaComentario;}   
    public Date getFecha() {return fechaComentario;}     

    public void setComentario(String comentario) {this.comentario = comentario;}   
    public String getComentario() {return comentario;}      

    public Usuario getUsuario()
    throws URISyntaxException, SQLException {
        Connection conn = Main.getConnection();

        UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();
        usuarioTemplate.setDataSource(conn);
        
        Usuario usuario = usuarioTemplate.getUsuario(this.ID_Usuario);

        if (!conn.isClosed()) 
        conn.close();

        if(usuario != null)
            return usuario;
        else
            return null;
    }

}