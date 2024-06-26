<%@page import="com.ems.model.Attendance"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.ems.model.Employee"%>
<%@page import="com.ems.dao.AttendanceDAO"%>
<%@page import="com.ems.dao.AttendanceDAOImpl"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Latest Attendance</title>
</head>
<body>
    <h1>Latest Attendance</h1>
    <%
        Employee employee = new Employee();
        employee.setEmployeeID(1005);
        
        AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
        Attendance attendance = attendanceDAO.getLatestAttendance(employee);
    %>
    <table border="1">
        <tr>
            <th>Attendance ID</th>
            <th>Attendance Date</th>
            <th>Clock In Time</th>
            <th>Clock Out Time</th>
        </tr>
        <tr>
            <td><%= attendance.getAttendanceID() %></td>
            <td><%= attendance.getAttendanceDate() %></td>
            <td><%= attendance.getClockInTime() %></td>
            <td><%= attendance.getClockOutTime() %></td>
        </tr>
    </table>
</body>
</html>

