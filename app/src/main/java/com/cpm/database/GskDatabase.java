package com.cpm.database;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cpm.delegates.CalCountBean;
import com.cpm.delegates.CallPerformanceDataModel;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.PerformanceDataModel;
import com.cpm.delegates.SkuDataModel;
import com.cpm.delegates.UploadDataModel;
import com.cpm.delegates.UploadSaveBean;
import com.cpm.delegates.languageBean;
import com.cpm.gettersetter.BrandGroupSaleGetterSetter;
import com.cpm.gettersetter.BrandInfoGetterSetter;
import com.cpm.gettersetter.CallPerformanceGetterSetter;
import com.cpm.gettersetter.IncentivePerformanceGetterSetter;
import com.cpm.gettersetter.JCPGetterSetter;
import com.cpm.gettersetter.PerformanceInfoGetterSetter;
import com.cpm.gettersetter.SkuInfoGetterSetter;
import com.cpm.gettersetter.SkuSaleInfoGetterSetter;
import com.cpm.gettersetter.StoreSaleInfoGetterSetter;
import com.cpm.gettersetter.UserInfoGetterSetter;
import com.cpm.gettersetter.languagegettersetter;
import com.cpm.xmlhandler.CallMTDGetterSetter;
import com.cpm.xmlhandler.IncentiveDashGetterSetter;
import com.gsk.constants.Constant;

@SuppressLint("LongLogTag")
public class GskDatabase {
    private Context m_ContextObj;
    private SQLiteDatabase SqlDataBaseObj;

    /**
     * @param ctx
     */
    public GskDatabase(Context ctx) {
        this.m_ContextObj = ctx;
    }

    public void CreateTable() {
        try {
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_SALES_RECORD);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_STORE_DETAILS);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_SALES_DATA);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_PERFORMANCE_DATA);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_CALL_PERFORMANCE);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_INCENTIVE);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_BRAND_INFO);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_SKU_INFO);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_STORE_SALE_INFO);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_SAVE_SALE_RECORD);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_COVERAGE_DATA);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_BRAND_COUNT_INFO);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_LANGUAGE);
            /////////cretenew...........by jeeeeeva

            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_OHC_INCENTIVE);
            SqlDataBaseObj.execSQL(com.gsk.constants.Constant.CREATE_TABLE_OHC_CALL_REPORT);


        } catch (Exception ex) {
            Log.d("Database Exception ", ex.getMessage());
        }
    }

    public void openDB() {
        try {
            SqlDataBaseObj = m_ContextObj.openOrCreateDatabase(
                    com.gsk.constants.Constant.DATABASE_NAME, m_ContextObj.MODE_WORLD_READABLE, null);
        } catch (Exception ex) {
            Log.d("Database Exception ", ex.getMessage());
        }
    }

    public void createTable(String table) {
        SqlDataBaseObj.execSQL(table);
    }

    public void createBrandGTable(String table) {
        SqlDataBaseObj.execSQL(table);
    }

    /**
     *
     */
    public void CloseDB() {
        try {
            SqlDataBaseObj.close();
        } catch (Exception ex) {
        }
    }

    public void InsertBrandSalesInfo(BrandInfoGetterSetter data) {
        ContentValues values = new ContentValues();

        try {
            for (int i = 0; i < data.getBRAND().size(); i++) {
                values.put("BRAND_ID", data.getBRAND_ID().get(i));
                values.put("BRAND", data.getBRAND().get(i));
                values.put("CATEGORY_ID", data.getCATEGORY_ID().get(i));
                values.put("COMPANY_ID", "");
                SqlDataBaseObj.insert(Constant.TABLE_BRAND_INFO, null, values);
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ", ex.getMessage());
        }
    }

    public void InsertBrandCallCountInfo(int mid, String brand_id,
                                         String brand, String count, String store_id) {

        ContentValues values = new ContentValues();

        try {
            values.put("MID", mid);
            values.put("BRAND_ID", brand_id);
            values.put("BRAND", brand);
            values.put("COUNT", count);
            values.put("STORE_ID", store_id);

            SqlDataBaseObj
                    .insert(Constant.TABLE_BRAND_COUNT_INFO, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }

    }

    public int CheckBrand(String brand) {

        Cursor dbcursor = null;
        int numrows = 0;
        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from "
                    + Constant.TABLE_BRAND_COUNT_INFO + " WHERE BRAND = '"
                    + brand + "'", null);

            if (dbcursor != null) {
                // dbcursor.moveToFirst();
                numrows = dbcursor.getCount();
                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

        return numrows;
    }

    public void UpdateBrandCount(String brand_id) {

        Cursor dbcursor = null;
        try {

            dbcursor = SqlDataBaseObj.rawQuery("Update "
                    + Constant.TABLE_BRAND_COUNT_INFO + " SET COUNT = COUNT+1 "
                    + " WHERE BRAND_ID = '" + brand_id + "'", null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();
                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

    }

    public void InsertSkuSalesInfo(SkuInfoGetterSetter data) {

        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getSKU_ID().size(); i++) {

                values.put("SKU_ID", data.getSKU_ID().get(i));
                values.put("SKU", data.getSKU().get(i));
                values.put("COMPANY_ID", "");
                values.put("BRAND_ID", data.getBrand_id().get(i));

                SqlDataBaseObj.insert(Constant.TABLE_SKU_INFO, null, values);

            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }

    }

    public void InsertStoreSalesInfo(StoreSaleInfoGetterSetter data, BrandInfoGetterSetter branddata, SkuInfoGetterSetter skudata) {

        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getBRAND_ID().size(); i++) {

                values.put("BRAND_ID", data.getBRAND_ID().get(i));
                values.put("SKU_ID", skudata.getSKU_ID().get(i));
                values.put("ARTICLE_NO", "0");
                values.put("BRAND_SEQUENCE", data.getBRAND_SEQUENCE().get(i));
                values.put("SKU_SEQUENCE", data.getSKU_SEQUENCE().get(i));
                values.put("MRP", "0");

                SqlDataBaseObj.insert(Constant.TABLE_STORE_SALE_INFO, null,
                        values);
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }

    }

    public void InsertSaveSaleRecord(ArrayList<SkuDataModel> data, int mid,
                                     ArrayList article, String store_id) {

        ContentValues values = new ContentValues();

        try {
            for (int i = 0; i < data.size(); i++) {
                values.put("MID", mid);

                values.put("SKU_ID", data.get(i).getSku_id());
                values.put("STORE_ID", store_id);
                values.put("SKUQTY", data.get(i).getSku_quantity());

                SqlDataBaseObj.insert(Constant.TABLE_SAVE_SALE_RECORD, null,
                        values);
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }

    }

    // get sale record

    // GET ARTICLE_NO


    public ArrayList<CalCountBean> getcall_count(String store_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");

        ArrayList<CalCountBean> cdata = new ArrayList<CalCountBean>();

        Cursor dbcursor = null;

        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from "
                    + Constant.TABLE_BRAND_COUNT_INFO + " where STORE_ID = '" + store_id
                    + "'", null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();

                dbcursor.moveToFirst();
                for (int i = 1; i <= numrows; i++) {

                    CalCountBean data = new CalCountBean();
                    data.setBrandid(dbcursor.getString(1));
                    data.setBrand(dbcursor.getString(2));
                    data.setCount(dbcursor.getString(3));
                    cdata.add(data);
                    dbcursor.moveToNext();

                }

                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return cdata;

    }

    public int InsertCoverageData(CoverageBean data) {
        int mid = 0;
        Cursor dbcursor = null;
        ContentValues values = new ContentValues();

        try {

            values.put("STORE_ID", data.getStoreId());
            values.put("USER_ID", data.getUserId());
            values.put("IN_TIME", data.getInTime());
            values.put("OUT_TIME", data.getOutTime());
            values.put("VISIT_DATE", data.getVisitDate());
            // values.put("LATITUDE", data.getLatitude());
            // values.put("LONGITUDE", data.getLongitude());
            // values.put("REASON_ID", data.getReasonid());
            // values.put("REASON", data.getReason());
            values.put("STATUS", "N");
            values.put("VERSION", data.getVersion());

            SqlDataBaseObj.insert(Constant.TABLE_COVERAGE_DATA, null, values);

            dbcursor = SqlDataBaseObj.rawQuery("SELECT MAX(ID) from "
                    + Constant.TABLE_COVERAGE_DATA, null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();
                dbcursor.moveToFirst();
                for (int i = 0; i < numrows; ++i) {

                    mid = dbcursor.getInt(0);
                    dbcursor.moveToNext();

                }

                dbcursor.close();

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }

        return mid;

    }

    public ArrayList<CoverageBean> getcoverage() {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");

        ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();

        Cursor dbcursor = null;

        try {

            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from "
                    + Constant.TABLE_COVERAGE_DATA, null);

            if (dbcursor != null) {

                dbcursor.moveToFirst();

                while (!dbcursor.isAfterLast()) {

                    CoverageBean sb = new CoverageBean();

                    sb.setMID((dbcursor.getInt(dbcursor
                            .getColumnIndexOrThrow("ID"))));
                    sb.setStoreId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_ID")));
                    sb.setUserId((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("USER_ID"))));
                    sb.setInTime(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IN_TIME")))));
                    sb.setOutTime(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("OUT_TIME")))));
                    sb.setVisitDate((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("VISIT_DATE"))))));

                    sb.setVersion((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("VERSION"))))));

                    cdata.add(sb);
                    dbcursor.moveToNext();
                }

                dbcursor.close();
                return cdata;

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());

        }
        return cdata;

    }

    public ArrayList<UploadSaveBean> getUploadData(String store_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");

        ArrayList<UploadSaveBean> sdata = new ArrayList<UploadSaveBean>();

        Cursor dbcursor = null;

        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from "
                    + Constant.TABLE_SAVE_SALE_RECORD + " where STORE_ID = '" + store_id
                    + "'", null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();

                dbcursor.moveToFirst();
                for (int i = 1; i <= numrows; i++) {

                    UploadSaveBean data = new UploadSaveBean();

                    data.setSku_id(dbcursor.getString(1));
                    data.setArticle(dbcursor.getString(2));
                    data.setSku_qty(dbcursor.getString(3));

                    sdata.add(data);
                    dbcursor.moveToNext();

                }

                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return sdata;

    }

	/*
     * check mid
	 */

    public int CheckMid(String currdate, String storeid) {

        Cursor dbcursor = null;
        int mid = 0;
        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from "
                    + Constant.TABLE_COVERAGE_DATA + "  WHERE VISIT_DATE = '"
                    + currdate + "' AND STORE_ID ='" + storeid + "'", null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();
                dbcursor.moveToFirst();
                for (int i = 0; i < numrows; ++i) {

                    mid = dbcursor.getInt(0);
                    dbcursor.moveToNext();

                }

                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

        return mid;
    }


    //JCP data


    public void InsertLanguageData(languagegettersetter data) {

        ContentValues values = new ContentValues();
        for (int i = 0; i < data.getlanguageid().size(); i++) {
            try {
                values.put("LANGUAGE_ID", data.getlanguageid().get(i));
                values.put("LANGUAGE", data.getlanguage().get(i));

                SqlDataBaseObj.insert(Constant.TABLE_LANGUAGE, null, values);

            } catch (Exception ex) {
                Log.d("Database Exception while Insert Closes Data ",
                        ex.getMessage());

            }
        }
    }

    public void InsertCallPerformanceData(CallPerformanceGetterSetter data) {

        ContentValues values = new ContentValues();
        for (int i = 0; i < data.getMtd().size(); i++) {

            try {
                values.put("PRODUCT", data.getProduct().get(i));
                values.put("PRODUCT_TARGET", data.getProducttarget().get(i));
                values.put("MTD_CALL", data.getMtd().get(i));
                values.put("ACHIEVEMENT", data.getAchievement().get(i));

                SqlDataBaseObj.insert(Constant.TABLE_CALL_PERFORMANCE, null,
                        values);

            } catch (Exception ex) {
                Log.d("Database Exception while Insert Closes Data ",
                        ex.getMessage());
            }
        }
    }

    public void delete_language_data() {

        try {
            SqlDataBaseObj.delete(Constant.TABLE_LANGUAGE, null, null);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }


    public void delete_call_performance_data() {

        try {
            SqlDataBaseObj.delete(Constant.TABLE_CALL_PERFORMANCE, null, null);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    public void delete_incentive_dashboard_data() {

        try {
            SqlDataBaseObj.delete(Constant.TABLE_OHC_INCENTIVE, null, null);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }


    public void delete_brandcount_info(int mid) {

        try {
            SqlDataBaseObj.delete(Constant.TABLE_BRAND_COUNT_INFO
                    + " where MID= '" + mid + "'", null, null);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }


    public void delete_salerecord_info(int mid) {

        try {
            SqlDataBaseObj.delete(Constant.TABLE_SAVE_SALE_RECORD
                    + " where MID= '" + mid + "'", null, null);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    public void delete_downloaded_data() {

        try {
            SqlDataBaseObj.delete(Constant.TABLE_STORE_SALE_INFO, null, null);
            SqlDataBaseObj.delete(Constant.TABLE_BRAND_INFO, null, null);
            SqlDataBaseObj.delete(Constant.TABLE_SKU_INFO, null, null);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    public void delete_uploaded_data() {

        try {

            SqlDataBaseObj.delete(Constant.TABLE_COVERAGE_DATA, null, null);
            SqlDataBaseObj.delete(Constant.TABLE_BRAND_COUNT_INFO, null, null);
            SqlDataBaseObj.delete(Constant.TABLE_SAVE_SALE_RECORD, null, null);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    public ArrayList<languageBean> getLanguageData() {

        Log.d("FetchingCoveragedata--------------->Start<------------",
                "------------------");

        ArrayList<languageBean> languagedata = new ArrayList<languageBean>();

        Cursor dbcursor = null;

        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from "
                    + Constant.TABLE_LANGUAGE, null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();
                dbcursor.moveToFirst();
                for (int i = 0; i < numrows; ++i) {

                    languageBean language = new languageBean();
                    language.setLanguageid(dbcursor.getString(1));
                    language.setLanguage(dbcursor.getString(2));
                    languagedata.add(language);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return languagedata;

    }

    public ArrayList<PerformanceDataModel> getPerformanceDataInfo() {
        Log.d("FetchingCoveragedata--------------->Start<------------",
                "------------------");
        ArrayList<PerformanceDataModel> performancedata = new ArrayList<PerformanceDataModel>();
        Cursor dbcursor = null;
        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from " + Constant.TABLE_PERFORMANCE_DATA, null);
            if (dbcursor != null) {
                int numrows = dbcursor.getCount();
                dbcursor.moveToFirst();
                for (int i = 0; i < numrows; ++i) {
                    PerformanceDataModel coverage = new PerformanceDataModel();
                    coverage.setSku(dbcursor.getString(1));
                    coverage.setSale(dbcursor.getString(2));
                    coverage.setMtd(dbcursor.getString(3));
                    coverage.setTarget(dbcursor.getString(4));
                    coverage.setAchievement(dbcursor.getString(5));
                    performancedata.add(coverage);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return performancedata;

    }

    public ArrayList<SkuSaleInfoGetterSetter> getSkusalesData() {
        Log.d("FetchingCoveragedata--------------->Start<------------", "------------------");
        ArrayList<SkuSaleInfoGetterSetter> performancedata = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from " + Constant.KEY_SKU_SALE, null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuSaleInfoGetterSetter coverage = new SkuSaleInfoGetterSetter();
                    coverage.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU")));
                    coverage.setTarget(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TARGET")));
                    coverage.setMtd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ACH_MTD")));
                    coverage.setSku_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_ID")));
                    coverage.setAch_per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ACH_PER")));
                    performancedata.add(coverage);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return performancedata;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return performancedata;

    }


    public ArrayList<BrandGroupSaleGetterSetter> getBrandGroupData() {
        Log.d("FetchingCoveragedata--------------->Start<------------", "------------------");
        ArrayList<BrandGroupSaleGetterSetter> performancedata = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from " + Constant.KEY_BRAND_GROUP_SALE, null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandGroupSaleGetterSetter coverage = new BrandGroupSaleGetterSetter();
                    coverage.setBrandgroup(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_GROUP")));
                    coverage.setTarget(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TARGET")));
                    coverage.setAchievment(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ACHIVEMENT")));
                    performancedata.add(coverage);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return performancedata;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return performancedata;

    }


    public ArrayList<SkuDataModel> getSkuData() {

        Log.d("FetchingCoveragedata--------------->Start<------------",
                "------------------");

        ArrayList<SkuDataModel> skudata = new ArrayList<SkuDataModel>();

        Cursor dbcursor = null;

        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT * FROM SKU_INFO", null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();
                dbcursor.moveToFirst();
                for (int i = 0; i < numrows; ++i) {

                    SkuDataModel coverage = new SkuDataModel();
                    coverage.setSku_id(dbcursor.getString(0));
                    coverage.setSku(dbcursor.getString(1));
                    coverage.setCompany_id(dbcursor.getString(2));


                    skudata.add(coverage);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return skudata;

    }

    public ArrayList<PerformanceDataModel> getIncentivePerformanceDataInfo() {

        Log.d("FetchingCoveragedata--------------->Start<------------",
                "------------------");

        ArrayList<PerformanceDataModel> performancedata = new ArrayList<PerformanceDataModel>();

        Cursor dbcursor = null;

        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from "
                    + Constant.TABLE_INCENTIVE, null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();
                dbcursor.moveToFirst();
                for (int i = 0; i < numrows; ++i) {

                    PerformanceDataModel coverage = new PerformanceDataModel();
                    coverage.setIncentive(dbcursor.getString(2));

                    performancedata.add(coverage);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return performancedata;

    }

    public ArrayList<CallPerformanceDataModel> getCallPerformanceDataInfo() {

        Log.d("FetchingCoveragedata--------------->Start<------------",
                "------------------");

        ArrayList<CallPerformanceDataModel> callperformancedata = new ArrayList<CallPerformanceDataModel>();

        Cursor dbcursor = null;

        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT  * from "
                    + Constant.TABLE_CALL_PERFORMANCE, null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();
                dbcursor.moveToFirst();
                for (int i = 0; i < numrows; ++i) {

                    CallPerformanceDataModel coverage = new CallPerformanceDataModel();
                    coverage.setProduct(dbcursor.getString(1));
                    coverage.setProduct_target(dbcursor.getString(2));
                    coverage.setMtd(dbcursor.getString(3));
                    coverage.setAchievement(dbcursor.getString(4));
                    callperformancedata.add(coverage);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return callperformancedata;

    }


    public String getBrandId(String type) {

        Cursor dbcursor = null;
        String brand_id = "0";

        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT * from "
                    + Constant.TABLE_BRAND_INFO + " WHERE BRAND = '" + type
                    + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                brand_id = dbcursor.getString(0);
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

        return brand_id;

    }


    public void deleteTable(String table_name) {
        SqlDataBaseObj.execSQL("delete from " + table_name);

    }

    public void deleteBrandGTable(String table_name) {
        SqlDataBaseObj.execSQL("delete from " + table_name);

    }

    public void insertINToSKUSale(SkuSaleInfoGetterSetter skuSaleInfoGetterSetter) {
        for (int i = 0; i < skuSaleInfoGetterSetter.getSku_id().size(); i++) {
            ContentValues initialValues = new ContentValues();
            initialValues.put("SKU_ID", skuSaleInfoGetterSetter.getSku_id().get(i));
            initialValues.put("SKU", skuSaleInfoGetterSetter.getSku().get(i));
            initialValues.put("TARGET", skuSaleInfoGetterSetter.getTarget().get(i));
            initialValues.put("ACH_MTD", skuSaleInfoGetterSetter.getMtd().get(i));
            initialValues.put("ACH_PER", skuSaleInfoGetterSetter.getAch_per().get(i));
            SqlDataBaseObj.insert(Constant.KEY_SKU_SALE, null, initialValues);
        }
    }


    public void insertBrandGroupSale(BrandGroupSaleGetterSetter skuSaleInfoGetterSetter) {

        for (int i = 0; i < skuSaleInfoGetterSetter.getTarget().size(); i++) {
            ContentValues initialValues = new ContentValues();
            initialValues.put("TARGET", skuSaleInfoGetterSetter.getTarget().get(i));
            initialValues.put("BRAND_GROUP", skuSaleInfoGetterSetter.getBrandgroup().get(i));
            initialValues.put("ACHIVEMENT", skuSaleInfoGetterSetter.getAchievment().get(i));
            SqlDataBaseObj.insert(Constant.KEY_BRAND_GROUP_SALE, null, initialValues);
        }
    }


    public void InsertIncentiveDashboardData(IncentiveDashGetterSetter data) {

        ContentValues values = new ContentValues();
        for (int i = 0; i < data.getIncentive().size(); i++) {

            try {
                values.put("MONTHSEQ", data.getMonthSEQ().get(i));
                values.put("MONTH_NAME", data.getMonth_name().get(i));
                values.put("INCENTIVE", data.getIncentive().get(i));

                SqlDataBaseObj.insert(Constant.TABLE_OHC_INCENTIVE, null, values);

            } catch (Exception ex) {
                Log.d("Database Exception while Insert Closes Data ",
                        ex.getMessage());
            }
        }
    }

    public void delete_callMTD_dashboard_data() {

        try {
            SqlDataBaseObj.delete(Constant.TABLE_OHC_CALL_REPORT, null, null);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    public void InsertCALLMTDDashboardData(CallMTDGetterSetter data) {
        ContentValues values = new ContentValues();
        for (int i = 0; i < data.getMtd_call().size(); i++) {
            try {
                values.put("TODAY_CALL", data.getToday_call().get(i));
                values.put("MTD_CALL", data.getMtd_call().get(i));

                SqlDataBaseObj.insert(Constant.TABLE_OHC_CALL_REPORT, null, values);

            } catch (Exception ex) {
                Log.d("Database Exception while Insert Closes Data ",
                        ex.getMessage());
            }
        }
    }

    //get HFD_INCENTIVE Master Data
    public ArrayList<IncentiveDashGetterSetter> getIncentiveMasterData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<IncentiveDashGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT * from OHC_INCENTIVE ORDER BY MONTHSEQ DESC", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    IncentiveDashGetterSetter sb = new IncentiveDashGetterSetter();
                    sb.setMonth_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow("MONTH_NAME")));
                    sb.setIncentive((dbcursor.getString(dbcursor.getColumnIndexOrThrow("INCENTIVE"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public CallMTDGetterSetter getcallMTDData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        CallMTDGetterSetter list = new CallMTDGetterSetter();
        Cursor dbcursor = null;
        try {
            dbcursor = SqlDataBaseObj.rawQuery("SELECT * from OHC_CALL_REPORT", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    list.setToday_call(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TODAY_CALL")));
                    list.setMtd_call((dbcursor.getString(dbcursor.getColumnIndexOrThrow("MTD_CALL"))));
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


}
