/**
 * 
 */
package com.abc.cricket.model.score;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.Assert;

import com.abc.cricket.model.Player;
import com.abc.cricket.model.Team;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@Entity
@Table(name="INNINGS")
@Getter @Setter @ToString
public class Innings {

	@Id
	@GeneratedValue(generator="SEQ_INNINGS")
	@SequenceGenerator(name="SEQ_INNINGS", initialValue=1, allocationSize=1)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="SCORECARD_ID")
	private Integer scorecardId;
	
	@ManyToOne
	@JoinColumn(name="BATTING_TEAM_ID")
	private Team battingTeam;
	
	@ManyToOne
	@JoinColumn(name="BOWLING_TEAM_ID")
	private Team bowlingTeam;
	
	@Column(name="RUNS")
	private int runs;
	
	@Column(name="OVER")
	private int over;
	
	@Column(name="BALL_IN_OVER")
	private int ballInOver;
	
	@Column(name="WICKETS")
	private int wickets;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="INNINGS_ID")
	private List<Batsman> batsmen;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="INNINGS_ID")
	private List<Bowler> bowlers;
	
	@Column(name="LAST_UPDATED")
	@Version
	@LastModifiedDate
	private Date lastUpdated;
	
	public synchronized void updateScore(BowledRecord br) {
		Assert.notNull(br, ()->"BowledRecord must not be null for Innings: "+this);
		
		/* no matter what, over & ball would get updated */
		setOver(br.getOver());
		setBallInOver(br.getBallInOver());
		
		/* create or get striker bats-man */
		Batsman striker = createOrGetBatsman(br.getStrikerBatsmanPlayerId());
		striker.updateScore(br.getRunsScored(), Optional.ofNullable(br.getBoundaryType()));
		
		/* ensure non-striker bats-man is created */
		createOrGetBatsman(br.getNonStrikerBatsmanPlayerId());
		
		/* create or get bowler */
		Bowler bowler = createOrGetBowler(br.getBowlerPlayerId());
		bowler.updateScore(br.getRunsScored(), Optional.ofNullable(br.getExtraType()), Optional.ofNullable(br.getOutType()));
		
		/* TODO Fall of wickets */
		
	}
	
	private Batsman createOrGetBatsman(int playerId) {
		if(getBatsmen()==null)
			setBatsmen(new ArrayList<>());

		return getBatsmen().stream()
				.filter(b->b.getPlayer().getId()==playerId)
				.findFirst()
				.orElseGet(()->{
					Player player = getBattingTeam().getPlayer(playerId);
					
					Batsman b = new Batsman();
					b.setInningsId(getId());
					b.setPlayer(player);
			
					getBatsmen().add(b);
					return b;
				});
	}
	
	private Bowler createOrGetBowler(int playerId) {
		if(getBowlers()==null)
			setBowlers(new ArrayList<>());
		
		return getBowlers().stream()
				.filter(b->b.getPlayer().getId()==playerId)
				.findFirst()
				.orElseGet(()->{
					Player player = getBowlingTeam().getPlayer(playerId);
					
					Bowler b = new Bowler();
					b.setInningsId(getId());
					b.setPlayer(player);
					
					getBowlers().add(b);
					return b;
				});
	}
}
