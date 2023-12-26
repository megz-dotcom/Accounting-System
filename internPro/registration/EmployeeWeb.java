package internPro.registration;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import internPro.dao.EmployeeDAO;
import internPro.model.Employee;


/**
 * Servlet implementation class EmployeeWeb
 */
@WebServlet("/EmployeeWeb")
public class EmployeeWeb extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;

    public void init() {
        employeeDAO = new EmployeeDAO();
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
        List<Employee> employees = employeeDAO.getAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("employee-list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("employee-edit.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeDAO.getEmployeeById(employeeId);

        request.setAttribute("employee", employee);
        request.getRequestDispatcher("employee-edit.jsp").forward(request, response);
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String bank = request.getParameter("bank");
  		String ifsc = request.getParameter("ifsc");
  		String account = request.getParameter("account");
  		String amount = request.getParameter("amount");
  		String status = request.getParameter("status");

        Employee employee = new Employee(name,bank, ifsc, account, amount, status);
        employeeDAO.addEmployee(employee);

        response.sendRedirect("EmployeeWeb");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String bank = request.getParameter("bank");
		String ifsc = request.getParameter("ifsc");
		String account = request.getParameter("account");
		String amount = request.getParameter("amount");
		String status = request.getParameter("status");
        Employee employee = new Employee(employeeId, name, bank, ifsc, account, amount, status);
        employeeDAO.updateEmployee(employee);

        response.sendRedirect("EmployeeWeb");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        employeeDAO.deleteEmployee(employeeId);

        response.sendRedirect("EmployeeWeb");
    }
}