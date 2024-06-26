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
public class Employee {
    private int EmployeeID;
    private String EmployeePassword;
    private String EmployeeName;
    private String EmployeePhoneNumber;
    private String EmployeeEmail;
    private String EmployeePassportNumber;
    private boolean EmployeeStatus;
    private double EmployeeHourlyPay;
    
    public Employee(){
        EmployeePassword = null;
        EmployeeName = null;
        EmployeePhoneNumber = null;
        EmployeeEmail = null;
        EmployeePassportNumber = null;
        EmployeeStatus = true;
        EmployeeHourlyPay = 0;
    }

    public Employee(int EmployeeID, String EmployeePassword, String EmployeeName, String EmployeePhoneNumber, String EmployeeEmail, String EmployeePassportNumber, boolean EmployeeStatus, double EmployeeHourlyPay) {
        this.EmployeeID = EmployeeID;
        this.EmployeePassword = EmployeePassword;
        this.EmployeeName = EmployeeName;
        this.EmployeePhoneNumber = EmployeePhoneNumber;
        this.EmployeeEmail = EmployeeEmail;
        this.EmployeePassportNumber = EmployeePassportNumber;
        this.EmployeeStatus = EmployeeStatus;
        this.EmployeeHourlyPay = EmployeeHourlyPay;
    }
    
    
    //retriever
    public int getEmployeeID(){
        return EmployeeID;
    }
    
    public String getEmployeePassword(){
        return this.EmployeePassword;
    }
    
    public String getEmployeeName(){
        return this.EmployeeName;
    }
    
    public String getPassportNumber(){
        return this.EmployeePassportNumber;
    }
    
    public String getEmployeeEmail(){
        return EmployeeEmail;
    }
    
    public String getEmployeePhoneNumber(){
        return EmployeePhoneNumber;
    }
    
    public boolean getEmployeeStatus(){
        return EmployeeStatus;
    }
    
    public String getEmployeePassportNumber(){
        return EmployeePassportNumber;
    }
    
    public double getEmployeeHourlyPay (){
        return EmployeeHourlyPay ;
    }
    //modifier
    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public void setEmployeePassword(String EmployeePassword) {
        this.EmployeePassword = EmployeePassword;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    public void setEmployeePhoneNumber(String EmployeePhoneNumber) {
        this.EmployeePhoneNumber = EmployeePhoneNumber;
    }

    public void setEmployeeEmail(String EmployeeEmail) {
        this.EmployeeEmail = EmployeeEmail;
    }

    public void setEmployeePassportNumber(String EmployeePassportNumber) {
        this.EmployeePassportNumber = EmployeePassportNumber;
    }

    public void setEmployeeStatus(boolean EmployeeStatus) {
        this.EmployeeStatus = EmployeeStatus;
    }

    public void setEmployeeHourlyPay(double EmployeeHourlyPay) {
        this.EmployeeHourlyPay = EmployeeHourlyPay;
    }
    //methods
    public boolean employeeSelectedInSchedule(int[] array, int number) {
    for (int i : array) {
        if (i == number) {
            return true;
        }
    }
    return false;
}

    
}
