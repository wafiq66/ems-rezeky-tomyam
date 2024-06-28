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
        final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        final SalaryDAO salaryDAO = new SalaryDAOImpl();
        Employee[] employees = employeeDAO.getAllEmployee();
        Employee employee;
        
        Integer monthObject = (Integer) request.getAttribute("month");
        monthObject = (monthObject != null) ? monthObject : 0;
        int monthInt = monthObject.intValue();
        
        Integer yearObject = (Integer) request.getAttribute("year");
        yearObject = (yearObject != null) ? yearObject : 0;
        int year = yearObject.intValue();
        
        Salary targetSalary = (Salary) request.getAttribute("targetSalary");
        
        Salary[] salaries = salaryDAO.getCalculatedEmployeeSalary(monthInt, year);
    %>
    <header class="header">
        <h1>Salary Report</h1>
    </header>
    <nav class="nav-bar">
            <ul>
                <li><a href="main_officer.jsp">Home</a></li>
                <li><a href="officer_employee_list.jsp">Employee</a></li>
                <li><a href="officer_salary_main.jsp">Salary</a></li>
                <li><a href="officer_verified_report.jsp">Report</a></li>
                <li><a href="welcome.html">Log Out</a></li>
            </ul>
        </nav>
    <main>
        <h2>Salary Report</h2>
        
        <form action="employee_salary.view" method="post">
            <label for="employeeID">Employee ID:</label>
            <select name="employeeID" id="employeeID">
                <option value="0" selected></option>
                <% for (Employee e : employees) { %>
                    <option value="<%= e.getEmployeeID() %>"><%= e.getEmployeeID() %></option>
                <% } %>
            </select>
            <label for="month">Month:</label>
            <input type="month" name="month" id="month" required>
            <input type="hidden" name="action" value="byMonth">
            <input type="submit" value="Get Salary Report">
        </form>
        <br>
        <div class="salary-report-container">
            <table id="salaryReportTable" border="1">
                <thead>
                    <tr>
                        <th>Employee ID</th>
                        <th>Employee Name</th>
                        <th>Month</th>
                        <th>Year</th>
                        <th>Total Hours Worked</th>
                        <th>Salary</th>
                    </tr>
                </thead>
                <tbody>  
                    <% if (targetSalary != null) {
                        employee = employeeDAO.getEmployeeById(targetSalary.getEmployeeID());
                    %>
                        <tr>
                            <td><%= employee.getEmployeeID() %></td>
                            <td><%= employee.getEmployeeName() %></td>
                            <td><%= targetSalary.getSalaryMonth() %></td>
                            <td><%= targetSalary.getSalaryYear() %></td>
                            <td><%= targetSalary.getTotalHoursWorked() %></td>
                            <td>RM<%= targetSalary.getSalaryAmount() %></td>
                        </tr>
                    <% } else {
                        for (Salary s : salaries) {
                            employee = employeeDAO.getEmployeeById(s.getEmployeeID());
                    %>
                        <tr>
                            <td><%= employee.getEmployeeID() %></td>
                            <td><%= employee.getEmployeeName() %></td>
                            <td><%= s.getSalaryMonth() %></td>
                            <td><%= s.getSalaryYear() %></td>
                            <td><%= s.getTotalHoursWorked() %></td>
                            <td>RM<%= s.getSalaryAmount() %></td>
                        </tr>
                    <% } } %>
                </tbody>
            </table>
        </div>
    </main>
    <footer class="footer">
        <p>&copy; rezky tomyam employee management system</p>
    </footer>
</body>
</html>
