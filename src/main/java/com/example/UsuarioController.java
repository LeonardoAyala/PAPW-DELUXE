package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.jdbc.core.JdbcTemplate; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.net.URI; 
import java.net.URISyntaxException; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import com.example.UserJDBCTemplate;
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

    @PostMapping("/registarUsuario") 
    public String userSubmit(@ModelAttribute Usuario usuario)    
    throws URISyntaxException, SQLException {      
        UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
        usuarioTemplate.setDataSource(Main.getConnection());   
        usuarioTemplate.create(usuario);      
        return "Home"; 
    }
}
