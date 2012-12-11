package bd;



import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.filter.*;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.io.*;

public abstract class BD
{
    private String base;
    private String fileName;
    private org.jdom.Document document;
    private Element racine;
    
    public BD(String file,String base)
    {
        fileName=file;
        this.base=base;
        init();
    }
    
    protected void init()
    {
	SAXBuilder sxb = new SAXBuilder();
        File leFile=null;
        try
            {
                leFile=new File(fileName);
                document = sxb.build(leFile);
            }
        catch(Exception e){
            document=new Document(new Element(base));
        };
        racine = document.getRootElement();
    }
    
    public boolean saveBD()
    {
        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        
        try
            {
                sortie.output(document, new FileOutputStream(fileName));
            }
        catch (java.io.IOException e){}
        return true;
    }
    
    public void affiche()
    {
        try
            {
                XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                sortie.output(document, System.out);
            }
        catch (java.io.IOException e){}
    }
    
    public Element getRootElement()
    {
        return racine;
    }
}
