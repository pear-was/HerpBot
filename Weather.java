import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Weather {
	
	private final static String apikey = "6801ea50771a2c3359ec76927975dc30";
	private final static String openweather = "http://api.openweathermap.org/data/2.5/weather?zip=%s&appid=%s";
	private static String zipcode = "75080";
	private String result;
	
	public Weather() {
	}
	public String getJson() {
		try {
			URL url = new URL(String.format(openweather, zipcode, apikey));
			//Debug
			//System.out.println(url);
			
			HttpURLConnection weatherurl = (HttpURLConnection) url.openConnection();
			weatherurl.setRequestMethod("GET");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(weatherurl.getInputStream()));
			StringBuilder returned = new StringBuilder();
			
			String line;
			while((line = reader.readLine()) != null) {
				returned.append(line);
			}
			reader.close();
			
			result = parseJson(returned.toString());
			
		} catch (Exception e) {
			System.out.println("Failed to connect to OpenWeather API");
		}
		
		return null;
	}
	private static String parseJson(String json) {
		
		JsonObject object = new JsonParser().parse(json).getAsJsonObject();
		
		String conditions = object.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
		
		JsonObject main = object.getAsJsonObject("main");
		double temp = main.get("temp").getAsDouble();
		
		String location = object.get("name").getAsString();
		
		return formatMessage(location, temp, conditions);
	}
	private static String formatMessage(String location, double temp, String conditions) {
		
		double tempF = (9.0 / 5.0) * (temp - 273) + 32;
		
		String output = "The temperature in " + location + " is " + String.format("%.0f", tempF) + "F and the current conditions are: " + conditions;
		
		return output;
	}
	public void setLocation(String location) {
		zipcode = location;
	}
	public String getResult() {
		getJson();
		return result;
	}
}
