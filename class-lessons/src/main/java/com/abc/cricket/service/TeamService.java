/**
 * 
 */
package com.abc.cricket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.cricket.model.Team;
import com.abc.cricket.repo.TeamRepo;

/**
 * @author Soham.Chakravarti
 *
 */
@Service
public class TeamService {

	@Autowired
	private TeamRepo repo;
	
	public List<Team> getTeams() {
		return repo.findAll();
	}
	
	public Team getTeam(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public int createTeam(Team t) {
		repo.save(t);
		return t.getId();
	}

}
