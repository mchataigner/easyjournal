package twmodele.profil;

import java.io.*;
import java.text.*;
import java.util.*;
import twmodele.gestionArticles.*;

public class Abonne extends Utilisateur{

    /* Attributs */
    private String prenom="";
    private String nom="";
    private String email="";
    private int nbArticlesVisites=0;
    private int [] articlesDejaNotes=new int [0];
    
    /* Constructeur */
    public Abonne()throws Exception{};
    public Abonne(int _id, String _login, String _motDePasse)throws Exception
    {
        super(_id, _login, _motDePasse);
        nbArticlesVisites = 0;
    }
    
    
    public Abonne(int _id, String _login, String _motDePasse, String _nom, String _prenom, String _email)throws Exception
    {
        super(_id,_login,_motDePasse);
        this.nom=_nom;
        this.prenom=_prenom;
        this.email=_email;
    }

    public Abonne(int _id, String _login, String _motDePasse, String _nom, String _prenom, String _email,int _nbArticlesVisites, int[] _articlesDejaNotes)throws Exception
    {
        super(_id,_login,_motDePasse);
        this.nom=_nom;
        this.prenom=_prenom;
        this.email=_email;
        this.nbArticlesVisites=_nbArticlesVisites;
        if(_articlesDejaNotes!=null)
            this.articlesDejaNotes=_articlesDejaNotes;
        else
            this.articlesDejaNotes=new int[0];
    }
    
    
    /* Méthodes */
    
    public String getPrenom(){
        return prenom;
    }
    
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    
    public String getNom(){
        return nom;
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }

    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    public int getNombreArticlesVisites(){
        return this.nbArticlesVisites;
    }
    
    public int getPoints()
    {
        return getNombreArticlesVisites();
        
    }
    
    public int[] getArticlesDejaNotes(){
        return this.articlesDejaNotes;
    }
    
    public void visiterArticle()
    {
        this.nbArticlesVisites++;
    }
    
    public void addArticleVisite(){
        this.nbArticlesVisites++;
    }

    public void noterArticle(int idArticle)
    {
        int[] tmp=new int[articlesDejaNotes.length+1];
        for(int i=0;i<articlesDejaNotes.length;i++)
            tmp[i]=articlesDejaNotes[i];
        tmp[articlesDejaNotes.length]=idArticle;
        articlesDejaNotes=tmp;
        tmp=null;
    }
    
    public void addArticleNote(int idArticle){
        this.noterArticle(idArticle);
    }
    
    
    
    
    
}
