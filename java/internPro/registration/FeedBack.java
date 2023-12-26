package internPro.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FeedBack
 */
@WebServlet("/FeedBack")
public class FeedBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//PrintWriter out=response.getWriter();
		//out.print("Working");
		String fName = request.getParameter("name");
		String email = request.getParameter("email");
		String phone  = request.getParameter("phone");
		String message = request.getParameter("message");
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//by default mysql port 3306
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?useSSL=false","root","Megha@2001");
			PreparedStatement pst=con.prepareStatement("insert into feedback(fName,email,phone,message) values(?,?,?,?); ");
			pst.setString(1, fName);
			pst.setString(2, email);
			pst.setString(3, phone);
			pst.setString(4, message);
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("home.jsp");
			if (rowCount > 0) {
				request.setAttribute("status","success");
				
			}else {
				request.setAttribute("status","Failed");
			}
			dispatcher.forward(request,response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
