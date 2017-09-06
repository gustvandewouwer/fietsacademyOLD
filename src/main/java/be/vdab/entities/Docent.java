package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import be.vdab.enums.Geslacht;

@Entity
@Table(name = "docenten")
// @NamedQuery(name = "Docent.findByWeddeBetween",
// query = "select d from Docent d where d.wedde between :van and :tot order by
// d.wedde, d.id")
public class Docent implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private long rijksRegisterNr;

	@Enumerated(EnumType.STRING)
	private Geslacht geslacht;

	@ElementCollection
	@CollectionTable(name = "docentenbijnamen", joinColumns = @JoinColumn(name = "docentid"))
	@Column(name = "Bijnaam")
	private Set<String> bijnamen;

	public String getNaam() {
		return voornaam + ' ' + familienaam;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public BigDecimal getWedde() {
		return wedde;
	}

	public long getRijksRegisterNr() {
		return rijksRegisterNr;
	}

	public Geslacht getGeslacht() {
		return geslacht;
	}

	public Set<String> getBijnamen() {
		return Collections.unmodifiableSet(bijnamen);
	}

	public Docent(String voornaam, String familienaam, BigDecimal wedde, Geslacht geslacht, long rijksRegisterNr) {
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setWedde(wedde);
		setGeslacht(geslacht);
		setRijksRegisterNr(rijksRegisterNr);
		bijnamen = new HashSet<>();
	}

	protected Docent() {
	}// default constructor is vereiste voor JPA

	public static boolean isVoornaamValid(String voornaam) {
		return voornaam != null && !voornaam.trim().isEmpty();
	}

	public static boolean isFamilienaamValid(String familienaam) {
		return familienaam != null && !familienaam.trim().isEmpty();
	}

	public static boolean isWeddeValid(BigDecimal wedde) {
		return wedde != null && wedde.compareTo(BigDecimal.ZERO) >= 0;
	}

	public static boolean isRijksRegisterNrValid(long rijksRegisterNr) {
		long getal = rijksRegisterNr / 100;
		if (rijksRegisterNr / 1_000_000_000 < 50) {
			getal += 2_000_000_000;
		}
		return rijksRegisterNr % 100 == 97 - getal % 97;
	}

	public void setVoornaam(String voornaam) {
		if (!isVoornaamValid(voornaam)) {
			throw new IllegalArgumentException();
		}
		this.voornaam = voornaam;
	}

	public void setFamilienaam(String familienaam) {
		if (!isFamilienaamValid(familienaam)) {
			throw new IllegalArgumentException();
		}
		this.familienaam = familienaam;
	}

	public void setWedde(BigDecimal wedde) {
		if (!isWeddeValid(wedde)) {
			throw new IllegalArgumentException();
		}
		this.wedde = wedde;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}

	public void setRijksRegisterNr(long rijksRegisterNr) {
		if (!isRijksRegisterNrValid(rijksRegisterNr)) {
			throw new IllegalArgumentException();
		}
		this.rijksRegisterNr = rijksRegisterNr;

	}

	public void opslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		wedde = wedde.multiply(factor).setScale(2, RoundingMode.HALF_UP);
	}

	public void addBijnaam(String bijnaam) {
		bijnamen.add(bijnaam);
	}

	public void removeBijnaam(String bijnaam) {
		bijnamen.remove(bijnaam);
	}
}