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
public class EmployeeSchedule {
    private int EmployeeScheduleID;
    private boolean ScheduleActivationStatus;
    //default constructor
    public EmployeeSchedule() {
    }
    //normal constructor
    public EmployeeSchedule(int EmployeeScheduleID, boolean ScheduleActivationStatus) {
        this.EmployeeScheduleID = EmployeeScheduleID;
        this.ScheduleActivationStatus = ScheduleActivationStatus;
    }
    //retriever
    public int getEmployeeScheduleID() {
        return EmployeeScheduleID;
    }

    public boolean getScheduleActivationStatus() {
        return ScheduleActivationStatus;
    }
    //modifier
    public void setEmployeeScheduleID(int EmployeeScheduleID) {
        this.EmployeeScheduleID = EmployeeScheduleID;
    }

    public void setScheduleActivationStatus(boolean ScheduleActivationStatus) {
        this.ScheduleActivationStatus = ScheduleActivationStatus;
    }
    
    
}
