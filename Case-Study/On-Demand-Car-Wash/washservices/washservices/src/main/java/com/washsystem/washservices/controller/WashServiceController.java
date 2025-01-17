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
import com.washsystem.washservices.model.WashServices;
import com.washsystem.washservices.service.WashServicesService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/services")
public class WashServiceController {
	
	
	@Autowired
	WashServicesService washService;
	
	@RequestMapping("/all/findAll")
	public ResponseEntity<List<WashServices>> findAllAddOn()
	{
		try {
			return new ResponseEntity(washService.findAllWashServices(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<WashServices>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/all/find/{name}")
	public ResponseEntity<Optional<AddOn>> findAddOn(@PathVariable("name") String washServicesName){
		try {
			return new ResponseEntity(washService.findWashService(washServicesName),HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/admin/delete/{name}")
	public ResponseEntity<Void> deleteAddOn(@PathVariable("name") String washServicesName){
		try {
			washService.deleteWashServices(washServicesName);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	//admin
	@PostMapping("/admin/add")
	public ResponseEntity<Void> deleteAddOn(@RequestBody WashServices washServices){
		try{
			washService.addWashServices(washServices);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//admin
	@PutMapping("/admin/updateStatus/{name}")
	public ResponseEntity<?> updateStatus(@PathVariable("name") String name){
		try{
			return washService.changeStatus(name);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
}
