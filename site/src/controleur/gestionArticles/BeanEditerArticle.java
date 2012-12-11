package controleur.gestionArticles;

import org.apache.struts.action.*;
import twmodele.profil.*;

public class BeanEditerArticle extends ActionForm {
    private String titre;
    private String contenu;
    private Redacteur redacteur;
    public String getTitre(){
	return titre;
    }
    public String getContenu(){
	return contenu;
    }
    public void setTitre(String titre){
	this.titre = titre;
    }
    public void setContenu(String contenu){
	this.contenu = contenu;
    }
    
    public Redacteur getRedacteur(){
	return this.redacteur;
    }
    
    public void setRedacteur(Redacteur redacteur){
	this.redacteur = redacteur;
    }
}
