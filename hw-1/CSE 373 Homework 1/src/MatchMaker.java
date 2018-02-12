// TODO: Remove each 'todo' comment once I implement each part!

// TODO: comment header for my program

import java.util.*;

public class MatchMaker {
	// TODO: declare my private fields here
	private TreeMap<String, List<String>> men;
	private TreeMap<String, List<String>> women;
	private HashMap<String, Person> persons;
	private boolean stable = false;
	
	
	// TODO: comment header
	public MatchMaker() {
		// TODO: implement the constructor
		this.men = new TreeMap<String, List<String>>();
		this.women = new TreeMap<String, List<String>>();
		this.persons = new HashMap<String, Person>();
	}
	
	// TODO: comment header
	public void addPerson(String name, String gender, String[] partners) {
		// TODO: implement this method
		if (!this.persons.containsKey(name)) {
			Person person = new Person(name);
			person.setGender(gender);
			this.persons.put(name, person);
		}
		
//		person.setPartners(partners);
		
		List<String> partners1 = new ArrayList<String>();
		for (int i = 0; i < partners.length; i++) {
			partners1.add(partners[i]);
		}
		
		if (gender.equals("M")) {
			if(!men.containsKey(name)) {
				men.put(name, partners1);
			}
			
		} else if (gender.equals("F")) {
			if (!women.containsKey(name)) {
				women.put(name, partners1);
			}
			
		}
		
	}
	
	// TODO: comment header
	public int getRank(String name, String partner) {
		// TODO: implement this method
		List<String> p = null;
		if (this.men.containsKey(name)) {
			p = men.get(name);
		} else if (this.women.containsKey(name)) {
			p = women.get(name);
		}
		
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).equals(partner)) {
				return i + 1;
			}
		}
		
		
		return 0;
	}
	
	// TODO: comment header
	public boolean isStable() {
		// TODO: implement this method
//		return false;
		for(Person p: this.persons.values() ) {
			if (!p.engaged)
				return false;
		}
		return stable;
	}
	
	// TODO: comment header
	public void nextRound() {
		// TODO: implement this method
		boolean flag = false;
		for(String name: this.men.keySet()) {
			Person m = this.persons.get(name);
			 if (!m.engaged && !men.get(name).isEmpty()) {
//				 int i = 0;
				 String wName = men.get(name).get(0);
				 Person w = this.persons.get(wName);
//				 System.out.println(w.engaged);
				 
//				 while(w.flag && i < men.get(name).size()) {
//					 
//					 w = men.get(name).get(i);
//					 i++;
//				 }
//				 if (i == men.get(name).size() && w.flag) {
//					 
//					 continue;
//				 }
				 men.get(name).remove(0);
				 m.ranking++;
				 if (!w.engaged) {
					 System.out.println(name + " proposes to " + w.name);
//					 System.out.println(getRank(w.name, m.name) +"---" + w.name + "---"+ m.name);
					 flag = true;
					 m.engaged = true;
					 w.engaged = true;
					 m.partner = w;
					 w.partner = m;
//					 m.ranking = getRank(m.name, w.name);
					 w.ranking = getRank(w.name, m.name);
				 } else {
					 Person m2 = w.partner;
					 if (getRank(w.name, m2.name) > getRank(w.name, m.name) ) {
//						 System.out.println(getRank(w.name, m2.name) +"---" + w.name + "---"+ m2.name);
						 m.engaged = true;
						 m.partner = w;
//						 m.ranking = getRank(m.name, w.name);
						 w.engaged = true;
						 w.partner = m;
						 w.ranking = getRank(w.name, m.name);
						 m2.engaged = false;
						 m2.partner = null;
//						 m2.ranking = 0;
						 
						 System.out.println(name + " proposes to " + w.name);
						 flag = true;
					 }
				 }
			 }
			 
		}
		if (flag) {
			stable = false;
		} else {
			stable = true;
		}
		
		
	}
	
	// TODO: comment header
	public void printMatches() {
		// TODO: implement this method
		for (String name: men.keySet()) {
			Person p = persons.get(name);
			if (p.engaged) {
				System.out.println(name + ": engaged to " + p.partner.name + " ( rank " + p.ranking + " )"  );
			} else {
				System.out.println(name + ": single");
			}
			
		}
		
		for (String name: women.keySet()) {
			Person p = persons.get(name);
			if (p.engaged) {
				System.out.println(name + ": engaged to " + p.partner.name + " ( rank " + p.ranking + " )"  );
			} else {
				System.out.println(name + ": single");
			}
			
		}
		
	}
}