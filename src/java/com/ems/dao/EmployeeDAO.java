/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ems.dao;
import com.ems.model.Employee;
import com.ems.model.Schedule;

/**
 *
 * @author user
 */
public interface EmployeeDAO {
    void addEmployee(Employee employee,int branchID, int officerID);
    void updateEmployee(Employee employee,int branchID);
    int getEmployeeBranchID(Employee employee);
    Employee getEmployeeByPassport(String passport);
    Employee getEmployeeById(int employeeID);
    Employee[] getAllEmployee();
    Employee[] getAllEmployeeById(int[] employeeID);
    Employee[] getAllEmployeeByBranch(int branchID); 
}
