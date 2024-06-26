/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;

import com.ems.connection.Connect;
import com.ems.model.Employee;
import com.ems.model.RestaurantManager;
import com.ems.model.Schedule;
import com.ems.model.EmployeeSchedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author user
 */
public class EmployeeScheduleDAOImpl implements EmployeeScheduleDAO{
    private Connection conn;

    public EmployeeScheduleDAOImpl(){
        this.conn = Connect.getConnection();
    }
    
    
    @Override
    public void activeSchedule(Schedule schedule, Employee employee) {
        this.conn = Connect.getConnection();
        String sqlCheckExistence = "SELECT * FROM EMPLOYEESCHEDULE WHERE SCHEDULEID = ? AND EMPLOYEEID = ?";
        String sqlUpdateActivationStatus = "UPDATE EMPLOYEESCHEDULE SET SCHEDULEACTIVATIONSTATUS = ? WHERE SCHEDULEID = ? AND EMPLOYEEID = ?";
        String sqlInsertNewRecord = "INSERT INTO EMPLOYEESCHEDULE (SCHEDULEACTIVATIONSTATUS, EMPLOYEEID, SCHEDULEID) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sqlCheckExistence)) {
            statement.setInt(1, schedule.getScheduleID());
            statement.setInt(2, employee.getEmployeeID());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Record exists, update activation status
                    try (PreparedStatement updateStatement = conn.prepareStatement(sqlUpdateActivationStatus)) {
                        updateStatement.setBoolean(1, true); // Set activation status to true
                        updateStatement.setInt(2, schedule.getScheduleID());
                        updateStatement.setInt(3, employee.getEmployeeID());
                        updateStatement.executeUpdate();
                    }
                } else {
                    // Record does not exist, insert new record
                    try (PreparedStatement insertStatement = conn.prepareStatement(sqlInsertNewRecord)) {
                        insertStatement.setBoolean(1, true); // Set activation status to true
                        insertStatement.setInt(2, employee.getEmployeeID());
                        insertStatement.setInt(3, schedule.getScheduleID());
                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error activating schedule: " + e.getMessage(), e);
        }
    }

    @Override
    public void deactiveSchedule(Schedule schedule,Employee employee){
        this.conn = Connect.getConnection();
        String sqlCheckExistence = "SELECT * FROM EMPLOYEESCHEDULE WHERE SCHEDULEID = ? AND EMPLOYEEID = ?";
        String sqlUpdateActivationStatus = "UPDATE EMPLOYEESCHEDULE SET SCHEDULEACTIVATIONSTATUS = ? WHERE SCHEDULEID = ? AND EMPLOYEEID = ?";
        String sqlInsertNewRecord = "INSERT INTO EMPLOYEESCHEDULE (SCHEDULEACTIVATIONSTATUS, EMPLOYEEID, SCHEDULEID) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sqlCheckExistence)) {
            statement.setInt(1, schedule.getScheduleID());
            statement.setInt(2, employee.getEmployeeID());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Record exists, update activation status
                    try (PreparedStatement updateStatement = conn.prepareStatement(sqlUpdateActivationStatus)) {
                        updateStatement.setBoolean(1, false); // Set activation status to true
                        updateStatement.setInt(2, schedule.getScheduleID());
                        updateStatement.setInt(3, employee.getEmployeeID());
                        updateStatement.executeUpdate();
                    }
                } else {
                    // Record does not exist, insert new record
                    try (PreparedStatement insertStatement = conn.prepareStatement(sqlInsertNewRecord)) {
                        insertStatement.setBoolean(1, false); // Set activation status to true
                        insertStatement.setInt(2, employee.getEmployeeID());
                        insertStatement.setInt(3, schedule.getScheduleID());
                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error activating schedule: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void deleteEmployeeSchedule(Schedule schedule) {
        this.conn = Connect.getConnection();
        String sql = "UPDATE EMPLOYEESCHEDULE SET SCHEDULEID = NULL WHERE SCHEDULEID =?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, schedule.getScheduleID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error deleting employee schedule: " + e.getMessage(), e);
        }
    }
    
    @Override
    public int[] getAllScheduleEmployee(Schedule schedule) {
        this.conn = Connect.getConnection();
        List<Integer> employeeIDList = new ArrayList<>();
        String sql = "SELECT E.EMPLOYEEID " +
                      "FROM EMPLOYEESCHEDULE ES " +
                      "JOIN EMPLOYEE E ON ES.EMPLOYEEID = E.EMPLOYEEID " +
                      "WHERE ES.SCHEDULEID =? AND ES.SCHEDULEACTIVATIONSTATUS = TRUE";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, schedule.getScheduleID());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    employeeIDList.add(resultSet.getInt("EMPLOYEEID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving employee IDs: " + e.getMessage(), e);
        }

        int[] employeeIDs = new int[employeeIDList.size()];
        for (int i = 0; i < employeeIDList.size(); i++) {
            employeeIDs[i] = employeeIDList.get(i);
        }

        return employeeIDs;
    }
    
    @Override
    public EmployeeSchedule getScheduleEmployee(Schedule schedule, Employee employee) {
        this.conn = Connect.getConnection();
        EmployeeSchedule employeeSchedule = null;
        String sql = "SELECT * FROM EMPLOYEESCHEDULE WHERE SCHEDULEID =? AND EMPLOYEEID =?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, schedule.getScheduleID());
            statement.setInt(2, employee.getEmployeeID());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    employeeSchedule = new EmployeeSchedule();
                    employeeSchedule.setEmployeeScheduleID(resultSet.getInt("EMPLOYEESCHEDULEID"));
                    employeeSchedule.setScheduleActivationStatus(resultSet.getBoolean("SCHEDULEACTIVATIONSTATUS"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving schedule employee: " + e.getMessage(), e);
        }

        return employeeSchedule;
    }
    
    @Override
    public EmployeeSchedule[] getScheduleStatus(Schedule schedule) {
    this.conn = Connect.getConnection();
    List<EmployeeSchedule> scheduleList = new ArrayList<>();
    String sql = "SELECT * FROM EMPLOYEESCHEDULE WHERE SCHEDULEID = ?";
    
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, schedule.getScheduleID());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                EmployeeSchedule employeeSchedule = new EmployeeSchedule();
                employeeSchedule.setEmployeeScheduleID(resultSet.getInt("EMPLOYEESCHEDULEID"));
                employeeSchedule.setScheduleActivationStatus(resultSet.getBoolean("SCHEDULEACTIVATIONSTATUS"));
                scheduleList.add(employeeSchedule);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Replace with logging framework in production
        throw new RuntimeException("Error retrieving schedule status: " + e.getMessage(), e);
    }
    
    return scheduleList.toArray(new EmployeeSchedule[0]);
}

}
