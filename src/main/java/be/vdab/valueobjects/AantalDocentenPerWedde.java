package be.vdab.valueobjects;

import java.math.BigDecimal;

public class AantalDocentenPerWedde {
	private final BigDecimal wedde;
	private final long aantal;

	public AantalDocentenPerWedde(BigDecimal wedde, long aantal) {
		this.wedde = wedde;
		this.aantal = aantal;
	}

	public BigDecimal getWedde() {
		return wedde;
	}

	public long getAantal() {
		return aantal;
	}
}