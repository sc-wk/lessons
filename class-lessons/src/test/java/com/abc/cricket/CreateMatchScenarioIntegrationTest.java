/**
 * 
 */
package com.abc.cricket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.cricket.controllers.MatchController;
import com.abc.cricket.controllers.TeamController;
import com.abc.cricket.model.Match;
import com.abc.cricket.model.Player;
import com.abc.cricket.model.Team;
import com.abc.cricket.model.view.MatchCreateRequest;
import com.abc.cricket.model.view.PlayerCreateRequest;
import com.abc.cricket.model.view.TeamCreateRequest;
import com.abc.cricket.repo.MatchRepo;
import com.abc.cricket.repo.TeamRepo;

/**
 * @author Soham Chakravarti
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=ScorecardApplication.class)
@ActiveProfiles("test")
public class CreateMatchScenarioIntegrationTest {

	@Autowired
	MatchController matchController;
	
	@Autowired
	TeamController teamController;
	
	@Autowired
	TeamRepo teamRepoToValidate;
	
	@Autowired
	MatchRepo matchRepoToValidate;
	
	@Test
	public void t01_createMatch() {
		// create team 1
		TeamCreateRequest tReq = new TeamCreateRequest();
		tReq.setName("India");
		
		int teamId = teamController.createTeam(tReq);
		boolean teamExistsInDb = teamRepoToValidate.existsById(teamId);
		assertTrue(teamExistsInDb);
		
		// add player 1
		PlayerCreateRequest p1Req = new PlayerCreateRequest();
		p1Req.setFirstName("Virat");
		p1Req.setLastName("Kohli");
		
		teamController.addPlayer(teamId, p1Req);
		
		// validate team & player
		Team teamViaController = teamController.getTeam(teamId);
		assertNotNull(teamViaController);
		assertEquals(Integer.valueOf(teamId), teamViaController.getId());
		assertEquals(tReq.getName(), teamViaController.getName());
		assertSame(1, teamViaController.getPlayers().size());
		assertEquals(p1Req.getFirstName(), teamViaController.getPlayers().get(0).getFirstName());
		assertEquals(p1Req.getLastName(), teamViaController.getPlayers().get(0).getLastName());
		
		// create match
		Team t1 = teamViaController;
		Team t2 = createTeam2();
		
		MatchCreateRequest mReq = new MatchCreateRequest();
		mReq.setTeam1Id(t1.getId());
		mReq.setTeam2Id(t2.getId());
		mReq.setWhen(new Date());
		
		// validate match
		int matchId = matchController.createMatch(mReq);
		boolean matchExistsInDb = matchRepoToValidate.existsById(matchId);
		assertTrue(matchExistsInDb);
		
		Match matchViaController = matchController.getMatch(matchId);
		assertNotNull(matchViaController);
		assertEquals(mReq.getWhen(), matchViaController.getWhen());
		assertEquals(mReq.getTeam1Id(), matchViaController.getTeam1().getId());
		assertEquals(mReq.getTeam2Id(), matchViaController.getTeam2().getId());
	}
	
	private Team createTeam2() {
		Team t2 = new Team();
		t2.setName("England");
		
		Player p1 = new Player();
		p1.setFirstName("Ben");
		p1.setLastName("Stokes");
		t2.addPlayer(p1);
		
		teamRepoToValidate.save(t2);
		
		return t2;
	}
}
