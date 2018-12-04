package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class CategoriaMapper implements RowMapper<Categoria> {
    public Categoria mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("ID_Categoria"));    
        categoria.setNombre(rs.getString("nombre_categoria"));       
        return categoria;    
    } 
} 