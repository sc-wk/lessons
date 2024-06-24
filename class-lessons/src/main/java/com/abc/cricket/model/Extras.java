package com.abc.cricket.model.dynamicdata;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham.Chakravarti
 *
 */
@Entity
@Table(name="EXTRAS")
@Getter @Setter @ToString
public class Extras {

	@Id
	@SequenceGenerator(name="SEQ_EXTRAS")
	@Column(name="ID")
	private int id;
	
	@Column(name="BYE")
	private int bye;
	
	@Column(name="LEG_BYE")
	private int legBye;
	
	@Column(name="WIDE")
	private int wide;
	
	@Column(name="NO_BALL")
	private int noBall;
	
	@Column(name="PENALTY")
	private int penalty;
	
	
	
}
