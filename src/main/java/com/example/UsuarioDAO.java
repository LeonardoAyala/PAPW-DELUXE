package com.example; 
import java.util.List; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public interface UsuarioDAO {    

    public static Connection getConnection(){
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";       
        final String DB_URL="jdbc:mysql://host/baseDatos";     
        final String USER = "usuario";     
        final String PASS = "contraseña";          
        Connection con = null;       
        try{         
            Class.forName(JDBC_DRIVER);      
            con = DriverManager.getConnection(DB_URL, USER, PASS);       
        }       
        catch(ClassNotFoundException | SQLException e)       
        {System.out.println(e);}       
        return con;     
    } 

    
    public void setDataSource(Connection connection);        
    public void create(String nombreUsuario, String apellido, String correo, String username,
        String contrasena, String telefono, String direccion);       
    public void create(Usuario usuario);     
    public Usuario getUsuario(Integer ID_Usuario);        
    public List<Usuario> listUsuario();        
    public void delete(Integer ID_Usuario);        
    public void update(Integer ID_Usuario, String nombreUsuario, String apellido, String correo, String username,
        String contrasena, String telefono, String direccion);
} 