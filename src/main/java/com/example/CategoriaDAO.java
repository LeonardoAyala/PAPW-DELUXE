package com.example; 
import java.util.List; 
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface CategoriaDAO {    
    public void setDataSource(Connection connection);        
    public void create(String nombre_categoria); 
    public Categoria getCategoria(Integer ID_Categoria);        
    public List<Categoria> listCategoria();        
    public void delete(Integer ID_Categoria);        
    public void update(Integer ID_Categoria, String nombre_categoria); 
} 