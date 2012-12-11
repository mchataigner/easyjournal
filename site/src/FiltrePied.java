import  java.io.*;
import  javax.servlet.*;
import  javax.servlet.http.*;
import  java.util.Locale;
import  java.text.*;
import  java.util.*;

public class FiltrePied implements Filter {
  
    public void init(FilterConfig fconfig) {}
    public void destroy() {}
    public void doFilter(ServletRequest requete, ServletResponse reponse, FilterChain chain) throws ServletException, IOException 
    {
      
        HttpSession session = ((HttpServletRequest) requete).getSession(true);
        String typeUtilisateur = (String)session.getAttribute("typeUtilisateur");   
        
        reponse.setContentType("text/plain; charset=UTF-8");
        reponse.setLocale(new Locale(Locale.FRENCH.getLanguage(), Locale.FRANCE.getCountry()));
    
        chain.doFilter(requete, reponse);
        PrintWriter writer = reponse.getWriter();
    
    
        writer.println("<hr/>");
        //writer.println("Statistiques: "+getNombreArticles()+" article(s) et "+getNombreCommentaires()+" commentaire(s) sur le site!"+"&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp; ");
    
        if(typeUtilisateur != null)
            {
                writer.println(" <a href='redacteursLesMieuxNotes.jsp'> Redacteurs les mieux notes </a> &nbsp;&nbsp;-&nbsp;&nbsp; ");
                writer.println(" <a href='articlesLesMieuxNotes.jsp'> Articles les mieux notes </a> &nbsp;&nbsp;-&nbsp;&nbsp;");
            }
    
        writer.println(" <a href='contact.jsp'> Contact </a> ");	
    }
}

