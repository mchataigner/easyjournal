import twmodele.profil.*;
import twmodele.gestionArticles.*;
import controleur.profil.*;
import controleur.gestionArticles.*;
import bd.*;
import java.util.Scanner;
public class MainTest
{
    
    public static void main(String[] args)throws Throwable
    {
        Scanner scn=new Scanner(System.in);
        //new Article();
        //new Redacteur();
        BDArticles bda=BDArticles.bda;
        BDUtilisateurs bd=BDUtilisateurs.bd;
        BDCommentaires bdc=BDCommentaires.bd;
        
        //bd.addUtilisateur(new Administrateur("dieu","dieu"));
        //        bd.upgradeAbonne(bd.getUtilisateur("aaa"));
        
        bda.setArticle(new Article(0,0,"pouik","kiki",0,0,new int[0]));
        
        // new Commentaire();
        
        
        // bdc.addCommentaire(new Commentaire());
        // bdc.addCommentaire(new Commentaire(0,2,0,"prout"));
        // bdc.setCommentaire(new Commentaire(0,2,0,"prout"));
        
        // int[] lesArt=new int[1];
        // lesArt[0]=0;
        // String login=scn.nextLine();
        // //Utilisateur testU=bd.getUtilisateur(0);
        // //System.out.println(testU);
        // new Redacteur();
        // bd.addUtilisateur(new Redacteur(2,login,"ki","prt","çarche!!!","hihi@fsdfsd",2,lesArt,lesArt));
        // //bd.saveBD();
        // bda.addArticle(new Article());
        
        // //(int id,int idRedacteur,String contenu,String titre,int noteMoyenne,int nbreNotesRecus,int[] idCommentaires)
        // bda.setArticle(new Article(56,1,"prout prout","hihi ça marche",0,0,lesArt));
        // bda.saveBD();
        // //bd.affiche();
        // //        Redacteur test=bda.getArticle(0).getRedacteur();
        // //System.out.println(test);
    }
}