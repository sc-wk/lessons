/**
 * 
 */
package com.abc.cricket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.cricket.model.Team;

/**
 * @author Soham Chakravarti
 *
 */
public interface TeamRepo extends JpaRepository<Team, Integer> {

}
