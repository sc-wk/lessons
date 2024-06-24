package com.abc.cricket.model.dynamicdata;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.abc.cricket.model.staticdata.Team;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham.Chakravarti
 *
 */
@Entity
@Table(name="INNING")
@Getter @Setter @ToString
public class Inning {

	
	@Id
	@SequenceGenerator(name="SEQ_INNING")
	@Column(name="ID")
	private int id;
	
	@ManyToOne
	@JoinColumn
	private Team teamToBat;
	
	@ManyToOne
	@JoinColumn
	private Team teamToBowl;
	
	@Column(name="RUNS")
	private int runs;	
	
	@Column(name="WICKETS")
	private int wickets;
	
	@Column(name="OVERS")
	private float overs;
		
	@OneToOne
	@JoinColumn
	private Extras extras;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="INNING_ID")
	private List<Batsman> batsman;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="INNING_ID")
	private List<Bowler> bowler;
	
}
