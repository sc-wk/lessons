/**
 * 
 */
package com.abc.cricket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.cricket.model.score.BowledRecord;
import com.abc.cricket.service.InningsService;

/**
 * @author Soham Chakravarti
 *
 */
@RestController
@RequestMapping("/innings")
public class InningsController {

	@Autowired
	private InningsService service;
	
	@RequestMapping(value="/{id}/nextBowl", method=RequestMethod.PUT)
	public void nextBowl(@PathVariable int id, BowledRecord br) {
		service.updateScore(br);
	}
}
