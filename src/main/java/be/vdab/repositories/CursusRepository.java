package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Cursus;

public class CursusRepository extends AbstractRepository {
	public List<Cursus> findByNaamContains(String woord) {
		return getEntityManager().createNamedQuery("Cursus.findByNaamContains", Cursus.class)
				.setParameter("zoals", '%' + woord + '%').getResultList();
	}
}