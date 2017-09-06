package be.vdab.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@DiscriminatorValue("G")
@Table(name="groepscursussen")
public class GroepsCursus extends Cursus {
	private static final long serialVersionUID = 1L;
	private LocalDate van;
	private LocalDate tot;
}