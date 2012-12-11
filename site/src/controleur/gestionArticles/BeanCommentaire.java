package controleur.gestionArticles;
import twmodele.gestionArticles.Commentaire;
import java.beans.*;
import java.io.*;
    public class BeanCommentaire implements Serializable{
	private static final long serialVersionUID = 42;
	private Commentaire commentaire;
	public BeanCommentaire(){
	    commentaire= new Commentaire();
	}
	public void setContenu(String _contenu){
            System.out.println(escape(_contenu));
            
	    commentaire.setContenu(escape(_contenu));
	}
        
        
        private String escape(String contenu)
        {
            return contenu.replaceAll("\n","<br/>");
            
        }
        
	public void setIdRedacteur(int id){
	    commentaire.setIdRedacteur(id);
	}
	public String getContenu(){
	    return commentaire.getContenu();
	}
	public int getIdRedacteur(){
	    return commentaire.getIdRedacteur();
	}
	public void setIdArticle(int id){
	    commentaire.setIdArticle(id);
	}
	public int getIdArticle(){
	    return commentaire.getIdArticle();
	}
	public void setId(int id){
	    commentaire.setId(id);
	}
	public int getId(){
	    return commentaire.getId();
	}
	public Commentaire getCommentaire(){
	    return commentaire;
	}
    }