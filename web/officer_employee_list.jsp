<%@page import="com.ems.model.Branch" %>
<%@page import="com.ems.model.Employee" %>
<%@page import="com.ems.dao.BranchDAO" %>
<%@page import="com.ems.dao.BranchDAOImpl" %>
<%@page import="com.ems.dao.EmployeeDAO" %>
<%@page import="com.ems.dao.EmployeeDAOImpl" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rezeky Tomyam</title>
</head>
<body>
    <%
        final BranchDAO branchDAO = new BranchDAOImpl();
        final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee[] employees = null;
        Branch[] branches = null;
        branches = branchDAO.getAllBranch();

    %>
    <h1>List Of Employee</h1>
    <nav>
        <ul>
            <li><a href="main_officer.jsp">Home</a></li>
            <li><a href="">Employee</a></li>
            <li><a href="#about">Salary</a></li>
            <li><a href="#about">Report</a></li>
            <li><a href="welcome.html">Log Out</a></li>
        </ul>
    </nav>
    <main>
        <a href="verify_passport.jsp">Add Employee +</a>
        <%
            for(Branch branch:branches){
        %>
        <table border="1">
            <h4>Branch: <%=branch.getBranchName() %></h4>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Password</th>
                    <th>Name</th>
                    <th>Phone Number</th>
                    <th>Email</th>
                    <th>Passport Number</th>
                    <th>Status</th>
                    <th>Hourly Pay</th>
                    <th>View</th>
                </tr>
            </thead>
            <tbody>
            <%
                employees = employeeDAO.getAllEmployeeByBranch(branch.getBranchID());
                for(Employee employee:employees){
            %>
                <tr>
                    <form action="manage_employee.do" method="post">
                        <td><%= employee.getEmployeeID() %></td>
                        <td><%= employee.getEmployeePassword() %></td>
                        <td><%= employee.getEmployeeName() %></td>
                        <td><%= employee.getEmployeePhoneNumber() %></td>
                        <td><%= employee.getEmployeeEmail() %></td>
                        <td><%= employee.getEmployeePassportNumber() %></td>
                        <td><%= employee.getEmployeeStatus() ? "Available" : "Not Available" %></td>
                        <td><%= employee.getEmployeeHourlyPay() %></td>
                        
                        <input type="hidden" name="action" value="viewEmployee">
                        <input type="hidden" name="employeeID" value="<%= employee.getEmployeeID() %>">
                        <td><input type="submit" value="View"></td>
                    </form>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <%
            }
        %>
        
    </main>
</body>
</html>