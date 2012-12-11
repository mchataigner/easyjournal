package controleur.gestionArticles;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import bd.BDCommentaires;
import twmodele.gestionArticles.*;

public class EditerCommentaireAction extends Action {

    
    public ActionForward execute (ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BDCommentaires bdcom = BDCommentaires.bd;
        BeanEditerCommentaire bec = (BeanEditerCommentaire)actionForm;
        
        int idCom = bec.getIdCommentaire();
        String com = bec.getContenu();
        
        Commentaire c = bdcom.getCommentaire(idCom);
        
        try{
            c.setContenu(com);
            bdcom.setCommentaire(c);
            return mapping.findForward("succes");
        }
        catch(Exception e){e.printStackTrace();}
        return mapping.findForward("echec");
    }
    
}
