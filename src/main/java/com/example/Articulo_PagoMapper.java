package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class Articulo_PagoMapper implements RowMapper<Articulo_Pago> {
    public Articulo_Pago mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Articulo_Pago articuloPago = new Articulo_Pago();
        articuloPago.setIdPago(rs.getInt("ID_Pago"));       
        articuloPago.setIdArticulo(rs.getInt("ID_Articulo"));         
     return articuloPago;    
    } 
} 