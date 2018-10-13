package com.example; 

import java.util.List; 
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class Articulo_CategoriaJDBCTemplate implements Articulo_CategoriaDAO {    

private JdbcTemplate jdbcTemplateObject; 
 
public void setDataSource(Connection connection) {   
    this.jdbcTemplateObject =      
        new JdbcTemplate(      
            new SingleConnectionDataSource(connection, false)     
    ); 
} 
 
public void create(Integer ID_Categoria, Integer ID_Articulo)  {   
    String SQL = "insert into Articulo_Categoria (ID_Categoria, " +
    "ID_Articulo) values (?, ?)";   
    jdbcTemplateObject.update(SQL, ID_Usuario, ID_Articulo);   
    System.out.println("Registro creado = " ID_Usuario);   
    return; 
}

public Articulo_Categoria getArticulo_Categoria(Integer ID_CategoriaArticulo) {
    String SQL = "select * from Articulo_Categoria where ID_CategoriaArticulo = ?";   
    Articulo_Categoria articuloCategoria = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{ID_CategoriaArticulo}, new Articulo_CategoriaMapper());   
    return articuloCategoria; 
} 
 
public List<Articulo_Categoria> listArticulo_Categoria() {   
    String SQL = "select * from Articulo_Categoria";   
    List <Articulo_Categoria> articuloCategorias =      
    jdbcTemplateObject.query(SQL, new Articulo_CategoriaMapper());   
    return articuloCategorias; 
}

public void delete(Integer ID_CategoriaArticulo) {
    String SQL = "delete from Articulo_Categoria where ID_CategoriaArticulo = ?";
    jdbcTemplateObject.update(SQL, ID_CategoriaArticulo);
    System.out.println("Borrado ID_CategoriaArticulo = " + ID_CategoriaArticulo );
    return;
}

public void update(Integer ID_CategoriaArticulo, Integer ID_Categoria,
        Integer ID_Articulo) {
    String SQL = "update Articulo_Categoria set ID_Categoria = ?, ID_Articulo = ?, " +
    "where ID_CategoriaArticulo = ?";
    jdbcTemplateObject.update(SQL, ID_Categoria, ID_Articulo, ID_CategoriaArticulo);
    System.out.println("Actualizado ID = " + ID_CategoriaArticulo );
    return;
}


} 