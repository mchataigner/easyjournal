<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>                                 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<link rel="stylesheet" type="text/css" href="technoweb.css" />

<html>
  <head>
    <title>Accueil Abonné</title>
  </head>
  <body>

<h2 align="center">Articles présents sur le site:</h2>
<%Collection<Article> lesArts=BDArticles.bda.getArticles();
for(Article i : lesArts) {
%>
<div align="center" class="titreAccueilAbonne"><a class="lienAccueilAbonne" href="article.jsp?idArticle=<%=i.getId()%>"><%=i.getTitre()%></a><br/></div>

<%
}
%>

<br/>
<br/>
<br/>

</body>
</html>



