/**
 * 
 */
package com.abc.cricket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.cricket.model.score.Scorecard;

/**
 * @author Soham.Chakravarti
 *
 */
public interface ScorecardRepo extends JpaRepository<Scorecard, Integer> {

	Scorecard findByMatchId(Integer matchId);
}
