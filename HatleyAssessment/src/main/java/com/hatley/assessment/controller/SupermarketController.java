package com.hatley.assessment.controller;

import java.io.File;

import java.util.List;


import javax.servlet.ServletContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hatley.assessment.exceptionutils.IllegalArabicNameException;
import com.hatley.assessment.model.SuperMarket;
import com.hatley.assessment.model.SupermarketWrapper;
import com.hatley.assessment.service.JpaSuperMarketService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("api/supermarkets")
public class SupermarketController {
	
	private static final String IMAGE_DIR = "images/";
	private  HttpHeaders httpHeaders= new HttpHeaders();
	
	
	@Autowired 
	private JpaSuperMarketService superMarketService ;
	
	
	@GetMapping(value = "listall" ,produces="Application/json")
    @ApiOperation(value = "show  all supermarkets")
	public ResponseEntity<List<SuperMarket>> listAll() {
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<List<SuperMarket>>(superMarketService.listAllSupermarkets(), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "{id}",produces="Application/json" )
	@ApiOperation(value = "get all information about a single supermarket  ")
	public ResponseEntity<SuperMarket> findById( @PathVariable("id") long id) {
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	    try {
	    SuperMarket supermarket=superMarketService.getAsupermarket(id);
	    
		return  new ResponseEntity<SuperMarket>(supermarket,httpHeaders,HttpStatus.OK);
	}catch(Exception e) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
	@PostMapping(value="create" ,produces="Application/json" ,consumes={ "multipart/form-data" })
 
	@ApiOperation(produces="Application/json", consumes="multipart/form-data" ,value = "adding a new supermaket to the database and returning  the saved supermarket information after saving if no validation error occurs "   )
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "image",   dataType = "__file", paramType = "form")
	})
	
	public  ResponseEntity<?> addsupperMarket(@Valid @ModelAttribute  SupermarketWrapper wrappedSupperMarket )throws  Exception {
		
		
		SuperMarket supermarketToBeAdded = new SuperMarket();
		supermarketToBeAdded.setActive(wrappedSupperMarket.isActive());
		supermarketToBeAdded.setEnglishName(wrappedSupperMarket.getEnglishName());
		supermarketToBeAdded.setArabicName(wrappedSupperMarket.getArabicName()); 
		supermarketToBeAdded.setAddress(wrappedSupperMarket.getAddress());
		MultipartFile image =wrappedSupperMarket.getImage();
		supermarketToBeAdded.setImageAddress(saveImage(image));
		try {
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return  new ResponseEntity<SuperMarket>( superMarketService.saveSupermarket(supermarketToBeAdded),httpHeaders,HttpStatus.CREATED);
		}catch (IllegalArabicNameException e) {
			System.out.println(e.getMessage());
			
		      return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@DeleteMapping(value = "{id}",produces="text/plain" )
	@ApiOperation(value = "delete a supermarket by id")
	public ResponseEntity<String> deleteById( @PathVariable("id") long id) {
		try {
			  superMarketService.deleteSupermarket(id);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    } catch (Exception e) {
		      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PutMapping(value= "{id}"  ,produces="Application/json")
	@ApiOperation(value = "updating a supermarket information and returning with the supermaket information after saving if no validation error occurs ")
	@ApiParam(name="id" ,format ="long")
	public ResponseEntity<SuperMarket> updateAsupermarket( @PathVariable("id") long id ,@Valid @RequestBody  SuperMarket unsavedSuperMarket) throws Exception {
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		 SuperMarket supermarketToBeUpdat = superMarketService.getAsupermarket(id);

		    if (supermarketToBeUpdat!=null) {
		      
		    	supermarketToBeUpdat.setAddress(unsavedSuperMarket.getAddress());
		    	supermarketToBeUpdat.setActive(unsavedSuperMarket.isActive());
		    	supermarketToBeUpdat.setArabicName(unsavedSuperMarket.getArabicName());
		    	supermarketToBeUpdat.setEnglishName(unsavedSuperMarket.getEnglishName());
		      return new ResponseEntity<>(superMarketService.upateSuperMarket(supermarketToBeUpdat), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		

	}
	
	private String saveImage(MultipartFile image) throws Exception {
		String imageLocation="" ;
		if (!image.isEmpty() ||image != null ) {
			 if(image.getOriginalFilename().endsWith("jpg")
			 ||image.getOriginalFilename().endsWith("png")||image.getOriginalFilename().endsWith("gif"))
			 {
				
				  imageLocation = ServletContext.class
		                  .getClassLoader().getResource(IMAGE_DIR).getPath() + image.getOriginalFilename();
				 
			
			try {
				image.transferTo(new File(imageLocation));
			} catch (Exception e) {
           
				 
					return  "image did not save to the server due to internal issues" ;
			}
			 
	}
			 
}return  imageLocation ;
		
	}
}
