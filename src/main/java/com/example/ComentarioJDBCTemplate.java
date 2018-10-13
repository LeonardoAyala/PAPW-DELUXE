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
 
public void create(Integer ID_Usuario, Integer ID_Articulo,
        Date fechaComentario, String comentario) {   
    String SQL = "insert into Comentario (ID_Usuario, " +
    "ID_Articulo, fechaComentario, comentario) values (?, ?, ?, ?)";   
    jdbcTemplateObject.update(SQL, ID_Usuario, ID_Articulo, fechaComentario, comentario);   
    System.out.println("Registro creado = " + comentario);   
    return; 
}

public Comentario getComentario(Integer ID_Comentario) {
    String SQL = "select * from Comentario where ID_Comentario = ?";   
    Comentario comentario = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{ID_Comentario}, new ComentarioMapper());   
    return comentario; 
} 
 
public List<Comentario> listComentario() {   
    String SQL = "select * from Comentario";   
    List <Comentario> comentarios =      
    jdbcTemplateObject.query(SQL, new ComentarioMapper());   
    return comentarios; 
}

public void delete(Integer ID_Comentario) {
    String SQL = "delete from Comentario where ID_Comentario = ?";
    jdbcTemplateObject.update(SQL, ID_Comentario);
    System.out.println("Borrado ID_Comentario = " + ID_Comentario );
    return;
}

public void update(Integer ID_Comentario, Integer ID_Usuario,
        Integer ID_Articulo, Date fechaComentario, String comentario) {
    String SQL = "update Usuario set ID_Usuario = ?, ID_Articulo = ?, " +
    "fechaComentario = ?, comentario = ?   where ID_Comentario = ?";
    jdbcTemplateObject.update(SQL, ID_Usuario, ID_Articulo, fechaComentario, comentario, ID_Comentario);
    System.out.println("Actualizado ID = " + ID_Comentario );
    return;
}


} 