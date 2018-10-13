package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class CompraMapper implements RowMapper<Compra> {
    public Compra mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Compra compra = new Compra();
        compra.setIdUsuario(rs.getInt("ID_Usuario"));       
        compra.setIdArticulo(rs.getInt("ID_Articulo"));   
        compra.setValoracion(rs.getDate("valoracion"));                      
        return compra;       
    } 
} 