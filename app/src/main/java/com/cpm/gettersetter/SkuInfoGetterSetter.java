package com.cpm.gettersetter;

import java.util.ArrayList;

public class SkuInfoGetterSetter {

	ArrayList<String> SKU_ID = new ArrayList<String>();
	ArrayList<String> SKU = new ArrayList<String>();
	ArrayList<String> COMPANY_ID = new ArrayList<String>();
	ArrayList<String> brand_id = new ArrayList<String>();
	public ArrayList<String> getTarget_value() {
		return target_value;
	}

	public void setTarget_value(String target_value) {
		this.target_value.add(target_value);
	}

	ArrayList<String> target_value = new ArrayList<String>();
	

	
	public ArrayList<String> getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id.add(brand_id);
	}

	public ArrayList<String> getSKU_ID() {
		return SKU_ID;
	}

	public void setSKU_ID(String sKU_ID) {
		this.SKU_ID.add(sKU_ID);
	}

	public ArrayList<String> getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		this.SKU.add(sKU);
	}

	public void setCOMPANY_ID(String cOMPANY_ID) {
		this.COMPANY_ID.add(cOMPANY_ID);
	}

	public ArrayList<String> getCOMPANY_ID() {
		return COMPANY_ID;
	}


}