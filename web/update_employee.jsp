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
		
    </head>
    <body>
        <%
            final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            final BranchDAO branchDAO = new BranchDAOImpl();
            Employee employee = (Employee) session.getAttribute("editingEmployee");
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
				<nav class="nav-bar">
                                    <ul>
                                        <li><a href="main_officer.jsp">Home</a></li>
                                        <li><a href="officer_employee_list.jsp">Employee</a></li>
                                        <li><a href="officer_salary_main.jsp">Salary</a></li>
                                        <li><a href="officer_verified_report.jsp">Report</a></li>
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
                      <label for="employeeID">Employee ID:</label>
                      <input type="text" id="employeeID"  value="<%= employee.getEmployeeID() %>" disabled>
                    </li>
                    <li>
                      <label for="phone">Phone:</label>
                      <input type="text" id="phone" name="phone" value="<%= employee.getEmployeePhoneNumber() %>" required>
                    </li>
                    <li>
                      <label for="email">Email:</label>
                      <input type="email" id="email" name="email" value="<%= employee.getEmployeeEmail() %>" required>
                    </li>
                    <li>
                      <label for="passportNo">Passport No:</label>
                      <input type="text" id="passportNo" value="<%= employee.getEmployeePassportNumber() %>" disabled>
                    </li>
                    <li>
                      <label for="status">Status:</label>
                      <select id="status" name="status">
                        <option value="true" <%= employee.getEmployeeStatus()? "selected" : "" %>>Active</option>
                        <option value="false" <%=!employee.getEmployeeStatus()? "selected" : "" %>>Inactive</option>
                      </select>
                    </li>
                    <li>
                      <label for="hourlyPay">Hourly Pay:</label>
                      <select id="hourlyPay" name="hourlyPay">
                        <option value="6.0" <%= employee.getEmployeeHourlyPay() == 6.0? "selected" : "" %>>6.0</option>
                        <option value="6.5" <%= employee.getEmployeeHourlyPay() == 6.5? "selected" : "" %>>6.5</option>
                        <option value="7.0" <%= employee.getEmployeeHourlyPay() == 7.0? "selected" : "" %>>7.0</option>
                        <option value="7.5" <%= employee.getEmployeeHourlyPay() == 7.5? "selected" : "" %>>7.5</option>
                      </select>
                    </li>
                    <li>
                        <label> Branch:</label>
                        <select name="branch">
                            <%
                                for(Branch a:branches){
                            %>
                            <option value="<%=a.getBranchID() %>"<%= a.getBranchID() == id? "selected" : "" %>><%=a.getBranchName() %></option>
                            <%
                                }
                            %>
                        </select>
                    </li>
                  </ul>

                  <label for="hourlyPay">Your Password:</label>
                  <input type="text" id="officerPassword" name="officerPassword" required> <br><br>

                  <input type="hidden" name="employeeID" value="<%= employee.getEmployeeID() %>">
                  <input type="hidden" name="employeeName" value="<%= employee.getEmployeeName() %>">
                  <input type="hidden" name="employeePassword" value="<%= employee.getEmployeePassword() %>">
                  <input type="hidden" name="employeePassportNumber" value="<%= employee.getEmployeePassportNumber()  %>">
                  <input type="submit" value="Update" name="action">
                  <a href="specific_employee.jsp">Cancel</a>
                  <p>${message}</p>
                </div>
              </div>
            </form>
				<!-- /Page Content -->
        </div>
		<!-- /Main Wrapper -->
    </body>
</html>