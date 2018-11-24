package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/registarUsuario") 
    public String userSubmit(@ModelAttribute Usuario usuario,
    @RequestParam(value = "image_avatar", required = false) MultipartFile imgPerfil,
    @RequestParam(value = "image_front", required = false) MultipartFile imgPortada)
    throws URISyntaxException, SQLException {      

        try{
            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            usuarioTemplate.setDataSource(Main.getConnection());
            
            usuario.setImagen_avatar(imgPerfil.getBytes());
            usuario.setImagen_portada(imgPortada.getBytes());

            usuarioTemplate.create(usuario);      
        }
        catch (Exception ex) {
            return "redirect:/";
        }
        return "redirect:/LogIn"; 
    }

    */
}