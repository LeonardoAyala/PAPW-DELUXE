package com.example; 
import java.util.List; 
import javax.sql.DataSource; 
import java.sql.Connection;
 
public interface UserDAO {    
    public void setDataSource(DataSource ds);        
    public void create(String name, Integer age);        
    public Student getUser(Integer id);        
    public List<User> listUsers();        
    public void delete(Integer id);        
    public void update(Integer id, Integer age); 
} 