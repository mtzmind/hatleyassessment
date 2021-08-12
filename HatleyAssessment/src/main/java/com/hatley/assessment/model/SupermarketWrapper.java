package com.hatley.assessment.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;
import com.hatley.assessment.validationan.ArabicCharValidator;
import io.swagger.annotations.ApiModelProperty;

public class SupermarketWrapper {
	@ApiModelProperty(value="image"   )
 private MultipartFile image ;
	@ApiModelProperty(
			  value = "true or false",
			
			  dataType = "boolean",
			  example = "true")
 @NotNull(message = "choose true or false")
 private boolean active ;
 @ApiModelProperty(
		  value = "supermarket arabic name",
		  dataType = "String",
		  example = "metro market")
@NotBlank(message = "supermarket English Name  is mandatory")
@Size( max = 100)
private String englishName ;
 @ApiModelProperty(
		  value = "supermarket arabic name",
		  dataType = "String",
		  example = "مترو  ماركت")
 @ArabicCharValidator
 private String arabicName ;
 @ApiModelProperty(
		  value = "supermarket Address",
		  dataType = "String",
		  example = "24 Almadinah Street , new Cairo")

private String address;
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public MultipartFile getImage() {
	return image;
}
public void setImage(MultipartFile image) {
	this.image = image;
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}
public String getEnglishName() {
	return englishName;
}
public void setEnglishName(String englishName) {
	this.englishName = englishName;
}
public String getArabicName() {
	return arabicName;
}
public void setArabicName(String arabicName) {
	this.arabicName = arabicName;
}
public SupermarketWrapper(MultipartFile image, boolean activated, String englishName, String arabicName) {
	super();
	this.image = image;
	this.active = activated;
	this.englishName = englishName;
	this.arabicName = arabicName;
}
public SupermarketWrapper() {
	
	
}
 
}
