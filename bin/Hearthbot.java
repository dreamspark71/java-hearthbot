import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.jibble.pircbot.*;

public class Hearthbot extends PircBot {
	// In seconds
	final int VOTE_TIME = 10;
	final int WAIT_TIME = 5;
	boolean isPlayerTurn = false;
	boolean readyForVotes = false;
	
	Hashtable<String, String> votes = new Hashtable<String, String>();
	
	
	public Hearthbot() {
		this.setName("Hearthbot");
	}
	
	public void onMessage(String channel, String sender, 
						String login, String hostname, String message) {
		if (message.equalsIgnoreCase("!time")) {
			String time = new java.util.Date().toString();
			sendMessage(channel, sender + ": The time is now " + time);
		} else if (message.contains("!vote ")) {
			String action = message.split("!vote ")[1];
			votes.put(sender, action);
			sendMessage(channel, sender + " voted to " + action);
		} else if (message.equalsIgnoreCase("!tally")) {
			String tally = getTally();
			sendMessage(channel, tally); 
		} else if (message.equalsIgnoreCase("!startTurn")) {
			readyForVotes = true;
			countdown(channel, 10);
			String tally = getTally();
			sendMessage(channel, tally);
		}
	}
	
	public void countdown(String channel, int seconds) {
		sendMessage(channel, "Starting " + Integer.toString(seconds) + " second countdown.");
		for (int i=0; i < seconds; i++) {
			//sendMessage(channel, "Seconds Remaining: " +  Integer.toString(seconds - i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}

	}
	
	public String getTally() {
		String tally = "";
		for (Map.Entry<String, String> entry : votes.entrySet()) {
			tally += entry.getKey() + " voted: " + entry.getValue() + "\n";
		}
		return tally;
	}
	
}
