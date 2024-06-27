<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.ems.model.Salary"%>
<%@page import="com.ems.model.Employee"%>
<%@page import="com.ems.dao.SalaryDAO"%>
<%@page import="com.ems.dao.SalaryDAOImpl"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Salary Information</title>
</head>
<body>
    <h1>Salary Information</h1>
    <%
        final SalaryDAO salaryDAO = new SalaryDAOImpl();
        Employee employee = new Employee();
        employee.setEmployeeID(1028);
        Salary salary = salaryDAO.getUncalculatedEmployeeSalary(employee, 6, 2024);
    %>
    <table border="1">
        <tr>
            <th>Employee ID</th>
            <th>Salary Month</th>
            <th>Salary Year</th>
            <th>Total Hours Worked</th>
            <th>Salary Amount</th>
        </tr>
        <tr>
            <td><%= salary.getEmployeeID() %></td>
            <td><%= salary.getSalaryMonth() %></td>
            <td><%= salary.getSalaryYear() %></td>
            <td><%= salary.getTotalHoursWorked() %></td>
            <td><%= salary.getSalaryAmount() %></td>
        </tr>
    </table>
</body>
</html>