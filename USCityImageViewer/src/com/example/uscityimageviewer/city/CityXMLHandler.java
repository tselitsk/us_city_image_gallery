package com.example.uscityimageviewer.city;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;



public class CityXMLHandler extends DefaultHandler {
	
	HashMap<Integer,CityData> citiesMap=new HashMap<Integer,CityData>();

	Boolean currentElement = false;
	String currentValue = "";
	 CityData cityData;

	public HashMap<Integer,CityData> getCities()
	{
		return citiesMap;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		 currentElement = true;
	        currentValue = "";
	        if (localName.equals("city")) {
	             cityData=new CityData();
	        } 
	}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			
			 if (localName.equalsIgnoreCase("name")){
			 	cityData.setName(currentValue);
			 	Log.d("endElement", "endElement");
			 }
		        else if (localName.equalsIgnoreCase("rank"))
		        cityData.setRank(Integer.parseInt(currentValue));
		        else if (localName.equalsIgnoreCase("population"))
		          cityData.setPopulation(Integer.parseInt(currentValue));
		        else if (localName.equalsIgnoreCase("city"))
		            citiesMap.put(cityData.getRank(), cityData);
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
				if (currentElement) {
				currentValue = new String(ch, start, length);
				currentElement = false;
				}
		}

}
