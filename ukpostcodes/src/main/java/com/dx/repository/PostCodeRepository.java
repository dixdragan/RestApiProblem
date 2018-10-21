package com.dx.repository;

import org.springframework.data.repository.Repository;

import com.dx.model.PostCode;

public interface PostCodeRepository extends Repository<PostCode, Long>{
	
	PostCode findByCodeAllIgnoringCase(String code);

	PostCode findById(int id);

	//void update(PostCode postCode);

}
