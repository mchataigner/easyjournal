package twmodele.profil;

import java.io.*;
import java.text.*;
import java.util.*;

public abstract class Utilisateur implements Serializable {

    /* Attributs */
    private String login="";
    private String motDePasse="";
    private int id=0;	
    private boolean estConnecte = false;
    
    /* Constructeur */
    public Utilisateur()throws Exception{};
    public Utilisateur(int _id, String _login, String _motDePasse)throws Exception{
        this.login = _login;
        this.motDePasse = _motDePasse;
        this.id = _id;
    }
	
    /* Méthodes */
    public String getLogin(){
        return this.login;
    }
		
    public void setLogin(String _login){
        this.login = _login;
    }
	
	
    public String getPassword(){
        return this.motDePasse;
    }	
	
    public void setPassword(String _motDePasse){
        this.motDePasse = _motDePasse;
    }

   public String getPasswordBis(){
        return this.motDePasse;
    }	
	
    public void setPasswordBis(String _motDePasse){
        this.motDePasse = _motDePasse;
    }


    public int getId(){
        return this.id;
    }
    
    public void setId(int _id)
    {
        this.id=_id;
    }

    public boolean estConnecte(){
        return this.estConnecte;
    }
			
    public boolean verifierMotDePasse(String _motDePasse){
        return _motDePasse.equals(this.motDePasse);
    }
	
    public boolean seConnecter(){
        return this.estConnecte = true;
    }

    public boolean seDeconnecter(){
        return this.estConnecte = false;
    }

	
	
}
	
