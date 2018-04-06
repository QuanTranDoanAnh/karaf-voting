package vn.quantda.example.osgi.voting.adapter;

import java.util.Date;

import org.apache.camel.Header;

import vn.quantda.example.osgi.voting.model.Vote;

public class IrcConverter {
	public Vote textToVote(String chatLine, @Header("irc.user.nick") String userNick) {
		String[] parts = chatLine.split(" ");
		String topic = parts[0];
		int voteNum = Integer.parseInt(parts[1]);
		return new Vote(topic, voteNum, userNick, new Date());
	}
}
