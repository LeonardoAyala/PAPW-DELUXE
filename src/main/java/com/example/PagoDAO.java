package com.example; 
import java.util.List; 
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface PagoDAO {    
    public void setDataSource(Connection connection);        
    public void create(String descripcionPago); 
    public Pago getPago(Integer ID_Pago);        
    public List<Pago> listPago();        
    public void delete(Integer ID_Pago);        
    public void update(Integer ID_Pago, String descripcionPago); 
} 