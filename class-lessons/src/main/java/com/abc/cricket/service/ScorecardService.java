/**
 * 
 */
package com.abc.cricket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.cricket.model.score.Scorecard;
import com.abc.cricket.repo.ScorecardRepo;

import lombok.Getter;

/**
 * @author Soham.Chakravarti
 *
 */
@Service
@Getter
public class ScorecardService extends AbstractService<Scorecard> {

	@Autowired
	private ScorecardRepo repo;
	
	@Override
	protected Class<Scorecard> getEntityClass() {
		return Scorecard.class;
	}
}
