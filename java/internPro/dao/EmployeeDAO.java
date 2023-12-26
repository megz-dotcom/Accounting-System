package internPro.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
 
import internPro.model.Employee;

public class EmployeeDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/company?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Megha@2001";

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employee")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
				String bank = resultSet.getString("bank");
				String ifsc = resultSet.getString("ifsc");
				String account = resultSet.getString("account");
				String amount = resultSet.getString("amount");
				String status = resultSet.getString("status");
                Employee employee = new Employee(id, name, bank, ifsc, account, amount, status);
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public void addEmployee(Employee employee) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO employee (name, bank, ifsc, account, amount, status) VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getBank());
            statement.setString(3, employee.getIfsc());
            statement.setString(4, employee.getAccount());
            statement.setString(5, employee.getAmount());
            statement.setString(6, employee.getStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE employee SET name = ?,  bank = ?, ifsc = ?, account = ?, amount = ?, status = ?  WHERE id = ?")) {

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getBank());
            statement.setString(3, employee.getIfsc());
            statement.setString(4, employee.getAccount());
            statement.setString(5, employee.getAmount());
            statement.setString(6, employee.getStatus());
            statement.setInt(7, employee.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int employeeId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE id = ?")) {

            statement.setInt(1, employeeId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE name LIKE ?")) {

            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String empName = resultSet.getString("name");
				String bank = resultSet.getString("bank");
				String ifsc = resultSet.getString("ifsc");
				String account = resultSet.getString("account");
				String amount = resultSet.getString("amount");
				String status = resultSet.getString("status");

                
                Employee employee = new Employee(id, empName, bank, ifsc, account, amount, status);
                employees.add(employee);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?")) {

            statement.setInt(1, employeeId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
				String bank = resultSet.getString("bank");
				String ifsc = resultSet.getString("ifsc");
				String account = resultSet.getString("account");
				String amount = resultSet.getString("amount");
				String status = resultSet.getString("status");
				
                employee = new Employee(employeeId, name, bank, ifsc, account, amount, status);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

}
