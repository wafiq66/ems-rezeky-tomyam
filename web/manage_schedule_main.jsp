<%@page import="com.ems.model.Schedule" %>
<%@page import="com.ems.dao.ScheduleDAO" %>
<%@page import="com.ems.dao.ScheduleDAOImpl" %>
<%@page import="com.ems.model.RestaurantManager" %>
<%@page import="com.ems.dao.ManagerDAO" %>
<%@page import="com.ems.dao.ManagerDAOImpl" %>
<!DOCTYPE html>
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
        int branchID = managerDAO.getRestaurantManagerBranchId(manager); // Replace with the desired branch ID (commented out for now)
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule[] schedules = scheduleDAO.getAllScheduleByBranch(branchID);
    %>
    <h1>Schedule</h1>
    <nav>
        <ul>
           <li><a href="main_manager.jsp">Home</a></li>
            <li><a href="report_update.jsp">Report</a></li>
            <li><a href="manage_schedule_main.jsp">Schedule</a></li>
            <li><a href="welcome.html">Log Out</a></li>
        </ul>
    </nav>
    <main>
        <form action="view_schedule.do" method="post">
            <input type="submit" value="View Current Schedule"><br><br>
        </form>
        <a href="create_schedule.jsp">Create Schedule</a> <br> <br>
        <p>${message}</p>
        <table border="1">
            <tr>
                <thead>
                    <th>Schedule Date</th>
                    <th>Start Shift</th>
                    <th>End Shift</th>
                    <th>View Schedule</th>
                </thead>
            </tr>
            <tbody>
                <% for (Schedule schedule : schedules) { %>
                <tr>
                    <form action="manage_schedule.do">
                        <td><%= schedule.getScheduleDate() %></td>
                        <td><%= schedule.getStartShift() %></td>
                        <td><%= schedule.getEndShift() %></td>
                        <input type="hidden" name="scheduleID" value="<%= schedule.getScheduleID() %>">
                        <input type="hidden" name="action" value="viewSpecificSchedule">
                        <td><input type="submit" value="View"></td>
                    </form>
                </tr>
                <% } %>
            </tbody>
        </table>
    </main>
</body>
</html>