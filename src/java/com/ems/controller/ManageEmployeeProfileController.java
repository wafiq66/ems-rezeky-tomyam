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
public class ManageEmployeeProfileController extends HttpServlet {
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
            //for page specific_employee.jsp
            String action = request.getParameter("action");
            
            //for page update_employee.jsp
            
            
            if(action.equals("viewEmployee")){
                viewEmployee(request,response);
            }
            else if(action.equals("Update")){
                updateEmployee(request,response);
            }
            else if(action.equals("updateProfile")){
                updateProfile(request,response);
            }
        }
    }
    
    protected void viewEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            HttpSession session = request.getSession();
            Employee employee = null;
            employee = employeeDAO.getEmployeeById(employeeID);
            
            session.setAttribute("editingEmployee", employee);
            request.getRequestDispatcher("specific_employee.jsp").forward(request, response);
        }catch(Exception e){
        
        }
    }
    protected void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            String employeePassword = request.getParameter("employeePassword");
            String employeeName = request.getParameter("employeeName");
            String employeePassportNumber = request.getParameter("employeePassportNumber");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            double hourlyPay = Double.parseDouble(request.getParameter("hourlyPay"));
            
            int branchID = Integer.parseInt(request.getParameter("branch"));

            Employee employee = new Employee(employeeID,employeePassword,employeeName,phone,email,employeePassportNumber,status,hourlyPay);
            
            String userPassword = request.getParameter("officerPassword");
            HttpSession session = request.getSession();
            HROfficer officer = (HROfficer) session.getAttribute("officerLog");
            if(officer.getOfficerPassword().equals(userPassword)){
                employeeDAO.updateEmployee(employee, branchID);
                String message="Successfully Updated";
                request.setAttribute("message", message);
                request.getRequestDispatcher("update_employee.jsp").forward(request, response);
            }else{
                String message="Invalid Password";
                request.setAttribute("message", message);
                request.getRequestDispatcher("update_employee.jsp").forward(request, response);
            }
        }catch(Exception e){
        
        }
    }
    
    protected void updateProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            System.out.println("SAMPAI DOK");
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute("employeeLog");
            
            String userPassword = request.getParameter("employeeCurrentPassword");
            int branchID = Integer.parseInt(request.getParameter("branchID"));
            //data to be change
            String newPassword = request.getParameter("password");
            String confirmPassword = request.getParameter("passwordConfirm");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            
            if(!newPassword.equals(confirmPassword)){
                String message="New password entered is not matching";
                request.setAttribute("message", message);
                request.getRequestDispatcher("update_profile.jsp").forward(request, response);
            }
            
            
            else if(employee.getEmployeePassword().equals(userPassword)){
                
                employee.setEmployeePassword(newPassword);
                employee.setEmployeePhoneNumber(phone);
                employee.setEmployeeEmail(email);
                employeeDAO.updateEmployee(employee, branchID);
                String message="Successfully Updated";
                request.setAttribute("message", message);
                request.getRequestDispatcher("update_profile.jsp").forward(request, response);
            }else{
                String message="Invalid Password";
                request.setAttribute("message", message);
                request.getRequestDispatcher("update_profile.jsp").forward(request, response);
            }
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
