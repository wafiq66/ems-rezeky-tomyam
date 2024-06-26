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
public class Branch {
    
    private int BranchID;
    private String BranchName;
    private String BranchAddress;
    //default constructor
    public Branch() {
    }
    //normal constructor
    public Branch(int BranchID, String BranchName, String BranchAddress) {
        this.BranchID = BranchID;
        this.BranchName = BranchName;
        this.BranchAddress = BranchAddress;
    }
    
    //retriever
    public int getBranchID() {
        return BranchID;
    }

    public String getBranchName() {
        return BranchName;
    }

    public String getBranchAddress() {
        return BranchAddress;
    }
    //modifier
    public void setBranchID(int BranchID) {
        this.BranchID = BranchID;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    public void setBranchAddress(String BranchAddress) {
        this.BranchAddress = BranchAddress;
    }
    
    
}
