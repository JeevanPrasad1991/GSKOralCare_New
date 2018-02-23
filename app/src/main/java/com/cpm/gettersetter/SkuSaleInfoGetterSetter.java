package com.cpm.gettersetter;

import java.util.ArrayList;

/**
 * Created by ankurk on 26-07-2017.
 */

public class SkuSaleInfoGetterSetter {

    String sku_sale_table_name;
    ArrayList<String> sku_id = new ArrayList<String>();
    ArrayList<String> sku = new ArrayList<String>();

    ArrayList<String> ach_mtd = new ArrayList<>();
    ArrayList<String> target = new ArrayList<>();
    ArrayList<String> ach_per = new ArrayList<String>();

    public ArrayList<String> getAch_per() {
        return ach_per;
    }

    public void setAch_per(String ach_per) {
        this.ach_per.add(ach_per);
    }

    public ArrayList<String> getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku.add(sku);
    }
    public ArrayList<String> getSku_id() {
        return sku_id;
    }
    public void setSku_id(String sale) {
        this.sku_id.add(sale);
    }
    public ArrayList<String> getMtd() {
        return ach_mtd;
    }
    public void setMtd(String mtd) {
        this.ach_mtd.add(mtd);
    }
    public ArrayList<String> getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target.add(target);
    }

    public String getSku_sale_table_name() {
        return sku_sale_table_name;
    }
    public void setSku_sale_table_name(String sku_sale_table_name) {
        this.sku_sale_table_name = sku_sale_table_name;
    }
}
