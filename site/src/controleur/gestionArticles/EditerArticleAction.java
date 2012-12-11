package controleur.gestionArticles;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import bd.*;
import twmodele.profil.*;
public class EditerArticleAction extends Action {
    private static BDArticles bdart=BDArticles.bda;
    private static BDUtilisateurs bdutil=BDUtilisateurs.bd;

    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse reponse)throws Exception{
	BeanEditerArticle bean = (BeanEditerArticle)actionForm;
	String titre = bean.getTitre();
	String contenu = bean.getContenu();
	BeanArticle beanArticle = new BeanArticle();
	beanArticle.setTitre(titre);
	beanArticle.setContenu(contenu);
	Redacteur redacteur=(Redacteur)bdutil.getUtilisateur(((Integer)request.getSession().getAttribute("id")).intValue());
	beanArticle.setIdRedacteur(redacteur.getId());
	beanArticle.setId(Integer.parseInt((String)request.getParameter("idArticle")));
	try
	{
            bdart.setArticle(beanArticle.getArticle());
            return mapping.findForward("succes");
	}
	catch(Exception e){}

        return mapping.findForward("echec");
    }
}
