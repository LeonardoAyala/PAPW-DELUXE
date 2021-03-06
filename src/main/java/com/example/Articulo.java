package com.example;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Articulo {
    private Integer ID_Articulo;   
    private String nombreArticulo;   
    private String descripcion;   
    private float precio;
    private Integer unidades;   

    private byte[] imagen_1;
    private byte[] imagen_2;    
    private byte[] imagen_3;
    private String video;

    private Integer publico;
    private Integer activo;
    private Integer visitas;
    private Integer oferta;
    private Integer ID_Usuario;
    private Date estampaTiempo;
    
    public Integer getId() {return ID_Articulo;}  
    public void setId(Integer ID_Articulo) {this.ID_Articulo = ID_Articulo;}

    public void setNombre(String nombreArticulo) {this.nombreArticulo = nombreArticulo;}   
    public String getNombre() {return nombreArticulo;}      

    public void setImagen_1(byte[] imagen_1) {this.imagen_1 = imagen_1;}   
    public byte[] getImagen_1() {return imagen_1;}   

    public void setImagen_2(byte[] imagen_2) {this.imagen_2 = imagen_2;}   
    public byte[] getImagen_2() {return imagen_2;}   

    public void setImagen_3(byte[] imagen_3) {this.imagen_3 = imagen_3;}   
    public byte[] getImagen_3() {return imagen_3;}       

    public void setVideo(String video) {this.video = video;}   
    public String getVideo() {return video;}    

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}   
    public String getDescripcion() {return descripcion;}

    public void setPrecio(float precio) {this.precio = precio;}   
    public float getPrecio() {return precio;}       

    public void setUnidades(Integer unidades) {this.unidades = unidades;}   
    public Integer getUnidades() {return unidades;}  

    public void setPublico(Integer publico) {this.publico = publico;}   
    public Integer getPublico() {return publico;}  

    public void setActivo(Integer activo) {this.activo = activo;}   
    public Integer getActivo() {return activo;}  

    public void setVisitas(Integer visitas) {this.visitas = visitas;}   
    public Integer getVisitas() {return visitas;}  

    public void setOferta(Integer oferta) {this.oferta = oferta;}   
    public Integer getOferta() {return oferta;}  

    public void setIdUsuario(Integer ID_Usuario) {this.ID_Usuario = ID_Usuario;}   
    public Integer getIdUsuario() {return ID_Usuario;}  

    public Date getEstampaTiempo() {return estampaTiempo;}  
    public void setEstampaTiempo(Date estampaTiempo) {this.estampaTiempo = estampaTiempo;}

    public List<Categoria> getCategorias()
    throws URISyntaxException, SQLException {
        Connection conn = Main.getConnection();

        CategoriaJDBCTemplate categoriaTemplate = new CategoriaJDBCTemplate();
        categoriaTemplate.setDataSource(conn);
        
        List<Categoria> categorias = categoriaTemplate.getCategorias(this.ID_Articulo);

        if (!conn.isClosed()) 
        conn.close();

        if(categorias != null)
            return categorias;
        else
            return null;
    }

    public Carrito getCarrito(Integer ID_Usuario)
    throws URISyntaxException, SQLException {
        Connection conn = Main.getConnection();

        CarritoJDBCTemplate carritoTemplate = new CarritoJDBCTemplate();
        carritoTemplate.setDataSource(conn);
        
        Carrito carrito = carritoTemplate.getCarrito(ID_Usuario ,this.ID_Articulo);

        if (!conn.isClosed()) 
        conn.close();

        if(carrito != null)
            return carrito;
        else
            return null;
    }

    public Compra getCompra(Integer ID_Usuario)
    throws URISyntaxException, SQLException {
        Connection conn = Main.getConnection();

        CompraJDBCTemplate compraTemplate = new CompraJDBCTemplate();
        compraTemplate.setDataSource(conn);
        
        Compra compra = compraTemplate.getCompra(ID_Usuario ,this.ID_Articulo);

        if (!conn.isClosed()) 
        conn.close();

        if(compra != null)
            return compra;
        else
            return null;
    }

    public Usuario getUsuario()
    throws URISyntaxException, SQLException {
        Connection conn = Main.getConnection();

        UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();
        usuarioTemplate.setDataSource(conn);
        
        Usuario usuario = usuarioTemplate.getUsuario(this.ID_Usuario);

        if (!conn.isClosed()) 
        conn.close();

        if(usuario != null)
            return usuario;
        else
            return null;
    }

}