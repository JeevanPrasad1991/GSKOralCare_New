package com.cpm.xmlhandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cpm.gettersetter.CallPerformanceGetterSetter;
import com.cpm.gettersetter.IncentivePerformanceGetterSetter;

public class IncentivePerformancerInfoXmlHandler extends DefaultHandler{
	
	String elementValue = null;
	Boolean elementOn = false;
	public static IncentivePerformanceGetterSetter data = null;

	public static IncentivePerformanceGetterSetter getXMLData() {
		return data;
	}

	public static void setXMLData(IncentivePerformanceGetterSetter data) {
		IncentivePerformancerInfoXmlHandler.data = data;
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
			data = new IncentivePerformanceGetterSetter();
		} else if (localName.equals("EMPINCENTIVE")) {
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
		if (localName.equalsIgnoreCase("MONTH_WEEK"))
			data.setMonth_week(elementValue);
		else if (localName.equalsIgnoreCase("INCENTIVE"))
			data.setIncentive(elementValue);
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
