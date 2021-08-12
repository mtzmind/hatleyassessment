package com.hatley.assessment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hatley.assessment.model.SuperMarket;

public interface SupermarketRepository extends JpaRepository<SuperMarket, Long > {
	

}
