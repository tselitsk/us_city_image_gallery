package com.example.uscityimageviewer.city;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.os.DropBoxManager.Entry;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.uscityimageviewer.ImgData;

/* Models city information retrieved from xml file
 * 
 * 
 */

public class CityData implements Parcelable{
	
	private	String name;
	private	int rank;
	private	int population;
	
	//using int instead of boolean because works better with parceable objects
	private int RETRIEVED_RESULTS=0;
	
	HashMap<String,String> imgDataMap;
	
	public CityData(){
		imgDataMap=new HashMap<String,String>();
	}
	
	public void setRetrieved()
	{
		this.RETRIEVED_RESULTS=1;
	}
	
	public int is_Retrieved()
	{
		return RETRIEVED_RESULTS;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public HashMap<String, String> getImgDataMap() {
		return imgDataMap;
	}
	
	public boolean hasImgResults()
	{
		if(imgDataMap.size()==0)
			return false;
		else
			return true;
	
	}
	
	public void putImgDataInMap(String id, String url)
	{
		imgDataMap.put(id, url);
	}
	
	public Set<String> getImgIds()
	{
		Set<String> keySet=imgDataMap.keySet();
		return keySet;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(rank);
		dest.writeInt(population);
		dest.writeMap(imgDataMap);
		dest.writeInt(RETRIEVED_RESULTS);
		
	}	
	
	public static final Parcelable.Creator<CityData> CREATOR =
			new Parcelable.Creator<CityData>() {

				@Override
				public CityData createFromParcel(Parcel source) {
					return new CityData();
				}

				@Override
				public CityData[] newArray(int size) {
					return new CityData[size];
				}
		
	};
	
	private void readFromParcel(Parcel in) {

		name= in.readParcelable(CityData.class.getClassLoader());
		rank=in.readInt();
		population=in.readInt();
		imgDataMap=in.readHashMap(HashMap.class.getClassLoader());
		RETRIEVED_RESULTS=in.readInt();
 
	}

}
