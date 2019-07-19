/**
 * 
 */
package com.abc.cricket.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.cricket.model.Stadium;
import com.abc.cricket.model.view.StadiumCreateRequest;
import com.abc.cricket.service.StadiumService;

/**
 * @author Soham.Chakravarti
 *
 */
@RestController
@RequestMapping("/stadiums")
public class StadiumController {
	
	@Autowired
	private StadiumService service;

	@RequestMapping
	public List<Stadium> getStadiums() {
		return service.findAll();
	}
	
	@RequestMapping("/{id}")
	public Stadium getStadium(@PathVariable int id) {
		return service.find(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public int createStadium(@Valid @RequestBody StadiumCreateRequest req) {
		Stadium s = req.convert();
		
		Stadium sCreated = service.create(s);
		return sCreated.getId();
	}
	
	
}
