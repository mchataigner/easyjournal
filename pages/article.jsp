<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>                                 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<link rel="stylesheet" type="text/css" href="technoweb.css" />
<html>
  <head>
    <title>Article</title>
  </head>
  <body>
 
     <%if(session.getAttribute("typeUtilisateur")==null)
       {%>
    <jsp:forward page="/pages/erreur.jsp?from=needLog" />
     <%}%>

 
     
<jsp:useBean id="BeanEditerCommentaire" class="controleur.gestionArticles.BeanEditerCommentaire" scope="page"/>
<jsp:setProperty name="BeanEditerCommentaire" property="*"/>

<jsp:useBean id="EditerCommentaireAction" class="controleur.gestionArticles.EditerCommentaireAction" scope="page"/>
<jsp:setProperty name="EditerCommentaireAction" property="*"/>







<% Article art=null;
   if(request.getParameter("idArticle")!=null){
   int idArticle = Integer.parseInt(request.getParameter("idArticle"));
   art = BDArticles.bda.getArticle(idArticle);
   }
   else
   {
   art = BDArticles.bda.getDernierArticle();
   }
Redacteur red = art.getRedacteur(); 
%>

<h1 align="center">Article: <%= art.getTitre() %> </h1>


<%
try
{
%>


<div class="article" align="center">
<table border="1pt" class="article">
  <tr>
    <td>Redacteur: <%=red.getLogin()%> </td>
    <td><%=red.getNombreArticles()%> article(s) rédigé(s) </td>
    <td><%=red.getPoints()%> Points </td>
  </tr>
  <tr>
    <td colspan="3" class="titre">
       <div align="center"> Titre de l'article: <%= art.getTitre() %></div>
    </td>
  </tr>
  <tr>
    <td colspan="3">
        <%= art.getContenu() %>
    </td>
  </tr>
  <tr>
    <td colspan="3">

       Note moyenne de l'article : <%= art.getNoteMoyenne() %>


    </td>
  </tr>
</table>
</div>





<%

Abonne ab = (Abonne)BDUtilisateurs.bd.getUtilisateur((Integer)(session.getAttribute("id")));
ab.addArticleVisite();
System.out.println("nombre d'articles lus : "+ab.getNombreArticlesVisites());


if((red.getLogin()).equals(session.getAttribute("login"))){%>

<div align="center"><a href="editerUnArticle.jsp?idArticle=<%=request.getParameter("idArticle")%>">Editer</a></div>



<%}%>
<br/>
<br/>
<%
Collection<Commentaire> lesComms=BDCommentaires.bd.getCommentaires(art.getId());
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
    <td> <%=redC.getPoints()%> Points  </td>
  </tr>
  <tr>
    <td colspan="2">

    
    	<%
    	if((redC.getLogin()).equals(session.getAttribute("login")))
		{
		%>
		<html:form action="pages/EditerCommentaire.do" method="POST">
		<html:textarea property="contenu" rows="10" cols="50" value="<%= i.getContenu() %>" />
		<html:hidden property="idCommentaire" value="<%=((Integer)(i.getId())).toString() %>" />
		<html:submit value="Editer" />
		</html:form>
		<%
		}		
		else
		{
		%>
        <%= i.getContenu() %>
        <%}%>
    </td>
  </tr>
</table>
<%}}%>
</div>
<br/>
<br/>


<%}
catch(Exception e)
{
%>

<%}%>


<jsp:include page="ecrireUnCommentaire.jsp">
  <jsp:param name="idArticle" value="<%=request.getParameter(\"idArticle\")%>" />
</jsp:include>



</body>
</html>



