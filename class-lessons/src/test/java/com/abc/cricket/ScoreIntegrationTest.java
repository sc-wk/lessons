/**
 * 
 */
package com.abc.cricket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.abc.cricket.controllers.InningsController;
import com.abc.cricket.controllers.ScorecardController;
import com.abc.cricket.model.Match;
import com.abc.cricket.model.Player;
import com.abc.cricket.model.Team;
import com.abc.cricket.model.score.BowledRecord;
import com.abc.cricket.model.score.Innings;
import com.abc.cricket.model.score.Scorecard;
import com.abc.cricket.model.score.view.ScorecardCreateRequest;
import com.abc.cricket.repo.BowledRecordRepo;
import com.abc.cricket.repo.InningsRepo;
import com.abc.cricket.repo.MatchRepo;
import com.abc.cricket.repo.ScorecardRepo;
import com.abc.cricket.repo.TeamRepo;

/**
 * @author Soham.Chakravarti
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=ScorecardApplication.class)
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@EnableTransactionManagement
public class ScoreIntegrationTest {

	@Autowired
	ScorecardController scorecardController;
	
	@Autowired
	InningsController inningsController;
	
	@Autowired
	TeamRepo teamRepoToValidate;
	
	@Autowired
	MatchRepo matchRepoToValidate;
	
	@Autowired
	ScorecardRepo scorecardRepoToValidate;
	
	@Autowired
	BowledRecordRepo bowledRepoToValidate;
	
	@Autowired
	InningsRepo inningsRepoToValidate;
	
	Match _m;
	
	@Before
	public void before_createMatch() {
		Team t1 = createTeam1();
		Team t2 = createTeam2();
		
		Match m = new Match();
		m.setTeam1(t1);
		m.setTeam2(t2);
		m.setWhen(new Date());
		
		_m = matchRepoToValidate.save(m);
		assertNotNull(_m.getId());
	}
	
	@Test
	public void t01_create_Scorecard() {
		ScorecardCreateRequest req = new ScorecardCreateRequest();
		req.setMatchId(_m.getId());
		req.setBattingTeamId(_m.getTeam1().getId());
		
		int scorecardId = scorecardController.createScorecard(req);
		boolean scorecardExistsInDb = scorecardRepoToValidate.existsById(scorecardId);
		assertTrue(scorecardExistsInDb);
	}
	
	@Test
	public void t02_first_bowl() {
		t01_create_Scorecard();
		Scorecard s = scorecardRepoToValidate.findByMatchId(_m.getId());
		assertNotNull(s);
		
		// next bowl
		BowledRecord br = new BowledRecord();
		br.setBallInOver(1);
		br.setBowlerPlayerId(s.getFirstInnings().getBowlingTeam().getPlayers().get(0).getId());
		br.setInningsId(s.getFirstInnings().getId());
		br.setNonStrikerBatsmanPlayerId(s.getFirstInnings().getBattingTeam().getPlayers().get(1).getId());
		br.setRunsScored(1);
		br.setStrikerBatsmanPlayerId(s.getFirstInnings().getBattingTeam().getPlayers().get(0).getId());
		
		int brId = inningsController.nextBowl(s.getFirstInnings().getId(), br);
		assertTrue(bowledRepoToValidate.existsById(brId));
		
		Innings firstInningsDb = inningsRepoToValidate.findById(s.getFirstInnings().getId()).orElse(null);
		assertNotNull(firstInningsDb);
		assertEquals(1, firstInningsDb.getBallInOver());
		assertEquals(0, firstInningsDb.getOver());
		
	}
	
	private Team createTeam1() {
		Team t1 = new Team();
		t1.setName("India");
		
		Player p1 = new Player();
		p1.setFirstName("MS");
		p1.setLastName("Dhoni");
		t1.addPlayer(p1);
		
		Player p2 = new Player();
		p2.setFirstName("Sachin");
		p2.setLastName("Tendulkar");
		t1.addPlayer(p2);
		
		teamRepoToValidate.save(t1);
		
		return t1;
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
