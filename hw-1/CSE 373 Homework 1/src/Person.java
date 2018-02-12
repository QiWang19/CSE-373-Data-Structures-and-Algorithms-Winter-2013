import java.util.*;
public class Person {
	public String name;
	public String gender;
	public boolean engaged;
	public Person partner;
	public List<Person> partners;
	public int ranking = 0;
//	public boolean flag = false;
	
	public Person(String name) {
		this.name = name;
		
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isEngaged() {
		return engaged;
	}

	public void setEngaged(boolean engaged) {
		this.engaged = engaged;
	}

//	public Person getPartner() {
//		return partner;
//	}

	public void setPartner(Person partner) {
		this.partner = partner;
	}

	public List<Person> getPartners() {
		return partners;
	}

//	public void setPartners(String[] partners) {
//		
//		
//		this.partners = new ArrayList<Person>();
//		for (int i = 0; i < partners.length; i++) 
//			
//			this.partners.add(new Person(partners[i] ) );
//	}
	
}
