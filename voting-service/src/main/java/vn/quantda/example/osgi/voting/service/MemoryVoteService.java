package vn.quantda.example.osgi.voting.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import vn.quantda.example.osgi.voting.model.Vote;
import vn.quantda.example.osgi.voting.model.VoteService;
import vn.quantda.example.osgi.voting.model.Voting;

public class MemoryVoteService implements VoteService {

	Map<String, Voting> votingMap;
	
	public MemoryVoteService() {
		this.votingMap = new HashMap<String, Voting>();
		addVote("test1", 1);
	}
	
	@Override
	public Response getVotings() {
		Link[] links = new Link[votingMap.size()];
		int c = 0;
		for (Voting voting : votingMap.values()) {
			links[c++] = Link.fromMethod(VoteService.class, "getVoting").rel(voting.getTopic()).build(); 
		}
		return Response.ok().links(links).build();
	}

	@Override
	public Voting getVoting(String topic) {
		if (this.votingMap.containsKey(topic)) {
			return votingMap.get(topic);
		} else {
			Voting voting = new Voting(topic);
			this.votingMap.put(topic, voting);
			return voting;
		}
	}

	@Override
	public String getStats(String topic) {
		return getVoting(topic).getStats();
	}

	@Override
	public Voting removeVoting(String topic) {
		return this.votingMap.remove(topic);
	}

	@Override
	public void addVote(Vote vote) {
		if (vote != null) {
			getVoting(vote.getTopic()).addVote(vote);
		}

	}

	@Override
	public void addVote(String topic, int voteNum) {
		getVoting(topic).addVote(new Vote(topic, voteNum));

	}

}
