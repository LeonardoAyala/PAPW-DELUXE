package com.example;
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

}
