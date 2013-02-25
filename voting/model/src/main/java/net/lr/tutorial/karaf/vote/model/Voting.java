package net.lr.tutorial.karaf.vote.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class Voting {
	private String topic;
	private List<Vote> votes;
	
	public Voting() {
		this.votes = new ArrayList<Vote>();
	}

	public Voting(String topic) {
		this();
		this.topic = topic;
	}
	
	@XmlElement
	public double getAverage() {
		int sum = 0;
		for (Vote vote : votes) {
			sum += vote.getVote();
		}
		return new Double(sum) / votes.size();
	}

	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}

	@XmlElement(name="vote")
	public List<Vote> getVotes() {
		return votes;
	}

	public void addVote(Vote vote) {
		if (vote.isValid()) {
			this.votes.add(vote);
		}
	}

	public String getStats() {
		VoteStats stats = new VoteStats();
		for (Vote vote : votes) {
			stats.addVote(vote);
		}
		return stats.getStats();
	}

}
