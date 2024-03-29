package be.vdab.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("*.htm")
public class JPAFilter implements Filter{
	private static EntityManagerFactory entityManagerFactory;

	@Override
	public void destroy() {
		
		entityManagerFactory.close();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		entityManagerFactory=Persistence.createEntityManagerFactory("vdab2");
		
	}
	
	public static EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}

}
