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
        final AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
        final BranchDAO branchDAO = new BranchDAOImpl();
        final ManagerDAO managerDAO = new ManagerDAOImpl();
        final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        
        Integer intObjMonth = (Integer) request.getAttribute("month");
        Integer intObjYear = (Integer) request.getAttribute("year");
        int month = 0,year = 0;
        if (intObjMonth == null || intObjYear == null) {
            // Handle the case when either month or year is null
            System.out.println("Month or year is null");
        } else {
            // Use intObjMonth and intObjYear
            month = intObjMonth.intValue();
            year = intObjYear.intValue();
        }
        
        RestaurantManager manager = (RestaurantManager) session.getAttribute("managerLog");
        Branch branch = branchDAO.getBranchById(managerDAO.getRestaurantManagerBranchId(manager));
        Attendance[] attendances;
        
        attendances = attendanceDAO.getAllAttendancesByBranch(branch,month,year);
        Employee employee;
        
        LocalDate currentDate = LocalDate.now();

        // Extract month and year as integers
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        
        Boolean ableToSubmit = (year < currentYear) || (year == currentYear && month < currentMonth);
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
    <a href="verified_report.jsp">View Verified Report</a><br>
    <form action="manage_report.do" method="post">
        <label for="month">Month:-</label>
        <input type="month" name="month" value="${fulldate}" required>
        <input type="hidden" name="action" value="getSpecificReport">
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
        <% if((month == 0 || year == 0) || attendances.length == 0) { %>
            <td colspan="6" style="text-align:center;"><em>No Record Has Been Found</em></td>
        <% } 
         else{ 
        %>
        <form action="manage_report.do" method="post">
            <input type="hidden" name="monthSubmit" value="<%=month%>">
            <input type="hidden" name="yearSubmit" value="<%=year%>">
            <input type="hidden" name="month" value="${fulldate}" >
            <input type="hidden" name="action" value="submitReport">
            <input type="submit" value="Verify and Submit" <%=(ableToSubmit)? "":"disabled"%>><br><br>
        </form>
        <p>${message}</p>
            <%    
         for(Attendance attendance : attendances){
                employee = employeeDAO.getEmployeeByAttendance(attendance);
        %>
            <tr>
                <td><%= employee.getEmployeeID() %></td>
                <td><%= employee.getEmployeeName() %></td>
                <td><%= attendance.getAttendanceID() %></td>
                <td><%= attendance.getAttendanceDate() %></td>
                <td><%= attendance.getClockInTime() %></td>
                <td><%= attendance.getClockOutTime() %></td>
            </tr>
        <%} 
            
        }%>
        
    </table>
</body>
</html>