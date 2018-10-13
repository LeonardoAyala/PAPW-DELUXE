package com.example; 

import java.util.List; 
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class CompraJDBCTemplate implements CompraDAO {    

private JdbcTemplate jdbcTemplateObject; 
 
public void setDataSource(Connection connection) {   
    this.jdbcTemplateObject =      
        new JdbcTemplate(      
            new SingleConnectionDataSource(connection, false)     
    ); 
} 
 
public void create(Integer ID_Usuario, Integer ID_Articulo,
        Integer valoracion) {   
    String SQL = "insert into Compra (ID_Usuario, " +
    "ID_Articulo, valoracion) values (?, ?, ?)";   
    jdbcTemplateObject.update(SQL, ID_Usuario, ID_Articulo, valoracion);   
    System.out.println("Registro creado = " + valoracion);   
    return; 
}

public Compra getCompra(Integer ID_Compra) {
    String SQL = "select * from Compra where ID_Compra = ?";   
    Compra compra = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{ID_Compra}, new CompraMapper());   
    return compra; 
} 
 
public List<Compra> listCompra() {   
    String SQL = "select * from Compra";   
    List <Compra> compras =      
    jdbcTemplateObject.query(SQL, new CompraMapper());   
    return compras; 
}

public void delete(Integer ID_Compra) {
    String SQL = "delete from Compra where ID_Compra = ?";
    jdbcTemplateObject.update(SQL, ID_Compra);
    System.out.println("Borrado ID_Compra = " + ID_Compra );
    return;
}

public void update(Integer ID_Compra, Integer ID_Usuario,
        Integer ID_Articulo, Integer valoracion) {
    String SQL = "update Usuario set ID_Usuario = ?, ID_Articulo = ?, " +
    "valoracion = ? where ID_Compra = ?";
    jdbcTemplateObject.update(SQL, ID_Usuario, ID_Articulo, valoracion, ID_Compra);
    System.out.println("Actualizado ID = " + ID_Compra );
    return;
}


} 