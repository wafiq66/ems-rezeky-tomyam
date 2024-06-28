/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.controller;

import com.ems.model.RestaurantManager;
import com.ems.dao.ManagerDAO;
import com.ems.dao.ManagerDAOImpl;
import com.ems.model.Report;
import com.ems.dao.ReportDAO;
import com.ems.dao.ReportDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class ManageReportController extends HttpServlet {
    final ManagerDAO managerDAO = new ManagerDAOImpl();
    final ReportDAO reportDAO = new ReportDAOImpl();
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
            if(action.equals("getSpecificReport")){
                getSpecificReport(request,response);
            }
            else if(action.equals("submitReport")){
                submitReport(request,response);
            }
        }
    }
    
    protected void getSpecificReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        
            String fullDate = request.getParameter("month");
            String[] parts = fullDate.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            
            request.setAttribute("month",month);
            request.setAttribute("year",year);
            request.setAttribute("fulldate",fullDate);
            System.out.println(month);
            System.out.println(year);
            request.getRequestDispatcher("report_update.jsp").forward(request, response);
        }catch(Exception e){
        
        }
    } 
    
    protected void submitReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String fullDate = request.getParameter("month");
            String[] parts = fullDate.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            
            HttpSession session = request.getSession();
            int submitYear = Integer.parseInt(request.getParameter("yearSubmit"));
            int submitMonth = Integer.parseInt(request.getParameter("monthSubmit"));
            RestaurantManager manager = (RestaurantManager)session.getAttribute("managerLog");
            String message = null;
            Report report = new Report(manager.getManagerID(),submitMonth,submitYear,true);
            report = reportDAO.RetrieveReport(report);
            if (report == null) {
                report = new Report(manager.getManagerID(),submitMonth,submitYear,true);
                reportDAO.RecordReport(report);
                message = "Report Submitted";
                System.out.println("Report not found");
            }else{
                message = "Report already submitted";
            }
            request.setAttribute("month",month);
            request.setAttribute("year",year);
            request.setAttribute("fulldate",fullDate);
            request.setAttribute("message",message);
            request.getRequestDispatcher("report_update.jsp").forward(request, response);
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
