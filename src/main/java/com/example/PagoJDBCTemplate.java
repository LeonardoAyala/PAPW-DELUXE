package com.example; 
import java.util.List; 
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class PagoJDBCTemplate implements PagoDAO {    

private JdbcTemplate jdbcTemplateObject; 
 
public void setDataSource(Connection connection) {   
    this.jdbcTemplateObject =      
        new JdbcTemplate(      
            new SingleConnectionDataSource(connection, false)     
    ); 
} 
 
public void create(String descripcionPago) {   
    String SQL = "insert into Pago (descripcionPago) values (?)";   
    jdbcTemplateObject.update(SQL, descripcionPago);   
    System.out.println("Registro creado = " + descripcionPago);   
    return; 
}

public Pago getPago(Integer ID_Pago) {
    String SQL = "select * from Pago where ID_Pago = ?";   
    Pago pago = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{ID_Pago}, new PagoMapper());   
    return pago; 
} 
 
public List<Pago> listPago() {   
    String SQL = "select * from Pago";   
    List <Pago> pagos =      
    jdbcTemplateObject.query(SQL, new PagoMapper());   
    return pagos; 
}

public void delete(Integer ID_Pago) {
    String SQL = "delete from Pago where ID_Pago = ?";
    jdbcTemplateObject.update(SQL, ID_Pago);
    System.out.println("Borrado ID_Pago = " + ID_Pago );
    return;
}

public void update(Integer ID_Pago, String descripcionPago) {
    String SQL = "update Pago set descripcionPago = ?  where ID_Pago = ?";
    jdbcTemplateObject.update(SQL, descripcionPago, ID_Pago);
    System.out.println("Actualizado ID = " + ID_Pago );
    return;
}


} 