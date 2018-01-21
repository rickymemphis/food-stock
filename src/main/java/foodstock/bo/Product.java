package foodstock.bo;

import java.io.Serializable;

public class Product implements Serializable {

	private String skn;
	private String name;
	private String description;

	public Product() {
		//
	}

	public Product(String skn, String name, String description) {
		this.skn = skn;
		this.name = name;
		this.description = description;
	}

	public String getSkn() {
		return skn;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setSkn(String skn) {
		this.skn = skn;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
