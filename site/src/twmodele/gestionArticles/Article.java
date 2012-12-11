package twmodele.gestionArticles;

import bd.*;
import java.io.*;
import java.util.*;
import java.text.*;
import twmodele.profil.*;

public class Article{
    
    private int id=0;
    private int idRedacteur=0;
    private String contenu="";
    private String titre="";
    private float noteMoyenne=0;
    private int nbreNotesRecus=0;
    private int[] idCommentaires=new int[1];
    
    //private static BDCommentaire bdcomm=new BDCommentaire();
    private static BDUtilisateurs bdutil=BDUtilisateurs.bd;
    
    
    public Article()throws Exception{}    
    
    public Article(int idRedacteur, String _contenu, String titre)throws Exception{
	this.idRedacteur=idRedacteur;
	this.titre=titre;
	this.contenu = _contenu;
	this.nbreNotesRecus=0;
        this.idCommentaires=new int[1];
    }
    public Article(int id,int idRedacteur,String contenu,String titre,float noteMoyenne,int nbreNotesRecus,int[] idCommentaires)throws Exception{
        this.id=id;
        this.idRedacteur=idRedacteur;
	this.titre=titre;
	this.contenu =contenu;
        this.noteMoyenne=noteMoyenne;
	this.nbreNotesRecus=nbreNotesRecus;
        this.idCommentaires=idCommentaires;
    }
    public boolean noter(int idUtilisateur, int note){
	boolean resultat = true;
	if(nbreNotesRecus==0){
	    nbreNotesRecus++;
	    noteMoyenne=note;
	}
	else{
	    noteMoyenne=(noteMoyenne*nbreNotesRecus+note)/(nbreNotesRecus+1);
	    nbreNotesRecus++;
	}
	return resultat;
    }
    public boolean editerContenuArticle(String nouveauContenu, int _idRedacteur ){
	boolean resultat = false;
	if(idRedacteur==_idRedacteur){
	    contenu = nouveauContenu;
	    resultat = true;
	}
	return resultat;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    public int getId()
    {
        return id;
    }

    public int getNombreNotes()
    {
        return nbreNotesRecus;
    }

    public boolean editerTitreArticle(String nouveauTitre, int _idRedacteur ){
	boolean resultat = false;
	if(idRedacteur==_idRedacteur){
	    titre = nouveauTitre;
	    resultat = true;
	}
	return resultat;
    }

    public String getContenu(){
	return contenu;
    }
    public int getIdRedacteur(){
	return idRedacteur;
    }
    
    public Redacteur getRedacteur()throws Exception
    {
        return (Redacteur)bdutil.getUtilisateur(getIdRedacteur());
    }
    
    public float getNoteMoyenne(){
	return noteMoyenne;
    }
    public String getTitre(){
	return titre;
    }
    public void setContenu(String _contenu){
	contenu = _contenu;
    }
    public void setTitre(String _titre){
	titre = _titre;
    }
    public void setIdRedacteur(int id){
	idRedacteur=id;
    }

    public void commenter(int idCommentaire)
    {
        int[] idCommentairesTmp=new int[idCommentaires.length+1];
        idCommentairesTmp[idCommentaires.length]=idCommentaire;
        idCommentaires=idCommentairesTmp;
        idCommentairesTmp=null;
    }
    
    public int[] getIdCommentaires()
    {
        return idCommentaires;
    }
    
    /*  public ArrayList<Commentaire> getCommentaires()
      {
		int[] idCom=getIdCommentaires();
		ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();
		for(int i=0;i<idCom.length;i++)
			(commentaires).add(bdcomm.getCommentaire(idCom[i]));
		return commentaires;
      }
     */
    
    public boolean equals(Object o)
    {
        if(o instanceof Article)
            return (((Article)o).getId()==this.getId());
        else return false;
    }
    
    public String toString()
    {
        StringBuilder str=new StringBuilder();
        str.append("id : "+id);
        str.append("\nid redacteur : "+idRedacteur);
        str.append("\ntitre : "+titre);
        str.append("\ncontenu : "+contenu);
        str.append("\nnote moyenne : "+noteMoyenne);
        str.append("\nnombre de notes reçues : "+nbreNotesRecus);
        return str.toString();
    }
   
}
