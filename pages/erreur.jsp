<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>                                 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">

<head>

	<title>Erreur !</title>
	<link href="technoweb.css" rel="stylesheet"  type="text/css"/>

</head>
<body>


<% if(request.getParameter("from")!=null)
{
String origine=(String)request.getParameter("from");

if(origine.equals("connexion"))
{
%>	
 <div align="center" >
Erreur: connexion impossible<br/>
Votre login ou votre mot de passe est certainement incorrect<br/>
<a href="connexion.jsp">Retour à la page de connexion</a><br/>
</div>	
<%
}
 
if(origine.equals("register"))
{
%>
<div align="center" >
Erreur: Enregistrement impossible<br/>
Remplissez tous les champs, et choisissez un login non utilisé<br/>
<a href="enregistrement.jsp">Retour à la page d'enregistrement</a><br/>
</div>	
<%
} 
 
 
if(origine.equals("commentaire"))
{
%>
<div align="center" >
Erreur: Vous essayez de poster un commentaire vide !<br/>
<a href="accueil.jsp">Retour à l'accueil</a><br/>
</div>	
<%
} 
 
 
if(origine.equals("article"))
{
%>
<div align="center" >
Erreur: Vous essayez de poster un article vide !<br/>
<a href="accueil.jsp">Retour à l'accueil</a><br/>
</div>	
<%
} 
 
if(origine.equals("needLog"))
{
%>
<div align="center" >
Erreur: Ce contenu est reservé aux Abonnés !<br/>
<a href="connexion.jsp">Connectez vous</a> ou <a href="enregistrement.jsp">créez un compte.</a><br/>
</div>	
<%
} 
 
 if(origine.equals("profil"))
{
%>
<div align="center" >
Erreur: mot de passe incorrecte ou champs vides<br/>
<a href="profil.jsp">Retour au profil</a><br/>
</div>	
<%
} 
 
 
 
 
}
%>














</body>
</html>
