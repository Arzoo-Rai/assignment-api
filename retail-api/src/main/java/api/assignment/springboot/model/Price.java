package api.assignment.springboot.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document (collection = "Price")
public class Price {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	  @Field("_id")
	  @JsonIgnore
	  private String oid;

	  @Field("id")
	  private Long id;

	
	private String name;
	

	private PriceInfo current_price;

	public Price() {
		
	}
	
	public Price(long id, String name, PriceInfo pinfo) {
		this.id = id;
		this.name = name;
		this.current_price = pinfo;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PriceInfo getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(PriceInfo current_price) {
		this.current_price = current_price;
	}


	

	@Override
	public String toString() {
		return "Price [id=" + id + ", name=" + name + ", current_price=" + current_price + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getCurrent_price()=" + getCurrent_price() + "]";
	}

}
