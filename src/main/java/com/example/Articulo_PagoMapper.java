package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class Articulo_PagoMapper implements RowMapper<Articulo_Pago> {
    public Articulo_Pago mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Articulo articulo = new Articulo();
        articulo.setIdPago(rs.getString("ID_Pago"));       
        articulo.setIdArticulo(rs.getString("ID_Articulo"));         
    } 
} 