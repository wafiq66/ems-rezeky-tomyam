/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;
import com.ems.model.EmployeeSchedule;
import com.ems.model.Employee;
import com.ems.model.Attendance;
import com.ems.model.Branch;
import com.ems.model.Report;
/**
 *
 * @author user
 */
public interface AttendanceDAO {
    
    void recordInAttendance(EmployeeSchedule employeeSchedule);
    void recordOutAttendance(Attendance attendance);
    Attendance[] getAllAttendances(Employee employee);
    Attendance[] getAllAttendancesByBranch(Branch branch);
    Attendance[] getAllAttendancesByBranch(Branch branch,int month,int year);
    Attendance[] getAllAttendancesByReport(Report report);
    Attendance getLatestAttendance(Employee employee);
    
}
