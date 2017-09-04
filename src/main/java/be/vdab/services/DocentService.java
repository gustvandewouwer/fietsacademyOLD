package be.vdab.services;

import java.util.Optional;

import javax.persistence.EntityManager;

import be.vdab.entities.Docent;
import be.vdab.filters.JPAFilter;
import be.vdab.repositories.DocentRepository;

public class DocentService {
	private final DocentRepository docentRepository = new DocentRepository();

	public Optional<Docent> read(long id) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return docentRepository.read(id, entityManager);
		} finally {
			entityManager.close();
		}
	}
}
