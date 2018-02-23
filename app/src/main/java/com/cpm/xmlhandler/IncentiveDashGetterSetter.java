package com.cpm.xmlhandler;

import java.util.ArrayList;

/**
 * Created by jeevanp on 2/23/2018.
 */

public class IncentiveDashGetterSetter {
    ArrayList<String>monthSEQ=new ArrayList<>();
    ArrayList<String>month_name=new ArrayList<>();

    public ArrayList<String> getMonthSEQ() {
        return monthSEQ;
    }

    public void setMonthSEQ(String monthSEQ) {
        this.monthSEQ.add(monthSEQ);
    }

    public ArrayList<String> getMonth_name() {
        return month_name;
    }

    public void setMonth_name(String month_name) {
        this.month_name.add(month_name);
    }

    public ArrayList<String> getIncentive() {
        return incentive;
    }

    public void setIncentive(String incentive) {
        this.incentive.add(incentive);
    }

    ArrayList<String>incentive=new ArrayList<>();
}
