package com.cpm.xmlhandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.cpm.gettersetter.UserInfoGetterSetter;

public class UserInfoXmlHandler extends DefaultHandler{
	
	String elementValue = null;
	Boolean elementOn = false;
	public static UserInfoGetterSetter data = null;

	public static UserInfoGetterSetter getXMLData() {
		return data;
	}

	public static void setXMLData(UserInfoGetterSetter data) {
		UserInfoXmlHandler.data = data;
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
			data = new UserInfoGetterSetter();
		} else if (localName.equals("ATTENDANCE_GSK_ORAL")) {
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
		if (localName.equalsIgnoreCase("STATUS"))
			data.setStatus(elementValue);
		else if (localName.equalsIgnoreCase("CURRENTDATE"))
			data.setCurrent_date(elementValue);
		else if (localName.equalsIgnoreCase("APP_VERSION"))
			data.setApp_version(elementValue);
		else if (localName.equalsIgnoreCase("APP_PATH"))
			data.setApp_path(elementValue);
		else if (localName.equalsIgnoreCase("KEYACCOUNT"))
			data.setKey_account(elementValue);
		else if (localName.equalsIgnoreCase("STORE_CODE"))
			data.setStore_card(elementValue);
		else if (localName.equalsIgnoreCase("STORE_NAME"))
			data.setStore_name(elementValue);
	
		else if (localName.equalsIgnoreCase("STORETYPE"))
			data.setStore_type(elementValue);
		else if (localName.equalsIgnoreCase("CITY"))
			data.setCity(elementValue);
		else if (localName.equalsIgnoreCase("ADDRESS"))
			data.setAddress(elementValue);
		else if (localName.equalsIgnoreCase("STORE_ID"))
			data.setStore_cd(elementValue);
		else if (localName.equalsIgnoreCase("LANGUAGE"))
			data.setlanguage(elementValue);
		  else if (localName.equalsIgnoreCase("PROCESS_ID"))
		        data.setProcessid(elementValue);
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
