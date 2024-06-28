
<%@page import="com.ems.model.HROfficer" %>
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
    HROfficer officer = (HROfficer) session.getAttribute("officerLog");
    String name = Character.toUpperCase(officer.getOfficerName().charAt(0)) + officer.getOfficerName().substring(1);
    %>
    <header>
        <h1>Hi <%=name%>... Welcome to Rezky Tomyam</h1>
        <nav class="nav-bar">
            <ul>
                <li><a href="main_officer.jsp">Home</a></li>
                <li><a href="officer_employee_list.jsp">Employee</a></li>
                <li><a href="officer_salary_main.jsp">Salary</a></li>
                <li><a href="officer_verified_report.jsp">Report</a></li>
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