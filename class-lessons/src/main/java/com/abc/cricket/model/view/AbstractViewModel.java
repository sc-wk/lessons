/**
 * 
 */
package com.abc.cricket.model.view;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Soham Chakravarti
 *
 */
public abstract class AbstractViewModel {

	protected static <O> O callback(Function<Integer, O> lookup, Integer id) {
		return Optional.ofNullable(id)
				.map(lookup)
				.orElse(null);
	}
}
