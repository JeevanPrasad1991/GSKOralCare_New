package com.cpm.xmlhandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by jeevanp on 2/23/2018.
 */

public class CallMTDGetterSetterXMLHandler extends DefaultHandler {
    String elementValue = null;
    Boolean elementOn = false;
    public static CallMTDGetterSetter data = null;

    public static CallMTDGetterSetter getXMLData() {
        return data;
    }

    public static void setXMLData(CallMTDGetterSetter data) {
        CallMTDGetterSetterXMLHandler.data = data;
    }

    /**
     * This will be called when the tags of the XML starts.
     **/
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        elementOn = true;

        if (localName.equals("begin"))
        {
            data = new CallMTDGetterSetter();
        } else if (localName.equals("OHC_CALL_REPORT")) {
            /**
             * We can get the values of attributes for eg. if the CD tag had an attribute( <CD attr= "band">Akon</CD> )
             * we can get the value "band". Below is an example of how to achieve this.
             *
             * String attributeValue = attributes.getValue("attr");
             * data.setAttribute(attributeValue);
             *
             * */
        }
    }

    /**
     * This will be called when the tags of the XML end.
     **/
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        elementOn = false;

        /**
         * Sets the values after retrieving the values from the XML tags
         * */
        if (localName.equalsIgnoreCase("TODAY_CALL"))
            data.setToday_call(elementValue);
        else if (localName.equalsIgnoreCase("MTD_CALL"))
            data.setMtd_call(elementValue);
    }



    /**
     * This is called to get the tags value
     **/
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        if (elementOn) {
            elementValue = new String(ch, start, length);
            elementOn = false;
        }

    }
}
