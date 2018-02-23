package com.cpm.gettersetter;

import java.util.ArrayList;

public class AcheivementGetterSetter {

	ArrayList<String> ACHIEVEMENT = new ArrayList<String>();
	ArrayList<String> Target = new ArrayList<String>();

	public ArrayList<String> getTarget() {
		return Target;
	}

	public void setTarget(String target) {
		this.Target.add(target);
	}

	public ArrayList<String> getACHIEVEMENT() {
		return ACHIEVEMENT;
	}

	public void setACHIEVEMENT(String aCHIEVEMENT) {
		this.ACHIEVEMENT.add(aCHIEVEMENT);
	}
	
}
	
	