/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;

import com.ems.connection.Connect;
import com.ems.model.Employee;
import com.ems.model.Salary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class SalaryDAOImpl implements SalaryDAO{
    private Connection conn;

    public SalaryDAOImpl(){
        this.conn = Connect.getConnection();
    }
    @Override
    public Salary[] getUncalculatedEmployeeSalary(int month, int year) {
        this.conn = Connect.getConnection();
        List<Salary> salaries = new ArrayList<>();
        String sql = "SELECT " +
                      "e.EMPLOYEEID, " +
                      "MONTH(a.ATTENDANCEDATE) AS AttendanceMonth, " +
                      "YEAR(a.ATTENDANCEDATE) AS AttendanceYear, " +
                      "FLOOR(SUM((HOUR(a.CLOCKOUTTIME) * 3600 + MINUTE(a.CLOCKOUTTIME) * 60 + SECOND(a.CLOCKOUTTIME)) - (HOUR(a.CLOCKINTIME) * 3600 + MINUTE(a.CLOCKINTIME) * 60 + SECOND(a.CLOCKINTIME))) / 3600) AS TotalHoursWorked, " +
                      "FLOOR(SUM((HOUR(a.CLOCKOUTTIME) * 3600 + MINUTE(a.CLOCKOUTTIME) * 60 + SECOND(a.CLOCKOUTTIME)) - (HOUR(a.CLOCKINTIME) * 3600 + MINUTE(a.CLOCKINTIME) * 60 + SECOND(a.CLOCKINTIME))) / 3600 * e.EMPLOYEEHOURLYPAY) AS TotalSalary " +
                      "FROM EMP.ATTENDANCE a " +
                      "JOIN EMP.EMPLOYEESCHEDULE es ON a.EMPLOYEESCHEDULEID = es.EMPLOYEESCHEDULEID " +
                      "JOIN EMP.EMPLOYEE e ON es.EMPLOYEEID = e.EMPLOYEEID " +
                      "WHERE MONTH(a.ATTENDANCEDATE) = ? AND YEAR(a.ATTENDANCEDATE) = ? " +
                      "GROUP BY e.EMPLOYEEID, MONTH(a.ATTENDANCEDATE), YEAR(a.ATTENDANCEDATE), e.EMPLOYEEHOURLYPAY";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, month);
            statement.setInt(2, year);

            // For debugging, print the SQL query
            System.out.println("Executing SQL: " + sql);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Salary salary = new Salary();
                    salary.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                    salary.setSalaryMonth(resultSet.getInt("AttendanceMonth"));
                    salary.setSalaryYear(resultSet.getInt("AttendanceYear"));
                    salary.setTotalHoursWorked(resultSet.getInt("TotalHoursWorked"));
                    salary.setSalaryAmount(resultSet.getDouble("TotalSalary"));
                    salaries.add(salary);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving salary for month " + month + " and year " + year + ": " + e.getMessage(), e);
        }

        // For debugging, print the number of salaries found
        System.out.println("Found " + salaries.size() + " salaries for month " + month + " and year " + year);

        return salaries.toArray(new Salary[0]);
    }

    @Override
    public Salary getUncalculatedEmployeeSalary(Employee employee, int month, int year) {
        this.conn = Connect.getConnection();
        Salary salary = new Salary();
        //sql line will be revised
        String sql = "SELECT " +
                      "e.EMPLOYEEID, " +
                      "MONTH(a.ATTENDANCEDATE) AS AttendanceMonth, " +
                      "YEAR(a.ATTENDANCEDATE) AS AttendanceYear, " +
                      "FLOOR(SUM((HOUR(a.CLOCKOUTTIME) * 3600 + MINUTE(a.CLOCKOUTTIME) * 60 + SECOND(a.CLOCKOUTTIME)) - (HOUR(a.CLOCKINTIME) * 3600 + MINUTE(a.CLOCKINTIME) * 60 + SECOND(a.CLOCKINTIME))) / 3600) AS TotalHoursWorked, " +
                      "FLOOR(SUM((HOUR(a.CLOCKOUTTIME) * 3600 + MINUTE(a.CLOCKOUTTIME) * 60 + SECOND(a.CLOCKOUTTIME)) - (HOUR(a.CLOCKINTIME) * 3600 + MINUTE(a.CLOCKINTIME) * 60 + SECOND(a.CLOCKINTIME))) / 3600 * e.EMPLOYEEHOURLYPAY) AS TotalSalary " +
                      "FROM EMP.ATTENDANCE a " +
                      "JOIN EMP.EMPLOYEESCHEDULE es ON a.EMPLOYEESCHEDULEID = es.EMPLOYEESCHEDULEID " +
                      "JOIN EMP.EMPLOYEE e ON es.EMPLOYEEID = e.EMPLOYEEID " +
                      "WHERE e.EMPLOYEEID = ? AND MONTH(a.ATTENDANCEDATE) = ? AND YEAR(a.ATTENDANCEDATE) = ? " +
                      "GROUP BY e.EMPLOYEEID, MONTH(a.ATTENDANCEDATE), YEAR(a.ATTENDANCEDATE), e.EMPLOYEEHOURLYPAY";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeID());
            statement.setInt(2, month);
            statement.setInt(3, year);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    salary.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                    salary.setSalaryMonth(resultSet.getInt("AttendanceMonth"));
                    salary.setSalaryYear(resultSet.getInt("AttendanceYear"));
                    salary.setTotalHoursWorked(resultSet.getInt("TotalHoursWorked"));
                    salary.setSalaryAmount(resultSet.getDouble("TotalSalary"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving salary for employee: " + e.getMessage(), e);
        } 

        return salary;
    }
    @Override
    public Salary[] getUncalculatedEmployeeSalary(Employee employee){
        this.conn = Connect.getConnection();
        List<Salary> salaryList = new ArrayList<>();
        //sql line will be revised 
        String sql = "SELECT " +
                      "e.EMPLOYEEID, " +
                      "MONTH(a.ATTENDANCEDATE) AS AttendanceMonth, " +
                      "YEAR(a.ATTENDANCEDATE) AS AttendanceYear, " +
                      "FLOOR(SUM((HOUR(a.CLOCKOUTTIME) * 3600 + MINUTE(a.CLOCKOUTTIME) * 60 + SECOND(a.CLOCKOUTTIME)) - (HOUR(a.CLOCKINTIME) * 3600 + MINUTE(a.CLOCKINTIME) * 60 + SECOND(a.CLOCKINTIME))) / 3600) AS TotalHoursWorked, " +
                      "FLOOR(SUM((HOUR(a.CLOCKOUTTIME) * 3600 + MINUTE(a.CLOCKOUTTIME) * 60 + SECOND(a.CLOCKOUTTIME)) - (HOUR(a.CLOCKINTIME) * 3600 + MINUTE(a.CLOCKINTIME) * 60 + SECOND(a.CLOCKINTIME))) / 3600 * e.EMPLOYEEHOURLYPAY) AS TotalSalary " +
                      "FROM EMP.ATTENDANCE a " +
                      "JOIN EMP.EMPLOYEESCHEDULE es ON a.EMPLOYEESCHEDULEID = es.EMPLOYEESCHEDULEID " +
                      "JOIN EMP.EMPLOYEE e ON es.EMPLOYEEID = e.EMPLOYEEID " +
                      "WHERE e.EMPLOYEEID = ? " +
                      "GROUP BY e.EMPLOYEEID, MONTH(a.ATTENDANCEDATE), YEAR(a.ATTENDANCEDATE), e.EMPLOYEEHOURLYPAY " +
                      "ORDER BY YEAR(a.ATTENDANCEDATE), MONTH(a.ATTENDANCEDATE), e.EMPLOYEEID";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeID());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Salary salary = new Salary();
                    salary.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                    salary.setSalaryMonth(resultSet.getInt("AttendanceMonth"));
                    salary.setSalaryYear(resultSet.getInt("AttendanceYear"));
                    salary.setTotalHoursWorked(resultSet.getInt("TotalHoursWorked"));
                    salary.setSalaryAmount(resultSet.getDouble("TotalSalary"));
                    salaryList.add(salary);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving salaries for employee: " + e.getMessage(), e);
        } 

        return salaryList.toArray(new Salary[0]);
    }
    
    @Override
    public void recordSalary(Salary salary) {
        this.conn = Connect.getConnection();
        String sql = "INSERT INTO Salary (EMPLOYEEID, SALARYMONTH, SALARYYEAR, TOTALHOURSWORKED, SALARYAMOUNT) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, salary.getEmployeeID());
            statement.setInt(2, salary.getSalaryMonth());
            statement.setInt(3, salary.getSalaryYear());
            statement.setInt(4, salary.getTotalHoursWorked());
            statement.setDouble(5, salary.getSalaryAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error recording salary: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Salary[] getCalculatedEmployeeSalary(int month, int year) {
        this.conn = Connect.getConnection();
        List<Salary> salaries = new ArrayList<>();
        String sql = "SELECT " +
                      "s.EMPLOYEEID, " +
                      "s.SALARYMONTH, " +
                      "s.SALARYYEAR, " +
                      "s.TOTALHOURSWORKED, " +
                      "s.SALARYAMOUNT " +
                      "FROM EMP.SALARY s " +
                      "WHERE s.SALARYMONTH = ? AND s.SALARYYEAR = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, month);
            statement.setInt(2, year);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Salary salary = new Salary();
                    salary.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                    salary.setSalaryMonth(resultSet.getInt("SALARYMONTH"));
                    salary.setSalaryYear(resultSet.getInt("SALARYYEAR"));
                    salary.setTotalHoursWorked(resultSet.getInt("TOTALHOURSWORKED"));
                    salary.setSalaryAmount(resultSet.getDouble("SALARYAMOUNT"));
                    salaries.add(salary);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving salary for month " + month + " and year " + year + ": " + e.getMessage(), e);
        } 

        return salaries.toArray(new Salary[0]);
    }
    
    @Override
    public Salary getCalculatedEmployeeSalary(Employee employee, int month, int year) {
        this.conn = Connect.getConnection();
        Salary salary = new Salary();
        String sql = "SELECT " +
                      "s.EMPLOYEEID, " +
                      "s.SALARYMONTH, " +
                      "s.SALARYYEAR, " +
                      "s.TOTALHOURSWORKED, " +
                      "s.SALARYAMOUNT " +
                      "FROM EMP.SALARY s " +
                      "WHERE s.EMPLOYEEID = ? AND s.SALARYMONTH = ? AND s.SALARYYEAR = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeID());
            statement.setInt(2, month);
            statement.setInt(3, year);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    salary.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                    salary.setSalaryMonth(resultSet.getInt("SALARYMONTH"));
                    salary.setSalaryYear(resultSet.getInt("SALARYYEAR"));
                    salary.setTotalHoursWorked(resultSet.getInt("TOTALHOURSWORKED"));
                    salary.setSalaryAmount(resultSet.getDouble("SALARYAMOUNT"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving salary for employee: " + e.getMessage(), e);
        } 

        return salary;
    }
    
    @Override
    public Salary[] getCalculatedEmployeeSalary(Employee employee){
        this.conn = Connect.getConnection();
       List<Salary> salaryList = new ArrayList<>();
        String sql = "SELECT " +
                      "s.EMPLOYEEID, " +
                      "s.SALARYMONTH, " +
                      "s.SALARYYEAR, " +
                      "s.TOTALHOURSWORKED, " +
                      "s.SALARYAMOUNT " +
                      "FROM EMP.SALARY s " +
                      "WHERE s.EMPLOYEEID = ? " +
                      "ORDER BY s.SALARYMONTH, s.SALARYYEAR";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeID());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Salary salary = new Salary();
                    salary.setEmployeeID(resultSet.getInt("EMPLOYEEID"));
                    salary.setSalaryMonth(resultSet.getInt("SALARYMONTH"));
                    salary.setSalaryYear(resultSet.getInt("SALARYYEAR"));
                    salary.setTotalHoursWorked(resultSet.getInt("TOTALHOURSWORKED"));
                    salary.setSalaryAmount(resultSet.getDouble("SALARYAMOUNT"));
                    salaryList.add(salary);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving salaries for employee: " + e.getMessage(), e);
        } 

        return salaryList.toArray(new Salary[0]);
    }
    
    @Override
    public void updateSalary(Salary salary) {
        this.conn = Connect.getConnection();
        String sql = "UPDATE Salary " +
                     "SET TOTALHOURSWORKED = ?, SALARYAMOUNT = ? " +
                     "WHERE EMPLOYEEID = ? AND SALARYMONTH = ? AND SALARYYEAR = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, salary.getTotalHoursWorked());
            statement.setDouble(2, salary.getSalaryAmount());
            statement.setInt(3, salary.getEmployeeID());
            statement.setInt(4, salary.getSalaryMonth());
            statement.setInt(5, salary.getSalaryYear());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new RuntimeException("No salary record found for update");
            }

            // Optionally, log or print the number of rows updated
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error updating salary: " + e.getMessage(), e);
        }
    }

    
    

}
