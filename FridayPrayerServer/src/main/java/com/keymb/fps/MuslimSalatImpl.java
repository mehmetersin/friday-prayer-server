package com.keymb.fps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MuslimSalatImpl implements TimeProviderIntf {

	final static Logger logger = LoggerFactory.getLogger(MuslimSalatImpl.class);

	public String getTime(String x, String y) {

		String dhuhr = "UNKNOWN";

		try {

			logger.debug("Start to process.");

			HttpClient client = new DefaultHttpClient();

			HttpPost post = new HttpPost("http://muslimsalat.com/" + x + ","
					+ y + "/daily.json?key=39b29a7d11bda9de726d0213fdf5400f");

			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			StringBuffer b = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				b.append(line);
			}

			logger.debug("Received Request From Server:" + b.toString());

			JSONParser jsonParser = new JSONParser();

			JSONObject jsonObject = (JSONObject) jsonParser.parse(b.toString());

			JSONArray lang = (JSONArray) jsonObject.get("items");

			Iterator i = lang.iterator();

			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				System.out.println("date_for " + innerObj.get("date_for")
						+ " dhuhr " + innerObj.get("dhuhr"));

				dhuhr = (String) innerObj.get("dhuhr");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dhuhr;

	}



}
