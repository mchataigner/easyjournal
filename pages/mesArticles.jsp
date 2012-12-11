<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>                                 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<link rel="stylesheet" type="text/css" href="technoweb.css" />
<html>
  <head>
    <title>Mes Articles</title>
  </head>
  <body>
 
 
<h1 align="center">Vos articles</h1>

<%
   System.out.println(session.getAttribute("id"));
   Redacteur red=(Redacteur)BDUtilisateurs.bd.getUtilisateur((Integer)session.getAttribute("id"));
   
   try
   {
   if(request.getParameter("action")!=null&&request.getParameter("action").equals("rm") && request.getParameter("idArticle")!=null){
   Article art=BDArticles.bda.getArticle(Integer.parseInt(request.getParameter("idArticle")));
   if(art.getIdRedacteur()==((Integer)session.getAttribute("id")).intValue())
   {
   System.out.println("prout");
   BDArticles.bda.delArticle(art.getId());
   
   
   %>


<%
   }
   
   
   
   
   
   }
   }
   catch(Exception e){e.printStackTrace();}
   Collection<Article> mesArts=BDArticles.bda.getArticles((Integer)session.getAttribute("id"));
   for(Article i: mesArts)
   {%>



<div align="center">
<table border="1pt" class="article">
  <tr>
    <td>
      <a class="article" href="article.jsp?idArticle=<%=i.getId()%>">Titre de l'article: <%= i.getTitre() %></a>
    </td>
  </tr>
  <tr>
    <td>
        <%= i.getContenu() %>
    </td>
  </tr>
  <tr>
    <td>

       note moyenne pour cet article : <%= Float.toString(i.getNoteMoyenne()) %>


    </td>
  </tr>
</table>

<div align="center"><a href="editerUnArticle.jsp?idArticle=<%=i.getId()%>"><input type="button" value="Editer"/></a></div>
<div align="center"><a href="mesArticles.jsp?action=rm&idArticle=<%=i.getId()%>"><input type="button" value="Supprimer"/></a></div>


</div>

<br/>
<br/>
<%}%>



</body>
</html>
