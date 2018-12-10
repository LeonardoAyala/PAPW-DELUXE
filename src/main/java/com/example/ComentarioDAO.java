package com.example; 
import java.util.List; 
import java.util.Date;
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface ComentarioDAO {    
    public void setDataSource(Connection connection);        

    public Comentario getComentario(Integer ID_Comentario);        
     
} 