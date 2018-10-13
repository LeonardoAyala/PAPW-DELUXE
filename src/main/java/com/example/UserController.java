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
public class UserController {

    @GetMapping("/user/{id}")
    public String userId(
        Model model,
        @PathVariable(value="id") final Integer id)
        throws URISyntaxException, SQLException {
        UserJDBCTemplate userTemplate = new UserJDBCTemplate();
        userTemplate.setDataSource(Main.getConnection());
        User user = userTemplate.getUser(id);
        model.addAttribute("user", user);
        return "user";
    }

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

    @GetMapping("/setUser")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "setUser";
    }

    /*
    @PostMapping("/user")
    public String userSubmit(@ModelAttribute User user)
    throws URISyntaxException, SQLException {
        UserJDBCTemplate userTemplate = new UserJDBCTemplate();
        userTemplate.setDataSource(Main.getConnection());
        userTemplate.create(user);
        String url = "/user/" + user.getId();
        return url;
    }
    */

}
