package be.vdab.dao;

import javax.persistence.EntityManager;

import be.vdab.entities.Artikel;

public class ArtikelDAO {
 public void create(Artikel artikel,EntityManager entityManager){
	 entityManager.persist(artikel);
 }
}
