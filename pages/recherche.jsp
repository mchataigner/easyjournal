<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>                                 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<link rel="stylesheet" type="text/css" href="technoweb.css" />
<html>
  <head>
    <title>Recherche</title>
  </head>
  <body>
    
    
    <h1 align="center"> Faites votre recherche ! </h1>




<div align="center">
  <form action="recherche.jsp" methode="POST">
    <select name="typeRecherche">
    	<option value="redacteur">Redacteur</option>
    	<option value="titre">titre</option>
    </select>
    <input type="text" name="recherche" value=""/>
    <input type="submit" value="Lancer la recherche"/>
  </form>
</div>


<%

   if(request.getParameter("recherche")!=null&&request.getParameter("recherche").trim()!=""&&request.getParameter("typeRecherche").equals("titre"))
   {
   Collection<Article> lesArticles=new ArrayList<Article>();
   try{
   lesArticles=BDArticles.bda.getArticles(request.getParameter("recherche").trim());
   }catch(Exception e){}
   
   if(lesArticles.size()==0)
{%>
<h2 align="center" ">Aucun résultat n'a été trouvé pour : <%=request.getParameter("recherche")%></h2>
<%}
   
   for(Article i: lesArticles)
   {
   %>
<div align="center" class="titreAccueilAbonne"><a class="lienAccueilAbonne" href="article.jsp?idArticle=<%=i.getId()%>"><%=i.getTitre()%></a><br/></div>


<%}}

   if(request.getParameter("recherche")!=null&&request.getParameter("recherche").trim()!=""&&request.getParameter("typeRecherche").equals("redacteur"))
   {
   Collection<Article> lesArticles=new ArrayList<Article>();
   try{
   lesArticles=BDArticles.bda.getArticles((BDUtilisateurs.bd.getUtilisateur(request.getParameter("recherche").trim())).getId());
   }catch(Exception e){}
   
   if(lesArticles.size()==0)
{%>
<h2 align="center" ">Aucun résultat n'a été trouvé pour : <%=request.getParameter("recherche")%></h2>
<%}
   
   for(Article i: lesArticles)
   {
   %>
<div align="center" class="titreAccueilAbonne"><a class="lienAccueilAbonne" href="article.jsp?idArticle=<%=i.getId()%>"><%=i.getTitre()%></a><br/></div>



<%
}}
%>


</body>
</html>

