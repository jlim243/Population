/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Jayden Lim
 *	@since	January 16, 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String name, state, designation;
	private int population;

	// constructor
	public City(String cName, String sName, String cType, int pop) {
		name = cName;
		state = sName;
		designation = cType;
		population = pop;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other) {
		if(other.population != this.population)
			return this.population - other.population;
		else if(!other.state.equals(this.state)) 
			return this.state.compareTo(other.state);
		else 
			return this.name.compareTo(other.name);
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other) {
		if(this.population == other.population && this.name.equals(other.name)
			&& this.state.equals(other.state) && this.designation.equals(other.designation))
				return true;
		return false;
	}
	/**	Accessor methods */
	/**
	 * Get name
	 * @return Name of city
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get state name
	 * @return State name of city
	 */
	public String getState() {
		return state;
	}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}