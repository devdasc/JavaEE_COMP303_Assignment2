package shoppingApp.model;

public class ProductOrder {


/**
 * Associates a catalog Item with a specific order by keeping track of the
 * number ordered and the total price. Also provides some convenience methods to
 * get at the Item data without first extracting the Item separately.
 */

	private Product product;
	private int numOfProducts;

	public ProductOrder(Product product) {
		setProduct(product);
		setNumOfProducts(1);
	}

	public Product getProduct() {
		return (product);
	}

	protected void setProduct(Product product) {
		this.product = product;
	}

	public String getProductSKU() {
		return (getProduct().getSKU());
	}

	public String getDescription() {
		return (getProduct().getDescription());
	}

	public String getLongDescription() {
		return (getProduct().getLongDescription());
	}

	public double getUnitPrice() {
		return (getProduct().getUnitPrice());
	}
	
	public int getNoInStock() {
		return (getProduct().getNoInStock());
	}
	
	public void setNoInStock(int n) {
		getProduct().setNoInStock(n);
	}

	public int getNumOfProducts() {
		return (numOfProducts);
	}

	public void setNumOfProducts(int n) {			
		this.numOfProducts = n;
		
	}

	public void incrementNumOfProducts() {
		setNumOfProducts(getNumOfProducts() + 1);
	}

	public void cancelOrder() {
		setNumOfProducts(0);
	}

	public double getTotalPrice() {
		return (getNumOfProducts() * getUnitPrice());
	}
}
