package com.example; 
import java.util.List; 
import java.util.Date;
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface ComentarioDAO {    
    public void setDataSource(Connection connection);        
    public void create(Integer ID_Usuario, Integer ID_Articulo,
        Date fechaComentario, String comentario); 
    public Comentario getComentario(Integer ID_Comentario);        
    public List<Comentario> listComentario();        
    public void delete(Integer ID_Comentario);        
    public void update(Integer ID_Comentario, Integer ID_Usuario,
        Integer ID_Articulo, Date fechaComentario, String comentario); 
} 