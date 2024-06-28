<%@page import="java.util.Arrays"%>
<%@page import="com.ems.model.Schedule" %>
<%@page import="com.ems.model.Employee" %>
<%@page import="com.ems.model.RestaurantManager" %>
<%@page import="com.ems.dao.ManagerDAO" %>
<%@page import="com.ems.dao.ManagerDAOImpl" %>
<%@page import="com.ems.dao.EmployeeScheduleDAO" %>
<%@page import="com.ems.dao.EmployeeScheduleDAOImpl" %>
<%@page import="com.ems.dao.EmployeeDAO" %>
<%@page import="com.ems.dao.EmployeeDAOImpl" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <script>
        function validateForm() {
            var checkboxes = document.getElementsByClassName('list-employee');
            var isChecked = false;
            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    isChecked = true;
                    break;
                }
            }
            if (!isChecked) {
                alert("Please select at least one employee.");
                return false;
            }
            return true;
        }
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Schedule</title>
</head>
<body>
    <h1>Rezeky Tomyam</h1>
    <nav>
        <ul>
            <li><a href="main_manager.jsp">Home</a></li>
            <li><a href="report_update.jsp">Report</a></li>
            <li><a href="manage_schedule_main.jsp">Schedule</a></li>
            <li><a href="welcome.html">Log Out</a></li>
        </ul>
    </nav>
    <a href="specific_schedule.jsp">Back</a><br><br>
    <main>
        <section class="employee-schedule">
            <%
                
                RestaurantManager manager = (RestaurantManager) session.getAttribute("managerLog");
                ManagerDAO managerDAO = new ManagerDAOImpl();
                int branchID = managerDAO.getRestaurantManagerBranchId(manager);
                
                EmployeeDAO employeeDAO = new EmployeeDAOImpl();
                Employee[] employees = employeeDAO.getAllEmployeeByBranch(branchID);
                
                Schedule schedule = (Schedule) session.getAttribute("editingSchedule");
                EmployeeScheduleDAO employeeScheduleDAO = new EmployeeScheduleDAOImpl();
                
                int[] employeeIDs = employeeScheduleDAO.getAllScheduleEmployee(schedule);
                
                
            %>
            <form action="manage_schedule.do" method="post" onsubmit="return validateForm()">
                <label for="">Date Begin:-</label>
                <input type="date" name="scheduleDate" value="<%=schedule.getScheduleDate()%>" required> <br> <br>
                <label for="">Start Shift:-</label>
                <input type="time" name="startShift" value="<%=schedule.getStartShift()%>" required> <br> <br>
                <label for="">End Shift</label>
                <input type="time" name="endShift" value="<%=schedule.getEndShift()%>" required> <br> <br>

                <p>Select Employee:-</p>
                <%
                    for (Employee employee : employees) {
                        if (employee.getEmployeeStatus()) { // Check if EmployeeStatus is true
                %>
                <input type="checkbox" name="list-employee" class="list-employee" value="<%=employee.getEmployeeID()%>" 
                       <%=employee.employeeSelectedInSchedule(employeeIDs, employee.getEmployeeID())? "checked" : ""%>>
                <label for=""><%=employee.getEmployeeID()%> <%=employee.getEmployeeName()%></label> <br>
                <%
                        }
                    }
                %>

                <br>
                <input type="hidden" name="scheduleID" value="<%=schedule.getScheduleID()%>">
                <input type="hidden" name="action" value="updateEmployee">
                <input name="save" type="submit" value="Save">
            </form>
            <form action="manage_schedule.do">
                <input type="hidden" name="action" value="deleteSchedule">
                <input name="delete" type="submit" value="Delete">
            </form>
        </section>
    </main>
</body>
</html>