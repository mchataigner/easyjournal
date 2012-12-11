package bd;


import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.filter.*;
import twmodele.gestionArticles.*;

import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;

import java.util.Collections;

public class BDArticles extends BD
{
    private static ArrayList<Article> lesArticles=new ArrayList<Article>();
    public static BDArticles bda=new BDArticles();
    private static int idlibre=0;
    
    
    public BDArticles()
    {
        super("/tmp/BDArticles.xml","BDArticles");
        initBDA();
    }
    
    public BDArticles(String fileName)
    {
        super(fileName,"BDArticles");
        initBDA();
    }
    
    public int getIdLibre()
    {
        return this.getBDIdLibre();
    }
    
    public int getBDIdLibre()
    {
        idlibre=-1;
        for(Article i:lesArticles)
            if(idlibre<i.getId())
                idlibre=i.getId();
        idlibre++;
        return idlibre;
    }
    
    private void initBDA()
    {
        List listArticles =this.getRootElement().getChildren("article");
        for(Object i:listArticles)
            {
                try
                    {
                        lesArticles.add(createArticle((Element)i));
                    }
                catch(Exception e){e.printStackTrace();}
            }
        getBDIdLibre();
    }
    
    private Article createArticle(Element article)throws Exception
    {
        List listCommentaires = article.getChildren("commentaire");
        int[] idCommentaires=new int[listCommentaires.size()];
        int k=0;
        Iterator i = listCommentaires.iterator();
        while(i.hasNext())
            {
                int id=((Element)i.next()).getAttribute("idCommentaire").getIntValue();
                idCommentaires[k]=id;
                k++;
            }
        return new Article(
                           article.getAttribute("id").getIntValue(),
                           article.getAttribute("idRedacteur").getIntValue(),
                           article.getChild("contenu").getText(),
                           article.getChild("titre").getText(),
                           article.getAttribute("noteMoyenne").getFloatValue(),
                           article.getAttribute("nombreNotes").getIntValue(),
                           idCommentaires 
                           );
    }
    
    
    public Article getArticle(int id)throws Exception
    {
        for(Article i:lesArticles)
            if(i.getId()==id)
                return i;
        throw new Exception("Il n'existe pas d'article avec cet id : "+id);
    }
    
    public Article getArticle(String titre)throws Exception
    {
        for(Article i:lesArticles)
            if(i.getTitre().equals(titre.trim()))
                return i;
        throw new Exception("Il n'existe pas d'article avec ce titre : "+titre.trim());
    }
    
    public Collection<Article> getArticles()
    {
        ArrayList<Article> result=new ArrayList<Article>();
        for(Article i:lesArticles)
            result.add(i);
        Collections.reverse(result);
        return result;
    }
    
    public Collection<Article> getArticles(int idRedacteur)
    {
        ArrayList<Article> result=new ArrayList<Article>();
        for(Article i:lesArticles)
            if(i.getIdRedacteur()==idRedacteur)
                result.add(i);
        Collections.reverse(result);
        return result;
    }
    
    public Collection<Article> getArticles(String titre)throws Exception
    {
        ArrayList<Article> result=new ArrayList<Article>();
        for(Article i:lesArticles)
            if(i.getTitre().contains(titre.trim()))
                result.add(i);
        Collections.reverse(result);
        return result;
    }
    
    public Article getDernierArticle()
    {
        if(lesArticles.size()==0)
            return null;
        else
            return lesArticles.get(lesArticles.size()-1);
    }
    
    public void delArticle(int id)throws Exception
    {
        
        Collection<Commentaire> lesComm=BDCommentaires.bd.getCommentaires(id);
        for(Commentaire com:lesComm)
            {
                BDCommentaires.bd.delCommentaire(com);
            }
        
        
        List listArticles=this.getRootElement().getChildren("article");
        ArrayList<Element> listArticlesTmp=new ArrayList<Element>();
        boolean trouve=false;
        Iterator j=listArticles.iterator();
        Element article=null;
        while(j.hasNext())
            {
                article=(Element)(j.next());
                try
                    {
                        if((article.getAttribute("id").getIntValue())==id)
                            trouve=true;
                        else
                            listArticlesTmp.add(article);
                    }
                catch(Exception e){};
            }
        System.out.println(trouve);
        if(trouve)
            {
                lesArticles.remove(this.getArticle(id));
                this.getRootElement().removeContent();
                for(Element kk:listArticlesTmp)
                    this.getRootElement().addContent(kk);
                this.saveBD();
            }
        else
            throw new Exception("impossible de supprimer l'article "+id);
    }
    
    public void setArticle(Article a)throws Exception
    {
        try
            {
                this.getArticle(a.getId());
                List listArticles =this.getRootElement().getChildren("article");
                boolean trouve=false;
                Iterator j=listArticles.iterator();
                Element article=null;
                while(j.hasNext()&&!trouve)
                    {
                        article=(Element)(j.next());
                        try
                            {
                                if((article.getAttribute("id").getIntValue())==a.getId())
                                    trouve=true;
                            }
                        catch(Exception e){e.printStackTrace();};
                    }
                
                article.setAttribute(new Attribute("idRedacteur",Integer.toString(a.getIdRedacteur())));
                article.setAttribute(new Attribute("nombreNotes",Integer.toString(a.getNombreNotes())));
                int[] lesCommentaires=a.getIdCommentaires();
                article.removeChildren("commentaire");
                for (int i=0;i<lesCommentaires.length;i++)
                    article.addContent((new Element("commentaire")).setAttribute("idCommentaire",Integer.toString(lesCommentaires[i])));
                article.removeChildren("titre");
                article.addContent((new Element("titre")).setText(a.getTitre()));
                article.removeChildren("contenu");
                article.addContent((new Element("contenu")).setText(a.getContenu()));
                article.setAttribute((new Attribute("noteMoyenne",Float.toString(a.getNoteMoyenne()))));
                
                
                int index=-1;
                trouve=false;
                int i=0;
                while(!trouve&&i<lesArticles.size())
                    {
                        if(lesArticles.get(i).getId()==a.getId())
                            {
                                index=i;
                                trouve=true;
                            }
                        i++;
                    }
                lesArticles.set(index,a);
                this.saveBD();
            }
        catch(Exception e)
            {
                this.addArticle(a);
            }

    }
    
    public void addArticle(Article a)throws Exception
    {
        try
            {
                this.getArticle(a.getId());
                a.setId(this.getIdLibre());
            }
        catch(Exception e)
            {
            }
        /*        
                  if(this.getArticle(a.getTitre())!=null)
                  {
                  throw new Exception("titre d'article déjà présent dans la base");
                  }
        */

        
        Element article=new Element("article");
        
        article.setAttribute(new Attribute("id",Integer.toString(a.getId())));
        article.setAttribute(new Attribute("idRedacteur",Integer.toString(a.getIdRedacteur())));
        article.setAttribute(new Attribute("nombreNotes",Integer.toString(a.getNombreNotes())));
        int[] lesCommentaires=a.getIdCommentaires();
        for (int i=0;i<lesCommentaires.length;i++)
            article.addContent((new Element("commentaire")).setAttribute("idCommentaire",Integer.toString(lesCommentaires[i])));
        article.addContent((new Element("titre")).setText(a.getTitre()));
        article.addContent((new Element("contenu")).setText(a.getContenu()));
        article.setAttribute((new Attribute("noteMoyenne",Float.toString(a.getNoteMoyenne()))));
        Element laRacine=this.getRootElement();
        laRacine.addContent(article);
        
        lesArticles.add(a);
        this.getBDIdLibre();
        this.saveBD();
    }
}
