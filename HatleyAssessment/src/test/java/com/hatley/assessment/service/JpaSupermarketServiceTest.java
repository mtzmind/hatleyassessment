package com.hatley.assessment.service;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.hatley.assessment.model.SuperMarket;



@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class JpaSupermarketServiceTest {

	
	
	@MockBean
	private JpaSuperMarketService  supermarketService ;
	
	@Test
	@DisplayName("testing  supermarket service layer to save a supermarket")
	@Order(1)
	public void testSaveSupermarket() {
		 SuperMarket supermarketToBeSaved=supermarketToBeSaved();
		
		 Mockito.when(supermarketService.saveSupermarket(supermarketToBeSaved)).thenReturn(supermarketToBeSaved);
		  SuperMarket savedSupermarket =supermarketService.saveSupermarket(supermarketToBeSaved);
		  
		 assertEquals(savedSupermarket.getId() ,supermarketToBeSaved.getId());
		 assertEquals("Metro Market",savedSupermarket.getEnglishName());
		 
	}
	@Test
	@Order(2)
	@DisplayName("testing  supermarket service layer to get a supermarket by id")
	public void testGetAsupermarket() {
		 SuperMarket supermarketToBeRetrived=supermarketToBeSaved();
		 Mockito.when(supermarketService.getAsupermarket(1l)).thenReturn(supermarketToBeRetrived);
		 SuperMarket retrivedSupermarket=supermarketService.getAsupermarket(1l);
		 assertEquals(retrivedSupermarket.getId() , supermarketToBeRetrived.getId());
		 assertEquals("مترو ماركت", retrivedSupermarket.getArabicName());
	}
	@Test
	@Order(3)
	@DisplayName("testing  supermarket service layer to get all supermarkets")
	public void testGetAllGatewaies() {
		SuperMarket aSupermarket=supermarketToBeSaved();
		 Mockito.when(supermarketService.listAllSupermarkets()).thenReturn(asList(aSupermarket));
		 List<SuperMarket> supermarkets=supermarketService.listAllSupermarkets();
		 assertEquals( 1,supermarkets.get(0).getId());
		 assertEquals(1, supermarkets.size()); 
	}
	@Test
	@Order(4)

	@DisplayName("testing  supermarket service layer to add a update a supermarket")
	public void tesAddAnAttachedDevice() throws Exception {
		SuperMarket supermarketBeforeUpdate=supermarketToBeSaved();
		
		supermarketBeforeUpdate.setEnglishName("Metro2");
		 Mockito.when(supermarketService.upateSuperMarket(supermarketBeforeUpdate)).thenReturn(supermarketBeforeUpdate);
		 SuperMarket updatedsupermarket=supermarketService.upateSuperMarket(supermarketBeforeUpdate);
		 assertEquals(1 ,updatedsupermarket.getId());
		 assertEquals("Metro2" ,updatedsupermarket.getEnglishName()); 
	}
	@Test
	@Order(5)
	@DisplayName("testing  supermarket service layer to delete a supermarket by id")
	public void testDeleteAgateway() {
	    doNothing().when(supermarketService).deleteSupermarket(1l);
	    supermarketService.deleteSupermarket(1l);
		 verify(supermarketService,times(1)).deleteSupermarket(1l);
	     verifyNoMoreInteractions(supermarketService);
	}
	
	
	private SuperMarket supermarketToBeSaved() {
		 SuperMarket supermarket  =new SuperMarket( );
		 supermarket.setId(1l);
		 supermarket.setActive(true);
		 supermarket.setAddress("home");
		 supermarket.setArabicName("مترو ماركت");
		 supermarket.setEnglishName("Metro Market");
		 return supermarket ;
	} 
	
 

}
