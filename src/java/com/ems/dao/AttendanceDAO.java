/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;
import com.ems.model.EmployeeSchedule;
import com.ems.model.Employee;
import com.ems.model.Attendance;
/**
 *
 * @author user
 */
public interface AttendanceDAO {
    
    void recordInAttendance(EmployeeSchedule employeeSchedule);
    void recordOutAttendance(Attendance attendance);
    Attendance[] getAllAttendances(Employee employee);
    Attendance getLatestAttendance(Employee employee);
}
