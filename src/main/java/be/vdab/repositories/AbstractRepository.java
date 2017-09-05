package be.vdab.repositories;

import javax.persistence.EntityManager;

import be.vdab.filters.JPAFilter;

abstract class AbstractRepository {
	EntityManager getEntityManager() {
		return JPAFilter.getEntityManager();
	}
}