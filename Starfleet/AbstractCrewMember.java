package il.ac.tau.cs.sw1.ex9.starfleet;

public abstract class AbstractCrewMember implements CrewMember{
	
	private String name;
	private int age;
	private int yearsInService;
	
	public AbstractCrewMember(String name, int age, int yearsInService){
		this.name = name;
		this.age = age;
		this.yearsInService = yearsInService;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
	
	public int getYearsInService(){
		return yearsInService;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCrewMember other = (AbstractCrewMember) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}
