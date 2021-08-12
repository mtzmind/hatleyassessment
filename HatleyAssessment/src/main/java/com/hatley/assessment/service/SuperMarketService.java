package com.hatley.assessment.service;

import java.util.List;

import com.hatley.assessment.model.SuperMarket;


public interface SuperMarketService {
	  public SuperMarket  getAsupermarket(long id);
	  public SuperMarket saveSupermarket (SuperMarket gateway);
	  public List<SuperMarket>listAllSupermarkets ();
	  public void  deleteSupermarket(long id);
	  public SuperMarket upateSuperMarket(SuperMarket supermarket);
	

}
