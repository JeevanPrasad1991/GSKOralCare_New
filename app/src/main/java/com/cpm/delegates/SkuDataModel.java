package com.cpm.delegates;


public class SkuDataModel {
	
	String sku_id;
	String sku ;
	String company_id;
	String sku_quantity,brandid;
	
	public String getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}
	public String getSku_quantity() {
		return sku_quantity;
	}
	public void setSku_quantity(String sku_quantity) {
		this.sku_quantity = sku_quantity;
	}
	public String getSku_id() {
		return sku_id;
	}
	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	

}
