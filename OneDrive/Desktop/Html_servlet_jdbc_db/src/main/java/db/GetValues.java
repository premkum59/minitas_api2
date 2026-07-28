package db;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/GetValues")
public class GetValues extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public GetValues() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		String table=request.getParameter("tname");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techworld","root","Ashwini123@");
			PreparedStatement pst2=con.prepareStatement("select * from "+table);
			ResultSet rs=pst2.executeQuery();
			while(rs.next()) {
				out.print(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3));
			}
			
			rs.close();
			pst2.close();
			con.close();
			response.setContentType("text/html");
			out.print("Rows Retrived");
			out.print("<br></br>");
			out.print("<a href='index.html'>Back</a>");
					
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	

}
