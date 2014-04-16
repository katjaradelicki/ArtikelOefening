package be.vdab.services;

import javax.persistence.EntityManager;

import be.vdab.dao.ArtikelDAO;
import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;

public class ArtikelService {
	private final ArtikelDAO artikelDao=new ArtikelDAO();
	
	public void create(Artikel artikel){
		
		EntityManager entityManager=  JPAFilter.getEntityManager();
		try{
		entityManager.getTransaction().begin();
		artikelDao.create(artikel, entityManager);
		entityManager.getTransaction().commit();
		}catch(RuntimeException ex){
			entityManager.getTransaction().rollback();
			throw ex;
		}finally{
			entityManager.close();
		}
		
	}

}
