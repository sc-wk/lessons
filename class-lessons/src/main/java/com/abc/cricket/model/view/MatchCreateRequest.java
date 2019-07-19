/**
 * 
 */
package com.abc.cricket.model.view;

import java.util.Date;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.abc.cricket.model.Match;
import com.abc.cricket.model.Stadium;
import com.abc.cricket.model.Team;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@Getter @Setter @ToString
public class MatchCreateRequest extends AbstractViewModel {

	@NotNull
	private Integer stadiumId;
	
	private Integer team1Id;
	private Integer team2Id;
	
	@NotNull
	private Date when;
	
	public Match convert(Function<Integer, Team> teamLookup, Function<Integer, Stadium> stadiumLookup) {
		Team t1 = callback(teamLookup, team1Id);
		Team t2 = callback(teamLookup, team2Id);
		
		Stadium s = callback(stadiumLookup, stadiumId);
		
		Match m = new Match();
		m.setStadium(s);
		m.setTeam1(t1);
		m.setTeam2(t2);
		m.setWhen(getWhen());
		
		return m;
	}

}
