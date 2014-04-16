package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.services.ArtikelService;

/**
 * Servlet implementation class ArtikelToevoegenServlet
 */
@WebServlet("/artikels/toevoegen")
public class ArtikelToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/artikels/toevoegen.jsp";
	private static final ArtikelService artikelService=new ArtikelService();
	private static final String REDIRECT_URL="/artikels/toegevoegd";
       
  
    public ArtikelToevoegenServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String naam=request.getParameter("naam");
		List<String> fouten=new ArrayList<>();
		if(naam==null || naam.isEmpty()){
			fouten.add("naam moet ingevuld zijn");
		}
		if(!naam.matches("[a-zA-Z ]+")){
			fouten.add("enkel letters en spaties zijn toegelaten in de naam");
		}
		BigDecimal aankoopprijs=null;
		try{
			aankoopprijs=new BigDecimal(request.getParameter("aankoopprijs"));
			if(aankoopprijs.compareTo(BigDecimal.ZERO)<0){
				fouten.add("aankoopprijs moet groter dan nul zijn");
			}
		}catch(NumberFormatException ex){
			fouten.add("aankoopprijs moet een getal zijn");
			
		}
		BigDecimal verkoopprijs=null;
		try{
			verkoopprijs=new BigDecimal(request.getParameter("verkoopprijs"));
			if(verkoopprijs!=null && aankoopprijs!=null && verkoopprijs.compareTo(aankoopprijs)<0){
				fouten.add("verkoopprijs moet groter dan de aankoopprijs zijn");
			}
		}catch(NumberFormatException ex){
			fouten.add("verkoopprijs moet een getal zijn");
			
		}
		if(fouten.isEmpty()){
			Artikel artikel=new Artikel(naam, aankoopprijs, verkoopprijs);
			artikelService.create(artikel);
			
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+REDIRECT_URL+"?artikelNr="+artikel.getArtikelNr()));
		}else{
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
