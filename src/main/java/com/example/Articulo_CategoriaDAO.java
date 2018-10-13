package com.example; 
import java.util.List; 
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface Articulo_CategoriaDAO {    
    public void setDataSource(Connection connection);        
    public void create(Integer ID_Categoria, Integer ID_Articulo); 
    public Articulo_Categoria getArticulo_Categoria(Integer ID_CategoriaArticulo);        
    public List<Articulo_Categoria> listArticulo_Categoria();        
    public void delete(Integer ID_CategoriaArticulo);        
    public void update(Integer ID_CategoriaArticulo, Integer ID_Categoria,
        Integer ID_Articulo); 
} 