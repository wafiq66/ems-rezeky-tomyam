<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="com.ems.model.Employee" %>
<%@page import="com.ems.dao.EmployeeDAO" %>
<%@page import="com.ems.dao.EmployeeDAOImpl" %>
<%@page import="com.ems.model.Attendance" %>
<%@page import="com.ems.dao.AttendanceDAO" %>
<%@page import="com.ems.dao.AttendanceDAOImpl" %>
<%@page import="com.ems.model.Schedule" %>
<%@page import="com.ems.dao.ScheduleDAO" %>
<%@page import="com.ems.dao.ScheduleDAOImpl" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance Page</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%
        
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
        
        final ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        final AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
        boolean allowClockIn = false, allowClockOut = false;
        Employee employee = (Employee) session.getAttribute("employeeLog");
        Schedule latestSchedule = scheduleDAO.fetchLatestSchedule(employee);
        Attendance latestAttendance = attendanceDAO.getLatestAttendance(employee);
        
        if(latestAttendance.isBothNone()){
            allowClockOut = false;
            if (currentTime.compareTo(latestSchedule.getStartShift()) >= 0 && currentTime.compareTo(latestSchedule.getEndShift()) <= 0) {
                allowClockIn = true;
            } else {
                allowClockIn = false;
            }
        }
        else if(latestAttendance.isBothNotNone()){
            allowClockOut = false;
            if (currentTime.compareTo(latestSchedule.getStartShift()) >= 0 && currentTime.compareTo(latestSchedule.getEndShift()) <= 0) {
                allowClockIn = true;
            } else {
                allowClockIn = false;
            }
        }
        else if (latestAttendance.isClockOutOnlyNone()){
            allowClockOut = true;
            allowClockIn = false;
        }
        
        
        
    %>
    <header class="header">
        <h1>Rezky Tomyam </h1>
        <p><%=currentTime%></p>
    </header>
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
    <a href="main_employee.jsp">Back</a>
    <div class="main-content">
        
        <section class="content">
            <div class="attendance-container">
                <div class="date-time">
                    <p id="currentDateTime"></p>
                </div>
                <h2>Hi Employee...</h2>
                <form action="record_attendance.do" method="post">
                    <input type="hidden" name="action" value="in">
                    <input type="submit" id="employeeId" value="Clock In" 
                           <%=allowClockIn? "":"disabled"%>
                           >
                </form>
                <form action="record_attendance.do" method="post">
                    <input type="hidden" name="action" value="out">
                    <input type="submit" id="employeeId" value="Clock Out" 
                           <%=allowClockOut? "":"disabled"%>
                           >
                </form>
                <p>${message}</p>
                <a href="attandance_history.jsp">View Attendance History</a>
                
            </div>
        </section>
    </div>
    <footer class="footer">
        <p>&copy; rezky tomyam employee management system</p>
    </footer>

</body>
</html>
