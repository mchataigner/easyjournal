<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>                          
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<link rel="stylesheet" type="text/css" href="technoweb.css" />
<html>
<head>

</head>
<body>
<%
   if(request.getSession().getAttribute("id")!=null)
{
   Utilisateur u=BDUtilisateurs.bd.getUtilisateur(((Integer)request.getSession().getAttribute("id")).intValue());
   
   if(u instanceof Abonne)
   {
   
   
   %>
 <h1 align="center"> Laisser un commentaire : </h1>

 
<jsp:useBean id="BeanEcrireCommentaire" class="controleur.gestionArticles.BeanEcrireCommentaire" scope="page"/>
<jsp:setProperty name="BeanEcrireCommentaire" property="*"/>

<jsp:useBean id="EcrireCommentaireAction" class="controleur.gestionArticles.EcrireCommentaireAction" scope="page"/>
<jsp:setProperty name="EcrireCommentaireAction" property="*"/>



<jsp:useBean id="BeanNoterArticle" class="controleur.gestionArticles.BeanNoterArticle" scope="page"/>
<jsp:setProperty name="BeanNoterArticle" property="*"/>

<jsp:useBean id="NoterArticleAction" class="controleur.gestionArticles.NoterArticleAction" scope="page"/>
<jsp:setProperty name="NoterArticleAction" property="*"/>
    
    
    

<% String login =(String)session.getAttribute("login"); %>


<%
int[] articlesNotes = ((Abonne)u).getArticlesDejaNotes();
int idArticle = Integer.parseInt(request.getParameter("idArticle"));
Boolean estDejaNote=false;
for(int i=0;i<articlesNotes.length;i++)
{
System.out.println(articlesNotes.length);
System.out.println("pouik");
System.out.println(articlesNotes[i]);
System.out.println(idArticle);

if(articlesNotes[i] == idArticle)
{
estDejaNote=true;
}
}
%>


<html:form action="pages/EcrireCommentaire.do" method="POST">
	<div align="center">
	<table>
          <html:hidden property="idArticle" value="<%=request.getParameter(\"idArticle\")%>" />
	<tr><td><div align="right"><html:textarea property="contenu" rows="5" cols="40" value="" /></div></td></tr>
	</table>
    <div align="center"><table> <td> <html:submit value="Poster le Commentaire"/></td></table></div>
    </div>
    <html:hidden property="abonne" value="<%= login%>"/>
</html:form>




<%if(estDejaNote)
{
%>

<div align="center"> Vous avez deja noté cet article</div>
<%
}
else
{
%>
<html:form action="pages/NoterArticle.do" method="POST">
		<div align="center">
                  <html:hidden property="idArticle" value="<%=request.getParameter(\"idArticle\")%>"/>
				<table> <td>   <html:select property="note">
   				 <option value="1">1</option>
    			 <option value="2">2</option>
    			 <option value="3">3</option>
    			 <option value="4">4</option>
    			 <option value="5">5</option>
    			 <option value="6">6</option>
    			 <option value="7">7</option>
    			 <option value="8">8</option>
    			 <option value="9">9</option>
    			 <option value="10">10</option>
    			 
    			 
				</html:select>
        		<html:submit value="noter" /></td></table>
        </div>
</html:form>

<%}}}%>

</body>
</html>
