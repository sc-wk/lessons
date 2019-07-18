/**
 * 
 */
package com.abc.cricket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.cricket.model.Match;
import com.abc.cricket.repo.MatchRepo;

/**
 * @author Soham Chakravarti
 *
 */
@Service
public class MatchService {

	@Autowired
	private MatchRepo repo;
	
	public List<Match> getMatches() {
		return repo.findAll();
	}
	
	public Match getMatch(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public Match create(Match m) {
		repo.save(m);
		return m;
	}
	
	public void update(Match m) {
		repo.save(m);
	}
}
