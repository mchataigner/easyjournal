package controleur.profil;

import twmodele.profil.Abonne;
import java.io.*;
import java.text.*;
import java.util.*;

public class BeanAbonne extends BeanUtilisateur{

    private Abonne abonne;

    /* Constructeur */
    public BeanAbonne(){
        try{
            abonne = new Abonne();
        }
        catch(Exception e){};
    }
    
    /* Méthodes */
        
    public String getLogin(){
        return abonne.getLogin();
    }    
	
    public void setLogin(String login){
        abonne.setLogin(login);
    }
	
    public String getPassword(){
        return abonne.getPassword();
    }
	    
    public void setPassword(String motDePasse){
        abonne.setPassword(motDePasse);
    }
    
     public String getPasswordBis(){
        return abonne.getPasswordBis();
    }
	  
	    
      public void setPasswordBis(String motDePasse){
        abonne.setPasswordBis(motDePasse);
    }
    
    
    public boolean verifierMotDePasse(String motDePasse){
        return abonne.verifierMotDePasse(motDePasse);
    }
	
    public int getId(){
        return abonne.getId();
    }
	
    public boolean estConnecte(){
        return abonne.estConnecte();
    }
			
    public boolean seConnecter(){
        return abonne.seConnecter();
    }

    public boolean seDeconnecter(){
        return abonne.seDeconnecter();
    }        
             
        
    public String getPrenom(){
        return abonne.getPrenom();
    }
    
    public void setPrenom(String prenom){
        abonne.setPrenom(prenom);
    }
        
    public String getNom(){
        return abonne.getNom();
    }
    
    public void setNom(String nom){
        abonne.setNom(nom);
    } 
    
    
    public String getEmail(){
        return abonne.getEmail();
    }
    
    public void setEmail(String email){
        abonne.setEmail(email);
    }

    public int getNombreArticlesVisites(){
        return abonne.getNombreArticlesVisites();
    }
    
     public int[] getArticlesDejaNotes(){
        return abonne.getArticlesDejaNotes();
    }

    public void addArticleVisite(){
        abonne.addArticleVisite();
    }
    
    public void addArticleNote(int _idArticle){
        abonne.addArticleNote(_idArticle);
    }
    
    
    public Abonne getAbonne(){
        return abonne;
    }
}
