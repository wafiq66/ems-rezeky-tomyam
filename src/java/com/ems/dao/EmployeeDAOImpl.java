/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;

import com.ems.model.Employee;
import com.ems.model.Schedule;
import com.ems.connection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDAOImpl implements EmployeeDAO {

    private Connection conn;

    public EmployeeDAOImpl() {
        this.conn = Connect.getConnection();
    }

    @Override
    public void addEmployee(Employee employee, int branchID, int officerID) {
        this.conn = Connect.getConnection();
        employee.setEmployeePassword("emp123");
        employee.setEmployeeStatus(true);
        String sql = "INSERT INTO EMPLOYEE (EMPLOYEEPASSWORD, EMPLOYEENAME, EMPLOYEEPHONENUMBER, EMPLOYEESTATUS, EMPLOYEEHOURLYPAY, EMPLOYEEEMAIL, EMPLOYEEPASSPORTNUMBER, BRANCHID, OFFICERID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, employee.getEmployeePassword());
            statement.setString(2, employee.getEmployeeName());
            statement.setString(3, employee.getEmployeePhoneNumber());
            statement.setBoolean(4, employee.getEmployeeStatus());
            statement.setDouble(5, employee.getEmployeeHourlyPay());
            statement.setString(6, employee.getEmployeeEmail());
            statement.setString(7, employee.getEmployeePassportNumber());
            statement.setInt(8, branchID);
            statement.setInt(9, officerID); // This value should be set dynamically based on your application logic
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error adding employee: " + e.getMessage(), e);
        } 
    }
    
    @Override
    public void updateEmployee(Employee employee, int branchID) {
        this.conn = Connect.getConnection();
        String sql = "UPDATE EMPLOYEE SET EMPLOYEENAME = ?, EMPLOYEEPHONENUMBER = ?, EMPLOYEESTATUS = ?, EMPLOYEEHOURLYPAY = ?, EMPLOYEEEMAIL = ?, EMPLOYEEPASSPORTNUMBER = ?, EMPLOYEEPASSWORD = ?, BRANCHID = ? WHERE EMPLOYEEID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, employee.getEmployeeName());
            statement.setString(2, employee.getEmployeePhoneNumber());
            statement.setBoolean(3, employee.getEmployeeStatus());
            statement.setDouble(4, employee.getEmployeeHourlyPay());
            statement.setString(5, employee.getEmployeeEmail());
            statement.setString(6, employee.getEmployeePassportNumber());
            statement.setString(7, employee.getEmployeePassword()); // Add this line
            statement.setInt(8, branchID);
            statement.setInt(9, employee.getEmployeeID()); 
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error updating employee: " + e.getMessage(), e);
        } 
    }

    @Override
    public int getEmployeeBranchID(Employee employee) {
        this.conn = Connect.getConnection();
        int branchID = 0; // Default branch ID, or whatever makes sense in your context
        String sql = "SELECT BRANCHID FROM EMPLOYEE WHERE EMPLOYEEID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeID());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    branchID = resultSet.getInt("BRANCHID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving branch ID for employee: " + e.getMessage(), e);
        } 
        return branchID;
    }
    
    @Override
    public Employee getEmployeeByPassport(String passport) {
        this.conn = Connect.getConnection();
        Employee employee = null;
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEEPASSPORTNUMBER = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, passport);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    employee = new Employee();
                    employee.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                    employee.setEmployeePassword(resultSet.getString("EMPLOYEEPASSWORD"));
                    employee.setEmployeeName(resultSet.getString("EMPLOYEENAME"));
                    employee.setEmployeePassportNumber(resultSet.getString("EMPLOYEEPASSPORTNUMBER"));
                    employee.setEmployeePhoneNumber(resultSet.getString("EMPLOYEEPHONENUMBER"));
                    employee.setEmployeeStatus(resultSet.getBoolean("EMPLOYEESTATUS"));
                    employee.setEmployeeHourlyPay(resultSet.getDouble("EMPLOYEEHOURLYPAY"));
                    employee.setEmployeeEmail(resultSet.getString("EMPLOYEEEMAIL"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving employee by passport: " + e.getMessage(), e);
        } 
        return employee;
    }

    @Override
    public Employee getEmployeeById(int employeeID) {
        this.conn = Connect.getConnection();
        Employee employee = null;
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEEID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, employeeID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    employee = new Employee();
                    employee.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                    employee.setEmployeePassword(resultSet.getString("EMPLOYEEPASSWORD"));
                    employee.setEmployeeName(resultSet.getString("EMPLOYEENAME"));
                    employee.setEmployeePassportNumber(resultSet.getString("EMPLOYEEPASSPORTNUMBER"));
                    employee.setEmployeePhoneNumber(resultSet.getString("EMPLOYEEPHONENUMBER"));
                    employee.setEmployeeStatus(resultSet.getBoolean("EMPLOYEESTATUS"));
                    employee.setEmployeeHourlyPay(resultSet.getDouble("EMPLOYEEHOURLYPAY"));
                    employee.setEmployeeEmail(resultSet.getString("EMPLOYEEEMAIL"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving employee by ID: " + e.getMessage(), e);
        } 
        return employee;
    }


    
    @Override
    public Employee[] getAllEmployee() {
        this.conn = Connect.getConnection();
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM EMPLOYEE";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                employee.setEmployeePassword(resultSet.getString("EMPLOYEEPASSWORD"));
                employee.setEmployeePassportNumber(resultSet.getString("EMPLOYEEPASSPORTNUMBER"));
                employee.setEmployeeName(resultSet.getString("EMPLOYEENAME"));
                employee.setEmployeePhoneNumber(resultSet.getString("EMPLOYEEPHONENUMBER"));
                employee.setEmployeeStatus(resultSet.getBoolean("EMPLOYEESTATUS"));
                employee.setEmployeeHourlyPay(resultSet.getDouble("EMPLOYEEHOURLYPAY"));
                employee.setEmployeeEmail(resultSet.getString("EMPLOYEEEMAIL"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving all employees: " + e.getMessage(), e);
        } 
        return employeeList.toArray(new Employee[0]);
    }
    
    
    
    @Override
    public Employee[] getAllEmployeeById(int[] employeeIDs) {
        this.conn = Connect.getConnection();
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEEID IN (" + 
                      Arrays.stream(employeeIDs).mapToObj(String::valueOf).collect(Collectors.joining(", ")) + 
                      ")";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                employee.setEmployeePassword(resultSet.getString("EMPLOYEEPASSWORD"));
                employee.setEmployeePassportNumber(resultSet.getString("EMPLOYEEPASSPORTNUMBER"));
                employee.setEmployeeName(resultSet.getString("EMPLOYEENAME"));
                employee.setEmployeePhoneNumber(resultSet.getString("EMPLOYEEPHONENUMBER"));
                employee.setEmployeeStatus(resultSet.getBoolean("EMPLOYEESTATUS"));
                employee.setEmployeeHourlyPay(resultSet.getDouble("EMPLOYEEHOURLYPAY"));
                employee.setEmployeeEmail(resultSet.getString("EMPLOYEEEMAIL"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving employees by IDs: " + e.getMessage(), e);
        } 
        return employeeList.toArray(new Employee[0]);
    }
    
    @Override
    public Employee[] getAllEmployeeByBranch(int branchID) {
        this.conn = Connect.getConnection();
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM EMPLOYEE WHERE BRANCHID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, branchID); // Set the branchID parameter here
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                    employee.setEmployeePassword(resultSet.getString("EMPLOYEEPASSWORD"));
                    employee.setEmployeePassportNumber(resultSet.getString("EMPLOYEEPASSPORTNUMBER"));
                    employee.setEmployeeName(resultSet.getString("EMPLOYEENAME"));
                    employee.setEmployeePhoneNumber(resultSet.getString("EMPLOYEEPHONENUMBER"));
                    employee.setEmployeeStatus(resultSet.getBoolean("EMPLOYEESTATUS"));
                    employee.setEmployeeHourlyPay(resultSet.getDouble("EMPLOYEEHOURLYPAY"));
                    employee.setEmployeeEmail(resultSet.getString("EMPLOYEEEMAIL"));
                    employeeList.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving all employees: " + e.getMessage(), e);
        }
        return employeeList.toArray(new Employee[0]);
    }

}
