package vn.quantda.example.osgi.voting.adapter;

import org.apache.camel.builder.RouteBuilder;

public class VotingRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("twitter://search?type=polling&delay=10&keywords=#esbvote")
		.bean(new TwitterConverter())
		.to("direct:vote");
		
		from("twitter://directmessage?type=polling&delay=60")
        .bean(new TwitterConverter())
        .to("direct:vote");
        
    	/*from("irc:karafvoting@cameron.freenode.net:6667/karafvoting")
    	.bean(new IrcConverter())
    	.to("direct:vote");*/ // URL not valid now
        
        from("direct:vote")
        .choice().when(body().isNotNull()).to("bean:voteService").end()
        .to("log:INFO");
	}

}
