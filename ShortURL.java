import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ShortURL {
	
	private String inputurl;
	private String result;
	private static final String googleshortener = "https://www.googleapis.com/urlshortener/v1/url";
	private static final String apikey = "AIzaSyD8M3WTqm5GeMJaCM32fbeQ6eRzowWAPDk";
	
	public ShortURL() {
		
	}
	public void getJson() {
		try {
			String inputjson = "{\"longUrl\": \"" + inputurl + "\"}";
			URL url = new URL(googleshortener + "?key=" + apikey);
			//Debug
			//System.out.println(url);
			
			HttpURLConnection googleurl = (HttpURLConnection) url.openConnection();
			googleurl.setRequestMethod("POST");
			googleurl.setRequestProperty("Content-Type", "application/json");
			
			googleurl.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(googleurl.getOutputStream());
			writer.writeBytes(inputjson);
			writer.flush();
			writer.close();
			
			int responseCode = googleurl.getResponseCode();
			System.out.println(responseCode);
			BufferedReader reader = new BufferedReader(new InputStreamReader(googleurl.getInputStream()));
			StringBuilder returned = new StringBuilder();
			
			String line;
			while((line = reader.readLine()) != null) {
				returned.append(line);
			}
			reader.close();
			
			result = parseJson(returned.toString());
			
		} catch (Exception e) {
			System.out.println("Failed to connect to URL Shortener API");
		}
	}
	private static String parseJson(String json) {
		JsonObject object = new JsonParser().parse(json).getAsJsonObject();
		
		String id = object.get("id").getAsString();
		return id;
	}
	public void setURL(String input) {
		inputurl = input;
	}
	public String getResult() {
		getJson();
		return result;
	}
}
