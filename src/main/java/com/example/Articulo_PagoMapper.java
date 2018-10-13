package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class Articulo_CategoriaMapper implements RowMapper<Articulo_Categoria> {
    public Articulo_Categoria mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Articulo articulo = new Articulo();
        articulo.setIdPago(rs.getString("ID_Pago"));       
        articulo.setIdArticulo(rs.getString("ID_Articulo"));         
    } 
} 