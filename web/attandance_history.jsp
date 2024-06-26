<%@page import="com.ems.model.Employee" %>
<%@page import="com.ems.model.Schedule" %>
<%@page import="com.ems.dao.EmployeeDAO" %>
<%@page import="com.ems.dao.EmployeeDAOImpl" %>
<%@page import="com.ems.dao.ScheduleDAO" %>
<%@page import="com.ems.dao.ScheduleDAOImpl" %>
<%@page import="com.ems.model.Attendance" %>
<%@page import="com.ems.dao.AttendanceDAO" %>
<%@page import="com.ems.dao.AttendanceDAOImpl" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance History</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%
        final AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
        Employee employee = (Employee) session.getAttribute("employeeLog");
        int[] attendanceID = (int[]) request.getAttribute("attendanceID");
        Attendance[] attendance = attendanceDAO.getAllAttendances(employee);
    %>
    <header class="header">
        <h1> Employee Attendance History</h1>
    </header>
    <nav class="nav-bar">
        <ul>
            <li><a href="main_employee.jsp">Home</a></li>
            <li><a href="employee_profile_main.jsp">Profile</a></li>
            <li><a href="employee_current_schedule.jsp">Schedule</a></li>
            <li><a href="employee_attendance.jsp">Attendance</a></li>
            <li><a href="#contact">Salary</a></li>
            <li><a href="welcome.html">Log Out</a></li>
        </ul>
    </nav>
    <a href="employee_attendance.jsp">Back</a>
    <div class="main-content">
        <aside class="sidebar">
            
        </aside>
        <section class="content">
            <div class="attendance-container">
                <h2> <%= employee.getEmployeeName()%>'s Attendance History</h2>
                <form action="attendance_record.view" method="post">
                    Month:-<input type="month" name="month" id="month" required> <br><br>
                    <input type="submit" value="Find">
                </form>
                
                <table id="attendanceHistoryTable" border="1">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Clock In Time</th>
                            <th>Clock Out Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if(attendanceID != null){
                                for(int id:attendanceID){
                                    for (Attendance a : attendance) {
                                        if(a.getAttendanceID() == id){
                        %>
                        <tr>
                            <td><%= a.getAttendanceDate() %></td>
                            <td><%= a.getClockInTime() %></td>
                            <td><%= a.getClockOutTime() %></td>
                        </tr>
                        <%
                                        }
                                    }
                                }
                            } else {
                                for (Attendance a : attendance) {
                        %>
                        <tr>
                            <td><%= a.getAttendanceDate() %></td>
                            <td><%= a.getClockInTime() %></td>
                            <td><%= a.getClockOutTime() %></td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </section>
    </div>
    <footer class="footer">
        <p>&copy; rezky tomyam employee management system</p>
    </footer>
</body>
</html>