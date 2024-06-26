/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;

import com.ems.connection.Connect;
import com.ems.model.Attendance;
import com.ems.model.Employee;
import com.ems.model.EmployeeSchedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class AttendanceDAOImpl implements AttendanceDAO{
    private Connection conn;

    public AttendanceDAOImpl(){
        this.conn = Connect.getConnection();
    }
    /**
     *
     * @param employee
     * @return
     */
    @Override
    public void recordInAttendance(EmployeeSchedule employeeSchedule) {
        this.conn = Connect.getConnection();
        String sql = "INSERT INTO EMP.ATTENDANCE (ATTENDANCEDATE, CLOCKINTIME, EMPLOYEESCHEDULEID) " +
                     "VALUES (CURRENT_DATE, CURRENT_TIME,?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, employeeSchedule.getEmployeeScheduleID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error recording in attendance: " + e.getMessage(), e);
        }
    }

    @Override
    public void recordOutAttendance(Attendance attendance) {
        this.conn = Connect.getConnection();
        String sql = "UPDATE EMP.ATTENDANCE " +
                     "SET CLOCKOUTTIME = CURRENT_TIME " +
                     "WHERE ATTENDANCEID =?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, attendance.getAttendanceID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error recording out attendance: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Attendance[] getAllAttendances(Employee employee) {
        this.conn = Connect.getConnection();
        List<Attendance> attendances = new ArrayList<>();
        String sql = "SELECT A.* " +
                     "FROM EMPLOYEESCHEDULE AS ES " +
                     "JOIN ATTENDANCE AS A ON (ES.EMPLOYEESCHEDULEID = A.EMPLOYEESCHEDULEID) " +
                     "WHERE ES.EMPLOYEEID =?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeID());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Attendance attendance = new Attendance(
                        resultSet.getInt("ATTENDANCEID"),
                        resultSet.getDate("ATTENDANCEDATE")!= null? resultSet.getDate("ATTENDANCEDATE").toString() : "none",
                        resultSet.getTime("CLOCKINTIME")!= null? new SimpleDateFormat("HH:mm:ss").format(resultSet.getTime("CLOCKINTIME")) : "none",
                        resultSet.getTime("CLOCKOUTTIME")!= null? new SimpleDateFormat("HH:mm:ss").format(resultSet.getTime("CLOCKOUTTIME")) : "none"
                    );
                    attendances.add(attendance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error fetching all attendances for employee: " + e.getMessage(), e);
        } 

        return attendances.toArray(new Attendance[0]);
    }
    
    @Override
    public Attendance getLatestAttendance(Employee employee) {
        this.conn = Connect.getConnection();
        Attendance attendance = null;
        String sql = "SELECT A.* " +
                     "FROM EMPLOYEESCHEDULE AS ES " +
                     "JOIN ATTENDANCE AS A ON (ES.EMPLOYEESCHEDULEID = A.EMPLOYEESCHEDULEID) " +
                     "WHERE ES.EMPLOYEEID =? " +
                     "ORDER BY A.ATTENDANCEDATE DESC " +
                     "FETCH FIRST 1 ROWS ONLY";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeID());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    attendance = new Attendance(
                        resultSet.getInt("ATTENDANCEID"),
                        resultSet.getDate("ATTENDANCEDATE")!= null? resultSet.getDate("ATTENDANCEDATE").toString() : "none",
                        resultSet.getTime("CLOCKINTIME")!= null? new SimpleDateFormat("HH:mm:ss").format(resultSet.getTime("CLOCKINTIME")) : "none",
                        resultSet.getTime("CLOCKOUTTIME")!= null? new SimpleDateFormat("HH:mm:ss").format(resultSet.getTime("CLOCKOUTTIME")) : "none"
                    );
                } else {
                    attendance = new Attendance(0, "none", "none", "none");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error fetching latest attendance for employee: " + e.getMessage(), e);
        } 

        return attendance;
    }
}
