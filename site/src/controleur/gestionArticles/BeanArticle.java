package controleur.gestionArticles;



import twmodele.gestionArticles.Article;
import java.beans.*;
import java.io.*;
    public class BeanArticle implements Serializable{
	private static final long serialVersionUID = 42;
	private Article article;
	public BeanArticle(){
            try{
                article = new Article();
            }catch(Exception e){};
	}
	public void setContenu(String _contenu){
            System.out.println(escape(_contenu));
            
	    article.setContenu(escape(_contenu));
	}
        
        
        private String escape(String contenu)
        {
            return contenu.replaceAll("\n","<br/>");
            
        }
        
        
	public void setTitre(String _titre){
	    article.setTitre(_titre);
	}
	public void setIdRedacteur(int id){
	    article.setIdRedacteur(id);
	}
	public String getContenu(){
	    return article.getContenu();
	}
	public String getTitre(){
	    return article.getTitre();
	}
	public int getIdAuteur(){
	    return article.getIdRedacteur();
	}
	public float getNoteMoyenne(){
	    return article.getNoteMoyenne();
	}
	public void setId(int id){
	    article.setId(id);
	}
	public int getId(){
	    return article.getId();
	}
	public Article getArticle(){
	    return this.article;
	}
    }
