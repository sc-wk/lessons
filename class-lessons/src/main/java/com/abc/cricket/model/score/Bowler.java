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
@Table(name="BOWLER")
@Getter @Setter @ToString
public class Bowler {

	@Id
	@GeneratedValue(generator="SEQ_BOWLER")
	@SequenceGenerator(name="SEQ_BOWLER", initialValue=1, allocationSize=1)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="INNINGS_ID")
	private Integer inningsId;
	
	@ManyToOne
	@JoinColumn
	private Player player;
	
	@Column(name="RUNS")
	private int runs;
	
	@Column(name="OVERS")
	private int overs;
	
	@Column(name="MAIDENS")
	private int maidens;
	
	@Column(name="MAIDEN_IN_OVER")
	private boolean maidenInOver;
	
	@Column(name="BALL_IN_OVER")
	private int ballInOver;
	
	@Column(name="WIDES")
	private int wides;
	
	@Column(name="NOBALLS")
	private int noballs;
	
	@Column(name="WICKETS")
	private int wickets;
	
	@Column(name="ECO_RATE")
	private float economyRate;
	
	@Column(name="LAST_UPDATED")
	@Version
	@LastModifiedDate
	private Date lastUpdated;
	
	public synchronized void updateScore(int runsScored, Optional<ExtraType> extras, Optional<OutType> out) {
		runs = runs + runsScored;
		
		if(runsScored > 0)
			maidenInOver = false;
		
		extras.ifPresent(e->{
			if(e == ExtraType.NOBALL)
				noballs++;
			else if(e == ExtraType.WIDE)
				wides++;
		});
		
		if(!extras.isPresent()) 
			updateBowlingNumbers();
		
		out.ifPresent(o->{
			if(o.isBowlerWicket())
				wickets++;	
		});
		
		refreshEconomyRate();
	}
	
	private void updateBowlingNumbers() {
		// end of over detected
		if(ballInOver == 5) {
			overs++;
			ballInOver = 0;
			
			if(maidenInOver)
				maidens++;
			
			maidenInOver = true;
		} else 
			ballInOver++;
	
	}
	
	private void refreshEconomyRate() {
		setEconomyRate(overs==0 ? 0 : runs/overs);
	}
}
