package com.example;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.net.URISyntaxException; 
import java.lang.Integer;
import java.lang.String;


@Controller
public class UsuarioController {

    @GetMapping("/Home")
    public String Homecoming(Model model, HttpSession session, 
    @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember) 
    throws URISyntaxException, SQLException {
        UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();
        usuarioTemplate.setDataSource(Main.getConnection());

        Usuario usuario;
        Usuario loggedUsuario;

        usuario = new Usuario();

        Integer id = (Integer) session.getAttribute("loggedUsuario_Id");
        loggedUsuario = usuarioTemplate.getUsuario(4);

        if(loggedUsuario != null && id != 0)
            usuario = loggedUsuario;

        model.addAttribute("usuario", usuario);
        return "Home";
    }

    @GetMapping("/SignIn")
    public String userForm(Model model) {   
        model.addAttribute("usuario", new Usuario());   
        return "SignIn";
    }

    @GetMapping("/LogIn")
    public String userLoginForm(Model model, 
        @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember) {   

        Usuario usuario = new Usuario();
        usuario.setUsername(cookieRemember);
        
        model.addAttribute("usuario", usuario);   
        return "LogIn";
    }

    @PostMapping("/loginUsuario") 
    public String userLogIn(HttpServletResponse response, HttpSession session, 
        @RequestParam(value = "rememberMe", required = false) String remember, 
        @ModelAttribute Usuario usuario)    
        throws URISyntaxException, SQLException {  

            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            
            usuarioTemplate.setDataSource(Main.getConnection());   
            Usuario usuarioLogged = usuarioTemplate.getUsuarioLogIn(
                usuario.getUsername(), usuario.getContrasena());  

            if(usuarioLogged != null){
                session.setAttribute("loggedUsuario_Nombre", usuario.getNombreUsuario() + usuario.getApellido());
                session.setAttribute("loggedUsuario_Username", usuario.getUsername());
                session.setAttribute("loggedUsuario_Id", usuario.getId());
                session.setAttribute("loggedUsuario", usuario);

                if (remember != null){
                    Cookie cookie = new Cookie("cookie_Remember", usuario.getUsername());
                    cookie.setMaxAge(3*86400);
                    response.addCookie(cookie);
                }
                else{
                    Cookie cookie = new Cookie("cookie_Remember", "");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                return "redirect:/";
            }
            else
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
}
