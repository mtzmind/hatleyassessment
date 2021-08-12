package com.hatley.assessment.dao;

import java.util.List;

import com.hatley.assessment.model.SuperMarket;


public interface SupermarketDAO {
public SuperMarket findByID(long id);
public SuperMarket saveSupermarket (SuperMarket supermarket) ;
public List<SuperMarket> listAllSuperMarkets ();
public void  deleteById(long id);
public SuperMarket updateSupermarket(SuperMarket supermarket);
}
