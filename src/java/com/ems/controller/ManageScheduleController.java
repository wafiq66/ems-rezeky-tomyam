/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.model.Schedule;
import com.ems.dao.ScheduleDAO;
import com.ems.dao.ScheduleDAOImpl;
import com.ems.model.EmployeeSchedule;
import com.ems.dao.EmployeeScheduleDAO;
import com.ems.dao.EmployeeScheduleDAOImpl;
import com.ems.model.RestaurantManager;
import com.ems.dao.ManagerDAO;
import com.ems.dao.ManagerDAOImpl;
import com.ems.model.Employee;
import com.ems.dao.EmployeeDAO;
import com.ems.dao.EmployeeDAOImpl;
import java.util.Arrays;
import javax.servlet.http.HttpSession;

public class ManageScheduleController extends HttpServlet {
    final ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
    final ManagerDAO managerDAO = new ManagerDAOImpl();
    final EmployeeScheduleDAO employeeScheduleDAO = new EmployeeScheduleDAOImpl();
    final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            if(action.equals("viewSpecificSchedule")){
                viewSpecificSchedule(request,response);
            }
            else if(action.equals("updateEmployee")){
                updateSchedule(request,response);
                System.out.println("masuk2");
            }
            else if(action.equals("deleteSchedule")){
                deleteSchedule(request,response);
            }
            else if(action.equals("createSchedule")){
                createSchedule(request,response);
            }
        }
    }
    protected void viewSpecificSchedule(HttpServletRequest request, HttpServletResponse response){
        try{
            int scheduleID = Integer.parseInt(request.getParameter("scheduleID")) ;
            HttpSession session = request.getSession();
            Schedule schedule = scheduleDAO.getScheduleByID(scheduleID);

            session.setAttribute("editingSchedule", schedule);
            request.getRequestDispatcher("specific_schedule.jsp").forward(request, response);
        }catch(Exception e){
            // Handle exception
        }
    }
    
    protected void updateSchedule(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession();
            Schedule schedule = (Schedule) session.getAttribute("editingSchedule");
            EmployeeSchedule[] employeeSchedule = employeeScheduleDAO.getScheduleStatus(schedule);
            RestaurantManager manager = (RestaurantManager) session.getAttribute("managerLog");
            //read value
            String date = request.getParameter("scheduleDate");
            String startShift = request.getParameter("startShift"); // Fix: changed from "scheduleDate" to "startShift"
            String endShift = request.getParameter("endShift");
            String[] employeeIDsStr = request.getParameterValues("list-employee");
            int[] employeeIDs = Arrays.stream(employeeIDsStr).mapToInt(Integer::parseInt).toArray();
            
            Employee[] selectedEmployee = employeeDAO.getAllEmployeeById(employeeIDs);
            Employee[] employeeBranch = employeeDAO.getAllEmployeeByBranch(managerDAO.getRestaurantManagerBranchId(manager));
            //deactive everything first
            for(Employee e: employeeBranch){
                employeeScheduleDAO.deactiveSchedule(schedule,e);
            }
            //reactive selected one
            for(Employee e: selectedEmployee){
                employeeScheduleDAO.activeSchedule(schedule,e);
            }
            
            String startShiftWithSecs = (startShift + ":00").substring(0, 8); // append ":00" and truncate to 8 characters
            String endShiftWithSecs = (endShift + ":00").substring(0, 8); // append ":00" and truncate to 8 characters
            
            System.out.println(date);
            System.out.println(startShiftWithSecs);
            System.out.println(endShiftWithSecs);


            schedule.setScheduleDate(date); // No need to format, use the original date string
            schedule.setStartShift(startShiftWithSecs); // No need to format, use the original startShift string
            schedule.setEndShift(endShiftWithSecs); // No need to format, use the original endShift string

            scheduleDAO.updateSchedule(schedule);
            String message = "Successfully Update!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("specific_schedule.jsp").forward(request, response);
            
        }catch(Exception e){
            // Handle exception
            System.out.println("noh");
            
        }
    }
    
    protected void deleteSchedule(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession();
            Schedule schedule = (Schedule) session.getAttribute("editingSchedule");
            
            employeeScheduleDAO.deleteEmployeeSchedule(schedule);
            scheduleDAO.deleteSchedule(schedule);
            
            session.setAttribute("editingSchedule",null);
            String message = "Successfully Deleted!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("manage_schedule_main.jsp").forward(request, response);
            
        }catch(Exception e){
            // Handle exception
            System.out.println("noh");
            
        }
    }
    
    protected void createSchedule(HttpServletRequest request, HttpServletResponse response){
        try{
            //call data
            HttpSession session = request.getSession();
            Schedule schedule = new Schedule();
            RestaurantManager manager = (RestaurantManager) session.getAttribute("managerLog");
            //read value
            String date = request.getParameter("scheduleDate");
            String startShift = request.getParameter("startShift"); // Fix: changed from "scheduleDate" to "startShift"
            String endShift = request.getParameter("endShift");
            
                //both of this to read array value of create schedule
            String[] employeeIDsStr = request.getParameterValues("list-employee");
            int[] employeeIDs = Arrays.stream(employeeIDsStr).mapToInt(Integer::parseInt).toArray();
            
            //get list of selected employee
            Employee[] selectedEmployee = employeeDAO.getAllEmployeeById(employeeIDs);
            //get list of employee by branch 
            Employee[] employeeBranch = employeeDAO.getAllEmployeeByBranch(managerDAO.getRestaurantManagerBranchId(manager));
            //to fix the format of the time
            String startShiftWithSecs = (startShift + ":00").substring(0, 8); // append ":00" and truncate to 8 characters
            String endShiftWithSecs = (endShift + ":00").substring(0, 8); // append ":00" and truncate to 8 characters
            //test
            System.out.println(date);
            System.out.println(startShiftWithSecs);
            System.out.println(endShiftWithSecs);

            //register the schedule into a class
            schedule.setScheduleDate(date); // No need to format, use the original date string
            schedule.setStartShift(startShiftWithSecs); // No need to format, use the original startShift string
            schedule.setEndShift(endShiftWithSecs); // No need to format, use the original endShift string
            
            schedule.setScheduleID(scheduleDAO.createSchedule(schedule));
            
            //deactive everything first
            for(Employee e: employeeBranch){
                employeeScheduleDAO.deactiveSchedule(schedule,e);
            }
            //reactive selected one
            for(Employee e: selectedEmployee){
                employeeScheduleDAO.activeSchedule(schedule,e);
            }
            
            String message = "Successfully Created!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("manage_schedule_main.jsp").forward(request, response);
            
        }catch(Exception e){
            // Handle exception
            System.out.println("noh");
            
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
