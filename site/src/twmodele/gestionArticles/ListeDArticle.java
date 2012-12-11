package twmodele.gestionArticles;


import java.io.*;
import java.util.*;
import java.text.*;
public class ListeDArticle{
    private ArrayList<Integer> listeDArticle;
    public static void main(String args[]){
	ListeDArticle la = new ListeDArticle();
	la.ajouterArticle(4);
	la.enleverArticle(4);
	System.out.println(la.getNombreDArticles());
    }
    public ListeDArticle(){
	listeDArticle = new ArrayList<Integer>();
    }
    public boolean ajouterArticle(int idArticle){
	boolean resultat = false;
	if(!listeDArticle.contains(idArticle))
	    {
	    listeDArticle.add(idArticle);
	    resultat = true;
	}
	return resultat;
    }
    public boolean enleverArticle(int idArticle){
	boolean resultat = false;
	if(listeDArticle.contains(idArticle))
	    {
		listeDArticle.remove((Integer)idArticle);
	    resultat = true;
	}
	return resultat;
    }
    public int getNombreDArticles(){
	return listeDArticle.size();
    }
    public void setListeDArticle(ArrayList<Integer> _listeDArticle){
	listeDArticle = _listeDArticle;
    }
    public ArrayList<Integer> getListeDArticle(){
	return listeDArticle;
    }
    public ListeDArticle faireUneRecherche(String motcle){
	return null;//a coder
    }
}