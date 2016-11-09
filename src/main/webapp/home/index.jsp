<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h1>Hello World! <%=request.getContextPath()%></h1>
        <c:out value="${test}" />
    </body>
</html>