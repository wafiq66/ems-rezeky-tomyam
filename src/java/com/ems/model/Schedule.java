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
public class Schedule {
    
    private int ScheduleID;
    private String ScheduleDate;
    private String StartShift;
    private String EndShift;
    //default constructor
    public Schedule() {
    }
    
    //normal constructor
    public Schedule(int ScheduleID, String ScheduleDate, String StartShift, String EndShift) {
        this.ScheduleID = ScheduleID;
        this.ScheduleDate = ScheduleDate;
        this.StartShift = StartShift;
        this.EndShift = EndShift;
    }
    //modifier
    public void setScheduleID(int ScheduleID) {
        this.ScheduleID = ScheduleID;
    }

    public void setScheduleDate(String ScheduleDate) {
        this.ScheduleDate = ScheduleDate;
    }

    public void setStartShift(String StartShift) {
        this.StartShift = StartShift;
    }

    public void setEndShift(String EndShift) {
        this.EndShift = EndShift;
    }
    //retriever
    public int getScheduleID() {
        return ScheduleID;
    }

    public String getScheduleDate() {
        return ScheduleDate;
    }

    public String getStartShift() {
        return StartShift;
    }

    public String getEndShift() {
        return EndShift;
    }
   
    
}
