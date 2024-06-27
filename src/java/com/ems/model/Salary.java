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
public class Salary {
    
    private int EmployeeID;
    private int SalaryMonth;
    private int SalaryYear;
    private int TotalHoursWorked;
    private double SalaryAmount;

    public Salary() {
    }

    public Salary(int EmployeeID, int SalaryMonth, int SalaryYear, int TotalHoursWorked, double SalaryAmount) {
        this.EmployeeID = EmployeeID;
        this.SalaryMonth = SalaryMonth;
        this.SalaryYear = SalaryYear;
        this.TotalHoursWorked = TotalHoursWorked;
        this.SalaryAmount = SalaryAmount;
    }
    //setter
    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public void setSalaryMonth(int SalaryMonth) {
        this.SalaryMonth = SalaryMonth;
    }

    public void setSalaryYear(int SalaryYear) {
        this.SalaryYear = SalaryYear;
    }

    public void setTotalHoursWorked(int TotalHoursWorked) {
        this.TotalHoursWorked = TotalHoursWorked;
    }

    public void setSalaryAmount(double SalaryAmount) {
        this.SalaryAmount = SalaryAmount;
    }
    //getter
    public int getEmployeeID() {
        return EmployeeID;
    }

    public int getSalaryMonth() {
        return SalaryMonth;
    }

    public int getSalaryYear() {
        return SalaryYear;
    }

    public int getTotalHoursWorked() {
        return TotalHoursWorked;
    }

    public double getSalaryAmount() {
        return SalaryAmount;
    }
    
}
