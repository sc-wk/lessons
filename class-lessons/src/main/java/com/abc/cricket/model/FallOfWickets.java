package com.abc.cricket.model.dynamicdata;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="FALL_OF_WICKETS")
@Getter @Setter @ToString

public class FallOfWickets {

	@Id
	@SequenceGenerator(name="SEQ_TEAM")
	@Column(name="ID")
	private int id;
	
	@Column(name="RUN_AT")
	private int runAt;
	
	@Column(name="WICKETS_AT")
	private int wicketAt;
	
	@OneToOne
	@JoinColumn(name="BATSMAN_ID")
	private Batsman batsman;
}
