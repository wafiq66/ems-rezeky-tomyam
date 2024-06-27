
<%@page import="com.ems.model.Employee" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Website</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%  
        Employee employee = (Employee) session.getAttribute("employeeLog");
        String name = Character.toUpperCase(employee.getEmployeeName().charAt(0)) + employee.getEmployeeName().substring(1);
    %>
    <header>
        <h1>Hi <%=name%>... Welcome to Rezky Tomyam</h1>
        <nav>
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="employee_profile_main.jsp">Profile</a></li>
                <li><a href="employee_current_schedule.jsp">Schedule</a></li>
                <li><a href="employee_attendance.jsp">Attendance</a></li>
                <li><a href="employee_salary_main.jsp">Salary</a></li>
                <li><a href="welcome.html">Log Out</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <section id="home">
            <h2>Home</h2>
            <p>  Lorem ipsum dolor sit amet consectetur adipisicing elit. Modi, provident voluptate aliquam tempora reprehenderit aperiam a id, officiis eligendi ullam excepturi, animi consectetur quam repudiandae placeat reiciendis cum recusandae? Eaque?   </p>
        </section>
    </main>
    <footer>
        <p>&copy; rezky tomyam employee management system</p>
    </footer>
</body>
</html>
