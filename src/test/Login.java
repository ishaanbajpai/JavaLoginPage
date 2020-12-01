package test;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

	class DBConn{
		public static Connection getConnection() {
			
			Connection con = null;
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ishaan","ishaanbajpai77");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return con;
		}
	}
  
  
public class Login extends HttpServlet {  
  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException { 
	
	Connection conn = DBConn.getConnection();
	
	
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    String n=request.getParameter("userName");  
    String p=request.getParameter("userPass");  
    
   
    try {
    	PreparedStatement ps = conn.prepareStatement("Insert into Users Values(?, ?)");
		ps.setString(1, n);
		ps.setString(2, p);
		ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
    
          
    if(p.equals("servlet")){  
        RequestDispatcher rd=request.getRequestDispatcher("servlet2");  
        rd.forward(request, response);  
    }  
    else{  
        out.print("Sorry UserName or Password Error!");  
        RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
        rd.include(request, response);  
                      
        }  
    }  
  
} 