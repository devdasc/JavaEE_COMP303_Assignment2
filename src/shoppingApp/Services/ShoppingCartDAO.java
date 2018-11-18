package shoppingApp.Services;
import java.util.*;

import shoppingApp.model.ProductList;
import shoppingApp.model.ProductOrder;

public class ShoppingCartDAO {


/**
 * A shopping cart data structure used to track orders. The OrderPage servlet
 * associates one of these carts with each user session.
 */

	private Vector productsOrdered;
	public Vector productList;

	/** Builds an empty shopping cart. */

	public ShoppingCartDAO() {
		
		productsOrdered = new Vector();
		
	}
	
	/**
	 * Returns Vector of ItemOrder entries giving Item and number ordered.
	 */
	public Vector getAllProducts() {
		productList = new Vector();
		productList.addAll(ProductList.getAllProducts());
		return productList;
	}

	/**
	 * Returns Vector of ItemOrder entries giving Item and number ordered.
	 */
	/*public Vector getProductsOrdered() {
		return productsOrdered;
	}*/

	/**
	 * Looks through cart to see if it already contains an order entry
	 * corresponding to item ID. If it does, increments the number ordered. If
	 * not, looks up Item in catalog and adds an order entry for it.
	 */
	/*public synchronized void addItem(String sku) {
		ProductOrder order;
		for (int i = 0; i < productsOrdered.size(); i++) {
			order = (ProductOrder) productsOrdered.elementAt(i);
			if (order.getProductSKU().equals(sku)) {
				order.incrementNumOfProducts();
				return;
			}
		}
		ProductOrder newOrder = new ProductOrder(ProductList.getProduct(sku));
		productsOrdered.addElement(newOrder);
	}*/

	/**
	 * Looks through cart to find order entry corresponding to item ID listed.
	 * If the designated number is positive, sets it. If designated number is 0
	 * (or, negative due to a user input error), deletes item from cart.
	 */
	/*public synchronized void setNumOrdered(String sku, int numOrdered) {
		ProductOrder order;
		for (int i = 0; i < productsOrdered.size(); i++) {
			order = (ProductOrder) productsOrdered.elementAt(i);
			if (order.getProductSKU().equals(sku)) {
				if (numOrdered <= 0) {
					productsOrdered.removeElementAt(i);
				} else {
					order.setNumOfProducts(numOrdered);
				}
				return;
			}
		}
		ProductOrder newOrder = new ProductOrder(ProductList.getProduct(sku));
		productsOrdered.addElement(newOrder);
	}*/
	
	public synchronized ProductOrder getOrderedProduct(String sku) {
		ProductOrder order;
		for (int i = 0; i < productsOrdered.size(); i++) {
			order = (ProductOrder) productsOrdered.elementAt(i);
			if (order.getProductSKU().equals(sku)) {
				return order;
			}
		}
		return null;
	}

	/*public synchronized int getNumOrdered() {
		return productsOrdered.size();
	}*/
	
	
	
	
}

