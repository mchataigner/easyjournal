package controleur.profil;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import bd.BDUtilisateurs;
import twmodele.profil.*;

public class ModProfilAction extends Action {

    
    public ActionForward execute (ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BDUtilisateurs bdutil = BDUtilisateurs.bd;
        ModProfilForm mpf = (ModProfilForm)actionForm;
        
        HttpSession session = ((HttpServletRequest)request).getSession(true);
        
        int id = Integer.parseInt(session.getAttribute("id").toString());
        String login = (String)session.getAttribute("login");
        
        Abonne u = (Abonne)bdutil.getUtilisateur(login);
        
        try{
        
            if(u.verifierMotDePasse(mpf.getAncienMDP())){
                
                u.setPrenom(mpf.getPrenom()); 
                u.setNom(mpf.getNom()); 
                u.setEmail(mpf.getEmail());
                u.setPassword(mpf.getNouveauMDP());
                
                bdutil.setUtilisateur(u);
                System.out.println("COUCOU1");
                return mapping.findForward("succes");
                
            }
            else
            {
                return mapping.findForward("echec");
            }

        }
        catch(Exception e){e.printStackTrace();}
        return mapping.findForward("echec");
    
    }    
}
