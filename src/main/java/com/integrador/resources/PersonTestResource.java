package com.integrador.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.entities.PersonTest;
import com.integrador.services.PersonTestService;

@RestController
@RequestMapping("personTests")
public class PersonTestResource {
	
	@Autowired
	private PersonTestService service;
	
	@GetMapping
	public List<PersonTest> findAllPersons(){
		return service.findAllPersons();
	}

}
