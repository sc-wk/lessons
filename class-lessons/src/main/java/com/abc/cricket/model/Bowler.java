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


@Entity
@Table(name="BOWLER")
@Getter @Setter @ToString
public class Bowler {

	@Id
	@SequenceGenerator(name="SEQ_BOWLER")
	@Column(name="ID")
	private int id;
	
	@ManyToOne
	@JoinColumn
	private Player player;
	
	@Column(name="OVERS")
	private float overs;
	
	@Column(name="MAIDENS")
	private int maidens;

	@Column(name="RUNS")
	private int runs;
	
	@Column(name="WICKETS")
	private int wickets;
	
	@Column(name="NO_BALLS")
	private int noBalls;
	
	@Column(name="WIDE_BALLS")
	private int wideBalls;
	
	@Column(name="ECONOMY")
	private float economy;
	
	
}
