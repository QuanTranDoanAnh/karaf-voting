
package vn.quantda.example.osgi.voting.command;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import vn.quantda.example.osgi.voting.model.VoteService;

@Command(scope = "vote", name = "add", description = "add a vote")
@Service
public class VoteAdd implements Action {
	protected VoteService voteService;

	@Argument(index = 0, name="topic", description = "Voting topic to show", required = true, multiValued = false)
	private String topic;
	
	@Argument(index = 1, name="vote", description = "Vote to add (1..6)", required = true, multiValued = false)
	private Integer vote;
	
	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

    @Override
    public Object execute() throws Exception {
    	 System.out.println(String.format("Vote %d added for topic %s", vote, topic));
         voteService.addVote(topic, vote);
         return null;
    }
}
