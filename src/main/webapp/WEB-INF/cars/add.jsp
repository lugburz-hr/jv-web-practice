<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new car</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/cars/add">
    Model  <input type="text" name="model"><br>
    Manufacturer  <input type="text" name="manufacturer_name"><br>
    <button type="submit">Confirm</button>
</form>
</body>
</html>
