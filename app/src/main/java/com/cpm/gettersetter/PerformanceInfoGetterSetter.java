package com.cpm.gettersetter;

import java.util.ArrayList;

public class PerformanceInfoGetterSetter {

	ArrayList<String> sku = new ArrayList<String>();
	ArrayList<String> sale = new ArrayList<String>();
	ArrayList<String> mtd = new ArrayList<String>();
	ArrayList<String> target = new ArrayList<String>();
	ArrayList<String> achievement = new ArrayList<String>();
	
	
	public ArrayList<String> getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku.add(sku);
	}
	public ArrayList<String> getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale.add(sale);
	}
	public ArrayList<String> getMtd() {
		return mtd;
	}
	public void setMtd(String mtd) {
		this.mtd.add(mtd);
	}
	public ArrayList<String> getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target.add(target);
	}
	public ArrayList<String> getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement.add(achievement);
	}
	
}