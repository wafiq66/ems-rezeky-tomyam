<%@page import="com.ems.model.Employee" %>
<%@page import="com.ems.model.Branch" %>
<%@page import="com.ems.dao.EmployeeDAO" %>
<%@page import="com.ems.dao.EmployeeDAOImpl" %>
<%@page import="com.ems.dao.BranchDAO" %>
<%@page import="com.ems.dao.BranchDAOImpl" %>
<%@page import="com.ems.model.Schedule" %>
<%@page import="com.ems.dao.ScheduleDAO" %>
<%@page import="com.ems.dao.ScheduleDAOImpl" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <title>Employee Profile - HRMS admin template</title>
		
    </head>
    <body>
        <%
            
            final ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
            Employee employee = (Employee) session.getAttribute("employeeLog");
            Branch branch = null;
            Schedule schedule = scheduleDAO.fetchLatestSchedule(employee);
            
        %>
        
		<!-- Main Wrapper -->
        <div class="main-wrapper">
		
			<!-- Header -->
            <div class="header">
				
				<!-- Header Title -->
                <div class="page-title-box">
					<h3>REZEKY TOMYAM </h3>
                </div>
				<!-- /Header Title -->
				
				
				
				<!-- Header Menu -->
				<nav>
					<ul>
						<li><a href="main_employee.jsp">Home</a></li>
                                                <li><a href="employee_profile_main.jsp">Profile</a></li>
                                                <li><a href="employee_current_schedule.jsp">Schedule</a></li>
                                                <li><a href="employee_attendance.jsp">Attendance</a></li>
                                                <li><a href="#contact">Salary</a></li>
                                                <li><a href="welcome.html">Log Out</a></li>
					</ul>
				</nav>
 			 <!-- /Header Menu -->
			<!-- /Header -->
				<!-- Page Content -->
					<div class="content container-fluid">

						<div class="profile-basic">
						  <h2 class="card-title">Profile </h2>
							

						  <h3 class="user-name m-t-0 mb-0"><%=employee.getEmployeeName()%></h3>
						  
						  
						  <h2 class="card-title">Schedule </h2>
						  <table border="1">
						      <tr>
						          <th>Schedule Date</th>
						          <th>Start Shift</th>
						          <th>End Shift</th>
						      </tr>
						      <tr>
						          <td><%= schedule.getScheduleDate() %></td>
						          <td><%= schedule.getStartShift() %></td>
						          <td><%= schedule.getEndShift() %></td>
						      </tr>
						  </table>
						  
						  <a href="main_employee.jsp">Back</a>
	  
					  </div>
				 
				<!-- /Page Content -->
        </div>
		<!-- /Main Wrapper -->
    </body>
</html>