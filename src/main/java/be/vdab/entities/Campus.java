package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.TelefoonNr;

@Entity
@Table(name = "campussen")
public class Campus implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@Embedded
	private Adres adres;

	@ElementCollection
	@CollectionTable(name = "campussentelefoonnrs", joinColumns = @JoinColumn(name = "campusid"))
	@OrderBy("fax")
	private Set<TelefoonNr> telefoonNrs;

	@OneToMany(mappedBy = "campus")
	// @JoinColumn(name = "campusid")
	@OrderBy("voornaam, familienaam")
	private Set<Docent> docenten;

	public Set<Docent> getDocenten() {
		return Collections.unmodifiableSet(docenten);
	}

	public void add(Docent docent) {
		docenten.add(docent);
		if (docent.getCampus() != this) { // als de andere kant nog niet bijgewerkt is
			docent.setCampus(this); // werk je de andere kant bij
		}
	}

	public void remove(Docent docent) {
		docenten.remove(docent);
		if (docent.getCampus() == this) { // als de andere kant nog niet bijgewerkt is
			docent.setCampus(null); // werk je de andere kant bij
		}
	}

	public Campus(String naam, Adres adres) {
		setNaam(naam);
		setAdres(adres);
		telefoonNrs = new LinkedHashSet<>();
		docenten = new LinkedHashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public Set<TelefoonNr> getTelefoonNrs() {
		return Collections.unmodifiableSet(telefoonNrs);
	}

	public void add(TelefoonNr telefoonNr) {
		telefoonNrs.add(telefoonNr);
	}

	public void remove(TelefoonNr telefoonNr) {
		telefoonNrs.remove(telefoonNr);
	}

	protected Campus() {
	}
}