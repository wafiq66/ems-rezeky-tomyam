<%@page import="com.ems.model.Salary" %>
<%@page import="com.ems.dao.SalaryDAO" %>
<%@page import="com.ems.dao.SalaryDAOImpl" %>
<%@page import="com.ems.model.Employee" %>
<%@page import="com.ems.dao.EmployeeDAO" %>
<%@page import="com.ems.dao.EmployeeDAOImpl" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Salary Report</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%
        final SalaryDAO salaryDAO = new SalaryDAOImpl();
        final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee = (Employee) session.getAttribute("employeeLog");
        
        
        Salary[] salaries = salaryDAO.getCalculatedEmployeeSalary(employee);
    %>
    <header class="header">
        <h1>Salary Report</h1>
    </header>
    <nav>
            <ul>
                <li><a href="main_employee.jsp">Home</a></li>
                <li><a href="employee_profile_main.jsp">Profile</a></li>
                <li><a href="employee_current_schedule.jsp">Schedule</a></li>
                <li><a href="employee_attendance.jsp">Attendance</a></li>
                <li><a href="employee_salary_main.jsp">Salary</a></li>
                <li><a href="welcome.html">Log Out</a></li>
            </ul>
        </nav>
    <main>
        <h2>Salary Report</h2>
        <h3><%=employee.getEmployeeName()%></h3>
        <br>
        <div class="salary-report-container">
            <table id="salaryReportTable" border="1">
                <thead>
                        <th>Month</th>
                        <th>Year</th>
                        <th>Total Hours Worked</th>
                        <th>Salary</th>
                    </tr>
                </thead>
                <tbody>  
                    <% for(Salary s:salaries){%>
                     <tr>
                            <td><%= s.getSalaryMonth() %></td>
                            <td><%= s.getSalaryYear() %></td>
                            <td><%= s.getTotalHoursWorked() %></td>
                            <td>RM<%= s.getSalaryAmount() %></td>
                        </tr>
                    
                    <%} %>
                       
                </tbody>
            </table>
        </div>
    </main>
    <footer class="footer">
        <p>&copy; rezky tomyam employee management system</p>
    </footer>
</body>
</html>

