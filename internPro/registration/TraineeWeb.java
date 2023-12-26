package internPro.registration;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import internPro.dao.TraineeDAO;
import internPro.model.Trainee;

/**
 * Servlet implementation class TraineeWeb
 */

@WebServlet("/TraineeWeb")
public class TraineeWeb extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TraineeDAO traineeDAO;

    public void init() {
        traineeDAO = new TraineeDAO();
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
                	deleteTrainee(request, response);
                    break;
                default:
                	listTrainees(request, response);
                    break;
            }
        } else {
        	listTrainees(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
          switch (action) {
            case "add":
                addTrainee(request, response);
                break;
            case "edit":
                updateTrainee(request, response);
                break;
          }
        }
    }

    private void listTrainees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Trainee> trainees = traineeDAO.getAllTrainees();
        request.setAttribute("trainees", trainees);
        request.getRequestDispatcher("trainee-list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("trainee-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int traineeId = Integer.parseInt(request.getParameter("id"));
        Trainee trainee = traineeDAO.getTraineeById(traineeId);
        request.setAttribute("trainee", trainee);
        request.getRequestDispatcher("trainee-form.jsp").forward(request, response);
    }

    private void addTrainee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String name = request.getParameter("name");
		String workshop = request.getParameter("workshop");
		String course = request.getParameter("course");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String percent = request.getParameter("percent");
		String amount = request.getParameter("amount");
		String status = request.getParameter("status");
		
		Trainee trainee = new Trainee(name, workshop, course, startDate, endDate, percent, amount, status);
        traineeDAO.addTrainee(trainee);
        response.sendRedirect("TraineeWeb");
    }

    private void updateTrainee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int traineeid = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String workshop = request.getParameter("workshop");
		String course = request.getParameter("course");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String percent = request.getParameter("percent");
		String amount = request.getParameter("amount");
		String status = request.getParameter("status");

		Trainee trainee = new Trainee(traineeid, name, workshop, course, startDate, endDate, percent, amount, status);
		traineeDAO.updateTrainee(trainee);
        response.sendRedirect("TraineeWeb");
    }

    private void deleteTrainee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int traineeId = Integer.parseInt(request.getParameter("id"));
        traineeDAO.deleteTrainee(traineeId);
        response.sendRedirect("TraineeWeb");
    }
}
