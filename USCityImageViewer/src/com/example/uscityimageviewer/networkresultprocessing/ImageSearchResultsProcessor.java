package com.example.uscityimageviewer.networkresultprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.uscityimageviewer.ImgData;

import android.util.Log;

public class ImageSearchResultsProcessor {
	private InputStream is;	
	private String URL="tbUrl";
private String IMAGE_ID="imageId";

//retrieves JSON objects from an input stream and puts the results in image objects.  The img objects are passed to the city object
	public ImageSearchResultsProcessor(InputStream is)
	{
		this.is=is;
	}
	
	public List<ImgData> processImageSearchResults()
	{
		JSONParser parser=new JSONParser(is);
		JSONArray jsonArray=parser.getJSONArrayResult();
		List<ImgData> imgDataList=getImageSearchResultSet(jsonArray);
		return imgDataList;
	}
	 
//TODO move values to top
	    public List<ImgData> getImageSearchResultSet(JSONArray jsonArray)
	    {
	    	//add values to consts library
	    	List<ImgData> imgSearchResults=new ArrayList<ImgData>();
	    	for(int i=0;i<jsonArray.length();i++)
	    	{
	    		try {
	    			ImgData imgData=new ImgData();
					JSONObject jsonObject=jsonArray.getJSONObject(i);
					
					String imgUrl=jsonObject.getString(URL);
					if(imgUrl!=null)
					imgData.setUrl(imgUrl);
					
					
					String imgId=jsonObject.getString(IMAGE_ID);
					if(imgId!=null)
					imgData.setImageId(imgId);
					
					imgSearchResults.add(imgData);
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	}
			return imgSearchResults;
	    		
	    }

}
