package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class ComentarioMapper implements RowMapper<Comentario> {
    public Comentario mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Comentario tComentario = new Comentario();
        tComentario.setIdUsuario(rs.getInt("ID_Usuario"));       
        tComentario.setIdArticulo(rs.getInt("ID_Articulo"));   
        tComentario.setFecha(rs.getDate("fechaComentario"));       
        tComentario.setComentario(rs.getString("comentario"));                   
        return tComentario;       
    } 
} 