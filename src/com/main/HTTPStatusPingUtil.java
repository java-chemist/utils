/**
 * 
 */
package com.main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author lugupta
 *
 */
public class HTTPStatusPingUtil {

	public static void main(String args[]) throws Exception {

		String[] hostList = { "http://localhost:8080/" };

		for (int i = 0; i < hostList.length; i++) {

			String url = hostList[i];
			String status = getStatus(url);

			System.out.println(url + "\t\tStatus:" + status);
		}

	}

	public static String getStatus(String url) throws IOException {

		String result = "";
		try {
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			int code = connection.getResponseCode();
			if (code == 200) {
				result = "Tomcat Server Running";
			}
		} catch (Exception e) {
			result = "Tomcat Server Not Running";
		}
		return result;
	}

}
