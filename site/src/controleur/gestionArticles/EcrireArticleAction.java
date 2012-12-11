package controleur.gestionArticles;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import bd.*;
import twmodele.profil.*;

public class EcrireArticleAction extends Action {
    private static BDArticles bdart=BDArticles.bda;
    private static BDUtilisateurs bdutil=BDUtilisateurs.bd;
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse reponse)throws Exception{
	BeanEcrireArticle bean = (BeanEcrireArticle)actionForm;
	String titre = bean.getTitre();
	String contenu = bean.getContenu();
        
    if(contenu.equals(""))
    {
    	return mapping.findForward("echec");
    }
    else
    { 
	BeanArticle beanArticle = new BeanArticle();
	beanArticle.setTitre(titre);
	beanArticle.setContenu(contenu);
    Redacteur redacteur=(Redacteur)bdutil.getUtilisateur(((Integer)request.getSession().getAttribute("id")).intValue());
	beanArticle.setIdRedacteur(redacteur.getId());
	beanArticle.setId(bdart.getIdLibre());
	bdart.addArticle(beanArticle.getArticle());
	
	redacteur.ecrireArticle(beanArticle.getId());

        return mapping.findForward("succes");
    }
    }
}
