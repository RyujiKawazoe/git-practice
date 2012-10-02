package jp.co.val.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyConnection {
	private HttpURLConnection connection;
	
	public void setConnection(String url, String method) throws IOException {
		HttpURLConnection connection = (HttpURLConnection)(new URL(url)).openConnection();
		//connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		connection.setRequestMethod(method);
		if(method.equals("POST") || method.equals("PUT")) {
			connection.setDoOutput(true);
		}
		
		this.connection = connection;
	}
	
	public String doConnection(String requestBody) throws IOException {
		// POSTまたはPUTならリクエストを送信
		String method = connection.getRequestMethod();
		if(method.equals("POST") || method.equals("PUT")) {
			try {
				PrintStream ps = new PrintStream(connection.getOutputStream());
				ps.print(requestBody);
				ps.close();
			} catch(IOException e) {
				connection.disconnect();
				throw e;
			}
		}
		
		// レスポンスの取得
		try {
			int code = connection.getResponseCode();
			if(code == 200) {
				InputStreamReader in = new InputStreamReader(connection.getInputStream(), "UTF-8");
				try {
					BufferedReader reader = new BufferedReader(in);
					StringBuffer buf = new StringBuffer();
					String line;
					try {
						while((line = reader.readLine()) != null) {
							buf.append(line);
						}
						
						return buf.toString();
					} finally {
						reader.close();
					}
				} finally {
					in.close();
				}
			} else {
				// とりあえず200番以外はnullを返却
				return null;
			}
		} finally {
			connection.disconnect();
		}
	}
}