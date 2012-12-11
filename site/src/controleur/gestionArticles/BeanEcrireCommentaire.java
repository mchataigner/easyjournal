package controleur.gestionArticles;

import org.apache.struts.action.*;
import twmodele.profil.Abonne;
public class BeanEcrireCommentaire extends ActionForm{
    private String contenu;
    private String abonne;
    private String article;

    public String getContenu(){
	return this.contenu;
    }
    
    public void setContenu(String _contenu){
	this.contenu = _contenu;
    }
    public void setAbonne(String _abonne){
	this.abonne = _abonne;
    }

	public String getAbonne(){
	return this.abonne;
    }
}
/*
    public String getArticle(){
	return Article;
    }
*/
