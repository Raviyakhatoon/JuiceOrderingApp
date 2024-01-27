package com.juice.entity;

public class Cart {
	 private  String cartId;
	 private String juiceId;
	 private String customerId;
	 private Integer quantity;// the number of food items added to cart.
	 private double subTotal;// price* quantity
	 private double  price;// food price 
	 private Juice f;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getjuiceId() {
		return juiceId;
	}
	public void setjuiceId(String juiceId) {
		this.juiceId = juiceId;
	}
	
	public void setCustomerId(String juiceId) {
		this.customerId = juiceId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setEmailId(String emailId) {
		this.customerId = customerId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Juice getF() {
		return f;
	}
	public void setF(Juice f) {
		this.f = f;
	}
	public Cart(String cartId, String juiceId, String customerId, Integer quantity, double subTotal, double price) {
		super();
		this.cartId = cartId;
		this.juiceId = juiceId;
		this.customerId = customerId;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.price = price;
		
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", juiceId=" + juiceId + ", customerId=" + customerId + ", quantity=" + quantity
				+ ", subTotal=" + subTotal + ", price=" + price + ", f=" + f + "]";
	}
	 

}
