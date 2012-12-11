<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="bd.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="twmodele.profil.*" %>  
<%@ page language="java" import="twmodele.gestionArticles.*" %>                                 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<link rel="stylesheet" type="text/css" href="technoweb.css" />
<html>
  <head>
    <title>Accueil</title>
  </head>
  <body>
    <%if(session.getAttribute("typeUtilisateur")==null)
       {%>
    <jsp:forward page="/pages/connexion.jsp" />
    <%
       }
       else if(session.getAttribute("typeUtilisateur")!="Administrateur")
       {%>
    <jsp:forward page="/pages/accueil.jsp" />
    <%
       }
       else
       {%>




    <div align="center">
      <a href="accueil.jsp?vue=utilisateurs">Liste de tous les utilisateurs</a> &nbsp&nbsp
      <a href="accueil.jsp?vue=articles">Liste de tous les articles</a>&nbsp&nbsp
      <a href="accueil.jsp?vue=commentaires">Liste de tous les commentaires</a>&nbsp&nbsp
    </div>
    
    <%
       if(request.getParameter("vue")!=null&&request.getParameter("vue").equals("articles")){
       
       if(request.getParameter("action")!=null&&request.getParameter("action").equals("rm"))
       {
       System.err.println("haha ça marche");
       try
       {
       int idarticle=Integer.parseInt(request.getParameter("idarticle"));
       BDArticles.bda.delArticle(idarticle);
       }
       catch(Exception e)
       {e.printStackTrace();
       }      
       }
       
       
       
       
       %>
    <h2 align="center">Liste de tous les articles :</h2>
    <%
       Collection<Article> lesArts=BDArticles.bda.getArticles();
       for(Article k:lesArts)
       {
       Redacteur red=k.getRedacteur();
       %>
    <div class="article" align="center">
      <table border="1pt" class="article">
        <tr>
          <td>Redacteur: <%=red.getLogin()%>  </td>
          <td><%=red.getNombreArticles()%> article(s) à son actif  </td>
        </tr>
        <tr>
          <td colspan="2" class="titre">
            Titre de l'article: <%= k.getTitre() %>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <p class="contenu">
              <%= k.getContenu() %>
            </p>
          </td>
        </tr>
        <tr>
          <td colspan="2">

            <%= Float.toString(k.getNoteMoyenne()) %>


          </td>
        </tr>
      </table>
      
      <a href="accueil.jsp?vue=articles&action=rm&type=article&idarticle=<%=k.getId()%>"><input type="button" value="Supprimer"/></a>
      
      
    </div>
    <br/>
    <br/>




    <%
       }
       }
       else if(request.getParameter("vue")!=null&&request.getParameter("vue").equals("commentaires"))
       {


       if(request.getParameter("action")!=null&&request.getParameter("action").equals("rm"))
       {
       System.err.println("haha ça marche");
       try
       {
       int idcommentaire=Integer.parseInt(request.getParameter("idcommentaire"));
       BDCommentaires.bd.delCommentaire(BDCommentaires.bd.getCommentaire(idcommentaire));
       }
       catch(Exception e)
       {e.printStackTrace();
       }      
       }
       
       

       %>
    <h2 align="center">Liste de tous les commentaires :</h2>

    <%
       Collection<Commentaire> lesComms=BDCommentaires.bd.getCommentaires();
       for(Commentaire l:lesComms)
       {
       Abonne red=l.getRedacteur();
       %>

    <div class="article" align="center">
      <table border="1pt" class="article">
        <tr>
          <td>Redacteur: <%=red.getLogin()%>  </td>
        </tr>
        
        <tr>
          <td colspan="2">
            <p class="contenu">
              <%= l.getContenu() %>
            </p>
          </td>
        </tr>
        
      </table>
      <a href="accueil.jsp?vue=commentaires&action=rm&type=commentaire&idcommentaire=<%=l.getId()%>"><input type="button" value="Supprimer"/></a>

    </div>
    <br/>
    <br/>



    <%
       }}
       else 
       {

       if(request.getParameter("vue")!=null&&request.getParameter("vue").equals("utilisateurs"))
       if(request.getParameter("action")!=null&&request.getParameter("action").equals("rm"))
       {
       System.err.println("haha ça marche");
       try
       {
       int idutilisateur=Integer.parseInt(request.getParameter("idutilisateur"));
       BDUtilisateurs.bd.delUtilisateur(BDUtilisateurs.bd.getUtilisateur(idutilisateur));
       }
       catch(Exception e)
       {e.printStackTrace();
       }      
       }
       
       

       %>
    <h2 align="center">Liste de tous les utilisateurs :</h2>
    
    <%
       Collection<Utilisateur> lesUtils=BDUtilisateurs.bd.getUtilisateurs();
       for(Utilisateur i:lesUtils)
       {
       %>
    <div class="utilisateur" align="center">
      <table border="1pt" class="article">
        <tr>
          <td>
            login : <%= i.getLogin() %>
          </td>
        </tr>
        <%if(!(i instanceof Administrateur)){%>
        <tr>
          <td>
              nom : <%= ((Abonne)i).getNom() %>
          </td>
        </tr>
        <tr>
          <td>

            prenom : <%= ((Abonne)i).getPrenom() %>


          </td>
        </tr>
        <tr>
          <td>

            email : <%= ((Abonne)i).getEmail() %>


          </td>
        </tr>
        <%if(i instanceof Redacteur){%>
        
        <tr>
          <td>

            Grade: Redacteur


          </td>
        </tr>
        
        
        
        <tr>
          <td>

            nombre d'articles écris : <%= ((Redacteur)i).getNombreArticles() %>


          </td>
        </tr>
        <%}
        else
        {
        %>
        
        <tr>
          <td>

            Grade: Abonne


          </td>
        </tr>
        
        
        
        <tr>
          <td>

           
  			<a href="redirection.jsp?user=<%=i.getLogin()%>"><input type="button" value="Nommer Redacteur"/></a>

          </td>
        </tr>
        
        
        
        
        
        
        <%} }%>
      </table>
      <a href="accueil.jsp?vue=utilisateurs&action=rm&type=utilisateur&idutilisateur=<%=i.getId()%>"><input type="button" value="Supprimer"/></a>
      
    </div>

    <br/>
    <br/>

    
    <%
       }
       }


       }
       %>
    
  </body>
</html>
