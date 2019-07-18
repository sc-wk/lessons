/**
 * 
 */
package com.abc.cricket.model.score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@Entity
@Table(name="BOWLED_RECORD")
@Getter @Setter @ToString
public class BowledRecord {

	@Id
	@SequenceGenerator(name="SEQ_BOWLED_RECORD")
	@Column(name="ID")
	private int id;
	
	@Column(name="INNINGS_ID")
	private int inningsId;
	
	@Column(name="OVER")
	private int over;
	
	@Column(name="BALL_IN_OVER")
	private int ballInOver;
	
	@Column(name="STRIKER_BATSMAN_PID")
	private int strikerBatsmanPlayerId;
	
	@Column(name="NONSTRIKER_BATSMAN_PID")
	private int nonStrikerBatsmanPlayerId;
	
	@Column(name="BOWLER_PID")
	private int bowlerPlayerId;
	
	@Column(name="RUNS_SCORED")
	private int runsScored;
	
	@Column(name="BOUNDARY_TYPE")
	@Enumerated(EnumType.STRING)
	private BoundaryType boundaryType;
	
	@Column(name="EXTRA_TYPE")
	@Enumerated(EnumType.STRING)
	private ExtraType extraType;
	
	@Column(name="OUT_TYPE")
	@Enumerated(EnumType.STRING)
	private OutType outType;
	
	@Column(name="OUT_BY_FIELDER_PID")
	private int outByFielderPlayerId;
	
	public boolean isOut() {
		return getOutType() != null;
	}
	
}
