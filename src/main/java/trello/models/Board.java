package trello.models;

import trello.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author naveen.chauhan on 12/07/22
 */
public class Board {
	private String id;
	private String name;
	private PRIVACY privacy;
	private String url;
	private List<Member> members;
	private List<Lists> listsList;

	private static final String DEFAULT_URL = "www.trello.com/";
	private static final PRIVACY DEFAULT_PRIVACY = PRIVACY.PUBLIC;

	private Board(String name) {
		this.name = name;
		this.id = IdGenerator.generateId();
		this.privacy = DEFAULT_PRIVACY;
		this.url = DEFAULT_URL + id;
		this.members = new ArrayList<>();
		this.listsList = new ArrayList<>();
	}

	public List<Lists> getListsList() {
		return listsList;
	}

	public String getId() {
		return id;
	}

	public static Board create(String name) {
		return new Board(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrivacy(PRIVACY privacy) {
		this.privacy = privacy;
	}

	@Override
	public String toString() {
		return "{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", privacy=" + privacy +
				", url='" + url + '\'' +
				", members=" + printMember(members) +
				", listsList=" + printLists(listsList) +
				'}';
	}

    private String printMember(List<Member> membersList) {
        final String[] result = {""};
        membersList.forEach(member -> {
            result[0] = result[0] + member.toString();
        });
        return result[0];
    }

    private String printLists(List<Lists> listsArray) {
        final String[] result = {""};
        listsArray.forEach(l -> {
            result[0] = result[0] + l.toString();
        });
        return result[0];
    }

	public void addMember(String argument) {
		this.members.add(new Member(argument, this.name));
	}

	public void removeMember(String argument) {
		Optional<Member> temp =  this.members.stream().filter(member -> argument.equals(member.getUserId())).findFirst();
		temp.ifPresent(member -> members.remove(member));
	}
}
