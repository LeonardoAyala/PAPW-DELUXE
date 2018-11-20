package com.example;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.net.URISyntaxException; 
import java.lang.Integer;
import java.lang.String;

import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class UsuarioController {
/*
    @PostMapping("/userEdit")
    public String userEditSubmit(@ModelAttribute User user)
    throws URISyntaxException, SQLException {
        UserJDBCTemplate userTemplate = new UserJDBCTemplate();
        userTemplate.setDataSource(Main.getConnection());
        userTemplate.update(
        user.getId(),
        user.getAge()
        );
        return "updateUser";
    }
*/
    @GetMapping("/Home/{ID_Usuario}")
    public String ususarioId(
        Model model,
        @PathVariable(value="ID_Usuario") final Integer ID_Usuario) 
        throws URISyntaxException, SQLException {
        UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();
        usuarioTemplate.setDataSource(Main.getConnection());
        Usuario usuario = usuarioTemplate.getUsuario(ID_Usuario);
        model.addAttribute("Home", usuario);
        return "Home";
    }
        
    @GetMapping("/SignIn")
    public String userForm(Model model) {   
        model.addAttribute("usuario", new Usuario());   
        return "SignIn";
    }

    @GetMapping("/LogIn")
    public String userLoginForm(Model model, @CookieValue(value = "cookie_Remember", 
        defaultValue ="") String cookieRemember) {   
            
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

                return "Home";
            }
            else
                return "LogIn";
    }

    @PostMapping("/registarUsuario") 
    public String userSubmit(@ModelAttribute Usuario usuario)    
    throws URISyntaxException, SQLException {      
        UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
        usuarioTemplate.setDataSource(Main.getConnection());   
        usuarioTemplate.create(usuario);      
        return "Home"; 
    }
}
