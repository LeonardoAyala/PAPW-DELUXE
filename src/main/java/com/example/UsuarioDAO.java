package com.example; 
import java.util.List; 
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface UsuarioDAO {    
    public void setDataSource(Connection connection);        
    public void create(String nombreUsuario, String apellido, String correo, String username,
        String contrasena, String telefono, String direccion);       
    public void create(Usaurio usuario);     
    public Usuario getUsuario(Integer ID_Usuario);        
    public List<Usuario> listUsuario();        
    public void delete(Integer ID_Usuario);        
    public void update(Integer ID_Usuario, String nombreUsuario, String apellido, String correo, String username,
        String contrasena, String telefono, String direccion);
} 