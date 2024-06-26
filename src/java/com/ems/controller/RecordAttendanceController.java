/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.model.Employee;
import com.ems.model.Schedule;
import com.ems.model.EmployeeSchedule;
import com.ems.model.Attendance;

import com.ems.dao.ScheduleDAO;
import com.ems.dao.ScheduleDAOImpl;
import com.ems.dao.EmployeeScheduleDAO;
import com.ems.dao.EmployeeScheduleDAOImpl;
import com.ems.dao.AttendanceDAO;
import com.ems.dao.AttendanceDAOImpl;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author user
 */
public class RecordAttendanceController extends HttpServlet {
    final ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
    final EmployeeScheduleDAO employeeScheduleDAO = new EmployeeScheduleDAOImpl();
    final AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
    String message = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        if(action.equals("in")){
            clockIn(request,response);
        }else if(action.equals("out")){
            clockOut(request,response);
            }
    }
    
    protected void clockIn(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute("employeeLog");
            Schedule schedule = scheduleDAO.fetchLatestSchedule(employee);
            EmployeeSchedule employeeSchedule = employeeScheduleDAO.getScheduleEmployee(schedule, employee);
            attendanceDAO.recordInAttendance(employeeSchedule);
            message = "You have successfully clocked in!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("employee_attendance.jsp").forward(request, response);
        }catch(Exception e){
        }
    }
    protected void clockOut(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute("employeeLog");
            Schedule schedule = scheduleDAO.fetchLatestSchedule(employee);
            Attendance attendance = attendanceDAO.getLatestAttendance(employee);
            
            LocalTime currentTime = LocalTime.now();
            LocalTime endTime = LocalTime.parse(schedule.getEndShift());
            
            LocalDate currentDate = LocalDate.now();
            LocalDate scheduleDate = LocalDate.parse(attendance.getAttendanceDate());
            
            if(currentTime.isAfter(endTime)){
                attendance.setClockOutTime(schedule.getEndShift());
            }
            
            if(currentDate.isAfter(scheduleDate)){
                attendance.setClockOutTime(schedule.getEndShift());
            }
            
            attendanceDAO.recordOutAttendance(attendance);
            
            message = "You have successfully clocked out!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("employee_attendance.jsp").forward(request, response);
        }catch(Exception e){
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
