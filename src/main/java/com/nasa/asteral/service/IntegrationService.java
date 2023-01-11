package com.nasa.asteral.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

@Service
public class IntegrationService {
	
	private final OkHttpClient client = new OkHttpClient();
	
	public <T> T doGetRequest(String endpoint, Map<String, String> queryParameters,
			Class<T> clazz) {
		try {
			HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint).newBuilder();
			queryParameters.forEach((k,v) -> urlBuilder.addQueryParameter(k, v));
		    String url = urlBuilder.build().toString();

		    Request request = new Request.Builder()
		      .url(url)
		      .build();
		    
		    Call call = client.newCall(request);
		    String response = call.execute().body().string();
		    
		    return new Gson().fromJson(response, clazz);
		} catch (Exception e) {
			// TODO Log exception first or else exception is lost
			throw new RuntimeException("Fetching data from API failed.");
		}
	}

}
