package shoppingApp.model;

public class Product {

/**
 * Describes a catalog product for shopping app. The itemID uniquely identifies
 * the product, the description gives brief info like the book title and
 * author, the long description describes the product in a couple of sentences, and
 * the unit price gives the current per-item price. Both the short and long
 * descriptions can contain HTML markup.
 **/

	private String sku;
	private String description;
	private int noInStock;
	private double unitPrice;
	private String longDescription;

	public Product(String sku, String description,int noInStock, double unitPrice, String longDescription) {
		setItemID(sku);
		setDescription(description);
		setLongDescription(longDescription);
		setUnitPrice(unitPrice);
		setNoInStock(noInStock);
	}

	public String getSKU() {
		return (sku);
	}

	protected void setItemID(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return (description);
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	public String getLongDescription() {
		return (longDescription);
	}

	protected void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	public int getNoInStock() {
		return (noInStock);
	}

	public void setNoInStock(int noInStock) {
		this.noInStock = noInStock;
	}

	public double getUnitPrice() {
		return (unitPrice);
	}

	protected void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
