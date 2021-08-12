package com.hatley.assessmet.controller;


import static java.util.Arrays.asList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.hatley.assessment.controller.SupermarketController;
import com.hatley.assessment.model.SuperMarket;
import com.hatley.assessment.service.JpaSuperMarketService;

@ContextConfiguration(classes= {com.hatley.assessment.HatleyAssessmentApplication.class})
@RunWith(SpringRunner.class)
@WebMvcTest( SupermarketController.class)
@TestMethodOrder(OrderAnnotation.class)

public class SupermarketControllerTest {
	@Autowired
	private MockMvc  mockMvc ;
	@MockBean
	private JpaSuperMarketService supermarketService ;

	
	@Test
	@Order(3)
	@DisplayName("testing  supermarket controller layer to get all supermarkets")
	public void testListAll() throws Exception{
		SuperMarket supermarket=supermarketToBeSaved();
		 Mockito.when(supermarketService.listAllSupermarkets()).thenReturn( asList(supermarket));
		 mockMvc.perform(MockMvcRequestBuilders.get("/api/supermarkets/listall" ).contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()",Matchers.equalTo(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id",Matchers.equalTo(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[0].active",Matchers.equalTo(true)))
		    .andExpect(MockMvcResultMatchers.jsonPath("$.[0].arabicName",Matchers.equalTo("مترو ماركت")))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[0].englishName",Matchers.equalTo("Metro Market")))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[0].address",Matchers.equalTo("home")));
	}
	@Test
	@Order(4)
	@DisplayName("testing  supermarket controller layer to delete a supermarket by id")
	public void testDeleteById() throws Exception{
	
		 mockMvc.perform(MockMvcRequestBuilders.delete("/api/supermarkets/{id}",1L ).contentType("application/json"))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isNoContent());
		 verify(supermarketService,times(1)).deleteSupermarket(1l);
	     verifyNoMoreInteractions(supermarketService);
	}
	@Test
	@Order(1)
	@DisplayName("testing  supermarket controller layer to save a supermarket")
	public void testAddsupperMarket() throws Exception{
		SuperMarket supermarketToBeSaved =supermarketToBeSaved() ;
		
		 Mockito.when(supermarketService.saveSupermarket(supermarketToBeSaved)).thenReturn( supermarketToBeSaved);
		 MockMultipartFile image = new MockMultipartFile( "image",  "mylogo.png", 
	       MediaType.IMAGE_JPEG_VALUE, 
	        "mylogo.png".getBytes()
	      );
		 
		 mockMvc.perform(MockMvcRequestBuilders.multipart("/api/supermarkets/create")
                 .file(image)
                 .param("englishName", "MetroMarket")
                 .param("arabicName", "مترو ماركت")
                  .param("address", "home")
                  .param("active","true"))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()",Matchers.equalTo(7)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.equalTo(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.active",Matchers.equalTo(true)))
		    .andExpect(MockMvcResultMatchers.jsonPath("$.arabicName",Matchers.equalTo("مترو ماركت")))
			.andExpect(MockMvcResultMatchers.jsonPath("$.englishName",Matchers.equalTo("Metro Market")))
			.andExpect(MockMvcResultMatchers.jsonPath("$.address",Matchers.equalTo("home")));
		 verify(supermarketService,times(1)).saveSupermarket(supermarketToBeSaved);
	     verifyNoMoreInteractions(supermarketService);
	}
	
	
	@Test
	@Order(2)
	@DisplayName("testing  supermarket controller layer to get a supermakret by id")
	public void testFindById() throws Exception{
		SuperMarket supermarketToBeSaved =supermarketToBeSaved() ;
		Mockito.when(supermarketService.getAsupermarket(1l)).thenReturn( supermarketToBeSaved);
	
		 mockMvc.perform(MockMvcRequestBuilders.get("/api/supermarkets/{id}",1L ).contentType("application/json"))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
		 verify(supermarketService,times(1)).getAsupermarket(1l);
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
