package com.example;

public class Usuario {
    private Integer ID_Usuario;
    private String nombreUsuario;
    private String apellido;
    private String correo;
    private String username;
    private String contrasena;
    private String telefono;
    private String direccion;
    /*
    private File imagen_avatar;
    private File imagen_portada;
    */
   
    //public void setId(Integer ID_Usuario) {this.ID_Usuario = ID_Usuario;}   
    public Integer getId() {return ID_Usuario;} 

    public void setNombre(String nombreUsuario) {this.nombreUsuario = nombreUsuario;}   
    public String getNombre() {return nombreUsuario;}   

    public void setApellido(String apellido) {this.apellido = apellido;}   
    public String getApellido() {return apellido;}   
  
    public void setCorreo(String correo) {this.correo = correo;}   
    public String getCorreo() {return correo;}   

    public void setUsername(String username) {this.username = username;}   
    public String getUsername() {return username;}   

    public void setContrasena(String contrasena) {this.contrasena = contrasena;}   
    public String getContrasena() {return contrasena;}  

    public void setTelefono(String telefono) {this.telefono = telefono;}   
    public String getTelefono() {return telefono;}    

    public void setDireccion(String direccion) {this.direccion = direccion;}   
    public String getDireccion() {return direccion;}    
}