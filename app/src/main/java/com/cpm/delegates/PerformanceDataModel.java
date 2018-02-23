package com.cpm.delegates;

import java.util.ArrayList;

public class PerformanceDataModel {
	
	String incentive;
	String sku ;
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getMtd() {
		return mtd;
	}
	public void setMtd(String mtd) {
		this.mtd = mtd;
	}
	public String getTarget() {
		return target;
	}
	public String getIncentive() {
		return incentive;
	}
	public void setIncentive(String incentive) {
		this.incentive = incentive;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	String sale ;
	String mtd ;
	String target ;
	String achievement;

}
