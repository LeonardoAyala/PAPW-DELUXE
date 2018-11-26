package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.URISyntaxException;
import com.example.UserJDBCTemplate;
import java.lang.Integer;
import java.lang.String;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticuloController {

    @GetMapping("/Publish")
    public String articuloForm(Model model, HttpServletResponse response, HttpSession session, 
    @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember) 
    throws URISyntaxException, SQLException {   
        Connection conn = Main.getConnection();
        UsuarioJDBCTemplate articuloTemplate = new UsuarioJDBCTemplate();   
        articuloTemplate.setDataSource(conn);   

        Usuario usuario;
        Usuario loggedUsuario;

        usuario = new Usuario();

        usuario.setNombreUsuario("Login To get started");
        usuario.setId(14);

        loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");

        if(loggedUsuario != null)
            usuario = loggedUsuario;

        model.addAttribute("usuario", usuario);
        
        if (!conn.isClosed()) 
            conn.close();


        model.addAttribute("articulo", new Articulo());   
        return "Publish";
    }

    @PostMapping("/publishArticulo") 
    public String publishArticulo( HttpServletResponse response, HttpSession session, 
    @ModelAttribute Articulo articulo,
    @RequestParam(value = "nombreArticulo", required = false) String nombreArticulo,
    @RequestParam(value = "tipo", required = false) Integer tipo,
    @RequestParam(value = "region", required = false) Integer region,
    @RequestParam(value = "image_1", required = false) MultipartFile image_1,
    @RequestParam(value = "image_2", required = false) MultipartFile image_2,
    @RequestParam(value = "image_3", required = false) MultipartFile image_3,
    @RequestParam(value = "video", required = false) String video,
    @RequestParam(value = "saveMe", required = false) String save)
    throws URISyntaxException, SQLException {    
        Connection conn = Main.getConnection();  
        try{
            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
            articuloTemplate.setDataSource(conn);   
            
            articulo.setNombre(nombreArticulo);
            articulo.setImagen_1(image_1.getBytes());
            articulo.setImagen_2(image_2.getBytes());
            articulo.setImagen_3(image_3.getBytes());
            articulo.setVideo(video);

            if (save != null){
                articulo.setActivo(1);
            }
            else{
                articulo.setActivo(0);
            }

            Articulo_Categoria articuloTipo = new Articulo_Categoria();
            articuloTipo.setIdCategoria(tipo);

            Articulo_Categoria articuloRegion = new Articulo_Categoria();
            articuloRegion.setIdCategoria(region);

            //articuloTemplate.create(articulo, );      
        }
        catch (Exception ex) {
        if (!conn.isClosed()) 
            conn.close();

            //return "error/" + ex;
            return "Publish";
        }
        if (!conn.isClosed()) 
            conn.close();

        return "redirect:/"; 
    }

}