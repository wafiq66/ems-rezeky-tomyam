/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.controller;

import com.ems.dao.AttendanceDAO;
import com.ems.dao.AttendanceDAOImpl;
import com.ems.model.Attendance;
import com.ems.model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class ViewAttendanceRecord extends HttpServlet {
    final AttendanceDAO attendanceDAO= new AttendanceDAOImpl();
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
            viewAttendance(request,response);
        }
    }
    protected void viewAttendance(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
           HttpSession session = request.getSession();
            String month = request.getParameter("month");
            Employee employee = (Employee) session.getAttribute("employeeLog");
            Attendance[] attendance = attendanceDAO.getAllAttendances(employee);
            List<Integer> attendanceIDs = new ArrayList<>();
            
            for (Attendance a : attendance) {
                if (a.isMonthMatched(month)) {
                    attendanceIDs.add(a.getAttendanceID());
                }
            }

            int[] attendanceIDArray = new int[attendanceIDs.size()];
            for (int i = 0; i < attendanceIDs.size(); i++) {
                attendanceIDArray[i] = attendanceIDs.get(i);
            }
            request.setAttribute("attendanceID",attendanceIDArray);
            request.getRequestDispatcher("attandance_history.jsp").forward(request, response); 
            
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
