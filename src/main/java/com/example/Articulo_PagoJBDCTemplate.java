package com.example; 

import java.util.List; 
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class Articulo_PagoJDBCTemplate implements Articulo_PagoDAO {    

private JdbcTemplate jdbcTemplateObject; 
 
public void setDataSource(Connection connection) {   
    this.jdbcTemplateObject =      
        new JdbcTemplate(      
            new SingleConnectionDataSource(connection, false)     
    ); 
} 
 
public void create(Integer ID_Pago, Integer ID_Articulo)  {   
    String SQL = "insert into Articulo_Pago (ID_Articulo, " +
    "ID_Pago) values (?, ?)";   
    jdbcTemplateObject.update(SQL, ID_Articulo, ID_Pago);   
    System.out.println("Registro creado = " + ID_Articulo);   
    return; 
}

public Articulo_Pago getArticulo_Pago(Integer ID_FormaPago) {
    String SQL = "select * from Articulo_Pago where ID_FormaPago = ?";   
    Articulo_Pago carrito = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{ID_FormaPago}, new Articulo_PagoMapper());   
    return carrito; 
} 
 
public List<Articulo_Pago> listArticulo_Pago() {   
    String SQL = "select * from Articulo_Pago";   
    List <Articulo_Pago> articuloCategorias =      
    jdbcTemplateObject.query(SQL, new Articulo_PagoMapper());   
    return articuloCategorias; 
}

public void delete(Integer ID_FormaPago) {
    String SQL = "delete from Articulo_Pago where ID_FormaPago = ?";
    jdbcTemplateObject.update(SQL, ID_FormaPago);
    System.out.println("Borrado ID_FormaPago = " + ID_FormaPago );
    return;
}

public void update(Integer ID_FormaPago, Integer ID_Pago,
        Integer ID_Articulo) {
    String SQL = "update Articulo_Pago set ID_Articulo = ?, ID_Pago = ?, " +
    "where ID_FormaPago = ?";
    jdbcTemplateObject.update(SQL, ID_Articulo, ID_Pago, ID_FormaPago);
    System.out.println("Actualizado ID = " + ID_FormaPago );
    return;
}


} 