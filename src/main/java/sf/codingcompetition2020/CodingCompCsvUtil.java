package sf.codingcompetition2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import sf.codingcompetition2020.structures.Agent;
import sf.codingcompetition2020.structures.Claim;
import sf.codingcompetition2020.structures.Customer;
import sf.codingcompetition2020.structures.Vendor;
import java.util.Scanner;

public class CodingCompCsvUtil {
	
	/* #1 
	 * readCsvFile() -- Read in a CSV File and return a list of entries in that file.
	 * @param filePath -- Path to file being read in.
	 * @param classType -- Class of entries being read in.
	 * @return -- List of entries being returned.
	 */
	public <T> List<T> readCsvFile(String filePath, Class<T> classType) {
		File file;
		Scanner inputFile;
		try {
			file = new File(filePath);
			inputFile = new Scanner(file);
		} 
		catch (FileNotFoundException e) {
			return null;
		}
		
		
		// Determine Class Type and add appropriate entries to list
		String header = inputFile.nextLine();
		String[] tokens = header.split(",");
	
		if (tokens[0].equals("agentId")) {
			List<Agent> agentList = new ArrayList<Agent>();
			
			while(inputFile.hasNextLine()) {
				String currentLine = inputFile.nextLine();
				
				String[] aT = currentLine.split(",");
				//use constructor to set Agent
				int id = Integer.parseInt(aT[0]);
				Agent currAgent = new Agent(id, aT[1], aT[2], aT[3], aT[4]);
				agentList.add(currAgent);
				
			}
			inputFile.close();
			return (List<T>) agentList;
		}
		else if(tokens[0].equals("claimId")) {
			List<Claim> claimList = new ArrayList<Claim>();
			
			while(inputFile.hasNextLine()) {
				String currentLine = inputFile.nextLine();
		
				String[] param = currentLine.split(",");
				//use constructor to set Claim
				int claimID = Integer.parseInt(param[0]);
				int custID = Integer.parseInt(param[1]);
				boolean closed = Boolean.parseBoolean(param[2]);
				int months = Integer.parseInt(param[3]);
				
				Claim currClaim = new Claim(claimID, custID, closed, months);
				claimList.add(currClaim);
				
			}
			inputFile.close();
			return (List<T>) claimList;
			
		}
		else if(tokens[0].equals("customerId")){
			List<Customer> customerList = new ArrayList<Customer>();
			
			while(inputFile.hasNextLine()) {
				String currentLine = inputFile.nextLine();
	
				String[] param = currentLine.split(",");
				//Need to figure out how to read in dependent list
				int custID = Integer.parseInt(param[0]);
				int age = Integer.parseInt(param[3]);
				int agentID = Integer.parseInt(param[5]);
				short rating = Short.parseShort(param[6]);
				boolean closed = Boolean.parseBoolean(param[2]);
			
				//use constructor to set Customer
				Customer currCustomer = new Customer(custID, param[1], param[2], age, param[4],agentID, rating, param[7]);
				customerList.add(currCustomer);
			}
			inputFile.close();
			return (List<T>) customerList;
			
		}
		else if(tokens[0].equals("vendorId")) {
			List<Vendor> vendorList = new ArrayList<Vendor>();
			
			while(inputFile.hasNextLine()) {
				String currentLine = inputFile.nextLine();
				String[] param = currentLine.split(",");	
				int vendorId = Integer.parseInt(param[0]);
				int rating = Integer.parseInt(param[2]);
				boolean inScope = Boolean.parseBoolean(param[3]);
			
				//use constructor to set vendor
				Vendor currVendor = new Vendor(vendorId, param[1], rating, inScope);
				vendorList.add(currVendor);
				
			}
			inputFile.close();
			return (List<T>) vendorList;
			
		}
		
		inputFile.close();
		return null;
		
	}

	
	/* #2
	 * getAgentCountInArea() -- Return the number of agents in a given area.
	 * @param filePath -- Path to file being read in.
	 * @param area -- The area from which the agents should be counted.
	 * @return -- The number of agents in a given area
	 */
	public int getAgentCountInArea(String filePath,String area) {
		List<Agent> allAgentsList = readCsvFile(filePath, Agent.class);
		
		int count = 0;
		
		for(int i = 0; i < allAgentsList.size(); ++i) {
			if(allAgentsList.get(i).getArea().equals(area)) {
				count++;
			}
		}
		return count;
	}

	
	/* #3
	 * getAgentsInAreaThatSpeakLanguage() -- Return a list of agents from a given area, that speak a certain language.
	 * @param filePath -- Path to file being read in.
	 * @param area -- The area from which the agents should be counted.
	 * @param language -- The language spoken by the agent(s).
	 * @return -- The number of agents in a given area
	 */
	public List<Agent> getAgentsInAreaThatSpeakLanguage(String filePath, String area, String language) {
		
		List<Agent> allAgentsList = readCsvFile(filePath, Agent.class);
		
		List<Agent> agentList = new ArrayList<Agent>();
		
		for(int i = 0; i < allAgentsList.size(); ++i) {
			if(allAgentsList.get(i).getArea().equals(area) && allAgentsList.get(i).getLanguage().equals(language)) {
				// need if statement for dependents and cars
				agentList.add(allAgentsList.get(i));
			}
		}
		return agentList;

	}
	
	
	/* #4
	 * countCustomersFromAreaThatUseAgent() -- Return the number of individuals from an area that use a certain agent.
	 * @param filePath -- Path to file being read in.
	 * @param customerArea -- The area from which the customers should be counted.
	 * @param agentFirstName -- First name of agent.
	 * @param agentLastName -- Last name of agent.
	 * @return -- The number of customers that use a certain agent in a given area.
	 */
	public short countCustomersFromAreaThatUseAgent(Map<String,String> csvFilePaths, String customerArea, String agentFirstName, String agentLastName) {
		//method not complete
		return 0;
	}

	
	/* #5
	 * getCustomersRetainedForYearsByPlcyCostAsc() -- Return a list of customers retained for a given number of years, in ascending order of their policy cost.
	 * @param filePath -- Path to file being read in.
	 * @param yearsOfServeice -- Number of years the person has been a customer.
	 * @return -- List of customers retained for a given number of years, in ascending order of policy cost.
	 */
	public List<Customer> getCustomersRetainedForYearsByPlcyCostAsc(String customerFilePath, short yearsOfService){
		List<Customer> allCustomersList = readCsvFile(customerFilePath, Customer.class);
		
		List<Customer> custRetainedForYearsByPlcyCostAscList = new ArrayList<Customer>();
		
		for(int i = 0; i < allCustomersList.size(); ++i) {
			if(allCustomersList.get(i).getYearsOfService() == yearsOfService) {
				custRetainedForYearsByPlcyCostAscList.add(allCustomersList.get(i));
			}
		}
		
		//TODO sort list by ascending order of policy cost
		
		return custRetainedForYearsByPlcyCostAscList;
		
	}

	
	/* #6
	 * getLeadsForInsurance() -- Return a list of individuals who’ve made an inquiry for a policy but have not signed up.
	 * *HINT* -- Look for customers that currently have no policies with the insurance company.
	 * @param filePath -- Path to file being read in.
	 * @return -- List of customers who’ve made an inquiry for a policy but have not signed up.
	 */
	public List<Customer> getLeadsForInsurance(String filePath) {
		
		List<Customer> allCustomersList = readCsvFile(filePath, Customer.class);
		
		List<Customer> customerLeadsList = new ArrayList<Customer>();
		
		for(int i = 0; i < allCustomersList.size(); ++i) {
			if(allCustomersList.get(i).getHomePolicy() == false && allCustomersList.get(i).getRentersPolicy() == false && 
					allCustomersList.get(i).getAutoPolicy() == false) {
				customerLeadsList.add(allCustomersList.get(i));
			}
		}
		
		return customerLeadsList;
	}


	/* #7
	 * getVendorsWithGivenRatingThatAreInScope() -- Return a list of vendors within an area and include options to narrow it down by: 
			a.	Vendor rating
			b.	Whether that vendor is in scope of the insurance (if inScope == false, return all vendors in OR out of scope, if inScope == true, return ONLY vendors in scope)
	 * @param filePath -- Path to file being read in.
	 * @param area -- Area of the vendor.
	 * @param inScope -- Whether or not the vendor is in scope of the insurance.
	 * @param vendorRating -- The rating of the vendor.
	 * @return -- List of vendors within a given area, filtered by scope and vendor rating.
	 */
	public List<Vendor> getVendorsWithGivenRatingThatAreInScope(String filePath, String area, boolean inScope, int vendorRating) {
		//Method not complete
		List<Vendor> vendors = readCsvFile(filePath, Vendor.class);
		
		return vendors;
	}


	/* #8
	 * getUndisclosedDrivers() -- Return a list of customers between the age of 40 and 50 years (inclusive), who have:
			a.	More than X cars
			b.	less than or equal to X number of dependents.
	 * @param filePath -- Path to file being read in.
	 * @param vehiclesInsured -- The number of vehicles insured.
	 * @param dependents -- The number of dependents on the insurance policy.
	 * @return -- List of customers filtered by age, number of vehicles insured and the number of dependents.
	 */
	public List<Customer> getUndisclosedDrivers(String filePath, int vehiclesInsured, int dependents) {
		List<Customer> customers = readCsvFile(filePath, Customer.class);
		
		List<Customer> undisclosedList = new ArrayList<Customer>();
		
		for(int i = 0; i < customers.size(); ++i) {
			if(customers.get(i).getAge() >= 40 && customers.get(i).getAge() <= 50) {
				// need if statement for dependents and cars
				undisclosedList.add(customers.get(i));
			}
		}
		
		return undisclosedList;
	}	


	/* #9
	 * getAgentIdGivenRank() -- Return the agent with the given rank based on average customer satisfaction rating. 
	 * *HINT* -- Rating is calculated by taking all the agent rating by customers (1-5 scale) and dividing by the total number 
	 * of reviews for the agent.
	 * @param filePath -- Path to file being read in.
	 * @param agentRank -- The rank of the agent being requested.
	 * @return -- Agent ID of agent with the given rank.
	 */
	public int getAgentIdGivenRank(String filePath, int agentRank) {
		//method not complete
		return 0;
	}	

	
	/* #10
	 * getCustomersWithClaims() -- Return a list of customers who’ve filed a claim within the last <numberOfMonths> (inclusive). 
	 * @param filePath -- Path to file being read in.
	 * @param monthsOpen -- Number of months a policy has been open.
	 * @return -- List of customers who’ve filed a claim within the last <numberOfMonths>.
	 */
	public List<Customer> getCustomersWithClaims(Map<String,String> csvFilePaths, short monthsOpen) {
		//method not complete
		List<Customer> customers = readCsvFile(csvFilePaths.get(0), Customer.class);
		
		return customers;
	}	

}
