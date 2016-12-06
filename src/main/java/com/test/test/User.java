package com.test.test;

import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
	   private String email;
	   
	   public User(){
	   }
	   
	   public void setEmail(String email){
		   this.email = email;
	   }
	   
	   public String getEmail(){
		   return email;
	   }
}
