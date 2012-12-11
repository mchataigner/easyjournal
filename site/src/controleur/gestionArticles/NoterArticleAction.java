package controleur.gestionArticles;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import bd.*;
import twmodele.profil.*;
import twmodele.gestionArticles.*;

public class NoterArticleAction extends Action {
    private static BDArticles bdart=BDArticles.bda;
    private static BDUtilisateurs bdu=BDUtilisateurs.bd;
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse reponse)throws Exception{
	BeanNoterArticle bean = (BeanNoterArticle)actionForm;
	int note = bean.getNote();
	int idArticle = Integer.parseInt((String)request.getParameter("idArticle"));
	Article article = bdart.getArticle(idArticle);
	article.noter(((Integer)request.getSession().getAttribute("id")).intValue(),note);
	String login = (String)(request.getSession().getAttribute("login"));
	Abonne u = (Abonne)bdu.getUtilisateur(login);
	u.noterArticle(idArticle);
	System.out.println("Note moyenne: "+article.getNoteMoyenne());
	try
	    {
		bdart.setArticle(article);
		return mapping.findForward("succes");
	    }
	catch(Exception e){}
	
        return mapping.findForward("echec");
    }
}
