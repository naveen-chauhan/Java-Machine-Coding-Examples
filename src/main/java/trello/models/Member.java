package trello.models;

/**
 * @author naveen.chauhan on 12/07/22
 */
public class Member {
	private String name;
	private final String userId;
	private final String emails;

	private static final String DEFAULT_NAME = "Naveen";

	public Member(String userId, String boardName) {
		this.userId = userId;
		this.emails = DEFAULT_NAME + boardName;
	}

	public String getName() {
		return name;
	}

	public String getUserId() {
		return userId;
	}

	public String getEmails() {
		return emails;
	}

	@Override
	public String toString() {
		return "{" +
				"name='" + name + '\'' +
				", userId='" + userId + '\'' +
				", emails='" + emails + '\'' +
				'}';
	}
}
