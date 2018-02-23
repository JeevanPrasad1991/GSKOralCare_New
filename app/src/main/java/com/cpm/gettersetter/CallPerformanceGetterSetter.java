package com.cpm.gettersetter;

import java.util.ArrayList;

public class CallPerformanceGetterSetter {

	ArrayList<String> product = new ArrayList<String>();
	ArrayList<String> producttarget = new ArrayList<String>();
	ArrayList<String> mtd = new ArrayList<String>();
	ArrayList<String> achievement = new ArrayList<String>();
	
	public ArrayList<String> getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product.add(product);
	}
	public ArrayList<String> getProducttarget() {
		return producttarget;
	}
	public void setProducttarget(String producttarget) {
		this.producttarget.add(producttarget);
	}
	public ArrayList<String> getMtd() {
		return mtd;
	}
	public void setMtd(String mtd) {
		this.mtd.add(mtd);
	}
	public ArrayList<String> getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement.add(achievement);
	}
	
}
	
	