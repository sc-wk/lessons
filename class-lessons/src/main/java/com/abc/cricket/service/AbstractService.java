/**
 * 
 */
package com.abc.cricket.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Soham Chakravarti
 *
 */
public abstract class AbstractService<T> {

	
	/**
	 * Primary entity class for this service instance
	 *  
	 * @return
	 */
	protected abstract Class<T> getEntityClass();
	
	/**
	 * Primary repository for this service instance
	 * @return
	 */
	protected abstract JpaRepository<T, Integer> getRepo();
	
	/**
	 * Returns found entity or null
	 * 
	 * @param id
	 * @return
	 */
	public T find(int id) {
		return getRepo().findById(id).orElse(null);
	}
	
	/**
	 * Returns found entity or throws an exception if none found
	 * 
	 * @param id
	 * @return
	 * @throws ModelNotFoundException
	 */
	public T get(int id) throws ModelNotFoundException {
		return getRepo().findById(id)
				.orElseThrow(()->new ModelNotFoundException(getClass().getSimpleName()+" model not found for id: "+id));
	}
	
	public List<T> findAll() {
		return getRepo().findAll();
	}
	
	public T create(T model) {
		return getRepo().save(model);
	}
}
