package com.juice.service;

import java.util.List;

import com.juice.entity.Cart;

public interface CartService {
	public boolean addToCart(Cart c);
	public boolean updateQuantity(String cartId, Integer quantity);
	public boolean deleteFoodItemFromcart(String cartId);
	public List<Cart> showMycart(String mailId);
	public List<Cart> showAllCart();
	public Cart getCartById(String cartId);
	public  boolean clearMyCart(String customerId);
	public  boolean  checkJuiceItem(String customerId ,String juiceId);
	
	

}
