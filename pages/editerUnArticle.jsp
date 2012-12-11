<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>                                 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<link rel="stylesheet" type="text/css" href="technoweb.css" />
<html>
  <head>
    <title>Edition d'article</title>
  </head>
  <body>
 
<%if(session.getAttribute("typeUtilisateur")==null)
{%>
<jsp:forward page="/pages/connexion.jsp" />
<%}
else if(session.getAttribute("typeUtilisateur")!="Redacteur")
{%>
<h1 align="center">Seul un Rédacteur peut éditer son article ! </h1>
<%}
else {%>

<h1 align="center"> Éditez votre article ! </h1>

<h2 align="center" class="temp"> Under Construction </h1>

<%
int idArticle = Integer.parseInt(request.getParameter("idArticle"));
Article art = BDArticles.bda.getArticle(idArticle);
Redacteur red = art.getRedacteur();
%>


<jsp:useBean id="BeanEditerArticle" class="controleur.gestionArticles.BeanEditerArticle" scope="page"/>
<jsp:setProperty name="BeanEditerArticle" property="*"/>

<jsp:useBean id="EditerArticleAction" class="controleur.gestionArticles.EditerArticleAction" scope="page"/>
<jsp:setProperty name="EditerArticleAction" property="*"/>


<html:form action="pages/EditerArticle.do" method="POST">
		<div align="center">
                  <html:hidden property="idArticle" value="<%=request.getParameter(\"idArticle\")%>"/>
		Titre: <html:text property="titre" value="<%=art.getTitre()%>"/><br/>
		<html:textarea property="contenu" rows="30" cols="70" value="<%=art.getContenu()%>" /><br/>
        <html:submit value="Editer" />
        </div>
</html:form>

<%}%>

</body>
</html>
