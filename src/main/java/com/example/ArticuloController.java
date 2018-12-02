package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.URISyntaxException;
import com.example.UserJDBCTemplate;

import java.io.File;
import java.io.IOException;
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

    @PostMapping("/product__info") 
    public String productInfo(@ModelAttribute Articulo articulo,
    @RequestParam(value = "idArticulo", required = false) Integer idArticulo)
    throws URISyntaxException, SQLException {      
        Connection conn = Main.getConnection();
        ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
        try{
            articuloTemplate.setDataSource(conn);   


        }
        catch (Exception ex) {
            if (!conn.isClosed()) 
            conn.close();

            return "redirect:/";
        }
        if (!conn.isClosed()) 
        conn.close();

        return "redirect:/item/"+ idArticulo.toString(); 
    }

    @GetMapping("/item/{ID_Articulo}")
    public String userId(
        Model model,  HttpSession session, 
        @PathVariable(value="ID_Articulo") final Integer ID_Articulo)
        throws URISyntaxException, SQLException {

            Connection conn = Main.getConnection();  

            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();
            articuloTemplate.setDataSource(conn);

            Articulo articulo = articuloTemplate.getArticulo(ID_Articulo);

            if(articulo != null){
                model.addAttribute("articulo", articulo);
                return "itemSpotlight";
            }

            return "redirect:/";
    }

    @GetMapping("/ImgArticulo1/{ID_Articulo}") 
    public void imageArt1(HttpServletResponse response,  
    @PathVariable(value="ID_Articulo") final Integer ID_Articulo)  
    throws URISyntaxException, SQLException, IOException {  

        Connection conn = Main.getConnection();
        ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
        articuloTemplate.setDataSource(conn);    

        Articulo articulo = articuloTemplate.getArticulo(ID_Articulo);      
        response.setContentType( "image/jpeg, image/jpg, image/png, image/gif");   
            response.getOutputStream().write(articulo.getImagen_1());
            response.getOutputStream().close(); 

        if (!conn.isClosed()) 
            conn.close();
    } 

    @GetMapping("/ImgArticulo2/{ID_Articulo}") 
    public void imageArt2(HttpServletResponse response,  
    @PathVariable(value="ID_Articulo") final Integer ID_Articulo)  
    throws URISyntaxException, SQLException, IOException {  

        Connection conn = Main.getConnection();
        ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
        articuloTemplate.setDataSource(conn);    

        Articulo articulo = articuloTemplate.getArticulo(ID_Articulo);      
        response.setContentType( "image/jpeg, image/jpg, image/png, image/gif");   
            response.getOutputStream().write(articulo.getImagen_2());
            response.getOutputStream().close(); 

        if (!conn.isClosed()) 
            conn.close();
    } 

    @GetMapping("/ImgArticulo3/{ID_Articulo}") 
    public void imageArt3(HttpServletResponse response,  
    @PathVariable(value="ID_Articulo") final Integer ID_Articulo)  
    throws URISyntaxException, SQLException, IOException {  

        Connection conn = Main.getConnection();
        ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
        articuloTemplate.setDataSource(conn);    

        Articulo articulo = articuloTemplate.getArticulo(ID_Articulo);      
        response.setContentType( "image/jpeg, image/jpg, image/png, image/gif");   
            response.getOutputStream().write(articulo.getImagen_2());
            response.getOutputStream().close(); 

        if (!conn.isClosed()) 
            conn.close();
    } 

    @PostMapping("/publishArticulo") 
    public String publish( @ModelAttribute Articulo articulo,
    BindingResult bindingResult, HttpServletResponse response, HttpSession session, 
    @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember,
    @RequestParam(value = "nombreArticulo", required = false) String nombreArticulo,
    @RequestParam(value = "tipo", required = false) Integer tipo,
    @RequestParam(value = "region", required = false) Integer region,
    @RequestParam(value = "image_1", required = false) MultipartFile image_1,
    @RequestParam(value = "image_2", required = false) MultipartFile image_2,
    @RequestParam(value = "image_3", required = false) MultipartFile image_3,
    @RequestParam(value = "video", required = false) MultipartFile video,
    @RequestParam(value = "saveMe", required = false) String save,
    HttpServletRequest request)
    throws URISyntaxException, SQLException {    
        Connection conn = Main.getConnection();  
       
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().stream()
            .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
        }
        try{
            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
            articuloTemplate.setDataSource(conn);   

            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            usuarioTemplate.setDataSource(conn);   
            
            Usuario usuario;
            Usuario loggedUsuario;
    
            usuario = new Usuario();

            usuario.setId(14);
    
            loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
    
            if(loggedUsuario != null)
                usuario = loggedUsuario;
    
            Articulo ultimoArticulo = articuloTemplate.getLastArticulo();

            Integer lastInteger;
            if(ultimoArticulo != null)
            lastInteger = ultimoArticulo.getId();
            else
            lastInteger = 0;

            lastInteger += 1;

            Articulo_Categoria articuloTipo = new Articulo_Categoria();
            articuloTipo.setIdCategoria(tipo);
            articuloTipo.setIdArticulo(lastInteger);

            Articulo_Categoria articuloRegion = new Articulo_Categoria();
            articuloRegion.setIdCategoria(region);
            articuloRegion.setIdArticulo(lastInteger);

            articulo.setNombre(nombreArticulo);

            articulo.setImagen_1(image_1.getBytes());
            articulo.setImagen_2(image_2.getBytes());
            articulo.setImagen_3(image_3.getBytes());

            articulo.setActivo(1);
            articulo.setVisitas(0);
            articulo.setOferta(0);

            if (save != null){
                articulo.setPublico(0);
            }
            else{
                articulo.setPublico(1);
            }

            articulo.setVideo("");

            if(!video.isEmpty()){
                ServletContext context = request.getServletContext();
                String path = context.getRealPath("/");
                File file = new File (path, lastInteger.toString()+".mp4");
                video.transferTo(file);
                articulo.setVideo(path);
            }

            articulo.setIdUsuario(usuario.getId());

            articuloTemplate.create(articulo, articuloTipo, articuloRegion);      
        }
        catch (Exception ex) {
            if (!conn.isClosed()) 
                conn.close();
            return "error/" + ex;
            //return "Publish";
        }
        if (!conn.isClosed()) 
            conn.close();

        return "redirect:/"; 
    }

}