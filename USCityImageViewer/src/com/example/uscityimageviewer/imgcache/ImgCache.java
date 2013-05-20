package com.example.uscityimageviewer.imgcache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;


/*Img Cache is a memory cache that stores the bitmaps that have been processed, so they can be easily retrieved again
 * Uses an eigth of the available VM memory. 
 */
public class ImgCache {
	
private static ImgCache instance;
private LruCache<String, Bitmap> mMemoryCache;
	
	private ImgCache()
	{
		 // Get max available VM memory, exceeding this amount will throw an
	    // OutOfMemory exception. Stored in kilobytes as LruCache takes an
	    // int in its constructor.
	    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

	    // Use 1/8th of the available memory for this memory cache.
	    final int cacheSize = maxMemory / 8;
	    
	    mMemoryCache = new LruCache<String, Bitmap>(cacheSize);
	    
	}
	

	
	public static ImgCache getInstance() {
        if (instance == null) {
                        if (instance == null) {
                                instance = new ImgCache ();
                        }
              }
        return  instance;
	}
	
	
	
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
	    if (getBitmapFromMemCache(key) == null && bitmap!=null) {
	    	mMemoryCache.put(key, bitmap);
	    }
	}
	
	

	public synchronized Bitmap getBitmapFromMemCache(String key) {
	    return mMemoryCache.get(key);
	}
	
	public int numItems()
	{
		return mMemoryCache.size();
	}

}
