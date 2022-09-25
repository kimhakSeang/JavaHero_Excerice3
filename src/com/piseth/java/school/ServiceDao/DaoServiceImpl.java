package com.piseth.java.school.ServiceDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.piseth.java.school.model.Person;

public class DaoServiceImpl implements DaoService{
	   private String url ="jdbc:postgresql://localhost:5432/postgres";
	   private String driver ="org.postgresql.Driver";
	   private String username ="postgres";
	   private String password = "Mylove77#*#";

		@Override
		public Connection connection() {
			Connection conn = null;
			try{  
	        Class.forName(driver);  
	        conn = DriverManager.getConnection(url,username,password); 
	        System.out.println("Connecting ....\n");
		    }catch(Exception ex){
		        System.out.println(ex);
		    }  
			return conn;
		}
}
