/**
 * 
 */
package com.am.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.am.beans.BowlAction;
import com.am.service.CricService;

/**
 * @author 612385366
 *
 */
@RestController
@RequestMapping(value="/CricEver")
public class MyController {
	
	Logger logger = LoggerFactory.getLogger(MyController.class);
	
	@Autowired
    @Qualifier(value = "cricServiceImpl")
    private CricService cricService;
	
	@RequestMapping(method = RequestMethod.GET, value="/test")
	public String test() {
		logger.info("Test here");
		return "Test Success";
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/updateBowl")
	public String updateBowl(@RequestBody BowlAction bowlAction) {
		logger.info("MyController :: updateBowl here");
		return cricService.bowlUpdate(bowlAction);
	}

}
