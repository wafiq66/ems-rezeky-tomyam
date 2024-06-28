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
    final BranchDAO branchDAO = new BranchDAOImpl();
    
    Branch[] branches = branchDAO.getAllBranch();
    
    Integer monthObj = (Integer) request.getAttribute("month");
    Integer yearObj = (Integer) request.getAttribute("year");
    
    int month = (monthObj != null) ? monthObj.intValue() : 0;
    int year = (yearObj != null) ? yearObj.intValue() : 0;
    
    RestaurantManager manager = (RestaurantManager) request.getAttribute("manager");
    String managerName = "";
    Report report = null;
    if (manager != null) {
        report = reportDAO.RetrieveReport(manager, month, year);
        managerName = manager.getManagerName();
    }
    
    Employee employee;
    Attendance[] attendances = null;
    
    if (report != null) {
        attendances = attendanceDAO.getAllAttendancesByReport(report);
    }
    %>
    <header>
        <h2>Report Information</h2>
        <nav class="nav-bar">
            <ul>
                <li><a href="main_officer.jsp">Home</a></li>
                <li><a href="officer_employee_list.jsp">Employee</a></li>
                <li><a href="officer_salary_main.jsp">Salary</a></li>
                <li><a href="officer_verified_report.jsp">Report</a></li>
                <li><a href="welcome.html">Log Out</a></li>
            </ul>
        </nav>
    </header>
    <a href="main_officer.jsp">Back</a><br>
    <form action="verified_report.view" method="post">
        <label for="branch">Branch:</label>
        <select name="branch">
        <%for(Branch branch : branches){%>
        <option value="<%=branch.getBranchID()%>"><%= branch.getBranchName()%></option>
        <%}%>
        </select>
        <label for="month">Month:</label>
        <input type="month" name="month" value="${fulldate}" required>
        <input type="hidden" name="action" value="getVerifiedReportOfficer">
        <input type="submit" value="Search"><br>
    </form>
    <br>
    <table border="1">
        <tr>
            <th>Report By</th>
            <th>Employee ID</th>
            <th>Employee Name</th>
            <th>Attendance ID</th>
            <th>Attendance Date</th>
            <th>Clock In Time</th>
            <th>Clock Out Time</th>
        </tr>
        <% if (attendances == null || attendances.length == 0) { %>
            <tr>
                <td colspan="7" style="text-align:center;"><em>No Record Has Been Found</em></td>
            </tr>
        <% } else {
            for (Attendance attendance : attendances) {
                employee = employeeDAO.getEmployeeByAttendance(attendance);
                if (employee != null) {
        %>
        <tr>
            <td><%=managerName%></td>
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
