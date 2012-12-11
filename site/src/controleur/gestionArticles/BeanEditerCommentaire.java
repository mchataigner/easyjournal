package controleur.gestionArticles;

import org.apache.struts.action.*;

public class BeanEditerCommentaire extends ActionForm {
    private String contenu;
    private int idCommentaire;
    
    public String getContenu(){
        return this.contenu;
    }
    
    public void setContenu(String _contenu){
        this.contenu=_contenu;
    }
    
    public int getIdCommentaire(){
        return this.idCommentaire;
    }
    
    public void setIdCommentaire(int _idc){
        this.idCommentaire = _idc;
    }

}
