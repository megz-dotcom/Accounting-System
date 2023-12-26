package internPro.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("name");
        String uemail = request.getParameter("email");
        String upwd = request.getParameter("pass");
        String umobile = request.getParameter("contact");

        RequestDispatcher dispatcher = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?useSSL=false", "root",
                    "Megha@2001");

            // Check if the email already exists
            boolean emailExists = checkIfEmailExists(con, uemail);
            if (emailExists) {
                request.setAttribute("emailError", "Email already exists");
                dispatcher = request.getRequestDispatcher("registration.jsp");
            } else {
                PreparedStatement pst = con.prepareStatement(
                        "insert into users(uname, upwd, uemail, umobile) values(?, ?, ?, ?) ");
                pst.setString(1, uname);
                pst.setString(2, upwd);
                pst.setString(3, uemail);
                pst.setString(4, umobile);

                int rowCount = pst.executeUpdate();
                dispatcher = request.getRequestDispatcher("registration.jsp");
                if (rowCount > 0) {
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("status", "Failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            dispatcher = request.getRequestDispatcher("registration.jsp");
            request.setAttribute("status", "Failed");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dispatcher.forward(request, response);
        }
    }

    private boolean checkIfEmailExists(Connection con, String email) throws SQLException {
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM users WHERE uemail = ?");
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
        return false;
    }
}
