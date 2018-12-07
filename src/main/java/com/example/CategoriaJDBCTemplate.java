package com.example; 
import java.util.List; 
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class CategoriaJDBCTemplate implements CategoriaDAO {    

private JdbcTemplate jdbcTemplateObject; 
 
public void setDataSource(Connection connection) {   
    this.jdbcTemplateObject =      
        new JdbcTemplate(      
            new SingleConnectionDataSource(connection, false)     
    ); 
} 

public Categoria getCategoria(Integer ID_Categoria) {
    String SQL = "Call Categoria_S (?)";   
    Categoria categoria = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{ID_Categoria}, new CategoriaMapper());   
    return categoria; 
} 
 
public List<Categoria> listCategoria() {   
    String SQL = "Call Categoria_S_Generic()"; 
    try{
        List<Categoria> categorias =      
        jdbcTemplateObject.query(SQL, new CategoriaMapper());   

        if(categorias != null)
            return categorias;
        else
            return null;
        }
        catch (Exception e) {
            return null;
        }
}

public List<Categoria> getCategorias(Integer ID_Articulo){
    String SQL = "Call Categorias_S_Articulo(?)"; 
    try{
    List<Categoria> categorias = jdbcTemplateObject.query(SQL, 
    new Object[]{ID_Articulo}, new CategoriaMapper());   

    if(categorias != null)
        return categorias;
    else
        return null;
    }
    catch (Exception e) {
        return null;
    }
}

} 