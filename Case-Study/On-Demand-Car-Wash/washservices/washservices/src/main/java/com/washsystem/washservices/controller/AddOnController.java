package com.washsystem.washservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.washsystem.washservices.model.AddOn;
import com.washsystem.washservices.service.AddOnServices;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/addOn")
public class AddOnController {
	
	@Autowired
	AddOnServices addOnServices;
	
	
	//All
	@RequestMapping("/all/findAll")
	public ResponseEntity<List<AddOn>> findAllAddOn()
	{
		try {
			return new ResponseEntity(addOnServices.findAllAddOn(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<AddOn>>(HttpStatus.NOT_FOUND);
		}
	}
	
	//all
	@RequestMapping("/all/find/{name}")
	public ResponseEntity<Optional<AddOn>> findAddOn(@PathVariable("name") String addOnName){
		try {
			return new ResponseEntity(addOnServices.findAddOn(addOnName),HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	//admin
	@RequestMapping("/admin/delete/{name}")
	public ResponseEntity<Void> deleteAddOn(@PathVariable("name") String addOnName){
		try {
			addOnServices.deleteAddOn(addOnName);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(HttpStatus.OK);
	}
	
	//Admin
	@PostMapping("/admin/add")
	public ResponseEntity<?> addAddOn(@RequestBody AddOn addOn){
		try{
			addOnServices.addAddOn(addOn);
		}
		catch(Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	//Admin
	@PutMapping("/admin/updateStatus/{name}")
	public ResponseEntity<?> updateStatus(@PathVariable("name") String name){
		try{
			return addOnServices.changeStatus(name);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
}
