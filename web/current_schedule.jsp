<%@page import="com.ems.dao.EmployeeScheduleDAOImpl"%>
<%@page import="com.ems.dao.EmployeeScheduleDAO"%>
<%@page import="com.ems.model.Employee" %>
<%@page import="com.ems.model.Schedule" %>
<%@page import="com.ems.model.RestaurantManager" %>
<%@page import="com.ems.dao.EmployeeDAO" %>
<%@page import="com.ems.dao.EmployeeDAOImpl" %>
<%@page import="com.ems.dao.ScheduleDAO" %>
<%@page import="com.ems.dao.ScheduleDAOImpl" %>
<%@page import="com.ems.dao.ManagerDAO" %>
<%@page import="com.ems.dao.ManagerDAOImpl" %>

<!DOCTYPE html>
<!--
    Description: In this html file all current schedule will be displayed with the list of employees
    assigned. For the employees that are still not assigned with any schedulee, their name will be 
    displayed on the "Not assigned section".  Take note that, this page is handle by manager only,
    so the employees list are for the respective branch only.
-->

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Schedule</title>
</head>
<body>
    <%
    RestaurantManager manager = (RestaurantManager) session.getAttribute("managerLog");
    ManagerDAO managerDAO = new ManagerDAOImpl();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    Employee[] employees = employeeDAO.getAllEmployeeByBranch(managerDAO.getRestaurantManagerBranchId(manager)); // Replace with the desired employees
    
    ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
    Schedule[] schedules = new Schedule[employees.length];
    
    for (int i = 0; i < employees.length; i++) {
        schedules[i] = scheduleDAO.fetchLatestSchedule(employees[i]);
    }
    
    EmployeeScheduleDAO employeeScheduleDAO = new EmployeeScheduleDAOImpl();
%>
    <h1>Rezeky Tomyam</h1>
    <nav>
        <nav>
           <li><a href="main_manager.jsp">Home</a></li>
            <li><a href="report_update.jsp">Report</a></li>
            <li><a href="manage_schedule_main.jsp">Schedule</a></li>
            <li><a href="welcome.html">Log Out</a></li>
        </nav>
    </nav>
    <a href="manage_schedule_main.jsp">Back</a><br><br>
    <main>
    <table border="1">
        <tr>
            <th>Employee Name</th>
            <th>Schedule Date</th>
            <th>Start Shift</th>
            <th>End Shift</th>
        </tr>

        <% for (int i = 0; i < employees.length; i++) { 
            if(employees[i].getEmployeeStatus()){
        %>
        <tr>
            <td><%= employees[i].getEmployeeName() %></td>
            <td><%= schedules[i] != null ? schedules[i].getScheduleDate() : "" %></td>
            <td><%= schedules[i] != null ? schedules[i].getStartShift() : "" %></td>
            <td><%= schedules[i] != null ? schedules[i].getEndShift() : "" %></td>
        </tr>
        <% }} %>
    </table>
</main>
    
    
</body>
</html>