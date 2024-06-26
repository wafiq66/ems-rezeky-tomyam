<%@page import="com.ems.model.Schedule" %>
<%@page import="com.ems.model.Employee" %>
<%@page import="com.ems.dao.EmployeeScheduleDAO" %>
<%@page import="com.ems.dao.EmployeeScheduleDAOImpl" %>
<%@page import="com.ems.dao.EmployeeDAO" %>
<%@page import="com.ems.dao.EmployeeDAOImpl" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Schedule</title>
</head>
<body>
    <h1>Rezeky Tomyam</h1>
    <nav>
        <ul>
            <li><a href="main_manager.jsp">Home</a></li>
            <li><a href="">Report</a></li>
            <li><a href="manage_schedule_main.jsp">Schedule</a></li>
            <li><a href="login_manager.jsp">Log Out</a></li>
        </ul>
    </nav>
    <a href="update_schedule.jsp">Update Schedule</a><br><br>
    <a href="manage_schedule_main.jsp">Back</a><br><br>
    <p>${message}</p>
    <main>
        <section class="employee-schedule">
            <%
                Schedule schedule = (Schedule) session.getAttribute("editingSchedule");
                EmployeeScheduleDAO employeeScheduleDAO = new EmployeeScheduleDAOImpl();
                int[] employeeIDs = employeeScheduleDAO.getAllScheduleEmployee(schedule);
                EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            %>
            <table border="1">
                <tr>
                    <th>Schedule Date</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                </tr>
                <tr>
                    <th><%=schedule.getScheduleDate()%></th>
                    <th><%=schedule.getStartShift()%></th>
                    <th><%=schedule.getEndShift()%></th>
                </tr>
            </table>
            <p>Employees:</p>
            <table border="1">
                <tr>
                    <td>Employee ID</td>
                    <td>Employee Name</td>
                    <td>Employee Phone Number</td>
                </tr>
                <%
                    for (int employeeID : employeeIDs) {
                        Employee employee = employeeDAO.getEmployeeById(employeeID);
                        if (employee.getEmployeeStatus()) { // Check if EmployeeStatus is true
                %>
                <tr>
                    <td><%=employee.getEmployeeID()%></td>
                    <td><%=employee.getEmployeeName()%></td>
                    <td><%=employee.getEmployeePhoneNumber()%></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
        </section>
    </main>
</body>
</html>