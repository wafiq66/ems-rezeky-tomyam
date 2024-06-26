/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.model;

/**
 *
 * @author user
 */
public class RestaurantManager {
    private int ManagerID;
    private String ManagerPassword;
    private String ManagerName;
    private String ManagerPhoneNumber;
    private String ManagerEmail;
    private boolean ManagerStatus;
    //default constructor
    public RestaurantManager() {
    }
    //normal constructor
    public RestaurantManager(int ManagerID, String ManagerPassword, String ManagerName, String ManagerPhoneNumber, String ManagerEmail, boolean ManagerStatus) {
        this.ManagerID = ManagerID;
        this.ManagerPassword = ManagerPassword;
        this.ManagerName = ManagerName;
        this.ManagerPhoneNumber = ManagerPhoneNumber;
        this.ManagerEmail = ManagerEmail;
        this.ManagerStatus = ManagerStatus;
    }
    //retriever
    public int getManagerID() {
        return ManagerID;
    }
   
    public String getManagerPassword() {
        return ManagerPassword;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public String getManagerPhoneNumber() {
        return ManagerPhoneNumber;
    }

    public String getManagerEmail() {
        return ManagerEmail;
    }

    public boolean getManagerStatus() {
        return ManagerStatus;
    }
    //modifier
    public void setManagerID(int ManagerID) {
        this.ManagerID = ManagerID;
    }

    public void setManagerPassword(String ManagerPassword) {
        this.ManagerPassword = ManagerPassword;
    }

    public void setManagerName(String ManagerName) {
        this.ManagerName = ManagerName;
    }

    public void setManagerPhoneNumber(String ManagerPhoneNumber) {
        this.ManagerPhoneNumber = ManagerPhoneNumber;
    }

    public void setManagerEmail(String ManagerEmail) {
        this.ManagerEmail = ManagerEmail;
    }

    public void setManagerStatus(boolean ManagerStatus) {
        this.ManagerStatus = ManagerStatus;
    }
    
}
