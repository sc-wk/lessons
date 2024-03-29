/**
 * 
 */
package com.abc.cricket.model.score;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.LastModifiedDate;

import com.abc.cricket.model.Player;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@Entity
@Table(name="BATSMAN")
@Getter @Setter @ToString
public class Batsman {

	@Id
	@GeneratedValue(generator="SEQ_BATSMAN")
	@SequenceGenerator(name="SEQ_BATSMAN", initialValue=1, allocationSize=1)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="INNINGS_ID")
	private Integer inningsId;
	
	@ManyToOne
	@JoinColumn
	private Player player;
	
	@Column(name="RUNS")
	private int runs;
	
	@Column(name="BOWLS_FACED")
	private int bowlsFaced;
	
	@Column(name="FOURS_SCORED")
	private int foursScored;
	
	@Column(name="SIXES_SCORED")
	private int sixesScored;
	
	@Column(name="STRIKE_RATE")
	private float strikeRate;
	
	@Column(name="LAST_UPDATED")
	@Version
	@LastModifiedDate
	private Date lastUpdated;
	
	public synchronized void updateScore(int runsScored, Optional<BoundaryType> boundary) {
		bowlsFaced++;
		runs = runs + runsScored;
		
		refreshStrikeRate();
		
		boundary.ifPresent(bt->{
			if(bt==BoundaryType.FOUR)
				foursScored++;
			else
				sixesScored++;
		});
	}
	
	private void refreshStrikeRate() {
		setStrikeRate(bowlsFaced==0 ? 0 : runs/bowlsFaced * 100);
	}
}
