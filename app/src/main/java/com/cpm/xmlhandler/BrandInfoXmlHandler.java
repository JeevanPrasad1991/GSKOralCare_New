package com.cpm.xmlhandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cpm.gettersetter.BrandInfoGetterSetter;
import com.cpm.gettersetter.StoreSaleInfoGetterSetter;

public class BrandInfoXmlHandler extends DefaultHandler{
	
	String elementValue = null;
	Boolean elementOn = false;
	public static BrandInfoGetterSetter data = null;

	public static BrandInfoGetterSetter getXMLData() {
		return data;
	}

	public static void setXMLData(BrandInfoGetterSetter data) {
		BrandInfoXmlHandler.data = data;
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
			data = new BrandInfoGetterSetter();
		} else if (localName.equals("BRAND_MASTER")) {
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
		if (localName.equalsIgnoreCase("BRAND_ID"))
			data.setBRAND_ID(elementValue);
		else if (localName.equalsIgnoreCase("BRAND"))
			data.setBRAND(elementValue);
		else if (localName.equalsIgnoreCase("CATEGORY_ID"))
			data.setCATEGORY_ID(elementValue);
		else if (localName.equalsIgnoreCase("COMPANY_ID"))
			data.setCOMPANY_ID(elementValue);
	
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
