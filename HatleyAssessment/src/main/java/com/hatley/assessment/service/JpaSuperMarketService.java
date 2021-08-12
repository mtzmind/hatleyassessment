package com.hatley.assessment.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatley.assessment.dao.SupermarketDAO;
import com.hatley.assessment.model.SuperMarket;



@Service
@Transactional
public class JpaSuperMarketService implements SuperMarketService {
     @Autowired
	private SupermarketDAO supermarketDao ;
    
   
	@Override
	public SuperMarket getAsupermarket(long id) {
		 return supermarketDao.findByID(id);
	}
	@Override
	public SuperMarket saveSupermarket(SuperMarket supermarket) {
		return  supermarketDao.saveSupermarket(supermarket) ; 
	}
	@Override
	public List<SuperMarket> listAllSupermarkets() {
		 return   supermarketDao.listAllSuperMarkets();
	}
	@Override
	public void deleteSupermarket(long id) {
		 supermarketDao.deleteById(id);
		
	}
	@Override
	public SuperMarket upateSuperMarket(SuperMarket supermarket) {
		return supermarketDao.updateSupermarket(supermarket) ; 
		
	} 
   
     
}
