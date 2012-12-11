import java.io.*;
import java.text.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FiltreEncodage implements Filter {
	private FilterConfig fconfig;
	
	
	public void init (FilterConfig fconfig) {this.fconfig = fconfig;}
	
	public void destroy () {} 
	
	public void doFilter (ServletRequest requete, ServletResponse reponse, FilterChain chain) throws ServletException, IOException{
		requete.setCharacterEncoding("UTF-8");
			reponse.setContentType("text/html");
			reponse.setCharacterEncoding("UTF-8");
		chain.doFilter(requete,reponse); 
		
		requete.setAttribute("numfiltre","Encodage");
			reponse.setContentType("text/html");
			reponse.setCharacterEncoding("UTF-8");
		
	}
	
}
