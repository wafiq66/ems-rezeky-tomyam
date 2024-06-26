/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;
import com.ems.model.Branch;
/**
 *
 * @author user
 */
public interface BranchDAO {
    
    Branch getBranchById(int branchId);
    Branch[] getAllBranch();

}
