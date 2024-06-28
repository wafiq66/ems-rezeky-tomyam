/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;

import com.ems.connection.Connect;
import com.ems.model.Report;
import com.ems.model.RestaurantManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ReportDAOImpl implements ReportDAO{
     private Connection conn;

    public ReportDAOImpl(){
        this.conn = Connect.getConnection();
    }
    @Override
    public void RecordReport(Report report) {
        this.conn = Connect.getConnection();
        String sql = "INSERT INTO REPORT (MANAGERID, REPORTMONTH, REPORTYEAR, REPORTVERIFICATIONSTATUS) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, report.getManagerID());
            statement.setInt(2, report.getReportMonth());
            statement.setInt(3, report.getReportYear());
            statement.setBoolean(4, report.getReportVerificationStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate entry for primary key
                System.out.println("Report already exists. Redirecting...");
            } else {
                e.printStackTrace(); // Replace with logging framework in production
                throw new RuntimeException("Error recording report: " + e.getMessage(), e);
            }
        }
    }
    
    @Override
    public Report RetrieveReport(RestaurantManager manager, int month, int year) {
        this.conn = Connect.getConnection();
        String sql = "SELECT * FROM REPORT WHERE MANAGERID =? AND REPORTMONTH =? AND REPORTYEAR =?";
        Report report = null;
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, manager.getManagerID());
            statement.setInt(2, month);
            statement.setInt(3, year);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    report = new Report();
                    report.setManagerID(resultSet.getInt("MANAGERID"));
                    report.setReportMonth(resultSet.getInt("REPORTMONTH"));
                    report.setReportYear(resultSet.getInt("REPORTYEAR"));
                    report.setReportVerificationStatus(resultSet.getBoolean("REPORTVERIFICATIONSTATUS"));
                    // Add any other columns you want to retrieve
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving report: " + e.getMessage(), e);
        }
        return report;
    }
    
    @Override
    public Report RetrieveReport(Report report) {
        this.conn = Connect.getConnection();
        String sql = "SELECT * FROM REPORT WHERE MANAGERID =? AND REPORTMONTH =? AND REPORTYEAR =?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, report.getManagerID());
            statement.setInt(2, report.getReportMonth());
            statement.setInt(3, report.getReportYear());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    report.setManagerID(resultSet.getInt("MANAGERID"));
                    report.setReportMonth(resultSet.getInt("REPORTMONTH"));
                    report.setReportYear(resultSet.getInt("REPORTYEAR"));
                    report.setReportVerificationStatus(resultSet.getBoolean("REPORTVERIFICATIONSTATUS"));
                    // Add any other columns you want to retrieve
                } else {
                    return null; // Return null if no report is found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving report: " + e.getMessage(), e);
        }
        return report;
    }
}
