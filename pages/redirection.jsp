<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>  
 <%@ page language="java" import="controleur.profil.*" %> 
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">

<head>
	<title>Redirection</title>
	<link href="technoweb.css" rel="stylesheet"  type="text/css"/>
	

	
</head>
<body>

<%
//if(session.getAttribute("typeUtilisateur")==null)
//{
//get les droit de l'utilisateur qui vient de se log
//Utilisateur user = request.getParameter("type");
//System.out.println("Pouik"+user);
//session.setAttribute("typeUtilisateur","Redacteur");
//}


if(session.getAttribute("typeUtilisateur")=="Abonne")
{


Utilisateur user=BDUtilisateurs.bd.getUtilisateur((String)session.getAttribute("login"));
System.out.println(user);
BDUtilisateurs.bd.upgradeAbonne(user);


%>
<jsp:forward page="connexion.jsp" />
<%}else{


if(session.getAttribute("typeUtilisateur")=="Administrateur")
{


Utilisateur utilisateur = BDUtilisateurs.bd.getUtilisateur((String)(request.getParameter("user")));
BDUtilisateurs.bd.upgradeAbonne(utilisateur);
}
%>

<jsp:forward page="accueil.jsp" />
<%


%>
<jsp:forward page="accueil.jsp" />
<%}%>
</body>
</html>

