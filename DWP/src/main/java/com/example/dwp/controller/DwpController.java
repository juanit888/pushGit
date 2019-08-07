package com.example.dwp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dwp.exception.IncompleteRequestException;
import com.example.dwp.model.User;
import com.example.dwp.service.DwpService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cityOrDistance")
@Api(value = "dwpController")
public class DwpController {

	@Autowired
	DwpService dwpService;

	@ApiOperation(value = "Returns a list of users within a distance less than 50 miles from the city or registered in the city", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully returned list of users"),
			@ApiResponse(code = 400, message = "The data in the request is incomplete") })
	@RequestMapping(value = "/{city}", method = RequestMethod.GET, produces = "application/json")
	public List<User> usersByCityOrDistance(@PathVariable("city") String city) throws IncompleteRequestException {

		if (city == null) {
			throw new IncompleteRequestException("The data in the request is incomplete");
		}

		List<User> respList =  dwpService.usersByCityOrDistance(city);
		
		return respList;
	}

}
