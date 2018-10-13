package com.example; 

import java.util.List; 
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface CompraDAO {    
    public void setDataSource(Connection connection);        
    public void create(Integer ID_Usuario, Integer ID_Articulo,
        Integer valoracion); 
    public Compra getCompra(Integer ID_Compra);        
    public List<Compra> listCompra();        
    public void delete(Integer ID_Compra);        
    public void update(Integer ID_Compra, Integer ID_Usuario,
        Integer ID_Articulo, Integer valoracion);
} 