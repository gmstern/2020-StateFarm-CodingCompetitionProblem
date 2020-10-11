package sf.codingcompetition2020.structures;

public class Agent {
	
	private int agentId;
	private String area;
	private String language;
	private String firstName;
	private String lastName;
	
	public Agent(int agentId, String area, String language, String firstName, String lastName) {
		this.agentId = agentId;
		this.area = area;
		this.language = language;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		// TODO Auto-generated method stub
		return this.firstName;
	}
	public int getAgentId() {
		// TODO Auto-generated method stub
		return this.agentId;
	}
	
	public String getArea() {
		return this.area;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	
}
