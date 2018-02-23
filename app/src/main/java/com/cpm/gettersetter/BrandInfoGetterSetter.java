package com.cpm.gettersetter;

import java.util.ArrayList;

public class BrandInfoGetterSetter {

	ArrayList<String> BRAND_ID = new ArrayList<String>();
	ArrayList<String> BRAND = new ArrayList<String>();
	ArrayList<String> CATEGORY_ID = new ArrayList<String>();
	ArrayList<String> COMPANY_ID = new ArrayList<String>();

	public ArrayList<String> getBRAND_ID() {
		return BRAND_ID;
	}

	public void setBRAND_ID(String bRAND_ID) {
		this.BRAND_ID.add(bRAND_ID);
	}

	public ArrayList<String> getBRAND() {
		return BRAND;
	}

	public void setBRAND(String bRAND) {
		this.BRAND.add(bRAND);
	}

	public ArrayList<String> getCATEGORY_ID() {
		return CATEGORY_ID;
	}

	public void setCATEGORY_ID(String cATEGORY_ID) {
		this.CATEGORY_ID.add(cATEGORY_ID);
	}

	public ArrayList<String> getCOMPANY_ID() {
		return COMPANY_ID;
	}

	public void setCOMPANY_ID(String cOMPANY_ID) {
		this.COMPANY_ID.add(cOMPANY_ID);
	}

}