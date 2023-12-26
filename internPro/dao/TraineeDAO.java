package internPro.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
 
import internPro.model.Trainee;

public class TraineeDAO {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/company?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Megha@2001";

    public List<Trainee> getAllTrainees() {
        List<Trainee> trainees = new ArrayList<>();
        String query = "SELECT * FROM trainee";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = conn.createStatement();
        	 ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
            	int id = rs.getInt("id");
            	String name = rs.getString("name");
				String workshop = rs.getString("workshop");
				String course = rs.getString("course");
				String startDate = rs.getString("startDate");
				String endDate = rs.getString("endDate");
				String percent = rs.getString("percent");
				String amount = rs.getString("amount");
				String status = rs.getString("status");
				Trainee trainee = new Trainee(id, name, workshop, course, startDate, endDate, percent, amount, status);
                trainees.add(trainee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainees;
    }
    
    public void addTrainee(Trainee trainee) {
        String query = "INSERT INTO trainee (name, workshop, course, startDate, endDate, percent, amount, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, trainee.getName());
            statement.setString(2, trainee.getWorkshop());
            statement.setString(3, trainee.getCourse());
            statement.setString(4, trainee.getStartDate());
            statement.setString(5, trainee.getEndDate());
            statement.setString(6, trainee.getPercent());
            statement.setString(7, trainee.getAmount());
            statement.setString(8, trainee.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTrainee(Trainee trainee) {
        String query = "UPDATE trainee SET name=?, workshop=?, course=?, startDate=?, endDate=?, percent=?, " +
                "amount=?, status=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, trainee.getName());
            statement.setString(2, trainee.getWorkshop());
            statement.setString(3, trainee.getCourse());
            statement.setString(4, trainee.getStartDate());
            statement.setString(5, trainee.getEndDate());
            statement.setString(6, trainee.getPercent());
            statement.setString(7, trainee.getAmount());
            statement.setString(8, trainee.getStatus());
            statement.setInt(9, trainee.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrainee(int traineeId) {
        String query = "DELETE FROM trainee WHERE id=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, traineeId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Trainee> searchTraineesByName(String name) {
        List<Trainee> trainees = new ArrayList<>();
        String query = "SELECT * FROM trainee WHERE name LIKE ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, "%" + name + "%");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
            	int id = rs.getInt("id");
				String trname = rs.getString("name");
				String workshop = rs.getString("workshop");
				String course = rs.getString("course");
				String startDate = rs.getString("startDate");
				String endDate = rs.getString("endDate");
				String percent = rs.getString("percent");
				String amount = rs.getString("amount");
				String status = rs.getString("status");
				
				Trainee trainee = new Trainee(id, trname, workshop, course, startDate, endDate, percent, amount, status);
                trainees.add(trainee);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trainees;
    }

    public Trainee getTraineeById(int traineeId) {
        Trainee trainee = null;
        String query = "SELECT * FROM trainee WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, traineeId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
            	String name = rs.getString("name");
				String workshop = rs.getString("workshop");
				String course = rs.getString("course");
				String startDate = rs.getString("startDate");
				String endDate = rs.getString("endDate");
				String percent = rs.getString("percent");
				String amount = rs.getString("amount");
				String status = rs.getString("status");
				
				trainee = new Trainee(traineeId, name, workshop, course, startDate, endDate, percent, amount, status);
            }
            rs.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainee;
    }
}
