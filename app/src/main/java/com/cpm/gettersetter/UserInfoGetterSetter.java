package com.cpm.gettersetter;

import java.util.ArrayList;

public class UserInfoGetterSetter {

	ArrayList<String> status = new ArrayList<String>();
	ArrayList<String> current_date = new ArrayList<String>();
	ArrayList<String> app_version = new ArrayList<String>();
	ArrayList<String> app_path = new ArrayList<String>();
	ArrayList<String> key_account = new ArrayList<String>();
	ArrayList<String> store_card = new ArrayList<String>();
	ArrayList<String> store_name= new ArrayList<String>();
	//ArrayList<String> category = new ArrayList<String>();
	ArrayList<String> store_type = new ArrayList<String>();
	ArrayList<String> city = new ArrayList<String>();
	ArrayList<String> address = new ArrayList<String>();
	ArrayList<String> store_cd = new ArrayList<String>();
	ArrayList<String> language = new ArrayList<String>();
	ArrayList<String> processid = new ArrayList<String>();
	
	public ArrayList<String> getProcessid() {
		return processid;
	}
	public void setProcessid(String processid) {
		this.processid.add(processid);
	}
	public ArrayList<String> getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status.add(status);
	}
	public ArrayList<String> getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version.add(app_version);
	}
	public ArrayList<String> getApp_path() {
		return app_path;
	}
	public void setApp_path(String app_path) {
		this.app_path.add(app_path);
	}
	public ArrayList<String> getKey_account() {
		return key_account;
	}
	public void setKey_account(String key_account) {
		this.key_account.add(key_account);
	}
	public ArrayList<String> getStore_card() {
		return store_card;
	}
	public void setStore_card(String store_card) {
		this.store_card.add(store_card);
	}
	public ArrayList<String> getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name.add(store_name);
	}
	public ArrayList<String> getCurrent_date() {
		return current_date;
	}
	public void setCurrent_date(String current_date) {
		this.current_date.add(current_date);
	}
/*	public ArrayList<String> getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category.add(category);
	}*/
	public ArrayList<String> getStore_type() {
		return store_type;
	}
	public void setStore_type(String store_type) {
		this.store_type.add(store_type);
	}
	public ArrayList<String> getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city.add(city);
	}
	public ArrayList<String> getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address.add(address);
	}
	public ArrayList<String> getStore_cd() {
		return store_cd;
	}
	public void setStore_cd(String store_cd) {
		this.store_cd.add(store_cd);
	}
	public ArrayList<String> getlanguage() {
		return language;
	}
	public void setlanguage(String lang) {
		this.language.add(lang);
	}
	
	
}