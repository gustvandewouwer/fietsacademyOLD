package be.vdab.repositories;

import java.util.Optional;

import javax.persistence.EntityManager;

import be.vdab.entities.Docent;

public class DocentRepository {
	public Optional<Docent> read(long id, EntityManager entityManager) {
		return Optional.ofNullable(entityManager.find(Docent.class, id));
	}

	public void create(Docent docent, EntityManager entityManager) {
		entityManager.persist(docent);
	}

	public void delete(long id, EntityManager entityManager) {
		read(id, entityManager).ifPresent(docent -> entityManager.remove(docent));
	}
}