package be.vdab.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.NoResultException;

import be.vdab.entities.Campus;
import be.vdab.entities.Docent;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnId;

public class DocentRepository extends AbstractRepository {
	public Optional<Docent> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Docent.class, id));
	}

	public void create(Docent docent) {
		getEntityManager().persist(docent);
	}

	public void delete(long id) {
		read(id).ifPresent(docent -> getEntityManager().remove(docent));
	}

	public List<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot, int vanafRij, int aantalRijen) {
		return getEntityManager().createNamedQuery("Docent.findByWeddeBetween", Docent.class).setParameter("van", van)
				.setParameter("tot", tot).setFirstResult(vanafRij).setMaxResults(aantalRijen)
				.setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph(Docent.MET_CAMPUS))
				.getResultList();
	}

	public List<VoornaamEnId> findVoornamen() {
		return getEntityManager().createQuery(
				"select new be.vdab.valueobjects.VoornaamEnId(d.id, d.voornaam) from Docent d order by d.voornaam",
				VoornaamEnId.class).getResultList();
	}

	public BigDecimal findMaxWedde() {
		return getEntityManager().createQuery("select max(d.wedde) from Docent d", BigDecimal.class).getSingleResult();
	}

	public List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
		return getEntityManager().createQuery("select new be.vdab.valueobjects.AantalDocentenPerWedde(d.wedde,count(d))"
				+ " from Docent d group by d.wedde", AantalDocentenPerWedde.class).getResultList();
	}

	public void algemeneOpslag(BigDecimal factor) {
		getEntityManager().createNamedQuery("Docent.algemeneOpslag").setParameter("factor", factor).executeUpdate();
	}

	public Optional<Docent> findByRijksRegisterNr(long rijksRegisterNr) {
		try {
			return Optional.of(getEntityManager().createNamedQuery("Docent.findByRijksRegisterNr", Docent.class)
					.setParameter("rijksRegisterNr", rijksRegisterNr).getSingleResult());
		} catch (NoResultException ex) {
			return Optional.empty();
		}
	}

	public List<Docent> findBestBetaaldeVanEenCampus(Campus campus) {
		return getEntityManager().createNamedQuery("Docent.findBestBetaaldeVanEenCampus", Docent.class)
				.setParameter("campus", campus).getResultList();
	}

	public Optional<Docent> readWithLock(long id) {
		return Optional.ofNullable(getEntityManager().find(Docent.class, id, LockModeType.PESSIMISTIC_WRITE));
	}

}