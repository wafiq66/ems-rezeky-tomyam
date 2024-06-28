<%@page import="com.ems.model.Report"%>
<%@page import="com.ems.dao.ReportDAOImpl"%>
<%@page import="com.ems.dao.ReportDAO"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.ems.model.Attendance"%>
<%@page import="com.ems.model.Branch"%>
<%@page import="com.ems.dao.AttendanceDAO"%>
<%@page import="com.ems.dao.AttendanceDAOImpl"%>
<%@page import="com.ems.dao.BranchDAO"%>
<%@page import="com.ems.dao.BranchDAOImpl"%>
<%@page import="com.ems.model.RestaurantManager"%>
<%@page import="com.ems.dao.ManagerDAO"%>
<%@page import="com.ems.dao.ManagerDAOImpl"%>
<%@page import="com.ems.model.Employee"%>
<%@page import="com.ems.dao.EmployeeDAO"%>
<%@page import="com.ems.dao.EmployeeDAOImpl"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Salary Information</title>
</head>
<body>
    <%
    final ReportDAO reportDAO = new ReportDAOImpl();
    final AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
    final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    
    Integer monthObj = (Integer) request.getAttribute("month");
    Integer yearObj = (Integer) request.getAttribute("year");
    
    int month = (monthObj != null) ? monthObj.intValue() : 0;
    int year = (yearObj != null) ? yearObj.intValue() : 0;
    
    RestaurantManager manager = (RestaurantManager) session.getAttribute("managerLog");
    Report report = null;
    if (manager != null) {
        report = reportDAO.RetrieveReport(manager, month, year);
    }
    
    Employee employee;
    Attendance[] attendances = null;
    
    if (report != null) {
        attendances = attendanceDAO.getAllAttendancesByReport(report);
    }
    %>
    <header>
        <h2>Report Information</h2>
        <nav>
            <ul>
                <li><a href="main_manager.jsp">Home</a></li>
                <li><a href="report_update.jsp">Report</a></li>
                <li><a href="manage_schedule_main.jsp">Schedule</a></li>
                <li><a href="welcome.html">Log Out</a></li>
            </ul>
        </nav>
    </header>
    <a href="report_update.jsp">Back</a><br>
    <form action="verified_report.view" method="post">
        <label for="month">Month:</label>
        <input type="month" name="month" value="${fulldate}" required>
        <input type="hidden" name="action" value="getVerifiedReport">
        <input type="submit" value="Search"><br>
    </form>
    <br>
    <table border="1">
        <tr>
            <th>Employee ID</th>
            <th>Employee Name</th>
            <th>Attendance ID</th>
            <th>Attendance Date</th>
            <th>Clock In Time</th>
            <th>Clock Out Time</th>
        </tr>
        <% if (attendances == null || attendances.length == 0) { %>
            <tr>
                <td colspan="6" style="text-align:center;"><em>No Record Has Been Found</em></td>
            </tr>
        <% } else {
            for (Attendance attendance : attendances) {
                employee = employeeDAO.getEmployeeByAttendance(attendance);
                if (employee != null) {
        %>
        <tr>
            <td><%= employee.getEmployeeID() %></td>
            <td><%= employee.getEmployeeName() %></td>
            <td><%= attendance.getAttendanceID() %></td>
            <td><%= attendance.getAttendanceDate() %></td>
            <td><%= attendance.getClockInTime() %></td>
            <td><%= attendance.getClockOutTime() %></td>
        </tr>
        <%         }
            }
        } %>
    </table>
</body>
</html>
