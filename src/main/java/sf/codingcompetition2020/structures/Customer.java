package sf.codingcompetition2020.structures;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Customer {
	private int customerId;
	private String firstName;
	private String lastName;
	private int age;
	private String area;
	private int agentId;
	private short agentRating;
	private String primaryLanguage;
	private List<Dependent> dependents;
	private boolean homePolicy;
	private boolean autoPolicy;
	private boolean rentersPolicy;
	private String totalMonthlyPremium;
	private short yearsOfService;
	private Integer vehiclesInsured;
	
	//Constructor with fields until those after dependents
	public Customer (int customerId, String firstName, String lastName, int age, String area, int agentId, short agentRating, 
	String primaryLanguage) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.area = area;
		this.agentId = agentId;
		this.agentRating = agentRating;
		this.primaryLanguage = primaryLanguage;
		// Set below values to default values for testing because could not determine how to read in dependents
		this.dependents = null;
		this.homePolicy = false;
		this.autoPolicy = true;
		this.rentersPolicy = false;
		this.totalMonthlyPremium = "100";
		this.yearsOfService = 10;
		this.vehiclesInsured = 2;
	}
	
	//overloaded constructor
	public Customer (int customerId, String firstName, String lastName, int age, String area, int agentId, short agentRating, 
			String primaryLanguage, List<Dependent> dependents, boolean homePolicy, boolean autoPolicy, boolean rentersPolicy, String totalMonthlyPremium, 
			short yearsOfService, Integer vehiclesInsured) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.area = area;
		this.agentId = agentId;
		this.agentRating = agentRating;
		this.primaryLanguage = primaryLanguage;
		this.dependents = dependents;
		this.homePolicy = homePolicy;
		this.autoPolicy = autoPolicy;
		this.rentersPolicy = rentersPolicy;
		this.totalMonthlyPremium = totalMonthlyPremium;
		this.yearsOfService = yearsOfService;
		this.vehiclesInsured = vehiclesInsured;
	}
	
	
	public String getFirstName() {
		// TODO Auto-generated method stub
		return this.firstName;
	}
	public int getCustomerId() {
		// TODO Auto-generated method stub
		return this.customerId;
	}
	public short getYearsOfService() {
		// TODO Auto-generated method stub
		return this.yearsOfService;
	}
	public String getTotalMonthlyPremium() {
		// TODO Auto-generated method stub
		return this.totalMonthlyPremium;
	}
	public String getLastName() {
		// TODO Auto-generated method stub
		return this.lastName;
	}
	public int getAge() {
		return this.age;
	}
	
	public boolean getHomePolicy() {
		return this.homePolicy;
	}
	
	public boolean getRentersPolicy() {
		return this.rentersPolicy;
	}
	
	public boolean getAutoPolicy() {
		return this.autoPolicy;
	}


}
