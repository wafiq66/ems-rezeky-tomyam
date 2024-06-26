/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;
import com.ems.model.Schedule;
import com.ems.model.Employee;
import com.ems.model.EmployeeSchedule;
/**
 *
 * @author user
 */
public interface EmployeeScheduleDAO {
    
    void activeSchedule(Schedule schedule,Employee employee);
    void deactiveSchedule(Schedule schedule,Employee employee);
    void deleteEmployeeSchedule(Schedule schedule);
    EmployeeSchedule getScheduleEmployee(Schedule schedule,Employee employee);
    int[] getAllScheduleEmployee(Schedule schedule);
    EmployeeSchedule[] getScheduleStatus(Schedule schedule);
}
