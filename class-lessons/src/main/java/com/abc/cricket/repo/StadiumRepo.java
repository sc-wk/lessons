/**
 * 
 */
package com.abc.cricket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.cricket.model.Stadium;

/**
 * @author Soham Chakravarti
 *
 */
public interface StadiumRepo extends JpaRepository<Stadium, Integer> {

}
