import org.apache.commons.validator.routines.UrlValidator;
import org.jibble.pircbot.*;
import java.util.regex.*;

public class HerpBot extends PircBot {
	
	private double v = 1.0;
	static final Pattern regex = Pattern.compile("(\\d{5})");
	
	public HerpBot() {
	}
	public HerpBot(String name) {
		this.setName(name);
	}
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		
		if(message.equalsIgnoreCase("!help")) {
			sendMessage(channel, "Hello, welcome to HerpBot " + v + "!");
			sendMessage(channel, "The following commands are available:");
			sendMessage(channel, "!time, for the time.");
			sendMessage(channel, "!weather [zip code], for the weather at the zip code. *US only*");
			sendMessage(channel, "!short [url] to shorten a link.");
		}
		else if (message.equals("!time")) {
			String time = new java.util.Date().toString();
			sendMessage(channel, "The time is now " + time);
		}
		else if (message.startsWith("!weather")) {
			Matcher zipcode = regex.matcher(message);
			Weather weather = new Weather();
			
			if (zipcode.find()) {
				String foundzip = zipcode.group(1);
				weather.setLocation(foundzip);
				
				String out = weather.getResult();
				if (out == null) {
					sendMessage(channel, "Error fetching weather information, please try again.");
				}
				else {
					sendMessage(channel, out);
				}
			}
			else {
				sendMessage(channel, "Invalid input, please try again.");
			}
		}
		else if (message.startsWith("!short")) {
			String[] input = message.split(" ");
			
			//Overload and test the url by itself or with http://
			UrlValidator validator = new UrlValidator() {
				@Override
				public boolean isValid(String value ) {
					return super.isValid(value) || super.isValid("http://" + value);
				}
			};
			if (input.length == 2 && validator.isValid(input[1])) {
				ShortURL shorten = new ShortURL();
				shorten.setURL(input[1]);
				
				sendMessage(channel, shorten.getResult());
			}
			else {
				sendMessage(channel, "Invalid input, please try again.");
			}
		}
	}
}

