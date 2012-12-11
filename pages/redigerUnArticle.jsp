<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>                                 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<link rel="stylesheet" type="text/css" href="technoweb.css" />
<html>
  <head>
    <title>Redaction d'article</title>
  </head>
  <body>
 
<%if(session.getAttribute("typeUtilisateur")==null)
{%>
<jsp:forward page="/pages/connexion.jsp" />
<%}
else if(session.getAttribute("typeUtilisateur")!="Redacteur")
{%>
<h1 align="center">Seul un Rédacteur peut écrire un article ! </h1>
<%}
else {%>

<h1 align="center"> Rédigez votre article ! </h1>

<jsp:useBean id="BeanEcrireArticle" class="controleur.gestionArticles.BeanEcrireArticle" scope="page"/>
<jsp:setProperty name="BeanEcrireArticle" property="*"/>

<jsp:useBean id="EcrireArticleAction" class="controleur.gestionArticles.EcrireArticleAction" scope="page"/>
<jsp:setProperty name="EcrireArticleAction" property="*"/>


<html:form action="pages/RedigerArticle.do" method="POST">
		<div align="center">
		Titre: <html:text property="titre" value=""/><br/>
		<html:textarea property="contenu" rows="30" cols="70" value=""/><br/>
        <html:submit value="Publier" />
        </div>
</html:form>

<%}%>

</body>
</html>
