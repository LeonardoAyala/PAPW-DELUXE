package com.example; 
import java.util.List; 
import java.sql.Connection;
 
public interface ArticuloDAO {    
    public void setDataSource(Connection connection);        

    public void create(Articulo articulo, Articulo_Categoria region, Articulo_Categoria tipo); 
    
    public Articulo getArticulo(Integer ID_Articulo);        
    public List<Articulo> listArticulo();        
    public List<Articulo> listArticulo(Integer ID_Categoria);        
    public void update(Articulo articulo, Articulo_Categoria tipo, Articulo_Categoria region); 
} 