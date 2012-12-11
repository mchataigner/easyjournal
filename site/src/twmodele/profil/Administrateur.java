package twmodele.profil;


public class Administrateur extends Utilisateur
{
    public Administrateur()throws Exception{}

    public Administrateur(int id, String login,String mdp)throws Exception
    {
        super(id,login,mdp);
    }

    public Administrateur(String login, String mdp)throws Exception
    {
        super();
        this.setLogin(login);
        this.setPassword(mdp);
    }
    
}