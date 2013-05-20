package com.example.uscityimageviewer.networkresultprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
InputStream is;	
String RESPONSE="responseData";
String RESULTS="results";

public JSONParser(InputStream is)
{
	this.is=is;
}
	
	 public String buildJSONString(){
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder inputStringBuilder = new StringBuilder();
			String line;
			
			try {
				line = bufferedReader.readLine();
				
				while(line != null){
		            inputStringBuilder.append(line);inputStringBuilder.append('\n');
		            line = bufferedReader.readLine();
				}
		 
			} catch (IOException e) {
				Log.d("Exception",e.toString());
			}
			
	       String JSONString=inputStringBuilder.toString();
	       //Log.d("Test Thread", JSONString);
	       return JSONString;
	    	
	  }
	    
	    public JSONArray buildJSONArray(String JSONString){
	    	
	    	try {
	    		JSONObject jsonObject=new JSONObject(JSONString);
	    		JSONObject jsonResponseData=jsonObject.getJSONObject(RESPONSE);
	    		JSONArray jsonArray=jsonResponseData.getJSONArray(RESULTS);
	    		
	    		return jsonArray;
	    		
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("Exception",e.toString());
			}
	    	
	    	return null;
	    }
	    
	    public JSONArray getJSONArrayResult()
	    {
	    	String jsonString=buildJSONString();
	    	JSONArray jsonArray=buildJSONArray(jsonString);
	    	return jsonArray;
	    }
}
