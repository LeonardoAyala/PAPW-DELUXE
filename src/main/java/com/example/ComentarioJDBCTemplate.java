package com.example; 
import java.util.List; 
import java.util.Date;
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class ComentarioJDBCTemplate implements ComentarioDAO {    

private JdbcTemplate jdbcTemplateObject; 
 
public void setDataSource(Connection connection) {   
    this.jdbcTemplateObject =      
        new JdbcTemplate(      
            new SingleConnectionDataSource(connection, false)     
    ); 
} 

public void publishComment(Comentario comentario) {   
    String SQL = "call Comentario_I (?, ?, ?)";   
    jdbcTemplateObject.update(SQL, comentario.getIdUsuario(), comentario.getIdArticulo(), comentario.getComentario());   
    System.out.println("Registro creado = " + comentario);   
    return; 
}

public List<Comentario> getComentariosOwnedByArticulo (Integer ID_Articulo){
    String SQL = "Call Comentario_S_Articulo (?)";  

    List<Comentario> comentarios = jdbcTemplateObject.query(SQL, 
    new Object[]{ID_Articulo}, new ComentarioMapper());   

    if(comentarios != null)
        return comentarios; 
    else
        return null;
}

public Comentario getComentario(Integer ID_Comentario) {
    String SQL = "select * from Comentario where ID_Comentario = ?";   
    Comentario comentario = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{ID_Comentario}, new ComentarioMapper());   
    return comentario; 
} 

} 