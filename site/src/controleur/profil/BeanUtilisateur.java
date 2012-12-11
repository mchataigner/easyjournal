package controleur.profil;

import twmodele.profil.Utilisateur;
import  java.beans.*;
import  java.io.*;
import  java.text.*;
import  java.util.*;
import org.apache.struts.action.*;



public abstract class BeanUtilisateur extends ActionForm implements Serializable {
    
    /* Attributs */
    Utilisateur utilisateur;// = new Utilisateur();

    /* Constructeur */
    public BeanUtilisateur() {
    }

    /* Méthodes */	
    public String getLogin(){
        return utilisateur.getLogin();
    }    
	
    public void setLogin(String login){
        utilisateur.setLogin(login);
    }
	
    public String getMotDePasse(){
        return utilisateur.getPassword();
    } 
	    
    public void setMotDePasse(String motDePasse){
        utilisateur.setPassword(motDePasse);
    }
	
    public boolean verifierMotDePasse(String motDePasse){
        return utilisateur.verifierMotDePasse(motDePasse);
    }
	
    public int getId(){
        return utilisateur.getId();
    }
	
    public boolean estConnecte(){
        return utilisateur.estConnecte();
    }
			
    public boolean seConnecter(){
        return utilisateur.seConnecter();
    }

    public boolean seDeconnecter(){
        return utilisateur.seDeconnecter();
    }
}

	

	
