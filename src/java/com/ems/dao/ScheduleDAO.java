/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;
import com.ems.model.Schedule;
import com.ems.model.Employee;
/**
 *
 * @author user
 */
public interface ScheduleDAO {
    
    Schedule getScheduleByID(int scheduleID);
    int createSchedule(Schedule schedule);
    void updateSchedule(Schedule schedule);
    void deleteSchedule(Schedule schedule);
    Schedule fetchLatestSchedule(Employee employee);
    Schedule[] getAllScheduleByBranch(int branchID);
    
}
