package controleur.gestionArticles;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import bd.*;

import twmodele.gestionArticles.*;
import twmodele.profil.*;


public class EcrireCommentaireAction extends Action {
    private static BDArticles bdart=BDArticles.bda;
    private static BDUtilisateurs bdutil=BDUtilisateurs.bd;
    private static BDCommentaires bdcom=BDCommentaires.bd;
    
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse reponse)throws Exception{
	BeanEcrireCommentaire bean = (BeanEcrireCommentaire)actionForm;
	String contenu = bean.getContenu();
	BeanCommentaire beanCommentaire = new BeanCommentaire();
	beanCommentaire.setContenu(contenu);
        Abonne abonne=(Abonne)bdutil.getUtilisateur(((Integer)request.getSession().getAttribute("id")).intValue());
	beanCommentaire.setIdRedacteur(abonne.getId());
        
        Article article=bdart.getArticle(Integer.parseInt(request.getParameter("idArticle")));
	beanCommentaire.setIdArticle(article.getId());
        
        
	//beanCommentaire.setId(bdart.getIdLibre());
	
	
	if(contenu.equals(""))
		return mapping.findForward("echec");
	else
	{
	
        bdcom.addCommentaire(beanCommentaire.getCommentaire());
        
        //        bdart.addArticle(beanArticle.getArticle());
        
        
        
        //	beanCommentaire.setIdRedacteur(request.getSession(true));
        //	beanCommentaire.setIdArticle(((Article)request.getAttribute("article")).getId());
	//	bean.setId(BDArticles.getIdLibre())
	///bdart.addArticle(beanCommentaire.getCommentaire());     // /!\ omg!!!

        return mapping.findForward("succes");
    }
    }
}
