

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
      Connection con;

	 
	public void init()
	{
	    
			try {
				Class.forName("com.mysql.cj.jbdc.Driver");
                String s="jdbc:mysql://localhost:3306/mydb";
                con =DriverManager.getConnection(s,"root","root");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		response.setContentType("text/html");
		 PreparedStatement pst=null;
		 List<String> mylist = new ArrayList<String>();

         PrintWriter pw = response.getWriter();
		 ResultSet rs = null;
		try {
			pst = con.prepareStatement("Select * from dept");
			rs = pst.executeQuery();
			while(rs.next())
			 {
				 mylist.add(rs.getString(3));
		    
			 }
			  request.setAttribute("java", mylist);
			pw.println(mylist);
			RequestDispatcher rd=request.getRequestDispatcher("Servlet2");
			rd.forward(request,response);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
