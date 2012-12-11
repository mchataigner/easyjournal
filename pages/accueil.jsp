<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="twmodele.gestionArticles.*" %>
<%@ page import="twmodele.profil.*" %>
<%@ page import="bd.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<link rel="stylesheet" type="text/css" href="technoweb.css" />

<html>
  <head>
    <title>Accueil</title>
  </head>
  <body>
  
    
    <h1 align="center"> Accueil </h1>

<% if(session.getAttribute("typeUtilisateur")==null){ %>
<jsp:include page="accueilInvite.jsp"/>
<%}%>

<% if(session.getAttribute("typeUtilisateur")=="Abonne"){ %>
<jsp:include page="accueilAbonne.jsp"/>
<%}%>

<% if(session.getAttribute("typeUtilisateur")=="Redacteur"){ %>
<jsp:include page="accueilRedacteur.jsp"/>
<%}%>

<% if(session.getAttribute("typeUtilisateur")=="Administrateur"){ %>
<jsp:include page="accueilAdmin.jsp"/>
<%}%>


</body>
</html>
