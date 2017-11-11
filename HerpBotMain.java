public class HerpBotMain {
	public static void main(String[] args) throws Exception {
		// Login details
		String server = "irc.freenode.net";
		String nick = "HerpBot";
		String channel = "#herpbottest";
		
		HerpBot bot = new HerpBot(nick);
		
		bot.setVerbose(true);
		
		bot.connect(server);
		
		bot.joinChannel(channel);
	}
}
