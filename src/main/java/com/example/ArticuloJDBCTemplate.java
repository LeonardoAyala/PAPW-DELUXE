package com.example; 
import java.util.List; 
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class ArticuloJDBCTemplate implements ArticuloDAO {    

private JdbcTemplate jdbcTemplateObject; 
 
public void setDataSource(Connection connection) {   
    this.jdbcTemplateObject =      
        new JdbcTemplate(      
            new SingleConnectionDataSource(connection, false)     
    ); 
} 

public Articulo getArticulo(Articulo articuloToFind){

    String SQL = "CALL Articulo_S_Find (?, ?, ?, ?, ?)";   

    try{
    Articulo articulo = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{articuloToFind.getNombre(), articuloToFind.getDescripcion(), 
            articuloToFind.getPrecio(), articuloToFind.getActivo(),  articuloToFind.getIdUsuario()},
        new ArticuloMapper());   
    return articulo; 
    }
    catch (Exception e) {
        return null;
    }
}

public void create(Articulo articulo, Articulo_Categoria region, Articulo_Categoria tipo) {   
    try{
        String SQL = "CALL Articulo_I_Publish (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        jdbcTemplateObject.update(SQL, articulo.getNombre(), articulo.getDescripcion(), 
        articulo.getPrecio(), articulo.getUnidades(), articulo.getImagen_1(),  
        articulo.getImagen_2(),  articulo.getImagen_3(), articulo.getVideo(), 
        articulo.getPublico(), articulo.getActivo(), articulo.getVistas(), 
        articulo.getOferta(), articulo.getIdUsuario());  
        return;   
    } catch (Exception e){
        return; 
    }
}

public Articulo getArticulo(Integer ID_Articulo) {
    String SQL = "select * from Articulo where ID_Articulo = ?";   
    Articulo articulo = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{ID_Articulo}, new ArticuloMapper());   
    return articulo; 
} 
 
public List<Articulo> listArticulo() {   
    String SQL = "select * from Articulo";   
    List <Articulo> articulos =      
    jdbcTemplateObject.query(SQL, new ArticuloMapper());   
    return articulos; 
}

public void delete(Integer ID_Articulo) {
    String SQL = "delete from Articulo where ID_Articulo = ?";
    jdbcTemplateObject.update(SQL, ID_Articulo);
    System.out.println("Borrado ID_Articulo = " + ID_Articulo );
    return;
}

public void update(Integer ID_Articulo, String nombre_articulo, 
        String descripcion, float precio, Integer unidades, 
        Integer publico, Integer activo, Integer vistas,
        Integer oferta, Integer ID_Usuario) {
    String SQL = "update Articulo set nombre_articulo = ?, descripcion = ?, precio = ?, unidades = ?, " +
        "publico = ?, activo = ?, vistas = ?, oferta = ?, ID_Usuario = ?  where ID_Articulo = ?";
    jdbcTemplateObject.update(SQL, nombre_articulo, descripcion, precio, unidades,
         publico, activo, vistas, oferta, ID_Usuario, ID_Articulo);
    System.out.println("Actualizado ID = " + ID_Articulo );
    return;
}


} 