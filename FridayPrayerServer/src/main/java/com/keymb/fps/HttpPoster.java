package com.keymb.fps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;

public class HttpPoster {

	public String getTime() {

		try {

			HttpClient client = new DefaultHttpClient();

			HttpPost post = new HttpPost(
					"http://muslimsalat.com/38.499366,%2043.399856/weekly.json?key=39b29a7d11bda9de726d0213fdf5400f");

			try {

				// StringEntity en = new StringEntity("<name>onur</onur>");

				// post.setEntity(en);

				HttpResponse response = client.execute(post);
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				String line = "";
				StringBuffer b = new StringBuffer();
				while ((line = rd.readLine()) != null) {
					b.append(line);
				}

				JSONParser jsonParser = new JSONParser();

				JSONObject jsonObject = (JSONObject) jsonParser.parse(b
						.toString());

				JSONArray lang = (JSONArray) jsonObject.get("items");

				Iterator i = lang.iterator();

				while (i.hasNext()) {
					JSONObject innerObj = (JSONObject) i.next();
					System.out.println("date_for " + innerObj.get("date_for")
							+ " dhuhr " + innerObj.get("dhuhr"));
				}

				return null;

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {
		HttpPoster p = new HttpPoster();

		System.out.println(p.getTime());

	}

}
