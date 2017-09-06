package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TelefoonNr implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nummer;
	private boolean fax;
	private String opmerking;

	public TelefoonNr(String nummer, boolean fax, String opmerking) {
		this.nummer = nummer;
		this.fax = fax;
		this.opmerking = opmerking; // je maakt getters voor nummer,fax,opmerking
	}

	public String getNummer() {
		return nummer;
	}

	public boolean isFax() {
		return fax;
	}

	public String getOpmerking() {
		return opmerking;
	}

	protected TelefoonNr() {
	} // default constructor voor JPA

	@Override
	public String toString() {
		return nummer;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TelefoonNr)) {
			return false;
		}
		TelefoonNr telefoonNr = (TelefoonNr) obj;
		return nummer.equalsIgnoreCase(telefoonNr.nummer);
	}

	@Override
	public int hashCode() {
		return nummer.toUpperCase().hashCode();
	}
}