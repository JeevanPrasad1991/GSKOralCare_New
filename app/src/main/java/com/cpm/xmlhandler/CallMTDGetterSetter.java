package com.cpm.xmlhandler;

import java.util.ArrayList;

/**
 * Created by jeevanp on 2/23/2018.
 */

public class CallMTDGetterSetter {
    ArrayList<String>today_call=new ArrayList<>();

    public ArrayList<String> getToday_call() {
        return today_call;
    }

    public void setToday_call(String today_call) {
        this.today_call.add(today_call);
    }

    public ArrayList<String> getMtd_call() {
        return mtd_call;
    }

    public void setMtd_call(String mtd_call) {
        this.mtd_call.add(mtd_call);
    }

    ArrayList<String> mtd_call =new ArrayList<>();

}
