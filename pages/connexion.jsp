<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>  
 <%@ page language="java" import="controleur.profil.*" %>                                

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<link rel="stylesheet" type="text/css" href="technoweb.css" />

<%if(request.getSession().getAttribute("id")!=null){%>
<jsp:forward page="accueil.jsp"/>
<%}%>


<html>
  <head>
    <title>Connexion</title>
  </head>
  <body>
 
<h1 align="center"> Connexion </h1>
<%BDUtilisateurs bd=BDUtilisateurs.bd;%>

<p><div align="center"> Merci de saisir votre login et votre mot de passe. Si vous ne possédez pas encore de compte et que vous souhaitez accéder au contenu de notre site, vous pouvez en créer un gratuitement <a href="enregistrement.jsp">en cliquant ici !</a></div></p>
 
<jsp:useBean id="log" class="controleur.profil.LogBean" scope="page"/>
<jsp:setProperty name="LogBean" property="*"/>

<jsp:useBean id="logaction" class="controleur.profil.LoginAction" scope="page"/>
<jsp:setProperty name="LoginAction" property="*"/>


<html:form action="pages/Connexion.do" method="POST">
	<div align="center">
	<table>
	<tr>
	<td><div align="right">login:</div></td> <td><html:text property="login" value=""/><br/></td>
	</tr>
	<tr>
	<td><div align="right">mot de passe:</div></td><td><html:password property="password" value=""/></td>
	</tr>
    </table>
    </div>
    <div align="center"><table><td><html:submit value="Se connecter" /></td></table></div>
</html:form>




</body>
</html>

