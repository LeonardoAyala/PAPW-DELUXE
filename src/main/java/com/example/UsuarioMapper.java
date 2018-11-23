package com.example; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
 
public class UsuarioMapper implements RowMapper<Usuario> {
    public Usuario mapRow(ResultSet rs, int rowNum) 
        throws SQLException {       
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("ID_Usuario"));
        usuario.setNombreUsuario(rs.getString("nombreUsuario"));       
        usuario.setApellido(rs.getString("apellido"));   
        usuario.setCorreo(rs.getString("correo"));       
        usuario.setUsername(rs.getString("username"));              
        usuario.setContrasena(rs.getString("contrasena"));       
        usuario.setTelefono(rs.getString("telefono"));              
        usuario.setDireccion(rs.getString("direccion"));   
        usuario.setImagen_portada(rs.getBytes("imagen_portada"));    
        usuario.setImagen_avatar(rs.getBytes("imagen_avatar"));    
        return usuario;    
    } 
} 