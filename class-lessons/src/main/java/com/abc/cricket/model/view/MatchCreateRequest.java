/**
 * 
 */
package com.abc.cricket.model.view;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import com.abc.cricket.model.Match;
import com.abc.cricket.model.Team;
import com.abc.cricket.service.ModelNotFoundException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@Getter @Setter @ToString
public class MatchCreateRequest {

	private int stadiumId;
	private int team1Id;
	private int team2Id;
	private Date when;
	
	public Match convert(Function<Integer, Team> teamLookup) {
		Team t1 = getTeam(teamLookup, team1Id);
		Team t2 = getTeam(teamLookup, team2Id);
		
		Match m = new Match();
		m.setTeam1(t1);
		m.setTeam2(t2);
		m.setWhen(getWhen());
		
		return m;
	}
	
	private Team getTeam(Function<Integer, Team> teamLookup, int id) {
		return Optional.ofNullable(teamLookup.apply(id))
				.orElseThrow(()->new ModelNotFoundException("Team not found for id: "+id));
	}
}
