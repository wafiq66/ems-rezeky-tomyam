/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;

import com.ems.connection.Connect;
import com.ems.model.RestaurantManager;
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
public class ManagerDAOImpl implements ManagerDAO{
    private Connection conn;

    public ManagerDAOImpl(){
        this.conn = Connect.getConnection();
    }
    @Override
    public int getRestaurantManagerBranchId(RestaurantManager manager) {
        this.conn = Connect.getConnection();
        int branchID = 0; // Default branch ID, or whatever makes sense in your context
        String sql = "SELECT BRANCHID FROM RESTAURANTMANAGER WHERE MANAGERID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, manager.getManagerID());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    branchID = resultSet.getInt("BRANCHID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving branch ID for manager: " + e.getMessage(), e);
        } 
        return branchID;
    }
    
    @Override
    public RestaurantManager getRestaurantManagerById(int RestaurantManagerID) {
        this.conn = Connect.getConnection();
        RestaurantManager manager = null;
        String sql = "SELECT * FROM RESTAURANTMANAGER WHERE MANAGERID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, RestaurantManagerID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    manager = new RestaurantManager();
                    manager.setManagerID(resultSet.getInt("MANAGERID"));
                    manager.setManagerPassword(resultSet.getString("MANAGERPASSWORD"));
                    manager.setManagerName(resultSet.getString("MANAGERNAME"));
                    manager.setManagerPhoneNumber(resultSet.getString("MANAGERPHONENUMBER"));
                    manager.setManagerEmail(resultSet.getString("MANAGEREMAIL"));
                    manager.setManagerStatus(resultSet.getBoolean("MANAGERSTATUS"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving manager by ID: " + e.getMessage(), e);
        } 
        return manager;
    }
    
    @Override
public RestaurantManager[] getRestaurantManagerByBranchId(int BranchID) {
    this.conn = Connect.getConnection();
    List<RestaurantManager> managerList = new ArrayList<>();
    String sql = "SELECT * FROM RESTAURANTMANAGER WHERE BRANCHID = ?";
    
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, BranchID);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                RestaurantManager manager = new RestaurantManager();
                manager.setManagerID(resultSet.getInt("MANAGERID"));
                manager.setManagerPassword(resultSet.getString("MANAGERPASSWORD"));
                manager.setManagerName(resultSet.getString("MANAGERNAME"));
                manager.setManagerPhoneNumber(resultSet.getString("MANAGERPHONENUMBER"));
                manager.setManagerEmail(resultSet.getString("MANAGEREMAIL"));
                manager.setManagerStatus(resultSet.getBoolean("MANAGERSTATUS"));
                managerList.add(manager);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Replace with logging framework in production
        throw new RuntimeException("Error retrieving managers by branch ID: " + e.getMessage(), e);
    } 
    
    return managerList.toArray(new RestaurantManager[0]);
}

    
    @Override
    public RestaurantManager[] getAllRestaurantManager() {
        this.conn = Connect.getConnection();
        List<RestaurantManager> managerList = new ArrayList<>();
        String sql = "SELECT * FROM RESTAURANTMANAGER";
        
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                RestaurantManager manager = new RestaurantManager();
                manager.setManagerID(resultSet.getInt("MANAGERID"));
                manager.setManagerPassword(resultSet.getString("MANAGERPASSWORD"));
                manager.setManagerName(resultSet.getString("MANAGERNAME"));
                manager.setManagerPhoneNumber(resultSet.getString("MANAGERPHONENUMBER"));
                manager.setManagerEmail(resultSet.getString("MANAGEREMAIL"));
                manager.setManagerStatus(resultSet.getBoolean("MANAGERSTATUS"));
                managerList.add(manager);
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving all managers: " + e.getMessage(), e);
        } 
        
        return managerList.toArray(new RestaurantManager[0]);
    }
}
