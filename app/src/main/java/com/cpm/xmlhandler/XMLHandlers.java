package com.cpm.xmlhandler;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.cpm.gettersetter.BrandGroupSaleGetterSetter;
import com.cpm.gettersetter.FailureGetterSetter;
import com.cpm.gettersetter.JCPGetterSetter;
import com.cpm.gettersetter.LoginGetterSetter;
import com.cpm.gettersetter.SkuSaleInfoGetterSetter;


public class XMLHandlers {


    // LOGIN XML HANDLER
    public static com.cpm.gettersetter.LoginGetterSetter loginXMLHandler(XmlPullParser xpp,
                                                                         int eventType) {
        LoginGetterSetter lgs = new LoginGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("RIGHT_NAME")) {
                        lgs.setRIGHT_NAME(xpp.nextText());
                    }
                    if (xpp.getName().equals("APP_VERSION")) {
                        lgs.setAPP_VERSION(xpp.nextText());
                    }
                    if (xpp.getName().equals("APP_PATH")) {
                        lgs.setAPP_PATH(xpp.nextText());
                    }
                    if (xpp.getName().equals("CURRENTDATE")) {
                        lgs.setCURRENTDATE(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return lgs;
    }


    public static SkuSaleInfoGetterSetter skuSaleHandler(XmlPullParser xmlPullParser, int eventType) {
        SkuSaleInfoGetterSetter skuSaleInfoGetterSetter = new SkuSaleInfoGetterSetter();

        try {
            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xmlPullParser.getEventType() == XmlPullParser.START_TAG) {
                    if (xmlPullParser.getName().equals("META_DATA")) {
                        skuSaleInfoGetterSetter.setSku_sale_table_name(xmlPullParser.nextText());
                    }
                    if (xmlPullParser.getName().equals("SKU_ID")) {
                        skuSaleInfoGetterSetter.setSku_id(xmlPullParser.nextText());
                    }
                    if (xmlPullParser.getName().equals("SKU")) {
                        skuSaleInfoGetterSetter.setSku(xmlPullParser.nextText());
                    }
                    if (xmlPullParser.getName().equals("TARGET")) {
                        skuSaleInfoGetterSetter.setTarget(xmlPullParser.nextText());
                    }
                    if (xmlPullParser.getName().equals("ACH_MTD")) {
                        skuSaleInfoGetterSetter.setMtd(xmlPullParser.nextText());
                    }
                    if (xmlPullParser.getName().equals("ACH_PER")) {
                        skuSaleInfoGetterSetter.setAch_per(xmlPullParser.nextText());
                    }

                }
                xmlPullParser.next();
            }
        } catch (XmlPullParserException xm) {
            xm.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skuSaleInfoGetterSetter;
    }


    public static BrandGroupSaleGetterSetter BrandGroupSaleHandler(XmlPullParser xmlPullParser, int eventType) {
        BrandGroupSaleGetterSetter brandGroupSaleGetterSetter = new BrandGroupSaleGetterSetter();

        try {
            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xmlPullParser.getEventType() == XmlPullParser.START_TAG) {
                    if (xmlPullParser.getName().equals("META_DATA")) {
                        brandGroupSaleGetterSetter.setMetadataTable(xmlPullParser.nextText());
                    }
                    if (xmlPullParser.getName().equals("BRAND_GROUP")) {
                        brandGroupSaleGetterSetter.setBrandgroup(xmlPullParser.nextText());
                    }

                    if (xmlPullParser.getName().equals("TARGET")) {
                        brandGroupSaleGetterSetter.setTarget(xmlPullParser.nextText());
                    }
                    if (xmlPullParser.getName().equals("ACHIVEMENT")) {
                        brandGroupSaleGetterSetter.setAchievment(xmlPullParser.nextText());
                    }
                }
                xmlPullParser.next();
            }
        } catch (XmlPullParserException xm) {
            xm.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return brandGroupSaleGetterSetter;
    }


    // FAILURE XML HANDLER
    public static FailureGetterSetter failureXMLHandler(XmlPullParser xpp,
                                                        int eventType) {
        FailureGetterSetter failureGetterSetter = new FailureGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("STATUS")) {
                        failureGetterSetter.setStatus(xpp.nextText());
                    }
                    if (xpp.getName().equals("ERRORMSG")) {
                        failureGetterSetter.setErrorMsg(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return failureGetterSetter;
    }

    // JCP XML HANDLER
    public static JCPGetterSetter JCPXMLHandler(XmlPullParser xpp, int eventType) {
        JCPGetterSetter jcpGetterSetter = new JCPGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        jcpGetterSetter.setMeta_data(xpp.nextText());
                    }

                    if (xpp.getName().equals("STORE_ID")) {
                        jcpGetterSetter.setSTORE_ID(xpp.nextText());
                    }
                    if (xpp.getName().equals("EMP_ID")) {
                        jcpGetterSetter.setEMP_ID(xpp.nextText());
                    }

                    if (xpp.getName().equals("STORE")) {
                        jcpGetterSetter.setSTORE(xpp.nextText());
                    }
                    if (xpp.getName().equals("CITY")) {
                        jcpGetterSetter.setCITY(xpp.nextText());
                    }
                    if (xpp.getName().equals("VISIT_DATE")) {
                        jcpGetterSetter.setVISIT_DATE(xpp.nextText());
                    }
                    if (xpp.getName().equals("PROCESS_ID")) {
                        jcpGetterSetter.setPROCESS_ID(xpp.nextText());
                    }
                    if (xpp.getName().equals("UPLOAD_STATUS")) {
                        jcpGetterSetter.setUPLOAD_STATUS(xpp.nextText());
                    }

                    if (xpp.getName().equals("REGION_ID")) {
                        jcpGetterSetter.setREGION_ID(xpp.nextText());
                    }

                    if (xpp.getName().equals("KEY_ID")) {
                        jcpGetterSetter.setKEY_ID(xpp.nextText());
                    }

                    if (xpp.getName().equals("STORETYPE_ID")) {
                        jcpGetterSetter.setSTORETYPE_ID(xpp.nextText());
                    }

                    if (xpp.getName().equals("CHECKOUT_STATUS")) {
                        jcpGetterSetter.setCHECKOUT_STATUS(xpp.nextText());
                    }

                    if (xpp.getName().equals("PKD_ENABLE")) {
                        jcpGetterSetter.setPACKED_KEY(xpp.nextText());
                    }

                    if (xpp.getName().equals("STATE_ID")) {
                        jcpGetterSetter.setState_id(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jcpGetterSetter;
    }


}
