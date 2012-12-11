package controleur.profil;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import bd.*;

public class RegisterAction extends Action {
	public ActionForward execute (ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BeanAbonne bean = (BeanAbonne)actionForm;
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordBis = request.getParameter("passwordBis");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("email");

		System.out.println(login+password+passwordBis+nom+prenom+mail);
		if(login !="" && password !="" && passwordBis.equals(password) && nom != "" && prenom != "" && mail != ""){
                    
                    bean.setPrenom(prenom);
                    bean.setNom(nom);
                    bean.setEmail(mail);
                    bean.setLogin(login);
                    bean.setPassword(password);
                    System.out.println("Succes !!!!");
                    
                    System.out.println(bean.getAbonne());
                    BDUtilisateurs.bd.addUtilisateur(bean.getAbonne());
                    
                    return mapping.findForward("succes");
		}
		else
			return mapping.findForward("echec");
	}
}
