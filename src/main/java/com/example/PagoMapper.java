package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class PagoMapper implements RowMapper<Pago> {
    public Pago mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Pago pago = new Pago();
        pago.setDescripcion(rs.getString("descripcionPago"));       
        return pago;    
    } 
} 