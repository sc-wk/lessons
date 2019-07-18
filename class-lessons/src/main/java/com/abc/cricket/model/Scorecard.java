package com.abc.cricket.model.dynamicdata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.abc.cricket.model.staticdata.Match;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham.Chakravarti
 *
 */
@Entity
@Table(name="SCORECARD")
@Getter @Setter @ToString

public class Scorecard {
	
	@Id
	@SequenceGenerator(name="SEQ_SCORECARD")
	@Column(name="ID")
	private int id;
	
	@OneToOne
	@JoinColumn
	private Match match;
	
	@OneToOne
	@JoinColumn
	private Inning inning1;
	
	@OneToOne
	@JoinColumn
	private Inning inning2;
	
	
}
