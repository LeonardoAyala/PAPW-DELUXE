package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class ArticuloMapper implements RowMapper<Articulo> {
    public Articulo mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Articulo articulo = new Articulo();
        articulo.setId(rs.getInt("ID_Articulo"));
        articulo.setNombre(rs.getString("nombre_articulo"));       
        articulo.setDescripcion(rs.getString("descripcion"));   
        articulo.setPrecio(rs.getFloat("precio"));       
        articulo.setUnidades(rs.getInt("unidades"));
        articulo.setImagen_1(rs.getBytes("imagen_1"));
        articulo.setImagen_2(rs.getBytes("imagen_2"));
        articulo.setImagen_3(rs.getBytes("imagen_3"));
        articulo.setVideo(rs.getString("video"));
        articulo.setPublico(rs.getInt("publico"));       
        articulo.setActivo(rs.getInt("activo"));              
        articulo.setVisitas(rs.getInt("visitas"));
        articulo.setOferta(rs.getInt("oferta"));              
        articulo.setIdUsuario(rs.getInt("ID_Usuario"));      
        articulo.setEstampaTiempo(rs.getDate("estampaTiempo"));  
        return articulo;    
    } 
} 