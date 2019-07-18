/**
 * 
 */
package com.abc.cricket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.cricket.model.Match;

/**
 * @author Soham Chakravarti
 *
 */
public interface MatchRepo extends JpaRepository<Match, Integer> {

}
