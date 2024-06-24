package com.abc.cricket.model.dynamicdata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.abc.cricket.model.staticdata.Player;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="BATSMAN")
@Getter @Setter @ToString
public class Batsman {

	@Id
	@SequenceGenerator(name="SEQ_BATSMAN")
	@Column(name="ID")
	private int id;
	
	@ManyToOne
	@JoinColumn
	private Player player;
	
	@Column(name="RUNS")
	private int runs;
	
	@Column(name="BALLS")
	private int balls;
	
	@Column(name="FOURS")
	private int fours;
	
	@Column(name="SIXES")
	private int sixes;
	
	@Column(name="STRIKE_RATE")
	private int strikeRate;
	
	@OneToOne
	@JoinColumn(name="OUT_BY_ID")
	private OutBy outby;
	
}
