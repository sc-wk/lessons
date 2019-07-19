/**
 * 
 */
package com.abc.cricket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.cricket.model.score.Scorecard;
import com.abc.cricket.model.score.view.ScorecardCreateRequest;
import com.abc.cricket.service.MatchService;
import com.abc.cricket.service.ScorecardService;

/**
 * @author Soham.Chakravarti
 *
 */
@RestController
@RequestMapping("/scorecard")
public class ScorecardController {

	@Autowired
	private ScorecardService service;
	
	@Autowired 
	private MatchService matchService;
	
	@RequestMapping(method=RequestMethod.POST)
	public int createScorecard(@RequestBody ScorecardCreateRequest req) {
		Scorecard s = req.convert(matchService::get);
		
		Scorecard sCreated = service.create(s);
		return sCreated.getId();
	}
	
}
