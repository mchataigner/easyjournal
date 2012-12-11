<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="modele.profil.*" %>  
<%@ page language="java" import="modele.gestionArticles.*" %>                          
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<link rel="stylesheet" type="text/css" href="technoweb.css" />

<%BDUtilisateurs bd=BDUtilisateurs.bd;%>


<html>
  <head>
    <title>Enregistrement</title>

<script language="JavaScript">
function check() {
	var msg = "";
		if (document.formulaire.mail.value != "")	{
		indexAroba = document.formulaire.mail.value.indexOf('@');
		indexPoint = document.formulaire.mail.value.indexOf('.');
		if ((indexAroba < 0) || (indexPoint < 0))		
		{
			alert(msg);
			}
		}
}
</script>


  </head>
  <body>
 
 <h1 align="center"> Créez votre compte ! </h1>


<p><div align="center"> Pour accéder au contenu de notre site gratuitement, il vous suffit de créer en compte en remplissant ce formulaire !</div></p>
 
 
 
<jsp:useBean id="BeanAbonne" class="controleur.profil.BeanAbonne" scope="page"/>
<jsp:setProperty name="BeanAbonne" property="*"/>

<jsp:useBean id="RegisterAction" class="controleur.profil.RegisterAction" scope="page"/>
<jsp:setProperty name="RegisterAction" property="*"/>



<html:form action="pages/Enregistrement.do" method="POST" focus="formulaire">
	<div align="center">
	<table>
	<tr><td><div align="right">nom: </div></td><td><html:text property="nom" value=""/><br/></td>
	<tr><td><div align="right">prenom: </div></td><td><html:text property="prenom" value=""/><br/></td>
	<tr><td><div align="right">adresse e-mail:</div></td><td> <html:text property="email" value="" name="mail"/><br/></td>
	<tr><td><div align="right">login: </div></td><td><html:text property="login" value=""/><br/></td>
	<tr><td><div align="right">mot de passe: </div></td><td><html:password property="password" value=""/><br/></td>
	<tr><td><div align="right">repeter mot de passe:</div></td><td><html:password property="passwordBis" value=""/><br/></td>
	</table>
    <div align="center"><table> <td> <html:submit onclick="Javascript:check()" value="Créer un compte"/></td></table></div>
	</div>
</html:form>




</body>
</html>

