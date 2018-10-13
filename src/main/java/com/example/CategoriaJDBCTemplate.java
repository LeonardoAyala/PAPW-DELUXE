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
 
public void create(String nombre_categoria) {   
    String SQL = "insert into Categoria (nombre_categoria) values (?)";   
    jdbcTemplateObject.update(SQL, nombre_categoria);   
    System.out.println("Registro creado = " + nombre_categoria);   
    return; 
}

public Categoria getCategoria(Integer ID_Categoria) {
    String SQL = "select * from Categoria where ID_Categoria = ?";   
    Categoria categoria = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{ID_Categoria}, new CategoriaMapper());   
    return categoria; 
} 
 
public List<Categoria> listCategoria() {   
    String SQL = "select * from Categoria";   
    List <Categoria> categorias =      
    jdbcTemplateObject.query(SQL, new CategoriaMapper());   
    return categorias; 
}

public void delete(Integer ID_Categoria) {
    String SQL = "delete from Categoria where ID_Categoria = ?";
    jdbcTemplateObject.update(SQL, ID_Categoria);
    System.out.println("Borrado ID_Categoria = " + ID_Categoria );
    return;
}

public void update(Integer ID_Categoria, String nombre_categoria) {
    String SQL = "update Categoria set nombre_categoria = ?  where ID_Categoria = ?";
    jdbcTemplateObject.update(SQL, nombre_categoria, ID_Categoria);
    System.out.println("Actualizado ID = " + ID_Categoria );
    return;
}


} 