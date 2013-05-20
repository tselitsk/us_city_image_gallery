package com.example.uscityimageviewer.city;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.util.Log;


public class FileParser {
//parses the city data from an xml file and returns it into a hashmap of city objects, where the key is the rank of the city (i.e. New York is 1 
//because it is the biggest city. 
	
	HashMap<Integer,CityData> citiesMap;
	Context ctx;

	public HashMap<Integer,CityData> getCities()
	{
		return citiesMap;
	}
		
		
		public FileParser(Context ctx)
		{
			citiesMap=new HashMap<Integer,CityData>();
			this.ctx=ctx;
		}
		
		public void parseXML() {  
			try {
	            SAXParserFactory factory = SAXParserFactory.newInstance();
	            SAXParser parser = factory.newSAXParser();
	            XMLReader reader = parser.getXMLReader();
	            CityXMLHandler myHandler=new CityXMLHandler();
	            reader.setContentHandler(myHandler);
	            InputSource is=new InputSource(ctx.getAssets().open("cities.xml"));
	            if(is!=null){
	            	reader.parse(is);
	            	citiesMap = myHandler.getCities();
	            }
	            
	            
	            		
	        } catch (ParserConfigurationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (SAXException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			   
			}
	
	
}
