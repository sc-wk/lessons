/**
 * 
 */
package com.abc.cricket.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.cricket.model.score.BowledRecord;
import com.abc.cricket.model.score.Innings;
import com.abc.cricket.repo.BowledRecordRepo;
import com.abc.cricket.repo.InningsRepo;

import lombok.Getter;

/**
 * @author Soham Chakravarti
 *
 */
@Service
@Getter 
public class InningsService extends AbstractService<Innings> {

	@Autowired
	private BowledRecordRepo brRepo;
	
	@Autowired
	private InningsRepo repo;
	
	@Override
	protected Class<Innings> getEntityClass() {
		return Innings.class;
	}
	
	/**
	 * 1. Persist {@link BowledRecord} into database <br>
	 * 2. Update {@link Innings} and persist changes to database
	 * 
	 * @param nextBowl
	 */
	@Transactional
	public int updateScore(BowledRecord nextBowl) {
	
		// 1. persist next bowl info in database
		BowledRecord nextBowlCreated = brRepo.save(nextBowl);
		
		// 2. retrieve Innings
		Innings inn = repo.findById(nextBowl.getInningsId())
						.orElseThrow(()->
							new ModelNotFoundException("Innings not found for id: "+nextBowl.getInningsId()
								+" from passed in BowledRecord: "+nextBowl));
		
		inn.updateScore(nextBowl);
		
		// 3. persist Innings info in database
		repo.save(inn);
		
		return nextBowlCreated.getId();
	}
}
