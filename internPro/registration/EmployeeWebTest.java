package internPro.registration;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import internPro.dao.EmployeeDAOTest;
import internPro.model.EmployeeTest;


/**
 * Servlet implementation class EmployeeWebTest
 */
@WebServlet("/EmployeeWebTest")
public class EmployeeWebTest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAOTest employeeDAO;

    public void init() {
        employeeDAO = new EmployeeDAOTest();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteEmployee(request, response);
                    break;
                default:
                    listEmployees(request, response);
                    break;
            }
        } else {
            listEmployees(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addEmployee(request, response);
                    break;
                case "edit":
                    updateEmployee(request, response);
                    break;
            }
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeeTest> employees = employeeDAO.getAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("employeeListTest.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("employeeFormTest.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        EmployeeTest employee = employeeDAO.getEmployeeById(employeeId);

        request.setAttribute("employee", employee);
        request.getRequestDispatcher("employeeFormTest.jsp").forward(request, response);
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String payment_date = request.getParameter("payment_date");
        String bank = request.getParameter("bank");
  		String ifsc = request.getParameter("ifsc");
  		String account = request.getParameter("account");
  		String amount = request.getParameter("amount");
  		String status = request.getParameter("status");

        EmployeeTest employee = new EmployeeTest(name,payment_date, bank, ifsc, account, amount, status);
        employeeDAO.addEmployee(employee);

        response.sendRedirect("EmployeeWebTest");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String payment_date = request.getParameter("payment_date");
        String bank = request.getParameter("bank");
		String ifsc = request.getParameter("ifsc");
		String account = request.getParameter("account");
		String amount = request.getParameter("amount");
		String status = request.getParameter("status");
        EmployeeTest employee = new EmployeeTest(employeeId, name,payment_date, bank, ifsc, account, amount, status);
        employeeDAO.updateEmployee(employee);

        response.sendRedirect("EmployeeWebTest");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        employeeDAO.deleteEmployee(employeeId);

        response.sendRedirect("EmployeeWebTest");
    }
}
