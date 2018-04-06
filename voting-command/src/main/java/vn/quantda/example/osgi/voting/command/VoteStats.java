
package vn.quantda.example.osgi.voting.command;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import vn.quantda.example.osgi.voting.model.VoteService;
import vn.quantda.example.osgi.voting.model.Voting;

@Command(scope = "vote", name = "stats", description = "list existing votes")
@Service
public class VoteStats implements Action {
	protected VoteService voteService;

	@Argument(index = 0, name = "topic", description = "Voting topic to show", required = true, multiValued = false)
	private String topic;

	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

	@Override
	public Object execute() throws Exception {
		System.out.println("Executing command list");
		Voting voting = voteService.getVoting(topic);
		System.out.println(voting.getStats());
		return null;
	}
}
