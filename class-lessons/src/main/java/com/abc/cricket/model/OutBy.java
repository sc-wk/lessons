package com.abc.cricket.model.dynamicdata;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.abc.cricket.model.staticdata.Player;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham.Chakravarti
 *
 */
@Entity
@Table(name="OUT_BY")
@Getter @Setter @ToString
public class OutBy {


	@Id
	@SequenceGenerator(name="SEQ_OUT_BY")
	@Column(name="ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="BOWLDED_BY")
	private Player bowledBy;
	
	@ManyToOne
	@JoinColumn(name="CAUGHT_BY")
	private Player caughtBy;
	
	@ManyToOne
	@JoinColumn(name="STUMPED_BY")
	private Player stumpedBy; 
	
	@ManyToOne
	@JoinColumn(name="RUN_OUT_BY")
	private Player runOutBy;

	@Column(name="LBW")
	private boolean lbw;
	
}
