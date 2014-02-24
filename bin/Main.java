import org.jibble.pircbot.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// Start bot 
		Hearthbot bot = new Hearthbot();
		
		// Enable debug output
		bot.setVerbose(true);
		
		// Connect to an IRC server.
		bot.connect("irc.mibbit.net");
		
		// Join the #hearthchat_test channel
		bot.joinChannel("#hearthchat_test");
	}

}
