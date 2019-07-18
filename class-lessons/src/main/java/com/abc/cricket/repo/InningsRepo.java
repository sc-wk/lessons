/**
 * 
 */
package com.abc.cricket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.cricket.model.score.Innings;

/**
 * @author Soham Chakravarti
 *
 */
public interface InningsRepo extends JpaRepository<Innings, Integer> {

}
