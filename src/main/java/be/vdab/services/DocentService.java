package be.vdab.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import be.vdab.entities.Campus;
import be.vdab.entities.Docent;
import be.vdab.exceptions.DocentBestaatAlException;
import be.vdab.repositories.CampusRepository;
import be.vdab.repositories.DocentRepository;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnId;

public class DocentService extends AbstractService {
	private final DocentRepository docentRepository = new DocentRepository();
	private final CampusRepository campusRepository = new CampusRepository();

	public Optional<Docent> read(long id) {
		return docentRepository.read(id);
	}

	public void create(Docent docent) {
		if (docentRepository.findByRijksRegisterNr(docent.getRijksRegisterNr()).isPresent()) {
			throw new DocentBestaatAlException();
		}
		beginTransaction();
		try {
			docentRepository.create(docent);
			commit();
		} catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}

	public void delete(long id) {
		beginTransaction();
		try {
			docentRepository.delete(id);
			commit();
		} catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}

	public void opslag(long id, BigDecimal percentage) {
		beginTransaction();
		try {
			docentRepository.read(id).ifPresent(docent -> docent.opslag(percentage));
			commit();
		} catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}

	public List<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot, int vanafRij, int aantalRijen) {
		return docentRepository.findByWeddeBetween(van, tot, vanafRij, aantalRijen);
	}

	public List<VoornaamEnId> findVoornamen() {
		return docentRepository.findVoornamen();
	}

	public BigDecimal findMaxWedde() {
		return docentRepository.findMaxWedde();
	}

	public List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
		return docentRepository.findAantalDocentenPerWedde();
	}

	public void algemeneOpslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		try {
			beginTransaction();
			docentRepository.algemeneOpslag(factor);
			commit();
		} catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}

	public void bijnaamToevoegen(long id, String bijnaam) {
		beginTransaction();
		try {
			docentRepository.read(id).ifPresent(docent -> docent.addBijnaam(bijnaam));
			commit();
		} catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}

	public void bijnamenVerwijderen(long id, String[] bijnamen) {
		beginTransaction();
		try {
			docentRepository.read(id)
					.ifPresent(docent -> Arrays.stream(bijnamen).forEach(bijnaam -> docent.removeBijnaam(bijnaam)));
			commit();
		} catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}

	public List<Docent> findBestBetaaldeVanEenCampus(long id) {
		Optional<Campus> optionalCampus = campusRepository.read(id);
		if (optionalCampus.isPresent()) {
			return docentRepository.findBestBetaaldeVanEenCampus(optionalCampus.get());
		}
		return Collections.emptyList();
	}
}