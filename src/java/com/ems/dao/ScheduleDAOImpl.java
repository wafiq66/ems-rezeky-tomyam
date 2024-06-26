/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;

import com.ems.connection.Connect;
import com.ems.model.Schedule;
import com.ems.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author user
 */
public class ScheduleDAOImpl implements ScheduleDAO{
    private Connection conn;

    public ScheduleDAOImpl(){
        this.conn = Connect.getConnection();
    }
    @Override
    public Schedule getScheduleByID(int scheduleID){
        this.conn = Connect.getConnection();
        Schedule schedule = null;
        String sql = "SELECT * " +
                      "FROM SCHEDULE " +
                      "WHERE SCHEDULEID = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, scheduleID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    schedule = new Schedule();
                    schedule.setScheduleID(resultSet.getInt("SCHEDULEID"));
                    schedule.setScheduleDate(resultSet.getDate("SCHEDULEDATE")!= null? resultSet.getDate("SCHEDULEDATE").toString() : null);
                    schedule.setStartShift(resultSet.getTime("STARTSHIFT")!= null? new SimpleDateFormat("HH:mm:ss").format(resultSet.getTime("STARTSHIFT")) : null);
                    schedule.setEndShift(resultSet.getTime("ENDSHIFT")!= null? new SimpleDateFormat("HH:mm:ss").format(resultSet.getTime("ENDSHIFT")) : null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving schedule by ID: " + e.getMessage(), e);
        } 

        return schedule;
    }
    
    @Override
    public int createSchedule(Schedule schedule) {
        this.conn = Connect.getConnection();
        String sql = "INSERT INTO SCHEDULE (SCHEDULEDATE, STARTSHIFT, ENDSHIFT) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, java.sql.Date.valueOf(schedule.getScheduleDate()));
            statement.setTime(2, java.sql.Time.valueOf(schedule.getStartShift()));
            statement.setTime(3, java.sql.Time.valueOf(schedule.getEndShift()));
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Return the generated ScheduleID
                } else {
                    throw new RuntimeException("Error creating schedule: no ID generated");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error creating schedule: " + e.getMessage(), e);
        }
    }
    @Override
    public void updateSchedule(Schedule schedule) {
        this.conn = Connect.getConnection();
        String sql = "UPDATE SCHEDULE " +
                      "SET SCHEDULEDATE = ?, STARTSHIFT = ?, ENDSHIFT = ? " +
                      "WHERE SCHEDULEID = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(schedule.getScheduleDate()));
            statement.setTime(2, java.sql.Time.valueOf(schedule.getStartShift()));
            statement.setTime(3, java.sql.Time.valueOf(schedule.getEndShift()));
            statement.setInt(4, schedule.getScheduleID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error updating schedule: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void deleteSchedule(Schedule schedule) {
        this.conn = Connect.getConnection();
        String sql = "DELETE FROM SCHEDULE WHERE SCHEDULEID = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, schedule.getScheduleID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error deleting schedule: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Schedule fetchLatestSchedule(Employee employee) {
        this.conn = Connect.getConnection();
        Schedule schedule = null;
        String sql = "SELECT S.* " +
                     "FROM EMPLOYEESCHEDULE AS ES " +
                     "JOIN SCHEDULE AS S ON (ES.SCHEDULEID = S.SCHEDULEID) " +
                     "WHERE ES.SCHEDULEACTIVATIONSTATUS = TRUE AND ES.EMPLOYEEID =? " +
                     "ORDER BY S.SCHEDULEDATE DESC " +
                     "FETCH FIRST 1 ROWS ONLY";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeID());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    schedule = new Schedule();
                    schedule.setScheduleID(resultSet.getInt("SCHEDULEID"));
                    schedule.setScheduleDate(resultSet.getDate("SCHEDULEDATE")!= null? resultSet.getDate("SCHEDULEDATE").toString() : "none");
                    schedule.setStartShift(resultSet.getTime("STARTSHIFT")!= null? new SimpleDateFormat("HH:mm:ss").format(resultSet.getTime("STARTSHIFT")) : "none");
                    schedule.setEndShift(resultSet.getTime("ENDSHIFT")!= null? new SimpleDateFormat("HH:mm:ss").format(resultSet.getTime("ENDSHIFT")) : "none");
                } else {
                    schedule = new Schedule();
                    schedule.setScheduleID(0);
                    schedule.setScheduleDate("none");
                    schedule.setStartShift("none");
                    schedule.setEndShift("none");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error fetching latest schedule for employee: " + e.getMessage(), e);
        } 

        return schedule;
    }
    
@Override
public Schedule[] getAllScheduleByBranch(int branchID) {
    this.conn = Connect.getConnection();
    List<Schedule> scheduleList = new ArrayList<>();
    String sql = "SELECT DISTINCT S.* " +
                  "FROM SCHEDULE S " +
                  "JOIN EMPLOYEESCHEDULE ES ON S.SCHEDULEID = ES.SCHEDULEID " +
                  "JOIN EMPLOYEE E ON ES.EMPLOYEEID = E.EMPLOYEEID " +
                  "WHERE E.BRANCHID =? AND E.EMPLOYEESTATUS = TRUE";
    
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, branchID);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleID(resultSet.getInt("SCHEDULEID"));
                schedule.setScheduleDate(resultSet.getDate("SCHEDULEDATE")!= null? resultSet.getDate("SCHEDULEDATE").toString() : null);
                schedule.setStartShift(resultSet.getTime("STARTSHIFT")!= null? new SimpleDateFormat("HH:mm:ss").format(resultSet.getTime("STARTSHIFT")) : null);
                schedule.setEndShift(resultSet.getTime("ENDSHIFT")!= null? new SimpleDateFormat("HH:mm:ss").format(resultSet.getTime("ENDSHIFT")) : null);
                scheduleList.add(schedule);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Replace with logging framework in production
        throw new RuntimeException("Error retrieving schedules by branch ID: " + e.getMessage(), e);
    } 
    
    return scheduleList.toArray(new Schedule[0]);
}
}
