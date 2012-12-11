package controleur.gestionArticles;

import org.apache.struts.action.*;
import twmodele.profil.*;

public class BeanNoterArticle extends ActionForm {
    private int note;
    public void setNote(int _note){
	note = _note;
    }
    public int getNote(){
	return note;
    }
}