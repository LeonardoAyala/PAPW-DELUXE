package com.example; 

import java.util.List; 
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface CarritoDAO {    
    public void setDataSource(Connection connection);        
    public void create(Integer ID_Usuario, Integer ID_Articulo,
        Integer unidades); 
    public Carrito getCarrito(Integer ID_Carrito);        
    public List<Carrito> listCarrito();        
    public void delete(Integer ID_Carrito);        
    public void update(Integer ID_Carrito, Integer ID_Usuario,
        Integer ID_Articulo, Integer unidades);
} 