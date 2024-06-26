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
public class Attendance {
    private int AttendanceID;
    private String AttendanceDate;
    private String ClockInTime;
    private String ClockOutTime;
    
    //default constructor
    public Attendance() {
    }
    //normal constructor

    public Attendance(int AttendanceID, String AttendanceDate, String ClockInTime, String ClockOutTime) {
        this.AttendanceID = AttendanceID;
        this.AttendanceDate = AttendanceDate;
        this.ClockInTime = ClockInTime;
        this.ClockOutTime = ClockOutTime;
    }
    //retriever

    public int getAttendanceID() {
        return AttendanceID;
    }

    public String getAttendanceDate() {
        return AttendanceDate;
    }

    public String getClockInTime() {
        return ClockInTime;
    }

    public String getClockOutTime() {
        return ClockOutTime;
    }
    //modifier

    public void setAttendanceID(int AttendanceID) {
        this.AttendanceID = AttendanceID;
    }

    public void setAttendanceDate(String AttendanceDate) {
        this.AttendanceDate = AttendanceDate;
    }

    public void setClockInTime(String ClockInTime) {
        this.ClockInTime = ClockInTime;
    }

    public void setClockOutTime(String ClockOutTime) {
        this.ClockOutTime = ClockOutTime;
    }
    //processer
    public boolean isBothNotNone() {
        return(!ClockInTime.equals("none") &&!ClockOutTime.equals("none"));
    }

    public boolean isBothNone() {
        return (ClockInTime.equals("none") && ClockOutTime.equals("none"));
    }

    public boolean isClockOutOnlyNone() {
        return(!ClockInTime.equals("none") && ClockOutTime.equals("none"));
    }
    
    
    
}
