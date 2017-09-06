package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Table(name = "cursussen")
//@DiscriminatorColumn(name = "soort")
public abstract class Cursus implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;

	@Override
	public String toString() {
		return naam;
	}
}