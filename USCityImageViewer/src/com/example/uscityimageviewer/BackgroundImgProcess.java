package com.example.uscityimageviewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

import com.example.uscityimageviewer.city.CityData;
import com.example.uscityimageviewer.networkresultprocessing.ImageSearchResultsProcessor;
/* This class takes a city data and returns image search results based on the name, which are
 * then returned in an image data object
 */
public class BackgroundImgProcess extends Thread{
private CityData cityData;
private String IMAGE_SEARCH_URL="http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
private String RESULTS_PARA="&rsz=8";
	
	BackgroundImgProcess(CityData cityData)
	{
			this.cityData=cityData;
			this.cityData.setRetrieved();
	}
	
	public CityData getCityData()
	{
		return this.cityData;
	}
	
	@Override
	public void run() {
		String searchTerm=cityData.getName();
		String url=buildUrl(searchTerm);

		InputStream is=null;
		try{
			is = ResultFetcher.fetch(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(is!=null){
			
			//takes an input stream and returns a list of data objects
			ImageSearchResultsProcessor imageSearchResultsProcessor=new ImageSearchResultsProcessor(is);
			List<ImgData>imgDataList=imageSearchResultsProcessor.processImageSearchResults();
			if(imgDataList!=null && imgDataList.size()!=0)
			{
			
				for(int i=0;i<imgDataList.size();i++)
				{
					ImgData imgData=imgDataList.get(i);
					String id=imgData.getImageId();
					String imgUrl=imgData.getUrl();
					if(id!=null && imgUrl!=null)
					{
						this.cityData.putImgDataInMap(id, imgUrl);
					}
				}
			}
			
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			super.run();
		}
	}
	
	//builds a url using the image search api url, the search term, and a parameter that asks the api to return 8 items
	public String buildUrl(String searchTerm)
	{
		String processedSearchTerm=searchTerm.replace(" ","+");
		return IMAGE_SEARCH_URL+processedSearchTerm+RESULTS_PARA;
	}

}
