package foodstock.bo;

import java.io.Serializable;
import java.util.Date;

public class Inventory implements Serializable {

	private static final long serialVersionUID = -6097522037856976826L;

	private String skn;
	private int quantity;
	private Date purchaseDate;
	private Date expirationDate;

	public Inventory() {

	}

	public Inventory(String skn, int quantity, Date purchaseDate, Date expirationDate) {
		this.skn = skn;
		this.quantity = quantity;
		this.purchaseDate = purchaseDate;
		this.expirationDate = expirationDate;
	}

	public String getSkn() {
		return skn;
	}

	public void setSkn(String skn) {
		this.skn = skn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
