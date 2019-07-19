/**
 * 
 */
package com.abc.cricket.model.score.view;

import java.util.function.Function;

import com.abc.cricket.model.Match;
import com.abc.cricket.model.score.Scorecard;
import com.abc.cricket.model.view.AbstractViewModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@Getter @Setter @ToString
public class ScorecardCreateRequest extends AbstractViewModel {

	private Integer matchId;
	
	private Integer battingTeamId;
	
	public Scorecard convert(Function<Integer, Match> matchLookup) {
		Match m = callback(matchLookup, matchId);
		
		Scorecard s = m.start(battingTeamId);
		return s;
	}
}
