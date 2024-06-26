/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;

import com.ems.model.Branch;
import com.ems.connection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ems.model.Branch;
import com.ems.connection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAOImpl implements BranchDAO {

    private Connection conn;

    public BranchDAOImpl() {
        this.conn = Connect.getConnection();
    }

    @Override
    public Branch getBranchById(int branchId) {
        this.conn = Connect.getConnection();
        Branch branch = null;
        String sql = "SELECT * FROM BRANCH WHERE BRANCHID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, branchId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    branch = new Branch();
                    branch.setBranchID(resultSet.getInt("BRANCHID"));
                    branch.setBranchName(resultSet.getString("BRANCHNAME"));
                    branch.setBranchAddress(resultSet.getString("BRANCHADDRESS"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving branch by ID: " + e.getMessage(), e);
        } 
        return branch;
    }

    @Override
    public Branch[] getAllBranch() {
        this.conn = Connect.getConnection();
        List<Branch> branchList = new ArrayList<>();
        String sql = "SELECT * FROM BRANCH";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Branch branch = new Branch();
                branch.setBranchID(resultSet.getInt("BRANCHID"));
                branch.setBranchName(resultSet.getString("BRANCHNAME"));
                branch.setBranchAddress(resultSet.getString("BRANCHADDRESS"));
                branchList.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging framework in production
            throw new RuntimeException("Error retrieving all branches: " + e.getMessage(), e);
        } 
        return branchList.toArray(new Branch[0]);
    }

    
}


