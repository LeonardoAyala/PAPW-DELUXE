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

    @PostMapping("/setUser") 
    public String userSubmit(@ModelAttribute User user)    
    throws URISyntaxException, SQLException {      
        UserJDBCTemplate userTemplate = new UserJDBCTemplate();   
        userTemplate.setDataSource(Main.getConnection());   
        userTemplate.create(user);    
        String url = "user/1";   
        return url; 
    }
}
