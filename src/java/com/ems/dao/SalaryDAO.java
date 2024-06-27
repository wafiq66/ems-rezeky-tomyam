/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;

import com.ems.model.Salary;
import com.ems.model.Employee;
/**
 *
 * @author user
 */
public interface SalaryDAO {
    
    Salary[] getUncalculatedEmployeeSalary(int month,int year);
    Salary getUncalculatedEmployeeSalary(Employee employee,int month,int year);
    Salary[] getUncalculatedEmployeeSalary(Employee employee);
    
    void recordSalary(Salary salary);
    void updateSalary(Salary salary);
    
    Salary[] getCalculatedEmployeeSalary(int month, int year);
    Salary getCalculatedEmployeeSalary(Employee employee, int month, int year);
    Salary[] getCalculatedEmployeeSalary(Employee employee);
}
