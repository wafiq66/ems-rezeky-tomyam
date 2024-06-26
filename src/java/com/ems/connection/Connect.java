/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class Connect {
    private static Connection conn;
    private Connect(){}
    
    public static Connection getConnection() {
        if (conn == null) {
            try{
            String driver = "org.apache.derby.jdbc.ClientDriver";
            String connectionString = "jdbc:derby://localhost:1527/EMPtest;create=true;user=emp;password=emp";
            Class.forName(driver);
            conn = DriverManager.getConnection(connectionString);
            System.out.println("Connection Sucessful");
        }catch(Exception e){
            System.out.println("Connection Failed");
        }
        }
        return conn;
    }
    
    
}
