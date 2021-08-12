package com.hatley.assessment.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hatley.assessment.validationan.ArabicCharValidator;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="supermarkets_tbl")
public class SuperMarket implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public SuperMarket(@NotBlank(message = "supermarket English Name mandatory") @Size(max = 512) String englishName,
			 @Size(max = 512) String arabicName, String address ,String imageAddress , boolean active) {
		super();
		this.englishName = englishName;
		this.arabicName = arabicName;
		this.address = address;
		this.imageAddress=imageAddress;
		this.active=active;
	}
	@ApiModelProperty(hidden = true)
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "supermarket_id")
	private long id ;
	
	@ApiModelProperty(hidden = true)
	@Version
	private int version ;
	
	@ApiModelProperty(
			  value = "supermarket English Name ",
			  name = "English Name",
			  dataType = "String",
			  example = "Metro Market")
	@NotBlank(message = "supermarket English Name  is mandatory")
	@Size( max = 100)
	@Column(name = "supermarket_englishName" )
	private String englishName;
	
	@ApiModelProperty(
			  value = "supermarket arabic name",
			  name = "arbic name",
			  dataType = "String",
			  example = "مترو  ماركت")
	@ArabicCharValidator
	@Column(name = "supermarket_ArabicName")
	private String arabicName;
	@ApiModelProperty(
			  value = "supermarket Image address at the server",
			  name = " Image Address",
			  dataType = "String",
			  example = "LocalHost/drive/image.jpeg")
	@Column(name = "supermarket_imageaddress"  )
	private String imageAddress;
	
	@ApiModelProperty(
			  value = "supermarket Address",
			  name = "address",
			  dataType = "String",
			  example = "24 Almadinah Street , new Cairo")
	@Column(name = "supermarket_address" ,unique=true )
	private String address;
    
	@ApiModelProperty(
			  value = "true or false",
			  name = "active",
			  dataType = "boolean",
			  example = "true")
	@NotNull(message = "choose true or false")
	@Column(name = "active"  )
	private boolean active;
    

	public SuperMarket() {
		super();
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



	public String getImageAddress() {
		return imageAddress;
	}



	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
	
	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	@Override
	public boolean equals  (Object obj){ 
		if(this==obj) return true ;
		if(!(obj instanceof SuperMarket))return false;
		SuperMarket gateway=(SuperMarket)obj ;
		return this.address.equals(gateway.getAddress());	
	}
	
	@Override
	public String toString() {
		StringBuilder builder= new StringBuilder();
		String appendedidAndversion=(this.id!=0) ? "supermarket [ id=" + this.id + ", version=" + this.version  : "supermarket [id  and verion are not set yet by the JPA";
		builder.append(appendedidAndversion);
		builder.append( ", English Name " +this.englishName  );
		builder.append( ", gatewaySerialNumber " +this.englishName  );
		builder.append( ", gateway IP " +this.address +" ]" );
		return builder.toString();
	}
	@Override 
	public int hashCode() {
		return this.address.hashCode();
    }
}
