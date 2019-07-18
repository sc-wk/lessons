/**
 * 
 */
package com.abc.cricket.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.abc.cricket.ApplicationException;
import com.abc.cricket.model.score.Innings;
import com.abc.cricket.model.score.Scorecard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham.Chakravarti
 *
 */
@Entity
@Table(name="MATCHES")
@Getter @Setter @ToString
public class Match {
	
	@Id
	@GeneratedValue(generator="SEQ_MATCHES")
	@SequenceGenerator(name="SEQ_MATCHES", initialValue=1, allocationSize=1)
	@Column(name="ID")
	private int id;
	
	@Column(name="WHEN")
	private Date when;
	
	@ManyToOne
	@JoinColumn
	private Stadium stadium;
	
	@ManyToOne
	@JoinColumn
	private Team team1;
	
	@ManyToOne
	@JoinColumn
	private Team team2;

	public Scorecard start(int battingTeamId) {
		Team battingTeam = getTeam(battingTeamId);
		Team bowlingTeam = getOtherTeam(battingTeamId);
		
		Innings firstInnings = new Innings();
		firstInnings.setBattingTeam(battingTeam);
		firstInnings.setBowlingTeam(bowlingTeam);
		
		Innings secondInnings = new Innings();
		secondInnings.setBattingTeam(bowlingTeam);
		secondInnings.setBowlingTeam(battingTeam);
		
		Scorecard sc = new Scorecard();
		sc.setMatchId(getId());
		sc.setFirstInnings(firstInnings);
		sc.setSecondInnings(secondInnings);
		
		return sc;
	}
	
	private Team getTeam(int teamId) throws ApplicationException {
		 if(getTeam1().getId()==teamId)
			 return getTeam1();
		 
		if(getTeam2().getId()==teamId)
			return getTeam2();
		
		throw new ApplicationException("Team id: "+teamId+" not found within Match: "+this);
	}
	
	private Team getOtherTeam(int teamId) {
		return getTeam1().getId()==teamId ? getTeam2() : getTeam1();
	}
}
