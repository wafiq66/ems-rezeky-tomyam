<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager Login Page</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header class="header">
        <h1>Manager Login</h1>
    </header>
    <div class="login-container">
        <div class="nav-bar">
            <a href="login_employee.jsp" class="nav-button">Employee</a>
            <a href="login_manager.jsp" class="nav-button">Manager</a>
            <a href="login_officer.jsp" class="nav-button">Officer</a>
            <a href="welcome.html" class="nav-button">Back</a>
        </div>
        <br>
        <h2>Login</h2>
        <form action="login_account.do" method="post">
            <div class="input-group">
                <label for="username">Manager ID:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <input type="hidden" name="action" value="managerLogin">
            <input type="submit" value="Login">
        </form>
        <p>${message}</p>
    </div>
</body>
</html>
