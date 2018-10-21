package com.dx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dx.model.Distance;
import com.dx.model.PostCode;
import com.dx.repository.PostCodeRepository;

@RestController
@RequestMapping("/guest")
public class PublicRestController {

	@Autowired
	PostCodeRepository codeRepository;

	@GetMapping("/distance/{pc1}/{pc2}")
	public ResponseEntity<Object> getByCode(@PathVariable("pc1") String pc1String, @PathVariable("pc2") String pc2String){

		if (pc1String.length()<7 || pc1String.length()>8 || pc2String.length()<7  || pc2String.length()>8) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		// AB101XG -> AB10 1XG
		if(pc1String.length()==7) pc1String = addSpace(pc1String);
		if(pc2String.length()==7) pc2String = addSpace(pc2String);

		PostCode postCode1 = codeRepository.findByCodeAllIgnoringCase(pc1String);
		if(postCode1==null){
			postCode1 = new PostCode();
			postCode1.setCode("NOT FOUND");
		}
		PostCode postCode2 = codeRepository.findByCodeAllIgnoringCase(pc2String);
		if(postCode2==null){
			postCode2 = new PostCode();
			postCode2.setCode("NOT FOUND");
		}

		Distance distance = new Distance();
		distance.calculateDistance(postCode1, postCode2);
		return ResponseEntity.ok(distance);
	}

	private String addSpace(String in) {
		return in.substring(0,4) + " " + in.substring(4);
	}
	
}
