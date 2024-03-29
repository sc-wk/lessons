/**
 * 
 */
package com.abc.cricket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.cricket.model.Match;
import com.abc.cricket.repo.MatchRepo;

import lombok.Getter;

/**
 * @author Soham Chakravarti
 *
 */
@Service
@Getter
public class MatchService extends AbstractService<Match> {

	@Autowired
	private MatchRepo repo;

	@Override
	protected Class<Match> getEntityClass() {
		return Match.class;
	}
}
