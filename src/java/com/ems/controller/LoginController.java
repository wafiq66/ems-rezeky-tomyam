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
import com.ems.model.HROfficer;
import com.ems.model.RestaurantManager;

import com.ems.dao.EmployeeDAO;
import com.ems.dao.ManagerDAO;
import com.ems.dao.OfficerDAO;

import com.ems.dao.EmployeeDAOImpl;
import com.ems.dao.ManagerDAOImpl;
import com.ems.dao.OfficerDAOImpl;

/**
 *
 * @author user
 */
public class LoginController extends HttpServlet {
    final private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    final private ManagerDAO managerDAO = new ManagerDAOImpl();
    final private OfficerDAO officerDAO = new OfficerDAOImpl();
    
    private String message = null;
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
        HttpSession session = request.getSession();
        int accountID = Integer.parseInt(request.getParameter("username"));
        String accountPassword = request.getParameter("password");
        String action = request.getParameter("action");
        
        if(action.equals("employeeLogin")){
            System.out.println("Success");
            loginEmployee(session,request,response,accountID,accountPassword);
        }
        else if(action.equals("managerLogin")){
            loginManager(session,request,response,accountID,accountPassword);
        }
        else if(action.equals("officerLogin")){
            loginOfficer(session,request,response,accountID,accountPassword);
        }
    }
    
    protected void loginEmployee(HttpSession session, HttpServletRequest request, HttpServletResponse response, int employeeID, String employeePassword) {
    try {
        Employee employee = employeeDAO.getEmployeeById(employeeID);

        if (employee == null) {
            String message = "Account Does Not Exist";
            request.setAttribute("message", message);
            request.getRequestDispatcher("login_employee.jsp").forward(request, response);
        } else {
            if (employee.getEmployeePassword().equals(employeePassword)) {
                if (employee.getEmployeeStatus()) {
                    session.setAttribute("employeeLog", employee);
                    request.getRequestDispatcher("main_employee.jsp").forward(request, response);
                } else {
                    String message = "Account Is Not Active";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("login_employee.jsp").forward(request, response);
                }
            } else {
                String message = "Wrong Password";
                request.setAttribute("message", message);
                request.getRequestDispatcher("login_employee.jsp").forward(request, response);
            }
        }
    } catch (Exception e) {
        // Properly handle exceptions, log them, or display an error message
        e.printStackTrace(); // Print stack trace for debugging purposes
        System.out.println("An error occurred while processing your request.");
    }
}

    protected void loginManager(HttpSession session,HttpServletRequest request, HttpServletResponse response,int managerID,String managerPassword){
       try {
    RestaurantManager manager = managerDAO.getRestaurantManagerById(managerID);

    if (manager == null) {
        String message = "Account Does Not Exist";
        request.setAttribute("message", message);
        request.getRequestDispatcher("login_manager.jsp").forward(request, response);
    } else {
        if (manager.getManagerPassword().equals(managerPassword)) {
            if (manager.getManagerStatus()) {
                session.setAttribute("managerLog", manager);
                request.getRequestDispatcher("main_manager.jsp").forward(request, response);
            } else {
                String message = "Account Is Not Active";
                request.setAttribute("message", message);
                request.getRequestDispatcher("login_manager.jsp").forward(request, response);
            }
        } else {
            String message = "Wrong Password";
            request.setAttribute("message", message);
            request.getRequestDispatcher("login_manager.jsp").forward(request, response);
        }
    }
    } catch (Exception e) {
        // Properly handle exceptions, log them, or display an error message
        e.printStackTrace(); // Print stack trace for debugging purposes
        System.out.println("An error occurred while processing your request.");
    } 
    }
    protected void loginOfficer(HttpSession session,HttpServletRequest request, HttpServletResponse response,int officerID,String officerPassword){
        try {
            HROfficer officer = officerDAO.getHROfficerById(officerID);

            if (officer == null) {
                message = "Account Does Not Exist";
                request.setAttribute("message", message);
                request.getRequestDispatcher("login_officer.jsp").forward(request, response);
            } else {
                if (officer.getOfficerPassword().equals(officerPassword)) {
                    session.setAttribute("officerLog", officer);
                    request.getRequestDispatcher("main_officer.jsp").forward(request, response);
                } else {
                    message = "Wrong Password";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("login_officer.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while processing your request.");
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
