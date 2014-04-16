<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<!doctype html>
<html lang="nl">
  <head>
    <title>Artikel toevoegen</title>
    <link rel="stylesheet" href="${contextPath}/styles/default.css"/> 
  </head>
  <body>
    <h1>Artikel toevoegen</h1>
    <form action="<c:url value='/artikels/toevoegen'/>" method="post"
      id="toevoegform">
      <label>Naam:
        <input name="naam" value="${param.naam}" autofocus/></label>
       <label>Aankoopprijs:
        <input type="number" name="aankoopprijs" value="${param.aankoopprijs}" /></label> 
        <label>Verkoopprijs:
        <input type="number" name="verkoopprijs" value="${param.verkoopprijs}" /></label> 
        <input type="submit" value="Toevoegen">
      
    </form>
    <c:import url="/WEB-INF/JSP/fouten.jsp"/>
  </body>
</html> 