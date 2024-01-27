package com.juice.entity;

public class Juice {
	private String juiceName;
	private String juiceId;
	private String category;
	private double price;
	private Integer quantityInStock;
	public String getJuiceName() {
		return juiceName;
	}
	public void setJuiceName(String juiceName) {
		this.juiceName = juiceName;
	}
	public String getJuiceId() {
		return juiceId;
	}
	public void setJuiceId(String juiceId) {
		this.juiceId = juiceId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	@Override
	public String toString() {
		return "[\njuiceName=" + " "+juiceName + ",\n juiceId=" + " "+juiceId + ",\n category=" +" "+ category + ",\n price=" +" "+ price
				+ ",\n quantityInStock=" + " "+quantityInStock +"]";
		
	
	}
	public Juice(String juiceId, String juiceName, String category,Integer quantityInStock,double price) {
		super();
		this.juiceName = juiceName;
		this.juiceId = juiceId;
		this.category = category;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}
	public Juice() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
