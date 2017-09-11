package be.vdab.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Campus;

public class CampusRepository extends AbstractRepository {
	public List<Campus> findByGemeente(String gemeente) {
		return getEntityManager().createNamedQuery("Campus.findByGemeente", Campus.class)
				.setParameter("gemeente", gemeente).getResultList();
	}

	public List<Campus> findAll() { 
		return getEntityManager().createNamedQuery("Campus.findAll", Campus.class).getResultList();
	}

	public Optional<Campus> read(long id) { // voor later in de cursus
		return Optional.ofNullable(getEntityManager().find(Campus.class, id));
	}
}