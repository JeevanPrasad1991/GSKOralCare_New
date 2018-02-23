package com.cpm.gettersetter;

import java.util.ArrayList;

public class StoreSaleInfoGetterSetter {

	ArrayList<String> BRAND_ID = new ArrayList<String>();
	ArrayList<String> SKU_ID = new ArrayList<String>();
	ArrayList<String> ARTICLE_NO = new ArrayList<String>();
	ArrayList<String> BRAND_SEQUENCE = new ArrayList<String>();
	ArrayList<String> SKU_SEQUENCE = new ArrayList<String>();
	ArrayList<String> MRP = new ArrayList<String>();
	
	public ArrayList<String> getBRAND_ID() {
		return BRAND_ID;
	}
	public void setBRAND_ID(String bRAND_ID) {
		this.BRAND_ID.add(bRAND_ID);
	}
	public ArrayList<String> getSKU_ID() {
		return SKU_ID;
	}
	public void setSKU_ID(String sKU_ID) {
		this.SKU_ID.add(sKU_ID);
	}
	public ArrayList<String> getARTICLE_NO() {
		return ARTICLE_NO;
	}
	public void setARTICLE_NO(String aRTICLE_NO) {
		this.ARTICLE_NO.add(aRTICLE_NO);
	}
	public ArrayList<String> getBRAND_SEQUENCE() {
		return BRAND_SEQUENCE;
	}
	public void setBRAND_SEQUENCE(String bRAND_SEQUENCE) {
		this.BRAND_SEQUENCE.add(bRAND_SEQUENCE);
	}
	public ArrayList<String> getSKU_SEQUENCE() {
		return SKU_SEQUENCE;
	}
	public void setSKU_SEQUENCE(String sKU_SEQUENCE) {
		this.SKU_SEQUENCE.add(sKU_SEQUENCE);
	}
	public ArrayList<String> getMRP() {
		return MRP;
	}
	public void setMRP(String mRP) {
		this.MRP.add(mRP);
	}

	

	
	
}