/**
 * 
 */
package com.abc.cricket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.cricket.model.score.BowledRecord;

/**
 * @author Soham Chakravarti
 *
 */
public interface BowledRecordRepo extends JpaRepository<BowledRecord, Integer> {

}
