/**
 * 
 */
package com.abc.cricket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.cricket.model.Stadium;
import com.abc.cricket.repo.StadiumRepo;

import lombok.Getter;

/**
 * @author Soham Chakravarti
 *
 */
@Service
@Getter
public class StadiumService extends AbstractService<Stadium> {

	@Autowired
	private StadiumRepo repo;
	
	@Override
	public Class<Stadium> getEntityClass() {
		return Stadium.class;
	}

}
