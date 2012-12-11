package twmodele.profil;

//import controleur.gestionArticle; ?
import java.io.*;
import java.text.*;
import java.util.*;

public class Redacteur extends Abonne{

    private float noteMoyenne=0;
    private int nombreArticles=0;
    private int[] articlesEcris=new int[0];
    
    public Redacteur()throws Exception{}
    public Redacteur(int _id,String _login, String _motDePasse)throws Exception
    {
        super(_id,_login, _motDePasse);
    }

    public Redacteur(int _id, String _login, String _motDePasse, String _nom, String _prenom, String _email)throws Exception
    {
        super(_id,_login,_motDePasse,_nom,_prenom,_email);
    }
    
    public Redacteur(int _id, String _login, String _motDePasse, String _nom, String _prenom, String _email,int _nbArticlesVisites, int[] _articlesDejaNotes,int[] _articlesEcris)throws Exception
    {
        super(_id,_login,_motDePasse,_nom,_prenom,_email,_nbArticlesVisites,_articlesDejaNotes);
        if(_articlesEcris!=null)
            {
                articlesEcris=_articlesEcris;
                nombreArticles=articlesEcris.length;
            }
        else
            {
                articlesEcris=new int[0];
                nombreArticles=0;
            }
        obtenirNoteMoyenne();
    }
    
    public Redacteur(Abonne a)throws Exception
    {
        super(a.getId(),a.getLogin(),a.getPassword(),a.getNom(),a.getPrenom(),a.getEmail(),a.getNombreArticlesVisites(),a.getArticlesDejaNotes());
        articlesEcris=new int[0];
        nombreArticles=0;
        obtenirNoteMoyenne();
    }
    
    public void obtenirNoteMoyenne(){
        noteMoyenne=0;//appelBD()
        
    }
	
    public float getNoteMoyenne(){
        return noteMoyenne;
    }
    
    public int[] getArticlesEcris()
    {
        return articlesEcris;
    }

    /*public void setNoteMoyenne(float _noteMoyenne){
        this.noteMoyenne=_noteMoyenne;
        }*/
	
    public int getNombreArticles(){
        return nombreArticles;
    }
	
    /*public void setNombreArticles(int _nombreArticles){
        this.nombreArticles=_nombreArticles;
        }*/
    
    public void ecrireArticle(int idArticle)
    {
        int[] tmp=new int[articlesEcris.length+1];
        for(int i=0;i<articlesEcris.length;i++)
            tmp[i]=articlesEcris[i];
        tmp[articlesEcris.length]=idArticle;
        articlesEcris=tmp;
        tmp=null;
        nombreArticles++;
    }
}
