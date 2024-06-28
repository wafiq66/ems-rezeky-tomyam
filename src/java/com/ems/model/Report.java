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
public class Report {
    private int ManagerID;
    private int ReportMonth;
    private int ReportYear;
    private boolean ReportVerificationStatus;
    //default constructor
    public Report() {
    }
    //normal constructor
    public Report(int ManagerID, int ReportMonth, int ReportYear, boolean ReportVerificationStatus) {
        this.ManagerID = ManagerID;
        this.ReportMonth = ReportMonth;
        this.ReportYear = ReportYear;
        this.ReportVerificationStatus = ReportVerificationStatus;
    }
    //retriever
    public int getManagerID() {
        return ManagerID;
    }

    public int getReportMonth() {
        return ReportMonth;
    }

    public int getReportYear() {
        return ReportYear;
    }

    public boolean getReportVerificationStatus() {
        return ReportVerificationStatus;
    }
    //modifier
    public void setManagerID(int ManagerID) {
        this.ManagerID = ManagerID;
    }

    public void setReportMonth(int ReportMonth) {
        this.ReportMonth = ReportMonth;
    }

    public void setReportYear(int ReportYear) {
        this.ReportYear = ReportYear;
    }

    public void setReportVerificationStatus(boolean ReportVerificationStatus) {
        this.ReportVerificationStatus = ReportVerificationStatus;
    }
    
    
    
}
