package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class ArticuloMapper implements RowMapper<Articulo> {
    public Articulo mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Articulo articulo = new Articulo();
        articulo.setNombre(rs.getString("nombre_articulo"));       
        articulo.setDescripcion(rs.getString("descripcion"));   
        articulo.setPrecio(rs.getFloat("precio"));       
        articulo.setUnidades(rs.getInt("unidades"));              
        articulo.setPublico(rs.getInt("publico"));       
        articulo.setActivo(rs.getInt("activo"));              
        articulo.setVistas(rs.getInt("vistas"));
        articulo.setOferta(rs.getInt("oferta"));              
        articulo.setIdUsuario(rs.getInt("ID_Usuario"));        
        return articulo;    
    } 
} 