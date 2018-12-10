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

    ////Select Articulos

    //Based on Categoria
    public List<Articulo> listArticulo(Integer ID_Categoria) {   
        String SQL = "Call Articulo_S_Categoria (?)"; 
    
        List<Articulo> articulos = jdbcTemplateObject.query(SQL, 
        new Object[]{ID_Categoria}, new ArticuloMapper());   
        return articulos; 
    }

    //All
    public List<Articulo> listArticulo() {   
        String SQL = "Call Articulo_S_Generic"; 
        List<Articulo> articulos =      
        jdbcTemplateObject.query(SQL, new ArticuloMapper());   
        return articulos; 
    }

    //Based on Usuario
    public List<Articulo> getArticulosOwnedByUsuario(Integer ID_Usuario) {
        String SQL = "call Articulo_S_Usuario(?)";   

        List<Articulo> articulos = jdbcTemplateObject.query(SQL, 
        new Object[]{ID_Usuario}, new ArticuloMapper());   

        if(!articulos.isEmpty())
            return articulos; 
        else
            return null;
    }

    public List<Articulo> getArticuloKartedByUsuario (Integer ID_Usuario){
        String SQL = "Call Articulo_S_Carrito (?)";  
    
        List<Articulo> articulos = jdbcTemplateObject.query(SQL, 
        new Object[]{ID_Usuario}, new ArticuloMapper());   
    
        if(articulos != null)
            return articulos; 
        else
            return null;
    }

    public List<Articulo> getArticuloBoughtByUsuario (Integer ID_Usuario){
        String SQL = "Call Articulo_S_Compra (?)";  
    
        List<Articulo> articulos = jdbcTemplateObject.query(SQL, 
        new Object[]{ID_Usuario}, new ArticuloMapper());   
    
        if(articulos != null)
            return articulos; 
        else
            return null;
    }

    //Select Articulos based on search likeness

    public List<Articulo> getArticuloLikeNombre (String searchString){
        String SQL = "Call Articulo_S_LikeNombre(?)";  

        List<Articulo> articulos = jdbcTemplateObject.query(SQL, 
        new Object[]{searchString}, new ArticuloMapper());   

        if(articulos != null)
            return articulos; 
        else
            return null;
    }

    public List<Articulo> getArticuloLikeDescripcion (String searchString){
        String SQL = "Call Articulo_S_LikeDescripcion(?)";  

        List<Articulo> articulos = jdbcTemplateObject.query(SQL, 
        new Object[]{searchString}, new ArticuloMapper());   

        if(articulos != null)
            return articulos; 
        else
            return null;
    }

    public List<Articulo> getArticuloLikeCategoria (String searchString){
        String SQL = "Call Articulo_S_LikeCategoria(?)";  

        List<Articulo> articulos = jdbcTemplateObject.query(SQL, 
        new Object[]{searchString}, new ArticuloMapper());   

        if(articulos != null)
            return articulos; 
        else
            return null;
    }

    public List<Articulo> getArticuloLikeUsuario (String searchString){
        String SQL = "Call Articulo_S_LikeUsuario(?)";  

        List<Articulo> articulos = jdbcTemplateObject.query(SQL, 
        new Object[]{searchString}, new ArticuloMapper());   

        if(articulos != null)
            return articulos; 
        else
            return null;
    }

    public List<Articulo> getArticuloLikeEstampaTiempo (String searchString){
        String SQL = "Call Articulo_S_LikeEstampaTiempo(?)";  

        List<Articulo> articulos = jdbcTemplateObject.query(SQL, 
        new Object[]{searchString}, new ArticuloMapper());   

        if(articulos != null)
            return articulos; 
        else
            return null;
    }

    ////Select Articulo

    //Last Articulo created
    public Articulo getLastArticulo() {
        String SQL = "CALL Articulo_S_Last()";   

        List<Articulo> articulos =      
        jdbcTemplateObject.query(SQL, new ArticuloMapper());   

        if(articulos.get(0) != null)
            return articulos.get(0); 
        else
            return null;
    }

    //Based on ID_Articulo
    public Articulo getArticulo(Integer ID_Articulo) {
        String SQL = "CALL Articulo_S (?)";   
        Articulo articulo = jdbcTemplateObject.queryForObject(SQL,       
            new Object[]{ID_Articulo}, new ArticuloMapper());   

        return articulo; 
    } 

    ////Insert Articulo

    public void create(Articulo articulo, Articulo_Categoria tipo, Articulo_Categoria region) {   
            String SQL = "CALL Articulo_I_Publish (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
            jdbcTemplateObject.update(SQL, articulo.getNombre(), articulo.getDescripcion(), 
            articulo.getPrecio(), articulo.getUnidades(), articulo.getImagen_1(),  
            articulo.getImagen_2(),  articulo.getImagen_3(), articulo.getVideo(), 
            articulo.getPublico(), articulo.getActivo(), articulo.getVisitas(), 
            articulo.getOferta(), articulo.getIdUsuario(), tipo.getIdCategoria(), region.getIdCategoria());  
    }

    ////Update Articulo

    public void  publishArticulo(Integer ID_Articulo){
        String SQL = "CALL Articulo_U_Publish (?)";   
        jdbcTemplateObject.update(SQL, ID_Articulo);
    }

    public void  deleteArticulo(Integer ID_Articulo){
        String SQL = "CALL Articulo_U_Delete (?)";   
        jdbcTemplateObject.update(SQL, ID_Articulo);
    }

    public void update(Articulo articulo, Articulo_Categoria tipo, Articulo_Categoria region) {
        String SQL = "call Articulo_U (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplateObject.update(SQL, articulo.getId(), articulo.getNombre(), articulo.getDescripcion(), 
        articulo.getPrecio(), articulo.getUnidades(), articulo.getImagen_1(),  
        articulo.getImagen_2(),  articulo.getImagen_3(), 
        articulo.getPublico(), articulo.getActivo(), articulo.getVisitas(), 
        articulo.getOferta(), articulo.getIdUsuario(), tipo.getIdCategoria(), 
        region.getIdCategoria());
    }

    public void purchaseArticulo(Integer ID_Usuario, Integer ID_Articulo, Carrito carrito) {
        String SQL = "call Compra_I (?, ?, ?, ?)";
        jdbcTemplateObject.update(SQL, ID_Usuario, ID_Articulo, carrito.getId(), 
        carrito.getUnidades());
    }

    public void rateArticulo(Articulo articulo) {
        String SQL = "call Articulo_U_Rate (?, ?)";
        jdbcTemplateObject.update(SQL, articulo.getId(), articulo.getVisitas());
    }

} 