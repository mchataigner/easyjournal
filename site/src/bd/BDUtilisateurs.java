package bd;


import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.filter.*;
import twmodele.profil.*;
import twmodele.gestionArticles.*;
import java.util.*;




public class BDUtilisateurs extends BD
{
    
    private static ArrayList<Utilisateur> lesUtilisateurs=new ArrayList<Utilisateur>();
    public static BDUtilisateurs bd=new BDUtilisateurs();
    private static int idlibre=0;
    
    public BDUtilisateurs()
    {
        super("/tmp/BDUtilisateurs.xml","BDUtilisateurs");
        initBDU();
    }
    
    public BDUtilisateurs(String fileName)
    {
        super(fileName,"BDUtilisateurs");
        initBDU();
    }
    
    private void initBDU()
    {
        List listUtilisateurs =this.getRootElement().getChildren("redacteur");
        for(Object i:listUtilisateurs)
            {
                try
                    {
                        lesUtilisateurs.add(createUtilisateur((Element)i));
                    }
                catch(Exception e){e.printStackTrace();}
            }
        
        listUtilisateurs =this.getRootElement().getChildren("abonne");
        for(Object i:listUtilisateurs)
            {
                try
                    {
                        lesUtilisateurs.add(createUtilisateur((Element)i));
                    }
                catch(Exception e){e.printStackTrace();}
            }
        
        listUtilisateurs =this.getRootElement().getChildren("administrateur");
        for(Object i:listUtilisateurs)
            {
                try
                    {
                        lesUtilisateurs.add(createUtilisateur((Element)i));
                    }
                catch(Exception e){e.printStackTrace();}
            }
        try
            {
                if(lesUtilisateurs.size()==0)
                    this.addUtilisateur(new Administrateur("admin","admin"));
            }
        catch(Exception e){};
        getBDIdLibre();
        
        
    }
    
    public int getIdLibre()
    {
        return getBDIdLibre();
    }
    
    public int getBDIdLibre()
    {
        idlibre=-1;
        for(Utilisateur i:lesUtilisateurs)
            if(idlibre<i.getId())
                idlibre=i.getId();
        idlibre++;
        return idlibre;
    }


    private Utilisateur createUtilisateur(Element utilisateur)throws Exception
    {
        if(!utilisateur.getName().equals("administrateur"))
           {
               List listArticlesNotes = utilisateur.getChild("articlesNotes").getChildren("article");
               Iterator i = listArticlesNotes.iterator();
               int [] idArticlesNotes=new int[listArticlesNotes.size()];
               int k=0;
               while(i.hasNext())
                   {
                       idArticlesNotes[k]=((Element)i.next()).getAttribute("id").getIntValue();
                       k++;
                   }
               if(utilisateur.getName().equals("redacteur"))
                   {
                       List listArticlesEcris = utilisateur.getChild("articlesEcris").getChildren("article");
                       i = listArticlesNotes.iterator();
                       int [] idArticlesEcris=new int[listArticlesEcris.size()];
                       int kk=0;
                       while(i.hasNext())
                           {
                               idArticlesEcris[kk]=((Element)i.next()).getAttribute("id").getIntValue();
                               kk++;
                           }
                       
                       return (Utilisateur)(new Redacteur(utilisateur.getAttribute("id").getIntValue(),
                                                          utilisateur.getAttribute("login").getValue(),
                                                          utilisateur.getAttribute("motdepasse").getValue(),
                                                          utilisateur.getAttribute("nom").getValue(),
                                                          utilisateur.getAttribute("prenom").getValue(),
                                                          utilisateur.getAttribute("email").getValue(),
                                                          utilisateur.getAttribute("nbArticlesVisites").getIntValue(),
                                                          idArticlesNotes,
                                                          idArticlesEcris));
                   }
               else if(utilisateur.getName().equals("abonne"))
                   {
                       return (Utilisateur)(new Abonne(utilisateur.getAttribute("id").getIntValue(),
                                                       utilisateur.getAttribute("login").getValue(),
                                                       utilisateur.getAttribute("motdepasse").getValue(),
                                                       utilisateur.getAttribute("nom").getValue(),
                                                       utilisateur.getAttribute("prenom").getValue(),
                                                       utilisateur.getAttribute("email").getValue(),
                                                       utilisateur.getAttribute("nbArticlesVisites").getIntValue(),
                                                       idArticlesNotes));
                   }
           }
        else if(utilisateur.getName().equals("administrateur"))
            {
                return new Administrateur(utilisateur.getAttribute("id").getIntValue(),
                                          utilisateur.getAttribute("login").getValue(),
                                          utilisateur.getAttribute("motdepasse").getValue()
                                          );
                
            }
        return null;
    }
    
    public Utilisateur getUtilisateur(int id)throws Exception
    {
        for(Utilisateur i:lesUtilisateurs)
            if(i.getId()==id)
                return i;
        throw new Exception("Il n'existe pas d'utilisateur pour cet id : "+id);
    }
    
    public Utilisateur getUtilisateur(String login)throws Exception
    {
        for(Utilisateur i:lesUtilisateurs)
            if(i.getLogin().equals(login))
                return i;
        throw new Exception("Il n'existe pas d'utilisateur pour ce login : "+login);
    }
    
    public Collection<Utilisateur> getUtilisateurs()
    {
        return lesUtilisateurs;
    }
    
    
    
    public void delUtilisateur(Utilisateur u)throws Exception
    {
        Collection<Article> lesArts=BDArticles.bda.getArticles(u.getId());
        for(Article art:lesArts)
            {
                BDArticles.bda.delArticle(art.getId());
            }
        
        Collection<Commentaire> lesComs=BDCommentaires.bd.getCommentairesAbonne(u.getId());
        for(Commentaire com:lesComs)
            BDCommentaires.bd.delCommentaire(com);
        
        
        
        
        
        List listUtilisateurs =this.getRootElement().getChildren("redacteur");
        ArrayList<Element> listUtilisateursTmp=new ArrayList<Element>();
        boolean trouve=false;
        Iterator j=listUtilisateurs.iterator();
        Element utilisateur=null;
        int index=-1;
        while(j.hasNext())
            {
                index++;
                utilisateur=(Element)(j.next());
                try
                    {
                        if((utilisateur.getAttribute("id").getIntValue())==u.getId())
                            trouve=true;
                        else
                            listUtilisateursTmp.add(utilisateur);
                    }
                catch(Exception e){};
            }
        
        
        listUtilisateurs=this.getRootElement().getChildren("abonne");
        j=listUtilisateurs.iterator();
        while(j.hasNext())
            {
                index++;
                utilisateur=(Element)(j.next());
                try
                    {
                        if((utilisateur.getAttribute("id").getIntValue())==u.getId())
                            trouve=true;
                        else
                            listUtilisateursTmp.add(utilisateur);
                    }
                catch(Exception e){};
            }
        
        listUtilisateurs=this.getRootElement().getChildren("administrateur");
        j=listUtilisateurs.iterator();
        while(j.hasNext())
            {
                index++;
                utilisateur=(Element)(j.next());
                try
                    {
                        if((utilisateur.getAttribute("id").getIntValue())==u.getId())
                            trouve=true;
                        else
                            listUtilisateursTmp.add(utilisateur);
                    }
                catch(Exception e){};
            }
        
        
        
        
        if(trouve)
            {
                
                lesUtilisateurs.remove(this.getUtilisateur(u.getId()));
                this.getRootElement().removeContent();
                for(Element kk:listUtilisateursTmp)
                    this.getRootElement().addContent(kk);
                this.saveBD();
            }
        else
            throw new Exception("impossible de supprimer le compte");
    }
    
    
    public void upgradeAbonne(Utilisateur u)throws Exception
    {
        if(! (this.getUtilisateur(u.getId()) instanceof Redacteur))
            {
                this.delUtilisateur(u);
                this.addUtilisateur(new Redacteur((Abonne)u));
            }
    }
    
    public void setUtilisateur(Utilisateur u)throws Exception
    {
        try
            {
                this.getUtilisateur(u.getId());
                Utilisateur verif=this.getUtilisateur(u.getLogin());
                
                if(verif.getId()!=u.getId())
                    throw new Exception("Login déjà utilisé");
                
                List listUtilisateurs =this.getRootElement().getChildren("redacteur");
                boolean trouve=false;
                Iterator j=listUtilisateurs.iterator();
                Element utilisateur=null;
                while(j.hasNext()&&!trouve)
                    {
                        utilisateur=(Element)(j.next());
                        try
                            {
                                if((utilisateur.getAttribute("id").getIntValue())==u.getId())
                                    trouve=true;
                            }
                        catch(Exception e){};
                    }
                
                if(!trouve)
                    {
                        listUtilisateurs=this.getRootElement().getChildren("abonne");
                        j=listUtilisateurs.iterator();
                        while(j.hasNext()&&!trouve)
                            {
                                utilisateur=(Element)(j.next());
                                try
                                    {
                                        if((utilisateur.getAttribute("id").getIntValue())==u.getId())
                                            trouve=true;
                                    }
                                catch(Exception e){};
                            }
                        if(!trouve)
                            {
                                listUtilisateurs=this.getRootElement().getChildren("administrateur");
                                j=listUtilisateurs.iterator();
                                while(j.hasNext()&&!trouve)
                                    {
                                        utilisateur=(Element)(j.next());
                                        try
                                            {
                                                if((utilisateur.getAttribute("id").getIntValue())==u.getId())
                                                    trouve=true;
                                            }
                                        catch(Exception e){};
                                        
                                    }
                            }
                    }
                utilisateur.setAttribute(new Attribute("login",u.getLogin()));
                utilisateur.setAttribute(new Attribute("motdepasse",u.getPassword()));
                if(u instanceof Administrateur)
                    {
                        utilisateur.setAttribute(new Attribute("nom",((Abonne)u).getNom()));
                        utilisateur.setAttribute(new Attribute("prenom",((Abonne)u).getPrenom()));
                        utilisateur.setAttribute(new Attribute("email",((Abonne)u).getEmail()));
                        utilisateur.setAttribute(new Attribute("nbArticlesVisites",Integer.toString(((Abonne)u).getNombreArticlesVisites())));
                        int[] articlesDejaNotes=((Abonne)u).getArticlesDejaNotes();
                        utilisateur.removeChildren("articlesNotes");
                        
                        Element elArticlesDejaNotes=new Element("articlesNotes");
                        for (int  i=0;i<articlesDejaNotes.length;i++)
                            elArticlesDejaNotes.addContent((new Element("article")).setAttribute("id",Integer.toString(i)));
                        utilisateur.addContent(elArticlesDejaNotes);
                        if(u instanceof Redacteur)
                            {
                                int[] articlesEcris=((Redacteur)u).getArticlesEcris();
                                Element elArticlesEcris=new Element("articlesEcris");
                                utilisateur.removeChildren("articlesEcris");
                                
                                for (int  i=0;i<articlesEcris.length;i++)
                                    elArticlesEcris.addContent((new Element("article")).setAttribute("id",Integer.toString(i)));
                                utilisateur.addContent(elArticlesEcris);
                            }
                    }
                else
                    {
                        System.err.println("Echec de reconnaissance du type de l'utilisateur.");
                    }
                
                int index=-1;
                trouve=false;
                int i=0;
                while(!trouve&&i<lesUtilisateurs.size())
                    {
                        if(lesUtilisateurs.get(i).getId()==u.getId())
                            {
                                index=i;
                                trouve=true;
                            }
                        i++;
                    }
                lesUtilisateurs.set(index,u);
                this.saveBD();
            }
        catch(Exception e)
            {
                this.addUtilisateur(u);
            }
    }
    
    
    
    public void addUtilisateur(Utilisateur u)throws Exception
    {
        try
            {
                this.getUtilisateur(u.getId());
                u.setId(this.getBDIdLibre());
            }
        catch(Exception e){};
        Utilisateur verif=null;
        try
            {
                verif=this.getUtilisateur(u.getLogin());
                
            }
        catch(Exception e)
            {
                //e.printStackTrace();
            };
        if(verif!=null)
            throw new Exception("Utilisateur déjà présent dans la base");
        
        Element utilisateur=null;
    	
        if(u instanceof Administrateur)
            {
                utilisateur=new Element("administrateur");
                utilisateur.setAttribute(new Attribute("id",Integer.toString(u.getId())));
                utilisateur.setAttribute(new Attribute("login",u.getLogin()));
                utilisateur.setAttribute(new Attribute("motdepasse",u.getPassword()));
                Element laRacine=this.getRootElement();
                laRacine.addContent(utilisateur);
                lesUtilisateurs.add(u);
                
                this.getBDIdLibre();
                this.saveBD();
                return;
            }
    	else if(u instanceof Redacteur)
            {
    		utilisateur=new Element("redacteur");
            }
   	else if(u instanceof Abonne)
            {
   		utilisateur=new Element("abonne");
            }
        else
            {
                System.err.println("Echec de reconnaissance du type de l'utilisateur.");
                return;
            }
        
        utilisateur.setAttribute(new Attribute("id",Integer.toString(u.getId())));
        utilisateur.setAttribute(new Attribute("login",u.getLogin()));
        utilisateur.setAttribute(new Attribute("motdepasse",u.getPassword()));
        utilisateur.setAttribute(new Attribute("nom",((Abonne)u).getNom()));
        utilisateur.setAttribute(new Attribute("prenom",((Abonne)u).getPrenom()));
        utilisateur.setAttribute(new Attribute("email",((Abonne)u).getEmail()));
        utilisateur.setAttribute(new Attribute("nbArticlesVisites",Integer.toString(((Abonne)u).getNombreArticlesVisites())));
        int[] articlesDejaNotes=((Abonne)u).getArticlesDejaNotes();
        Element elArticlesDejaNotes=new Element("articlesNotes");
        for (int  i=0;i<articlesDejaNotes.length;i++)
            elArticlesDejaNotes.addContent((new Element("article")).setAttribute("id",Integer.toString(i)));
        utilisateur.addContent(elArticlesDejaNotes);
        if(u instanceof Redacteur)
            {
                int[] articlesEcris=((Redacteur)u).getArticlesEcris();
                Element elArticlesEcris=new Element("articlesEcris");
    		for (int  i=0;i<articlesEcris.length;i++)
                    elArticlesEcris.addContent((new Element("article")).setAttribute("id",Integer.toString(i)));
                utilisateur.addContent(elArticlesEcris);
            }
   	else
            {
   		System.err.println("Echec de reconnaissance du type de l'utilisateur.");
            }

   	Element laRacine=this.getRootElement();
        laRacine.addContent(utilisateur);
        lesUtilisateurs.add(u);
    	
        this.getBDIdLibre();
        this.saveBD();
        
    }
    


    public boolean estUtilisateurValide(String _login, String _mdp)
    {
        try
            {
                Utilisateur u=this.getUtilisateur(_login);
                return u.verifierMotDePasse(_mdp);
            }
        catch(Exception e)
            {
                return false;
            }
    }
    
    
    
    
}
