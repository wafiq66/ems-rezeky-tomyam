/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.controller;

import com.ems.model.Branch;
import com.ems.dao.BranchDAO;
import com.ems.dao.BranchDAOImpl;
import com.ems.model.RestaurantManager;
import com.ems.dao.ManagerDAO;
import com.ems.dao.ManagerDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class ViewVerifiedAttendanceController extends HttpServlet {
    final BranchDAO branchDAO = new BranchDAOImpl();
    final ManagerDAO managerDAO = new ManagerDAOImpl();
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
            if(action != null && action.equals("getVerifiedReport")) {
                System.out.println("Success");
                getVerifiedReport(request, response);
            } 
            else if(action != null && action.equals("getVerifiedReportOfficer")){
                getVerifiedReportOfficer(request,response);
            }
            else {
                out.println("Action parameter is missing or incorrect.");
            }
        }
    }

    
    protected void getVerifiedReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String fullDate = request.getParameter("month");
            if(fullDate != null) {
                String[] parts = fullDate.split("-");
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);

                request.setAttribute("month", month);
                request.setAttribute("year", year);
                request.setAttribute("fulldate", fullDate);
                System.out.println(month);
                System.out.println(year);
                
                request.getRequestDispatcher("verified_report.jsp").forward(request, response);
            } else {
                out.println("Month parameter is missing.");
            }
        }
    }
    
    protected void getVerifiedReportOfficer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            int branchID = Integer.parseInt(request.getParameter("branch")) ;
            Branch branch = branchDAO.getBranchById(branchID);
            RestaurantManager manager = managerDAO.getRestaurantManagerByBranch(branch);
            String fullDate = request.getParameter("month");
            request.setAttribute("manager",manager);
            if(fullDate != null) {
                String[] parts = fullDate.split("-");
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);

                request.setAttribute("month", month);
                request.setAttribute("year", year);
                request.setAttribute("fulldate", fullDate);
                System.out.println(month);
                System.out.println(year);
                
                request.getRequestDispatcher("officer_verified_report.jsp").forward(request, response);
            } else {
                out.println("Month parameter is missing.");
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
