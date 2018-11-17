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

    private byte[] imagen_avatar;
    private byte[] imagen_portada;
   
    //public void setId(Integer ID_Usuario) {this.ID_Usuario = ID_Usuario;}   
    public Integer getId() {return ID_Usuario;} 

    public void setNombre(String nombreUsuario) {this.nombreUsuario = nombreUsuario;}   
    public String getNombre() {return nombreUsuario;}   

    public void setImagen_avatar(byte[] imagen_avatar) {this.imagen_avatar = imagen_avatar;}   
    public byte[] getImagen_avatar() {return imagen_avatar;}   

    public void setImagen_portada(byte[] imagen_portada) {this.imagen_portada = imagen_portada;}   
    public byte[] getImagen_portada() {return imagen_portada;}   

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