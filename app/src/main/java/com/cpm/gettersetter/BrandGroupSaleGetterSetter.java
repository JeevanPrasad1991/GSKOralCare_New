package com.cpm.gettersetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 16-08-2017.
 */

public class BrandGroupSaleGetterSetter {
    String  metadataTable;
    ArrayList<String>brandgroup=new ArrayList<>();

    public String getMetadataTable() {
        return metadataTable;
    }

    public void setMetadataTable(String metadataTable) {
        this.metadataTable = metadataTable;
    }

    public ArrayList<String> getBrandgroup() {
        return brandgroup;
    }

    public void setBrandgroup(String brandgroup) {
        this.brandgroup.add(brandgroup);
    }

    public ArrayList<String> getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target.add(target);
    }

    public ArrayList<String> getAchievment() {
        return achievment;
    }

    public void setAchievment(String achievment) {
        this.achievment.add(achievment);
    }

    ArrayList<String>target=new ArrayList<>();
    ArrayList<String>achievment=new ArrayList<>();
}
