<%@page import="com.ems.model.Employee" %>
<%@page import="com.ems.model.Branch" %>
<%@page import="com.ems.dao.EmployeeDAO" %>
<%@page import="com.ems.dao.EmployeeDAOImpl" %>
<%@page import="com.ems.dao.BranchDAO" %>
<%@page import="com.ems.dao.BranchDAOImpl" %>

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
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <!--font style-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet" />
    
    <link rel="stylesheet" href="css/updateprofile.css"> <!-- add this line to link to your CSS file -->
</head>

<body>
    <%
        final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        final BranchDAO branchDAO = new BranchDAOImpl();
        Employee employee = (Employee) session.getAttribute("employeeLog");
        Branch[] branches = branchDAO.getAllBranch();
        Branch branch = null;
        int id = employeeDAO.getEmployeeBranchID(employee);
        branch = branchDAO.getBranchById(id);
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
        </div>
        <!-- /Header -->
        <!-- Page Content -->
        <form action="manage_employee.do" method="post">
            <div class="content container-fluid">
                <div class="profile-basic">
                    <h2 class="card-title">Edit Profile </h2>
                    <h3 class="user-name m-t-0 mb-0"><%= employee.getEmployeeName() %></h3>
                    <ul class="personal-info">
                        <li>
                            <label for="employeeID">Employee Password:</label>
                            <input name="password" type="password" id="employeePassword"  value="<%= employee.getEmployeePassword() %>" required>
                        </li>
                        <li>
                            <label for="employeeID">Confirm Employee Password:</label>
                            <input name="passwordConfirm" type="password" id="employeeConfirmPassword"  value="<%= employee.getEmployeePassword() %>" required>
                        </li>
                        <li>
                            <label for="phone">Phone:</label>
                            <input name="phone" type="text" id="phone" name="phone" value="<%= employee.getEmployeePhoneNumber() %>" required>
                        </li>
                        <li>
                            <label for="email">Email:</label>
                            <input name="email" type="email" id="email" name="email" value="<%= employee.getEmployeeEmail() %>" required>
                        </li>
                    

                    <li>
                        <label for="employeeCurrentPassword">Current Password:</label>
                        <input type="password" id="employeeCurrentPassword" name="employeeCurrentPassword" required> <br><br>
                    </li>
                    </ul>
                    <input type="hidden" name="branchID" value="<%=branch.getBranchID() %>">
                    <input type="hidden" name="action" value="updateProfile">
                    <input type="submit" value="Update">
                  <a href="employee_profile_main.jsp">Cancel</a>
                  <p>${message}</p>
                </div>
              </div>
            </form>
				<!-- /Page Content -->
        </div>
		<!-- /Main Wrapper -->
    </body>
</html>