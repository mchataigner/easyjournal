package controleur.profil;

import org.apache.struts.action.*;

public class ModProfilForm extends ActionForm {
    private String ancienMDP="";
    private String nouveauMDP="";
    private String prenom="";
    private String nom="";
    private String email="";
   
   
    public String getAncienMDP(){
        return this.ancienMDP;
    }
    
    public  void setAncienMDP(String _amdp){
        this.ancienMDP=_amdp;
    }
    
    public String getNouveauMDP(){
        return this.nouveauMDP;
    }
    
    public  void setNouveauMDP(String _nmdp){
        this.nouveauMDP=_nmdp;
    }
    
    public String getPrenom(){
        return this.prenom;
    }
    
    public  void setPrenom(String _p){
        this.prenom=_p;
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public  void setNom(String _n){
        this.nom=_n;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public  void setEmail(String _e){
        this.email=_e;
    }
}
