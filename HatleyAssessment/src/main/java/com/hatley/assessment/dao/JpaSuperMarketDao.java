package com.hatley.assessment.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.hatley.assessment.model.SuperMarket;

@Transactional
@Repository("supermarketDao")
public class JpaSuperMarketDao implements SupermarketDAO {
	 @Autowired
		private SupermarketRepository superMarketRepository ;

	@Override
	public SuperMarket findByID(long id) {
		 return superMarketRepository.findById(id).get();
	}

	@Override
	public SuperMarket saveSupermarket(SuperMarket supermarket) {
    	return  superMarketRepository.save(supermarket) ; 
	}

	@Override
	public List<SuperMarket>listAllSuperMarkets() {
		return   superMarketRepository.findAll();
	}

	@Override
	public void deleteById(long id) {
		superMarketRepository.deleteById(id);

	}

	@Override
	public SuperMarket updateSupermarket(SuperMarket superMarket){
		 return  superMarketRepository.save(superMarket);
	}

	



}
