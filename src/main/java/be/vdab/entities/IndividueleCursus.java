package be.vdab.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@DiscriminatorValue("I")
@Table(name="individuelecursussen")
public class IndividueleCursus extends Cursus {
	private static final long serialVersionUID = 1L;
	private int duurtijd;
}