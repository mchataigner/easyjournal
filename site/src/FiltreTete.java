import  java.io.*;
import  javax.servlet.*;
import  javax.servlet.http.*;
import  java.util.Locale;
import  java.text.*;
import  java.util.*;

public class FiltreTete implements Filter {
  
    public void init(FilterConfig fconfig) {}
    public void destroy() {}
    public void doFilter(ServletRequest requete, ServletResponse reponse, FilterChain chain) throws ServletException, IOException 
    {
   		
        PrintWriter writer;
        HttpSession session = ((HttpServletRequest)requete).getSession(true);
        String typeUtilisateur = (String)session.getAttribute("typeUtilisateur");   
        String login="";
        System.out.println(typeUtilisateur);
                 
        //reponse.setContentType("text/plain; charset=UTF-8");
        //reponse.setLocale(new Locale(Locale.FRENCH.getLanguage(), Locale.FRANCE.getCountry()));
        
        writer=reponse.getWriter();
        writer.println("<div align='center'>");
        writer.println(" <a class='entete' href='accueil.jsp'> Accueil </a> &nbsp;&nbsp;-&nbsp;&nbsp;");
        if( typeUtilisateur == null)
            {
                writer.println(" <a class='entete' href='connexion.jsp'> Connexion </a> &nbsp;&nbsp;-&nbsp;&nbsp;");
                writer.println(" <a class='entete' href='enregistrement.jsp'> Enregistrement </a> &nbsp;&nbsp;-&nbsp;&nbsp;"); 
            }
    
        else
            {
                
                login = (String)session.getAttribute("login");
                if( typeUtilisateur.equals("Redacteur"))
                    {

                        writer.println(" <a class='entete' href='redigerUnArticle.jsp'> Rediger un article</a> &nbsp;&nbsp;-&nbsp;&nbsp;");
                        writer.println(" <a class='entete' href='mesArticles.jsp'> Mes articles</a> &nbsp;&nbsp;-&nbsp;&nbsp;");
                    }
                else
                    {
                        if(typeUtilisateur.equals("Administrateur"))
                            {
                                
                            }
                        else
                            {
                                //Abonne
                            }
                    }
                writer.println(" <a class='entete' href='deconnexion.jsp'> Deconnexion </a> &nbsp;&nbsp;-&nbsp;&nbsp;");
            }
        writer.println(" <a class='entete' href='recherche.jsp'> Rechercher </a> ");
        writer.println("</div>");




        writer.println("<hr/>");

        
        writer.println("<div class=\"test\">");
        chain.doFilter(requete, reponse);
        writer.println("</div>");

        writer.println("<hr/>");
        writer.println("<div align='center'>");
        //writer.println("Statistiques: "+getNombreArticles()+" article(s) et "+getNombreCommentaires()+" commentaire(s) sur le site!"+"&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp; ");
    
        if(typeUtilisateur != null)
            {
                writer.println(" <a class='entete' href='redacteursLesMieuxNotes.jsp'> Redacteurs les mieux notes </a> &nbsp;&nbsp;-&nbsp;&nbsp; ");
                writer.println(" <a class='entete' href='articlesLesMieuxNotes.jsp'> Articles les mieux notes </a> &nbsp;&nbsp;-&nbsp;&nbsp;");
                writer.println(" <a class='entete' href='profil.jsp'> Profil ("+login+" : "+typeUtilisateur+") </a> &nbsp;&nbsp;-&nbsp;&nbsp;");
            }
    
        

        writer.println(" <a class='entete' href='contact.jsp'> Contact </a> ");
        writer.println("</div>");
        
        writer.flush();
        writer.close();
      
        
    }
}
