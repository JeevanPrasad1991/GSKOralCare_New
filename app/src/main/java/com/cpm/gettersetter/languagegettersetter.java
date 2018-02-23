package com.cpm.gettersetter;

import java.util.ArrayList;

public class languagegettersetter {
	ArrayList<String> languageid = new ArrayList<String>();
	ArrayList<String> language = new ArrayList<String>();
	
	public ArrayList<String> getlanguageid() {
		return languageid;
	}
	public void setlanguageid(String langid) {
		this.languageid.add(langid);
	}
	public ArrayList<String> getlanguage() {
		return language;
	}
	public void setlanguage(String lang) {
		this.language.add(lang);
	}

}
