package com.example; 
import java.util.List; 
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface ArticuloDAO {    
    public void setDataSource(Connection connection);        
    public void create(String nombre_articulo, String descripcion, float precio,
        Integer unidades, Integer publico, Integer activo, Integer vistas,
        Integer oferta, Integer ID_Usuario); 
    public Articulo getArticulo(Integer ID_Articulo);        
    public List<Articulo> listArticulo();        
    public void delete(Integer ID_Articulo);        
    public void update(Integer ID_Articulo, String nombre_articulo, String descripcion, float precio,
        Integer unidades, Integer publico, Integer activo, Integer vistas,
        Integer oferta, Integer ID_Usuario); 
} 