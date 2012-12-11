package twmodele.gestionArticles;


import java.io.*;
import java.util.*;
import java.text.*;
import twmodele.profil.*;
import bd.*;


public class Commentaire{
    private int id;
    private int idAuteur;
    private int idArticle;
    private String contenu;
    private static BDUtilisateurs bdutil=BDUtilisateurs.bd;
    
    public Commentaire(){}
    public Commentaire(int _idAuteur, int _idArticle, String _contenu){
	this.idAuteur=_idAuteur;
        this.idArticle=_idArticle;
	this.contenu = _contenu;
    }
    
    public Commentaire(int _id,int _idAuteur, int _idArticle,String _contenu)
    {
        this(_idAuteur,_idArticle,_contenu);
        this.setId(_id);
    }
    
    public boolean editerCommentaire(String nouveauContenu, int _idAuteur ){
	boolean resultat = false;
	if(_idAuteur==idAuteur){
	    resultat=true;
	    contenu = nouveauContenu;
	}
	return resultat;
    }
    
    
    public String getContenu(){
	return contenu;
    }

    public int getIdRedacteur(){
	return idAuteur;
    }
    public Abonne getRedacteur()throws Exception{
        return (Abonne)bdutil.getUtilisateur(getIdRedacteur());
    }
    public int getIdArticle(){
        return idArticle;
    }
    public void setContenu(String _contenu){
	contenu = _contenu;
    }
    public void setIdRedacteur(int id){
	idAuteur=id;
    }
    public void setIdArticle(int id){
        idArticle=id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    
    public boolean equals(Object o)
    {
        if(o instanceof Commentaire)
            return (((Commentaire)o).getId()==this.getId());
        else return false;
    }
    public String toString()
    {
        StringBuilder str=new StringBuilder();
        str.append("id : "+id);
        str.append("\nid Article : "+idArticle);
        str.append("\nid Auteur : "+idAuteur);
        str.append("\ncontenu : "+contenu);
        return str.toString();
    }

}
