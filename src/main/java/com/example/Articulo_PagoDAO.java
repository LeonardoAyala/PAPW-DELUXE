package com.example; 
import java.util.List; 
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface Articulo_CategoriaDAO {    
    public void setDataSource(Connection connection);        
    public void create(Integer ID_Pago, Integer ID_Articulo); 
    public Articulo_Categoria getArticulo_Categoria(Integer ID_FormaPago);        
    public List<Articulo> listArticulo_Categoria();        
    public void delete(Integer ID_FormaPago);        
    public void update(Integer ID_FormaPago, Integer ID_Pago,
        Integer ID_Articulo); 
} 