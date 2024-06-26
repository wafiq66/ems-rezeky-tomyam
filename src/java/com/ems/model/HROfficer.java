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
public class HROfficer {
    private int OfficerID;
    private String OfficerPassword;
    private String OfficerName;
    private String OfficerPhoneNumber;
    private String OfficerEmail;
    
    //default constructor
    public HROfficer() {
    }
    //normal constructor
    public HROfficer(int OfficerID, String OfficerPassword, String OfficerName, String OfficerPhoneNumber, String OfficerEmail) {
        this.OfficerID = OfficerID;
        this.OfficerPassword = OfficerPassword;
        this.OfficerName = OfficerName;
        this.OfficerPhoneNumber = OfficerPhoneNumber;
        this.OfficerEmail = OfficerEmail;
    }
    //modifier
    public void setOfficerID(int OfficerID) {
        this.OfficerID = OfficerID;
    }

    public void setOfficerPassword(String OfficerPassword) {
        this.OfficerPassword = OfficerPassword;
    }

    public void setOfficerName(String OfficerName) {
        this.OfficerName = OfficerName;
    }

    public void setOfficerPhoneNumber(String OfficerPhoneNumber) {
        this.OfficerPhoneNumber = OfficerPhoneNumber;
    }

    public void setOfficerEmail(String OfficerEmail) {
        this.OfficerEmail = OfficerEmail;
    }

    public int getOfficerID() {
        return OfficerID;
    }
    //retriever
    public String getOfficerPassword() {
        return OfficerPassword;
    }

    public String getOfficerName() {
        return OfficerName;
    }

    public String getOfficerPhoneNumber() {
        return OfficerPhoneNumber;
    }

    public String getOfficerEmail() {
        return OfficerEmail;
    }
    
}
