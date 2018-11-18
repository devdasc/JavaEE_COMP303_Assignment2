package shoppingApp.Services;
import java.util.*;

import javax.servlet.RequestDispatcher;

import shoppingApp.model.Product;
import shoppingApp.model.ProductList;
import shoppingApp.model.ProductOrder;

public class ShoppingCartBLL {
	
	private Vector productsOrdered;
	public Vector productList;
	
	public ShoppingCartBLL()
	{
		productsOrdered = new Vector();
		
		ShoppingCartDAO cartDAO=new ShoppingCartDAO();	
		//productsOrdered= cartDAO.pro
		productList =cartDAO.getAllProducts();
	}
	
	public synchronized String addItem(String sku) {
		
		String validationMsg=null;
		validationMsg=validateAvailability(sku, 1);
		
		if(validationMsg==null)
		{	
			ProductOrder order;
			for (int i = 0; i < productsOrdered.size(); i++) {
				order = (ProductOrder) productsOrdered.elementAt(i);
				if (order.getProductSKU().equals(sku)) {
					order.incrementNumOfProducts();
					updateProductStock(sku, 1);
					return null;
				}
			}
			ProductOrder newOrder = new ProductOrder(ProductList.getProduct(sku));
			productsOrdered.addElement(newOrder);
			updateProductStock(sku, 1);
		}
		
		return validationMsg;
	}
	
	public void updateProductStock(String sku, int buyQuantity)
	{
		Product product=ProductList.getProduct(sku);
		product.setNoInStock(product.getNoInStock()-buyQuantity);
		ProductList.setProduct(product);
	}
	
	public synchronized String setNumOrdered(String sku, int numOrdered) {
		ProductOrder order;
		String validationMsg=null;
		validationMsg=validateAvailability(sku, numOrdered);
		
		if(validationMsg==null)
		{		
			for (int i = 0; i < productsOrdered.size(); i++) {
				order = (ProductOrder) productsOrdered.elementAt(i);
				if (order.getProductSKU().equals(sku)) {
					if (numOrdered <= 0) {
						productsOrdered.removeElementAt(i);
					} else {
						int alreadyOrdered=order.getNumOfProducts();
						
						order.setNumOfProducts(numOrdered);
						updateProductStock(sku, numOrdered-alreadyOrdered);
					}
					return null;
				}
		}
			ProductOrder newOrder = new ProductOrder(ProductList.getProduct(sku));
			productsOrdered.addElement(newOrder);
		}
		return validationMsg;		
	}
	
	public String validateAvailability(String sku, int numOrdered)
	{
		String errorMsg=null;
		int alreadyOrdered=0;
		ProductOrder order=getOrderedProduct(sku);
		
		if(order!=null)
		{
		 alreadyOrdered=getOrderedProduct(sku).getNumOfProducts();
		}
		
		if((numOrdered-alreadyOrdered)>ProductList.getProduct(sku).getNoInStock())
		{
			errorMsg="Product not available in stock";			
		}		
		return errorMsg;
	}
	
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
	
	public Vector getProductsOrdered() {
		return productsOrdered;
	}
	
	public synchronized int getNumOrdered() {
		return productsOrdered.size();
	}

}
