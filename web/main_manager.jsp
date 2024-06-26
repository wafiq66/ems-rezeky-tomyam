
<%@page import="com.ems.model.RestaurantManager"%>
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
    RestaurantManager manager = (RestaurantManager) session.getAttribute("managerLog");
    String name = Character.toUpperCase(manager.getManagerName().charAt(0)) + manager.getManagerName().substring(1);
    %>

    <header>
        <h1>Hi <%=name%>... Welcome to Rezky Tomyam</h1>
        <nav>
            <ul>
                <li><a href="main_manager.jsp">Home</a></li>
                <li><a href="#about">Report</a></li>
                <li><a href="manage_schedule_main.jsp">Schedule</a></li>
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
