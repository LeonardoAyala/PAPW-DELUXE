package com.example; 
import java.util.List; 
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface Articulo_PagoDAO {    
    public void setDataSource(Connection connection);        
    public void create(Integer ID_Pago, Integer ID_Articulo); 
    public Articulo_Pago getArticulo_Pago(Integer ID_FormaPago);        
    public List<Articulo_Pago> listArticulo_Pago();        
    public void delete(Integer ID_FormaPago);        
    public void update(Integer ID_FormaPago, Integer ID_Pago,
        Integer ID_Articulo); 
} 