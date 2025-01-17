package com.washSystem.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.washSystem.user.model.Customer;
import com.washSystem.user.model.Washer;
import com.washSystem.user.repository.WasherRepository;

@Service
public class WasherService {

	@Autowired
	WasherRepository washerRepo;

	
	public List<Washer> findAllWasher() throws Exception{
		List<Washer> washerList= washerRepo.findAll();
		if(washerList.isEmpty())
			throw new Exception();
		return washerList;
	}
	
	public Washer findWasher(long id) throws IllegalArgumentException, Exception{
		Washer washer = washerRepo.findById(id).orElseThrow(()-> new Exception());
		
		return washer;
	}
	
	public void deleteWasher(long id) throws IllegalArgumentException, Exception{
		Washer washer = washerRepo.findById(id).orElseThrow(()-> new Exception());
		washerRepo.deleteById(id);
	}
	
	public long saveWasher(Washer washer) {
		washer.setStatus(true);
		washerRepo.save(washer);
		return washer.getWasherId();
	}
    
    public Washer updateEmployee(long id,Washer updatedWasher) throws IllegalArgumentException,Exception{
    	Washer washer = washerRepo.findById(id).orElseThrow(()-> new Exception());
    	washer.setEmail(updatedWasher.getEmail());
    	washer.setWasherId(updatedWasher.getWasherId());
    	washer.setName(updatedWasher.getName());
    	washer.setWasherAddress(updatedWasher.getWasherAddress());
        final Washer newWasher = washerRepo.save(washer);
    	return newWasher;
    }
    public ResponseEntity<?> changeStatus(long id) throws Exception{
		Washer washer = washerRepo.findById(id).orElseThrow(()-> new Exception());
		if(washer.getStatus())
			washer.setStatus(false);
		else
			washer.setStatus(true);
		washerRepo.save(washer);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
