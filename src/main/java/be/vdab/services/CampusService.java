package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Campus;
import be.vdab.repositories.CampusRepository;

public class CampusService extends AbstractService {
	private final CampusRepository campusRepository = new CampusRepository();

	public List<Campus> findByGemeente(String gemeente) {
		return campusRepository.findByGemeente(gemeente);
	}

	public List<Campus> findAll() { // voor later in de cursus
		return campusRepository.findAll();
	}

	public Optional<Campus> read(long id) { // voor later in de cursus
		return campusRepository.read(id);
	}
}