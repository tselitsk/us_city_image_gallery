package com.example.uscityimageviewer;

import java.util.HashMap;

import com.example.uscityimageviewer.R;
import com.example.uscityimageviewer.R.id;
import com.example.uscityimageviewer.R.layout;
import com.example.uscityimageviewer.R.menu;
import com.example.uscityimageviewer.city.CityData;
import com.example.uscityimageviewer.city.FileParser;
import com.example.uscityimageviewer.imgcache.ImgCache;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class GalleryActivity extends FragmentActivity {

	private ImagePagerAdapter mAdapter;
    private ViewPager mPager;
    HashMap<Integer,CityData> cityDataMap;
    int FIRST_CITY=1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_img_fragment);
		 
		//parses an xml file of city data, and loads it into a hashmap of city objects
		FileParser fileParser=new FileParser(this);
		fileParser.parseXML();
		cityDataMap=fileParser.getCities();
		
		if (cityDataMap==null ||cityDataMap.size() == 0) {
	        throw new IllegalArgumentException("city cata must not equal 0");
	    }
		
		//loads the first city from the dataset
		CityData firstCityData=cityDataMap.get(FIRST_CITY);
		
		//the chosen city object is used to get search results and turn them into image objects, which are used to fill the viewpager
		BackgroundImgProcess backgroundImgProcess=new BackgroundImgProcess(firstCityData);
		backgroundImgProcess.start();
		try {
			backgroundImgProcess.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mAdapter = new ImagePagerAdapter(getSupportFragmentManager(),cityDataMap);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
		
	}
	
	public  class ImagePagerAdapter extends FragmentStatePagerAdapter
	{
		int numCitys=10;
		
		int numOfPages=numCitys;
		HashMap<Integer,CityData> cityDataMap;
		
		public ImagePagerAdapter(FragmentManager fm, HashMap<Integer,CityData> cityDataMap) {
			super(fm);
			
			this.cityDataMap=cityDataMap;
		}
	
		@Override
		public Fragment getItem(int position) {
			//the position starts at 0 but the city rank starts at 1
			int rank=position+1;
			
			
			CityData cityData=cityDataMap.get(rank);
			
			return ImageFragment.newInstance(position,cityData);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return numOfPages;
		}
	}
		
	
	}


