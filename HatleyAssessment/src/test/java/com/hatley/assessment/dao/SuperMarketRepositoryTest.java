package com.hatley.assessment.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import com.hatley.assessment.model.SuperMarket;
import static java.util.Arrays.asList;


@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class SuperMarketRepositoryTest {
	

	private  static SuperMarket supermarket ;
	 @BeforeAll
	    public static void setUp() {
	       
	        supermarket=asupermarkettobesaved();
	    }
	@MockBean
	private  SupermarketDAO supermarketDAO ;
	
	@Test
	@DisplayName("testing inserting a supermarket information into the database")
	@Rollback(false)
	@Order(1)
	public void testSaveAsupermarket() {
		Mockito.when(supermarketDAO.saveSupermarket(supermarket)).thenReturn(supermarket);
		supermarketDAO.saveSupermarket(supermarket);
		 assertNotNull(supermarket);
		 
		 assertTrue(supermarket.getId()==1);
	}
	
	
    
	@Test
	@Order(2)
	@DisplayName("testing getting a supermarket information from the database with specific id ")
	public void testFindById() {
		Mockito.when(supermarketDAO.findByID(1)).thenReturn(supermarket);
		supermarketDAO.findByID(1);
		 assertNotNull(supermarket);
		 assertEquals(1, supermarket.getId());
		 assertEquals("Metro", supermarket.getEnglishName());
	}
	
	@Test
	@Order(3)
	@DisplayName("testing updating a supermarket information  ")
	public void testUpdateSupermarket() {
		supermarket.setEnglishName("Metro2");
		Mockito.when(supermarketDAO.updateSupermarket(supermarket)).thenReturn(supermarket);
		supermarketDAO.updateSupermarket(supermarket);
		 assertNotNull(supermarket);
		 assertEquals(1, supermarket.getId());
		 assertEquals("Metro2", supermarket.getEnglishName());
	}
	@Test
	@Order(4)
	@DisplayName("testing updating a supermarket information  ")
	public void listAllSuperMarketst() {
		
		Mockito.when(supermarketDAO.listAllSuperMarkets()).thenReturn(asList(supermarket));
		List<SuperMarket> actual = supermarketDAO.listAllSuperMarkets();
		assertEquals(supermarket, actual.get(0));
		 assertEquals(1, supermarket.getId());
		 assertEquals("Metro2", supermarket.getEnglishName());
	}
	@Test
	@Order(5)
	@DisplayName("testing deleting supermarket information from the database with specific id ")
	public void testdeleteById() {
		doNothing().when(supermarketDAO).deleteById(1l);
		supermarketDAO.deleteById(1l);
		 verify(supermarketDAO,times(1)).deleteById(1l);
	     verifyNoMoreInteractions(supermarketDAO);
		 
	}
	
	private static SuperMarket asupermarkettobesaved() {
		 SuperMarket supermarket  =new SuperMarket( );
		 supermarket.setId(1l);
		 supermarket.setEnglishName("Metro");
		 supermarket.setArabicName("مترو");
		 supermarket.setActive(true);
		 supermarket.setAddress("some physical addresss");
		 supermarket.setImageAddress("localhost://somedrive/image.jpeg");
		 return supermarket ;
	} 
	
	
}
