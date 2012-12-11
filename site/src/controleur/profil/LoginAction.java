package controleur.profil;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import bd.BDUtilisateurs;
import twmodele.profil.*;

public class LoginAction extends Action {

    
    public ActionForward execute (ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BDUtilisateurs bdutil=BDUtilisateurs.bd;
        LogBean bean = (LogBean)actionForm;
        String login = request.getParameter("login");
        String password = request.getParameter("password");

   
        HttpSession session = ((HttpServletRequest)request).getSession(true);
        
        try
            {
                Utilisateur util=bdutil.getUtilisateur(login);
                
                System.out.println(util);
                
                if(util.verifierMotDePasse(password))
                {
                	/*if(util instanceof Administrateur)
                	{
                		session.setAttribute("typeUtilisateur","Administrateur");
                	}
					else                
                	*/
                	if(util instanceof Redacteur)
                	{
                		session.setAttribute("typeUtilisateur","Redacteur");
                	}
                	else if(util instanceof Abonne)
                	{
                			session.setAttribute("typeUtilisateur","Abonne");
                	}
                	else if(util instanceof Administrateur)
                	{
                			session.setAttribute("typeUtilisateur","Administrateur");
                	}
                	else
                	{
                		System.err.println("Erreur: Utilisateur sans type");
                	}	
                session.setAttribute("login", login);
                session.setAttribute("id",util.getId());
                return mapping.findForward("succes");
                
                }
            }
        catch(Exception e){e.printStackTrace();}
        return mapping.findForward("echec");
    }
}
