<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="twmodele.gestionArticles.*" %>
<%@ page import="twmodele.profil.*" %>
<%@ page import="bd.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>






<%
Article dernierArticle=null ;
try
{
dernierArticle = BDArticles.bda.getDernierArticle();
Redacteur red = dernierArticle.getRedacteur();
%>
    <h2 align="center"> Dernier article publié : </h2>


<div class="article" align="center">
<table border="1pt" class="article">
  <tr>
    <td>Redacteur: <%=red.getLogin()%>  </td>
    <td><%=red.getNombreArticles()%> articles(s) rédigé(s) </td>
    <td><%=red.getPoints()%> points </td>
  </tr>
  <tr>
    <td colspan="3" class="titre">
       <div align="center"> Titre de l'article: <%= dernierArticle.getTitre() %></div>
    </td>
  </tr>
  <tr>
    <td colspan="3">
        <%= dernierArticle.getContenu() %>
    </td>
  </tr>
  <tr>
    <td colspan="3">

        Note moyenne de l'article : <%= Float.toString(dernierArticle.getNoteMoyenne()) %>


    </td>
  </tr>
</table>
</div>

<br/>
<br/>

<%}
catch(Exception e)
{
%>
    <h2 align="center">Aucun article sur le site</h2>


<%}%>


<%
if(dernierArticle!=null)
{
Collection<Commentaire> lesComms=BDCommentaires.bd.getCommentaires(dernierArticle.getId());
if(lesComms.size()!=0)
{%>
<h3 align="center"> Commentaires : </h3>
<br/>
<br/>

<div class="commentaire" align="center">
<%
for(Commentaire i:lesComms)
{
Abonne redC=(Abonne)BDUtilisateurs.bd.getUtilisateur(i.getIdRedacteur());
%>


<table border="1pt" class="commentaire">
  <tr>
    <td>Commentaire de:  <%=redC.getLogin()%></td>
    <td><%=redC.getPoints()%> Points </td>
  </tr>
  <tr>
    <td colspan="2">
        <%= i.getContenu() %>
    </td>
  </tr>
</table>


<%}}}%>
</div>
<br/>
<br/>


