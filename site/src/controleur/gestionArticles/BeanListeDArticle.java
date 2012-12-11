package controleur.gestionArticles;



import twmodele.gestionArticles.ListeDArticle;
import java.beans.*;
import java.io.*;
import java.util.*;

public class BeanListeDArticle implements Serializable{
    private static final long serialVersionUID = 42;
    private ListeDArticle lDA;
	public BeanListeDArticle(){
	lDA = new ListeDArticle();
    }
    public void setlisteDArticle(ArrayList<Integer> listeDArticle){
	lDA.setListeDArticle(listeDArticle);
    }
    public ArrayList<Integer> getListeDArticle(){
	return lDA.getListeDArticle();
    }
}