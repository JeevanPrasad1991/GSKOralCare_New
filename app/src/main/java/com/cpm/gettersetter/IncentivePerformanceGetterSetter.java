package com.cpm.gettersetter;

import java.util.ArrayList;

public class IncentivePerformanceGetterSetter {

	ArrayList<String> month_week = new ArrayList<String>();
	ArrayList<String> incentive = new ArrayList<String>();
	
	public ArrayList<String> getMonth_week() {
		return month_week;
	}
	public void setMonth_week(String month_week) {
		this.month_week.add(month_week);
	}
	public ArrayList<String> getIncentive() {
		return incentive;
	}
	public void setIncentive(String incentive) {
		this.incentive.add(incentive);
	}
	
	
}
	
	