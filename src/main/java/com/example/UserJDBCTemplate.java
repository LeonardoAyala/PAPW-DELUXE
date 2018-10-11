package com.examplw; 
import java.util.List; 
import javax.sql.DataSource; 
import org.springframework.jdbc.core.JdbcTemplate; 
public class UserJDBCTemplate implements UserDAO {    

private JdbcTemplate jdbcTemplateObject; 
 
public void setDataSource(Connection connection) {   
    this.jdbcTemplateObject =      
        new JdbcTemplate(      
            new SingleConnectionDataSource(connection, false)     
    ); 
} 
 
public void create(String name, Integer age) {   
    String SQL =      
    "insert into User (name, age) values (?, ?)";   
    jdbcTemplateObject.update(SQL, name, age);   
    System.out.println(     "Registro creado = " + name + " Age = " + age   );   
    return; 
}

public User getUser(Integer id) {
    String SQL = "select * from User where id = ?";   
    User user = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{id}, new UserMapper());   
    return user; 
} 
 
public List<User> listUsers() {   
    String SQL = "select * from User";   
    List <User> users =      
    jdbcTemplateObject.query(SQL, new UserMapper());   
    return users; 
}

} 