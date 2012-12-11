<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>                                 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<link rel="stylesheet" type="text/css" href="technoweb.css" />
<html>
  <head>
    <title>Profil</title>
  </head>
  <body>

<%if(request.getSession().getAttribute("typeUtilisateur")==null){%>
<jsp:forward page="/pages/connexion.jsp"/>

<%}%>
 
<h1 align="center"> Votre profil </h1>


<%
if(request.getParameter("modif")!=null)
{%>

<jsp:useBean id="ModProfilForm" class="controleur.profil.ModProfilForm" scope="page"/>
<jsp:setProperty name="ModProfilForm" property="*"/>

<jsp:useBean id="ModProfilAction" class="controleur.profil.ModProfilAction" scope="page"/>
<jsp:setProperty name="ModProfilAction" property="*"/>

<%
Utilisateur i=BDUtilisateurs.bd.getUtilisateur(((Integer)request.getSession().getAttribute("id")).intValue());
%>

<html:form action="pages/EditerProfil.do" method="POST">
	<div align="center">
	<table>
	<tr><td><div align="right">nom: </div></td><td><html:text property="nom" value="<%=((Abonne)i).getNom()%>"/><br/></td></tr>
	<tr><td><div align="right">prenom: </div></td><td><html:text property="prenom" value="<%= ((Abonne)i).getPrenom() %>"/><br/></td></tr>
	<tr><td><div align="right">adresse e-mail:</div></td><td> <html:text property="email" value="<%= ((Abonne)i).getEmail() %>"/><br/></td></tr>
	<tr><td><div align="right">ancien mot de passe: </div></td><td><html:password property="ancienMDP"/><br/></td></tr>
	<tr><td><div align="right">nouveau mot de passe:</div></td><td><html:password property="nouveauMDP" value="<%= ((Abonne)i).getPassword() %>"/><br/></td></tr>
	</table>
	</div>
    <div align="center"><table> <td> <html:submit value="Modifier le compte"/></td></table>
</html:form>


<table> <td> <a href="profil.jsp"><input type="button" value="annuler"/></a></td></table>


<table> <td><a href="redirection.jsp"><input type="button" value="Cheat"/></a></td></table></div>


<%
   }
   else
   {Utilisateur i;
   try
   {
   i=BDUtilisateurs.bd.getUtilisateur(((Integer)request.getSession().getAttribute("id")).intValue());
   
%>
<div align="center">
      <table border="1pt" class="profil">
        <tr>
          <td >
           <div align="center"> login : <%= i.getLogin() %></div>
          </td>
        </tr>
        <%if(!(i instanceof Administrateur)){%>
        <tr>
          <td>
              <div align="center"> nom : <%= ((Abonne)i).getNom() %></div>
          </td>
        </tr>
        <tr>
          <td>

           <div align="center"> prenom : <%= ((Abonne)i).getPrenom() %></div>


          </td>
        </tr>
        <tr>
          <td>

           <div align="center"> email : <%= ((Abonne)i).getEmail() %></div>


          </td>
        </tr>
        <tr>
          <td>

           <div align="center"> nombre d'articles visités : <%= ((Abonne)i).getNombreArticlesVisites() %></div>


          </td>
        </tr>
        <%if(i instanceof Redacteur){%>
        <tr>
          <td>

          <div align="center">  nombre d'articles écris : <%= ((Redacteur)i).getNombreArticles() %></div>


          </td>
        </tr>
  </div>     
<%}}

%>
<div align="center"> <table> <td><a href="profil.jsp?modif=1"><input type="button" value="modifier"/></a></td></table></div>

<%
}
   catch(Exception e){}
}%>

</body>
</html>
