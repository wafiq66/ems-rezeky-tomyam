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
import com.ems.dao.EmployeeDAO;
import com.ems.dao.EmployeeDAOImpl;
import com.ems.model.HROfficer;

/**
 *
 * @author user
 */
public class RegisterEmployeeController extends HttpServlet {
    //DAO class
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
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
            
            String passportNumberInput = request.getParameter("employeePassportNumber");
            String action = request.getParameter("action");
            if(action.equals("verifyPassportNumber")){
                verifyPassportNumber(request,response,passportNumberInput);
            }
            else if(action.equals("recordEmployee")){
                recordEmployee(request,response);
            }
        }
    }
    
    protected void verifyPassportNumber(HttpServletRequest request, HttpServletResponse response, String passportNumberInput){
            
        try{
            HttpSession session = request.getSession();
            Employee[] employees = null;
            employees = employeeDAO.getAllEmployee();
            boolean passportExist = false;
            
            for(Employee e:employees){
                if(e.getEmployeePassportNumber().equals(passportNumberInput)){
                    passportExist = true;
                }
            }
            if(passportExist){
                request.setAttribute("message","Passport Number Has Already Exist");
                request.getRequestDispatcher("verify_passport.jsp").forward(request, response);
            }
            else{
                session.setAttribute("newPassportNumber",passportNumberInput);
                request.setAttribute("message","");
                request.getRequestDispatcher("registrator_employee.jsp").forward(request, response);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
    
    protected void recordEmployee(HttpServletRequest request, HttpServletResponse response){
        
        try{
            HttpSession session = request.getSession();
            HROfficer officer = (HROfficer) session.getAttribute("officerLog");
            String condition = "disabled";
            Employee employee = new Employee();
            employee.setEmployeeName(request.getParameter("name"));
            employee.setEmployeePhoneNumber(request.getParameter("phoneNumber"));
            employee.setEmployeeEmail(request.getParameter("email"));
            employee.setEmployeeHourlyPay(Double.parseDouble(request.getParameter("hourlyPay")));
            employee.setEmployeePassportNumber(request.getParameter("passportNumber"));
            
            employeeDAO.addEmployee(employee, Integer.parseInt(request.getParameter("branch")),officer.getOfficerID());
            
            
            Employee registeredEmployee = null;
            registeredEmployee = employeeDAO.getEmployeeByPassport(request.getParameter("passportNumber"));
            
            request.setAttribute("condition",condition);
            request.setAttribute("registeredEmployee",registeredEmployee);
            request.setAttribute("registerNotice","New Employee Has Been Registered");
            request.getRequestDispatcher("registrator_employee.jsp").forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
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
