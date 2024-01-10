<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Exitoso</title>
</head>
<body>
    <h2>Bienvenido, <%= request.getAttribute("persona").getNombre() %> <%= request.getAttribute("persona").getApellido() %></h2>
    <p>RUT: <%= request.getAttribute("persona").getRut() %></p>
    <p>Teléfono: <%= request.getAttribute("persona").getTelefono() %></p>
    <p>Fecha de Ingreso: <%= request.getAttribute("persona").getFechaIngreso() %></p>
</body>
</html>
