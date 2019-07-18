/**
 * 
 */
package com.abc.cricket.model.score;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Soham Chakravarti
 *
 */
@Getter @RequiredArgsConstructor
public enum OutType {

	BOWLED(true),
	CAUGHT(true),
	LBW(true),
	STUMPED(true),
	RUNOUT(false),
	RUNOUT_NON_STRIKER(false),
	HITWKT(true),
	RETIRED_HURT(false);
	
	private final boolean isBowlerWicket;
}
