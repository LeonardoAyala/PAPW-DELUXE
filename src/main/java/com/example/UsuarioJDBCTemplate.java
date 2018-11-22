package com.example; 
import java.util.List; 
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class UsuarioJDBCTemplate implements UsuarioDAO {    

private JdbcTemplate jdbcTemplateObject; 
 
public void setDataSource(Connection connection) {   
    this.jdbcTemplateObject =      
        new JdbcTemplate(      
            new SingleConnectionDataSource(connection, false)     
    ); 
} 
 
public void create(String nombreUsuario, 
    String apellido, String correo, String username,
    String contrasena, String telefono, String direccion) {   
    String SQL = "insert into Usuario (nombreUsuario, apellido, " +
        "correo, username, contrasena, telefono, " +
        "direccion) values (?, ?, ?, ?, ?, ?, ?)";   
    jdbcTemplateObject.update(SQL, nombreUsuario, apellido, correo, username, 
    contrasena, telefono, direccion);   
    System.out.println("Registro creado = " + nombreUsuario);   
    return; 
}

public void create(Usuario usuario) {   
    String SQL = "insert into Usuario (nombreUsuario, apellido, " +
        "correo, username, contrasena, telefono, " +
        "direccion, imagen_avatar, imagen_portada) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";   
    jdbcTemplateObject.update(SQL, usuario.getNombreUsuario(), usuario.getApellido(),
    usuario.getCorreo(), usuario.getUsername(), usuario.getContrasena(), 
    usuario.getTelefono(), usuario.getDireccion(),
    usuario.getImagen_avatar(), usuario.getImagen_portada() );   
    System.out.println("Registro creado = " + usuario.getNombreUsuario());   
    return; 
}

public Usuario getUsuario(Integer ID_Usuario) {
    try{
        String SQL = "select * from Usuario where ID_Usuario = ?";   
        Usuario usuario = jdbcTemplateObject.queryForObject(SQL,       
            new Object[]{ID_Usuario}, new UsuarioMapper());   
        return usuario; 
    }
    catch (Exception e) {
        return null;
    }
} 
/*
public Usuario getUsuarioLogIn(Usuario usuarioToSearch) {
    String SQL = "select * from Usuario where nombreUsuario = ? and contrasena = ?";   
    Usuario usuario = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{usuarioToSearch.getNombreUsuario(),
        usuarioToSearch.getContrasena()}, new UsuarioMapper());   
    return usuario; 
} 
 */
public Usuario getUsuarioLogIn(String username, String contrasena) {
    //String SQL = "select * from Usuario where nombreUsuario = ?";   
    String SQL = "select * from Usuario where username = ? and contrasena = ?";   

    try {
        Usuario usuario = jdbcTemplateObject.queryForObject(SQL,       
            new Object[]{username, contrasena}, new UsuarioMapper());   

        if(usuario != null)
            return usuario;
    }
    catch (Exception e) {
        return null;
    }
    return null;

} 
 
 
public List<Usuario> listUsuario() {   
    String SQL = "select * from Usuario";   
    List <Usuario> usuarios =      
    jdbcTemplateObject.query(SQL, new UsuarioMapper());   
    return usuarios; 
}

public void delete(Integer ID_Usuario) {
    String SQL = "delete from Usuario where ID_Usuario = ?";
    jdbcTemplateObject.update(SQL, ID_Usuario);
    System.out.println("Borrado ID_Usuario = " + ID_Usuario );
    return;
}

public void update(Integer ID_Usuario ,String nombreUsuario, 
    String apellido, String correo, String username,
    String contrasena, String telefono, String direccion) {
    String SQL = "update Usuario set nombreUsuario = ?, apellido = ?, correo = ?, username = ?, " +
        "contrasena = ?, telefono = ?, direccion = ? where ID_Usuario = ?";
    jdbcTemplateObject.update(SQL, nombreUsuario, apellido, correo, username,
         contrasena, telefono, direccion, ID_Usuario);
    System.out.println("Actualizado ID = " + ID_Usuario );
    return;
}


} 