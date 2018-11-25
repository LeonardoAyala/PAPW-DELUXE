package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.net.URISyntaxException; 
import com.example.UserJDBCTemplate;
import java.lang.Integer;
import java.lang.String;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticuloController {

    @GetMapping("/Publish")
    public String articuloForm(Model model) {   
        model.addAttribute("articulo", new Articulo());   
        return "Publish";
    }

    /*
    @GetMapping("/LogIn")
    public String userLoginForm(Model model, 
        @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember) {   

        Usuario usuario = new Usuario();
        usuario.setUsername(cookieRemember);
        
        model.addAttribute("usuario", usuario);   
        return "LogIn";
    }

    */
    @PostMapping("/publishArticulo") 
    public String publishArticulo(@ModelAttribute Articulo articulo,
    @RequestParam(value = "nombreArticulo", required = false) String nombreArticulo,
    @RequestParam(value = "imagen_1", required = false) MultipartFile imagen_1,
    @RequestParam(value = "imagen_2", required = false) MultipartFile imagen_2,
    @RequestParam(value = "imagen_3", required = false) MultipartFile imagen_3)
    throws URISyntaxException, SQLException {      
        try{
            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
            articuloTemplate.setDataSource(Main.getConnection());
            
            articulo.setNombre(nombreArticulo);
            articulo.setImagen_1(imagen_1.getBytes());
            articulo.setImagen_2(imagen_2.getBytes());
            articulo.setImagen_3(imagen_3.getBytes());

            if(articulo.getNombre() == null)
            return "Publish";

            //articuloTemplate.create(articulo, );      
        }
        catch (Exception ex) {
            return "error/" + ex;
        }
        return "redirect:/LogIn"; 
    }

}