
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.controller;

import com.ems.dao.EmployeeDAO;
import com.ems.dao.EmployeeDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.model.Salary;
import com.ems.dao.SalaryDAO;
import com.ems.dao.SalaryDAOImpl;
import com.ems.model.Employee;
import java.time.LocalDate;
/**
 *
 * @author user
 */
public class ViewEmployeeSalaryController extends HttpServlet {
    final SalaryDAO salaryDAO = new SalaryDAOImpl();
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
            
            String action = request.getParameter("action");
            if(action.equals("byMonth")){
                viewByMonth(request,response);
            }
            
        }
    }
    protected void viewByMonth(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    calculateSalaryPastMonth();
           // Get the value of the "month" parameter from the request
    String month = request.getParameter("month");
    int employeeID = Integer.parseInt(request.getParameter("employeeID"));
    boolean filterPass = true;
    
    // Split the string into year and month
    String[] parts = month.split("-");

    // Convert the year and month parts into integers
    int year = Integer.parseInt(parts[0]);
    int monthInt = Integer.parseInt(parts[1]);

    // Print the results to verify
    System.out.println("Year: " + year);
    System.out.println("Month: " + monthInt);
    //to get the local current time
    LocalDate currentDate = LocalDate.now();

    // Extract month and year as integers
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();

    // Print the current month and year
    System.out.println("Current Month (integer): " + currentMonth);
    System.out.println("Current Year (integer): " + currentYear);
    
    Salary[] salaries = salaryDAO.getCalculatedEmployeeSalary(monthInt,year);
    
    Boolean exist = salaries.length !=0;
    System.out.println(exist);
    
    if(exist){
        if(!(currentYear > year || (currentYear == year && currentMonth > monthInt))){
            salaries = salaryDAO.getUncalculatedEmployeeSalary(monthInt,year);
            for(Salary s:salaries){
                salaryDAO.updateSalary(s);
                System.out.println("Salary: RM" + s.getSalaryAmount());
            }
            System.out.println("Successfully Updated!");
        } 
    }
    else{
        salaries = salaryDAO.getUncalculatedEmployeeSalary(monthInt,year);
        System.out.println(salaries.length);
        Boolean ableToCalculate = salaries.length != 0;
        if(ableToCalculate){
            for(Salary s:salaries){
            salaryDAO.recordSalary(s);
            
            System.out.println("Successfully Calculate And Recorded!");
        }
        }else{
            System.out.println("Not able to calculate!");
            filterPass = false;
        }
        
    }
    
    Salary empSalary = null;
    
    if(employeeID>0 && filterPass){
        Employee targetEmp = employeeDAO.getEmployeeById(employeeID);
        empSalary = salaryDAO.getCalculatedEmployeeSalary(targetEmp, monthInt, year);
    }
    
    
    request.setAttribute("targetSalary", empSalary);
    request.setAttribute("month",monthInt);
    request.setAttribute("year",year);
    request.getRequestDispatcher("officer_salary_main.jsp").forward(request, response); 
    
    }
   
    protected void calculateSalaryPastMonth()
            throws ServletException, IOException {
    
    //to get the local current time
    LocalDate currentDate = LocalDate.now();

    // Extract month and year as integers
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();
    if(currentMonth == 1){
        currentMonth = 12;
        currentYear -=1;
    }
    else{
        currentMonth -=1;
    }
    // Print the current month and year
    System.out.println("Past Month (integer): " + currentMonth);
    System.out.println("Past Year (integer): " + currentYear);
    
    Salary[] listSalary = salaryDAO.getCalculatedEmployeeSalary(currentMonth,currentYear);
    if(listSalary.length > 0){
        listSalary = salaryDAO.getUncalculatedEmployeeSalary(currentMonth, currentYear);
        for(Salary s:listSalary){
            salaryDAO.updateSalary(s);
            System.out.println("Done update previous month salary");
        }
    }
    else{
        listSalary = salaryDAO.getUncalculatedEmployeeSalary(currentMonth, currentYear);
        for(Salary s:listSalary){
            salaryDAO.recordSalary(s);
            System.out.println("Done calculate previous month salary");
        }
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
