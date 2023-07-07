<%-- 
    Document   : index
    Created on : 6 mars 2023, 15:03:43
    Author     : ITU
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Import de la bibliothèque JSTL Core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%-- Accès à l'objet et à ses propriétés avec EL --%>
        <c:set var="myKeys" value="${myObject.keySet()}" />

        <%-- Parcours des clés avec une boucle forEach --%>
        <c:forEach items="${myKeys}" var="key">
            Valeur: ${myObject.get(key)}<br>
        </c:forEach>
            
        <form method="post" action="/sprint7/do/Emp-add">
            <input type="text" name="Nom">            
            <input type="text" name="Prenom">
            <input type="submit" value="valider">
        </form>

    </body>
</html>
