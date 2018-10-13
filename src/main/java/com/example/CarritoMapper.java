package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class CarritoMapper implements RowMapper<Carrito> {
    public Carrito mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Carrito carrito = new Carrito();
        carrito.setIdUsuario(rs.getInt("ID_Usuario"));       
        carrito.setIdArticulo(rs.getInt("ID_Articulo"));   
        carrito.setUnidades(rs.getInt("unidades"));                      
        return carrito;       
    } 
} 