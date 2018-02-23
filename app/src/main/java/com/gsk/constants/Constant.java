package com.gsk.constants;

import com.cpm.gettersetter.SkuSaleInfoGetterSetter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Constant {

	public static final String DATABASE_NAME = "GskDatabase";

	public static final String KEY_SUCCESS = "Success";
	public static final String KEY_FAILURE = "Failure";
	public static final String KEY_FALSE = "False";
	public static final String KEY_CHANGED = "Changed";
	public static final String KEY_NO_DATA = "NODATA";

	//public static final String APP_VERSION = "3";
	public static final String NAMESPACE = "http://tempuri.org/";
//	public static final String Local_URL = "http://10.200.20.133/GSK_Merk_Service/GSK.asmx";


	//last url on which app use to work
//	public static final String LOGIN_URL = "http://www.parinaam.in/gskmerc/gsk.asmx";

	public static final String NEW_URL = "http://gskmtm.parinaam.in/GskmtService.asmx";

	public static final String LOGIN_URL = "http://Gskmt.parinaam.in/gsk.asmx";

	//public static final String URL = "http://www.parinaam.in/gskmerc/gsk.asmx";
	//public static final String URL = "http://parinaam.in/gskoral/OralWebService.asmx";

	public static final String METHOD_UPLOAD_STOCK_XML_DATA = "DrUploadXml";
	public static final String METHOD_NAME_UNIVERSAL_DOWNLOAD = "Download_Universal";
	public static final String SOAP_ACTION_UNIVERSAL = "http://tempuri.org/"
			+ METHOD_NAME_UNIVERSAL_DOWNLOAD;

	public static final String SOAP_ACTION_ATTENDENCE = "http://tempuri.org/UserLoginDetail_Sales";
	//public static final String METHOD_NAME_ATTENDENCE = "GSKORALAttendance";
	public static final String METHOD_NAME_ATTENDENCE = "UserLoginDetail_Sales";
	public static final String SOAP_ACTION_PERFORMANCE_DETAIL = "http://tempuri.org/SKUPerformance";
	public static final String METHOD_NAME_PERFORMANCE_DETAIL = "SKUPerformance";

	public static final String SOAP_ACTION_UPLOAD = "http://tempuri.org/Upload_Store_SKU";
	public static final String METHOD_NAME_UPLOAD = "Upload_Store_SKU";

	public static final String SOAP_ACTION_CALL_PERFORMANCE = "http://tempuri.org/ProductPerformance";
	public static final String METHOD_NAME_CALL_PERFORMANCE = "ProductPerformance";

	public static final String SOAP_ACTION_ACHEIVEMENT = "http://tempuri.org/GSKOralCALLTarget";
	public static final String METHOD_NAME_ACHEIVEMENT = "GSKOralCALLTarget";

	public static final String SOAP_ACTION_INCENTIVE_PERFORMANCE = "http://tempuri.org/EMPPerformanceIncentive";
	public static final String METHOD_NAME_INCENTIVE_PERFORMANCE = "EMPPerformanceIncentive";

	public static final String SOAP_ACTION_UPLOAD_COUNT = "http://tempuri.org/Upload_Store_SKU_CALLS";
	public static final String METHOD_NAME_UPLOAD_COUNT = "Upload_Store_SKU_CALLS";

	public static final String SOAP_ACTION_BRAND_INFO = "http://tempuri.org/GSKORAL_BrandInfo";
	public static final String METHOD_NAME_BRAND_INFO = "GSKORAL_BrandInfo";

	public static final String SOAP_ACTION_SKU_INFO = "http://tempuri.org/GSKORAL_SkuInfo";
	public static final String METHOD_NAME_SKU_INFO = "GSKORAL_SkuInfo";

	public static final String SOAP_ACTION_STORE_SALE = "http://tempuri.org/GSKORAL_StoreSales";
	public static final String METHOD_NAME_STORE_SALE = "GSKORAL_StoreSales";

	public static final String SOAP_ACTION_DR_COVERAGE = "http://tempuri.org/UploadStoreCoverageWidReasonAndProcessId";
	public static final String METHOD_NAME_DR_COVERAGE = "UploadStoreCoverageWidReasonAndProcessId";

	public static final String METHOD_UPLOAD_DR_STORE_COVERAGE_LOC = "UPLOAD_STORE_COVERAGE_WSC";

	public static final String SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE = "http://tempuri.org/"
			+ METHOD_UPLOAD_DR_STORE_COVERAGE_LOC;

	public static final String SOAP_ACTION_DR_STORE_SALE = "http://tempuri.org/UPDATE_DR_STORE_SALE_GSK_ORAL";
	public static final String METHOD_NAME_DR_STORE_SALE = "UPDATE_DR_STORE_SALE_GSK_ORAL";

	public static final String SOAP_ACTION_DR_STORE_CALL = "http://tempuri.org/UPDATE_DR_STORE_CALLS";
	public static final String METHOD_NAME_DR_STORE_CALL = "UPDATE_DR_STORE_CALLS";

	public static final String SOAP_ACTION_BRAND_TARGET = "http://tempuri.org/GSKOralBrandTarget";
	public static final String METHOD_NAME_BRAND_TARGET = "GSKOralBrandTarget";


	public static final String SOAP_ACTION_UPLOAD_ASSET_XMLDATA = "http://tempuri.org/"
			+ METHOD_UPLOAD_STOCK_XML_DATA;

	//language master
	public static final String SOAP_ACTION_LANGUAGE = "http://tempuri.org/LanguageMasterDownload";
	public static final String METHOD_NAME_LANGUAGE = "LanguageMasterDownload";

	public static final String SOAP_ACTION_SKU_TARGET_PERFORMANCE = "http://tempuri.org/GSKOralskuTargetPerformance";
	public static final String METHOD_NAME_SKU_TARGET_PERFORMANCE = "GSKOralskuTargetPerformance";

	public static final String CREATE_TABLE_SALES_RECORD = "CREATE TABLE SALES_RECORD (ID INTEGER PRIMARY KEY AUTOINCREMENT,SENSODYNE VARCHAR,COLGATE_SENSITIVE VARCHAR,OTHER_SENSITIVE VARCHAR,RAPID_RELIEF VARCHAR,PEPSODENT_SENSITIVE VARCHAR,NORMAL_TOOTHPASTE VARCHAR,PARODONTAX VARCHAR)";

	public static final String TABLE_SALES_RECORD = "SALES_RECORD";

	public static final String CREATE_TABLE_STORE_DETAILS = "CREATE TABLE STORE_DETAILS (ID INTEGER PRIMARY KEY AUTOINCREMENT,KEYACCOUNT VARCHAR,STORE_CODE VARCHAR,STORENAME VARCHAR,STORETYPE VARCHAR,CITY VARCHAR,ADDRESS VARCHAR,STORE_CD VARCHAR,LANGUAGE VARCHAR)";

	public static final String TABLE_STORE_DETAILS = "STORE_DETAILS";

	public static final String CREATE_TABLE_SALES_DATA = "CREATE TABLE SALES_DATA (ID INTEGER PRIMARY KEY AUTOINCREMENT,s40 VARCHAR,s40_id VARCHAR,s80 VARCHAR,s80_id VARCHAR,s150 VARCHAR,s150_id VARCHAR,m40 VARCHAR,m40_id VARCHAR,m80 VARCHAR,m80_id VARCHAR,m150 VARCHAR,m150_id VARCHAR,p40 VARCHAR,p40_id VARCHAR,p80 VARCHAR,p80_id VARCHAR,r40 VARCHAR,r40_id VARCHAR,r80 VARCHAR,r80_id VARCHAR,brush VARCHAR,brush_id VARCHAR,STORE_ID VARCHAR,DATE VARCHAR,LATITUDE VARCHAR,LONGITUDE VARCHAR)";

	public static final String TABLE_SALES_DATA = "SALES_DATA";

	public static final String CREATE_TABLE_PERFORMANCE_DATA = "CREATE TABLE PERFORMANCE_DATA (ID INTEGER PRIMARY KEY AUTOINCREMENT,SKU VARCHAR,SALE VARCHAR,MTD VARCHAR,TARGET VARCHAR,ACHIEVEMENT VARCHAR)";

	public static final String TABLE_PERFORMANCE_DATA = "PERFORMANCE_DATA";

	public static final String CREATE_TABLE_CALL_PERFORMANCE = "CREATE TABLE CALL_PERFORMANCE (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT VARCHAR,PRODUCT_TARGET VARCHAR,MTD_CALL VARCHAR,ACHIEVEMENT VARCHAR)";

	public static final String TABLE_CALL_PERFORMANCE = "CALL_PERFORMANCE";

	public static final String TABLE_LANGUAGE = "LANGUAGE";

	public static final String CREATE_TABLE_LANGUAGE = "CREATE TABLE LANGUAGE (ID INTEGER PRIMARY KEY AUTOINCREMENT,LANGUAGE_ID VARCHAR,LANGUAGE VARCHAR)";

	public static final String CREATE_TABLE_INCENTIVE = "CREATE TABLE INCENTIVE (ID INTEGER PRIMARY KEY AUTOINCREMENT,MONTH_WEEK VARCHAR,INCENTIVE VARCHAR)";

	public static final String TABLE_INCENTIVE = "INCENTIVE";

	public static final String CREATE_TABLE_BRAND_INFO = "CREATE TABLE BRAND_INFO (BRAND_ID VARCHAR,BRAND VARCHAR,CATEGORY_ID VARCHAR,COMPANY_ID VARCHAR)";

	public static final String TABLE_BRAND_INFO = "BRAND_INFO";

	public static final String CREATE_TABLE_SKU_INFO = "CREATE TABLE SKU_INFO (SKU_ID VARCHAR,SKU VARCHAR,COMPANY_ID VARCHAR,BRAND_ID VARCHAR)";

	public static final String TABLE_SKU_INFO = "SKU_INFO";

	public static final String CREATE_TABLE_STORE_SALE_INFO = "CREATE TABLE STORE_SALE_INFO (BRAND_ID VARCHAR,SKU_ID VARCHAR,ARTICLE_NO VARCHAR,BRAND_SEQUENCE VARCHAR,SKU_SEQUENCE VARCHAR,MRP VARCHAR)";

	public static final String TABLE_STORE_SALE_INFO = "STORE_SALE_INFO";

	public static final String CREATE_TABLE_SAVE_SALE_RECORD = "CREATE TABLE SAVE_SALE_RECORD (MID VARCHAR,SKU_ID VARCHAR,STORE_ID VARCHAR,SKUQTY VARCHAR)";

	public static final String TABLE_SAVE_SALE_RECORD = "SAVE_SALE_RECORD";

	public static final String TABLE_COVERAGE_DATA = "COVERAGE_DATA";

	public static final String CREATE_TABLE_COVERAGE_DATA = "CREATE TABLE COVERAGE_DATA (ID INTEGER PRIMARY KEY AUTOINCREMENT ,STORE_ID VARCHAR,USER_ID VARCHAR, IN_TIME VARCHAR,OUT_TIME VARCHAR,VISIT_DATE VARCHAR,LATITUDE VARCHAR,LONGITUDE VARCHAR,REASON_ID VARCHAR,REASON VARCHAR,STATUS VARCHAR,VERSION VARCHAR)";

	public static final String CREATE_TABLE_BRAND_COUNT_INFO = "CREATE TABLE BRAND_COUNT_RECORD_INFO (MID VARCHAR, BRAND_ID VARCHAR, BRAND VARCHAR, COUNT VARCHAR,STORE_ID VARCHAR)";
	public static final String TABLE_BRAND_COUNT_INFO = "BRAND_COUNT_RECORD_INFO";
	public static String KEY_SKU_SALE="GSKORAL_SKU_SALE";
	public static String KEY_BRAND_GROUP_SALE="GSKORAL_BRAND_GROUP_SALE";

	public static final String TABLE_OHC_INCENTIVE= "OHC_INCENTIVE";
	public static final String CREATE_TABLE_OHC_INCENTIVE = "CREATE TABLE OHC_INCENTIVE (MONTHSEQ INTEGER,MONTH_NAME VARCHAR,INCENTIVE INTEGER)";

	public static final String TABLE_OHC_CALL_REPORT = "OHC_CALL_REPORT";
	public static final String CREATE_TABLE_OHC_CALL_REPORT = "CREATE TABLE OHC_CALL_REPORT (TODAY_CALL INTEGER,MTD_CALL INTEGER)";

}
