/**
 * 
 */
package com.abc.cricket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.abc.cricket.model.Player;
import com.abc.cricket.model.Team;
import com.abc.cricket.repo.TeamRepo;

import lombok.Getter;

/**
 * @author Soham.Chakravarti
 *
 */
@Service
@Getter
public class TeamService extends AbstractService<Team> {

	@Autowired
	private TeamRepo repo;
	
	@Override
	protected Class<Team> getEntityClass() {
		return Team.class;
	}
	
	@Transactional
	public void addPlayers(int id, Player p) {
		Assert.notNull(p, ()->"Players must not be null for team with id: "+id);
		
		Team t = get(id);
		
		t.addPlayer(p);
		
		repo.save(t);
	}
	
	@Transactional
	public Player removePlayer(int id, int playerId) {
		Team t = get(id);
		
		return t.removePlayer(playerId);
	}
	
}
