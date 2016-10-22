<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form method="post" action="<%=request.getContextPath()%>/login/login">
            username: <input type="text" name="username" />  
            password: <input type="text" name="password" />
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
