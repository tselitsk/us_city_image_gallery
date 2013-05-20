package com.example.uscityimageviewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class ResultFetcher {
	
	
//fetches an input stream from a client request. 
	
	public static InputStream fetch(String address) throws MalformedURLException,IOException {
	    HttpGet httpRequest = new HttpGet(address);
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);
	    HttpEntity entity = response.getEntity();
	    BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
	    InputStream instream = bufHttpEntity.getContent();
	    return instream;
	}

}
