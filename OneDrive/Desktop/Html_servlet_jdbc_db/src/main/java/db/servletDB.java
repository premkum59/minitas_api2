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


@WebServlet("/servletDB")
public class servletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public servletDB() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("eid"));
		String name=request.getParameter("ename");
		double salary =Double.parseDouble(request.getParameter("esal"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techworld","root","Ashwini123@");
			PreparedStatement ps=con.prepareStatement("insert into staff values(?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setDouble(3, salary);
			ps.executeUpdate();
			out.println("Row crated");
			response.setContentType("text/html");
			out.print("<br></br>");
			out.print("<a href='index.html'>Back</a>");
			ps.close();
			con.close();
					
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
