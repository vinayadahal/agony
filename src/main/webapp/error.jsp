<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>ERROR! Something went wrong. See StackTrace log:</h1>

        <pre style="background: #000;color: #fff;padding: 10px;">
            <%=request.getAttribute("excep")%>
        </pre>
    </body>
</html>
