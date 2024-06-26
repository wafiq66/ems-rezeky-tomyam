<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="com.ems.model.Branch" %>
<%@page import="com.ems.dao.BranchDAO" %>
<%@page import="com.ems.dao.BranchDAOImpl" %>
<%@page import="com.ems.model.Employee" %>
<%@page import="com.ems.dao.EmployeeDAO" %>
<%@page import="com.ems.dao.EmployeeDAOImpl" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <title>Employee Registration Form</title>
		
		<!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
		
		<!-- Bootstrap CSS -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
		
		<!-- Fontawesome CSS -->
        <link rel="stylesheet" href="assets/css/font-awesome.min.css">
		
		<!-- Lineawesome CSS -->
        <link rel="stylesheet" href="assets/css/line-awesome.min.css">
		
		<!-- Select2 CSS -->
		<link rel="stylesheet" href="assets/css/select2.min.css">
		
		<!-- Datetimepicker CSS -->
		<link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css">
		
		<!-- Tagsinput CSS -->
		<link rel="stylesheet" href="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css">
		
		<!-- Main CSS -->
        <link rel="stylesheet" href="assets/css/style.css">
		
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
			<script src="assets/js/html5shiv.min.js"></script>
			<script src="assets/js/respond.min.js"></script>
		<![endif]-->
    </head>
    <body>
        <%
            String passportNumber = (String)session.getAttribute("newPassportNumber");
            String registerNotice = (String)request.getAttribute("registerNotice");
            BranchDAO branchDAO = new BranchDAOImpl();
            Branch[] branch = null;
            branch = branchDAO.getAllBranch();
            
        %>
            <section class="container">
                    <header> Registration Form</header>
                    <form action="register_employee.do" class="form" method="post">
                            
                            <div class="input-box">
                                    <label> Passport Number:-</label>
                                    <input name="passportNumber" type="text" placeholder="Enter passport number" value="<%=passportNumber %>" disabled/>
                            </div>
                            <br>
                            <div class="input-box">
                                    <label> Name:-</label>
                                    <input name="name" type="text" placeholder="Enter full name" required/>
                            </div>
                            <br>
                            <div class="input-box">
                                    <label> Phone Number:-</label>
                                    <input name="phoneNumber" type="text" placeholder="Enter phone number" required/>
                            </div>
                            <br>

                            <div class="input-box">
                                    <label> Email:-</label>
                                    <input name="email" type="text" placeholder="Enter email address" required/>
                            </div>

                            <br>

                            <div class="input-box">
                                    <label> Hourly Pay:-</label>
                                    <select name="hourlyPay" placeholder="Enter hourly pay">
                                        <option value="6.0">6.0</option>
                                        <option value="6.5">6.5</option>
                                        <option value="7.0">7.0</option>
                                        <option value="7.5">7.5</option>
                                    </select>
                            </div>
                            <br>
                            <div class="column">
                                <div class="select-box">
                                    <label> Branch:-</label>
                                    <select name="branch">
                                        <%
                                            for(Branch a:branch){
                                        %>
                                        <option value="<%=a.getBranchID() %>" ><%=a.getBranchName() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <br>
                            <input name="action" type="hidden" value="recordEmployee" />
                            <input name="passportNumber" type="hidden" value="<%=passportNumber %>" />
                            <input type="submit" value="Register" ${condition}/>
                    </form>
                    <%
                        
                        if(registerNotice != null && !registerNotice.isEmpty()){%>
                    <p>${registerNotice}</p>
                    <p>Employee ID: ${registeredEmployee.employeeID}</p>
                    <p>Employee Password: ${registeredEmployee.employeePassword}</p>
                    <%}%>
                    <a href="verify_passport.jsp">Back</a>
            </section>
    </body>	
</html>	
				
				

			
				
				