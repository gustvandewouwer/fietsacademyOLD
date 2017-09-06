package be.vdab.services;

import java.util.List;

import be.vdab.entities.Cursus;
import be.vdab.repositories.CursusRepository;

public class CursusService extends AbstractService {
	private final CursusRepository cursusRepository = new CursusRepository();

	public List<Cursus> findByNaamContains(String woord) {
		return cursusRepository.findByNaamContains(woord);
	}
}