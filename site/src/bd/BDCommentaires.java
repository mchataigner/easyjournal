package bd;


import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.filter.*;
import twmodele.gestionArticles.Commentaire;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class BDCommentaires extends BD
{
    private static ArrayList<Commentaire> lesCommentaires=new ArrayList<Commentaire>();
    public static BDCommentaires bd=new BDCommentaires();
    private static int idlibre=0;
    
    public BDCommentaires()
    {
        super("/tmp/BDCommentaires.xml","BDCommentaires");
        initBDC();
    }
    
    public BDCommentaires(String fileName)
    {
        super(fileName,"BDCommentaires");
        initBDC();
    }
    
    public int getIdLibre()
    {
        return this.getBDIdLibre();
    }
    
    public int getBDIdLibre()
    {
        idlibre=-1;
        for(Commentaire i:lesCommentaires)
            if(idlibre<i.getId())
                idlibre=i.getId();
        idlibre++;
        return idlibre;
    }
    
    public void initBDC()
    {
        List listCommentaires = this.getRootElement().getChildren("commentaire");
        for(Object i:listCommentaires)
            {
                try
                    {
                        lesCommentaires.add(createCommentaire((Element)i));
                    }
                catch(Exception e){e.printStackTrace();};
            }
        getBDIdLibre();
    }
    
    private Commentaire createCommentaire(Element commentaire)throws Exception
    {
        return new Commentaire(commentaire.getAttribute("id").getIntValue(),
                               commentaire.getAttribute("idRedacteur").getIntValue(),
                               commentaire.getAttribute("idArticle").getIntValue(),
                               commentaire.getChild("contenu").getText()
                               );
    }
    
    public Commentaire getCommentaire(int id)throws Exception
    {
        for(Commentaire i:lesCommentaires)
            if(i.getId()==id)
                return i;
        throw new Exception("Il n'existe pas de commentaire avec cet id : "+id);
    }
    
    public Collection<Commentaire> getCommentaires()
    {
        ArrayList<Commentaire> result=new ArrayList<Commentaire>();
        for(Commentaire i:lesCommentaires)
            result.add(i);
        Collections.reverse(result);
        return result;
    }
    
    public Collection<Commentaire> getCommentaires(int idArticle)
    {
        ArrayList<Commentaire> result=new ArrayList<Commentaire>();
        for(Commentaire i:lesCommentaires)
            if(i.getIdArticle()==idArticle)
                result.add(i);
        return result;
    }
    
    
    public Collection<Commentaire> getCommentairesAbonne(int idAbonne)
    {
        ArrayList<Commentaire> result=new ArrayList<Commentaire>();
        for(Commentaire i:lesCommentaires)
            if(i.getIdRedacteur()==idAbonne)
                result.add(i);
        return result;
        
        
    }
    
    public void setCommentaire(Commentaire c)
    {
        try
            {
                this.getCommentaire(c.getId());
                List listCommentaires=this.getRootElement().getChildren("commentaire");
                boolean trouve=false;
                Iterator j=listCommentaires.iterator();
                Element commentaire=null;
                while(j.hasNext()&&!trouve)
                    {
                        commentaire=(Element)(j.next());
                        try
                            {
                                if((commentaire.getAttribute("id").getIntValue())==c.getId())
                                    trouve=true;
                            }
                        catch(Exception e){};
                    }
                commentaire.setAttribute(new Attribute("idRedacteur",Integer.toString(c.getIdRedacteur())));
                commentaire.setAttribute(new Attribute("idArticle",Integer.toString(c.getIdArticle())));
                commentaire.removeChildren("contenu");
                System.out.println("hihi");
                
                commentaire.addContent((new Element("contenu")).setText(c.getContenu()));
                
                int index=-1;
                trouve=false;
                int i=0;
                while(!trouve&&i<lesCommentaires.size())
                    {
                        if(lesCommentaires.get(i).getId()==c.getId())
                            {
                                index=i;
                                trouve=true;
                            }
                        i++;
                    }
                lesCommentaires.set(index,c);
                this.saveBD();
            }
        catch(Exception e)
            {
                this.addCommentaire(c);
            }
        //this.affiche(); //pour le débugage ;)
    }
    
    public void delCommentaire(Commentaire c)throws Exception
    {
        List listCommentaires=this.getRootElement().getChildren("commentaire");
        boolean trouve=false;
        ArrayList<Element> listCommentairesTmp=new ArrayList<Element>();
        this.getRootElement().removeContent();
        try
            {
                for(Element i:(List<Element>)listCommentaires)
                    if(i.getAttribute("id").getIntValue()!=c.getId())
                        this.getRootElement().addContent(i);
                lesCommentaires.remove(this.getCommentaire(c.getId()));
            }
        catch(Exception e){e.printStackTrace();};
        
        this.saveBD();
    }
    /*
    public void delCommentaire(int id)throws Exception
    {
        delCommentaire(this.getCommentaire(id));
    }
    */
    public void addCommentaire(Commentaire c)
    {
        try
            {
                this.getCommentaire(c.getId());
                c.setId(this.getIdLibre());
            }
        catch(Exception e){}
        
        Element commentaire=new Element("commentaire");
        
        commentaire.setAttribute(new Attribute("id",Integer.toString(c.getId())));
        commentaire.setAttribute(new Attribute("idRedacteur",Integer.toString(c.getIdRedacteur())));
        commentaire.setAttribute(new Attribute("idArticle",Integer.toString(c.getIdArticle())));
        commentaire.addContent((new Element("contenu")).setText(c.getContenu()));
        Element laRacine=this.getRootElement();
        laRacine.addContent(commentaire);

        lesCommentaires.add(c);
        this.getIdLibre();
        this.saveBD();
    }
}