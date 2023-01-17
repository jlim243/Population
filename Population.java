import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *	Population - Filter through and search for cities in the US
 *  based on population, state name, or city name which is sorted
 *  using multiple different sorting methods
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Jayden Lim
 *	@since	January 16, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	SortMethods sorter = new SortMethods();
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	public Population() {
		cities = new ArrayList<>();
	}
	
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}

	/** Loads population data */
	public void loadData() {
		Scanner scan = FileUtils.openToRead(DATA_FILE);
		scan.useDelimiter("[\t\n]");
		String state, name, designation, tmp;
		int population;
		while(scan.hasNextLine()) {
			tmp = scan.nextLine();
			state = tmp.substring(0,tmp.indexOf("\t"));
			tmp = tmp.substring(tmp.indexOf("\t")+1);
			name = tmp.substring(0,tmp.indexOf("\t"));
			tmp = tmp.substring(tmp.indexOf("\t")+1);
			designation = tmp.substring(0,tmp.indexOf("\t"));
			tmp = tmp.substring(tmp.indexOf("\t")+1);
			population = Integer.parseInt(tmp);
			cities.add(new City(name, state, designation, population));
		}
	}

	// Run Population program
	public static void main(String[]args) {
		Population runner = new Population();
		runner.run();
	}

	//** Run Population program */
	public void run() {
		loadData();
		printIntroduction();
		System.out.println(cities.size() + " cities in database");
		printMenu();
		int input = Prompt.getInt("Enter selection");
		long startMillisec, endMillisec;
		while(input != 9) {
			//Options
			switch(input) {
				case 1:
					System.out.println("\nFifty least populous cities\n");
					System.out.printf("%10s %21s %22s %20s\n", "State", "City", "Type", "Population");
					startMillisec = System.currentTimeMillis();
					sorter.selectionSortPopulation(cities);
					endMillisec = System.currentTimeMillis();
					for(int i = 0; i < 50; i++) {
						System.out.printf("%3d: %s\n", i+1, cities.get(i));
					}
					System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds\n");
					break;
				case 2:
					System.out.println("\nFifty most populous cities\n");
					System.out.printf("%10s %21s %22s %20s\n", "State", "City", "Type", "Population");
					startMillisec = System.currentTimeMillis();
					sorter.mergeSortPopulation(cities);
					endMillisec = System.currentTimeMillis();
					for(int i = 0; i < 50; i++) {
						System.out.printf("%3d: %s\n", i+1, cities.get(i));
					}
					System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds\n");
					break;
				case 3:
					System.out.println("\nFifty cities sorted by name\n");
					System.out.printf("%10s %21s %22s %20s\n", "State", "City", "Type", "Population");
					startMillisec = System.currentTimeMillis();
					sorter.insertionSortName(cities);
					endMillisec = System.currentTimeMillis();
					for(int i = 0; i < 50; i++) {
						System.out.printf("%3d: %s\n", i+1, cities.get(i));
					}
					System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds\n");
					break;
				case 4:
					System.out.println("\nFifty cities sorted by name descending\n");
					System.out.printf("%10s %21s %22s %20s\n", "State", "City", "Type", "Population");
					startMillisec = System.currentTimeMillis();
					sorter.mergeSortName(cities);
					endMillisec = System.currentTimeMillis();
					for(int i = 0; i < 50; i++) {
						System.out.printf("%3d: %s\n", i+1, cities.get(i));
					}
					System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds\n");
					break;
				case 5:
					System.out.println();
					String userState = Prompt.getString("Enter state name (ie. Alabama)");
					List<City> stateCities = new ArrayList<>();
					for(int i = 0; i < cities.size(); i++) {
						if(cities.get(i).getState().equalsIgnoreCase(userState)) {
							stateCities.add(cities.get(i));
						}
					}
					while(stateCities.size() <= 0) {
						System.out.println("ERROR: " + userState + " is not valid");
						userState = Prompt.getString("Enter state name (ie. Alabama)");
						stateCities = new ArrayList<>();
						for(int i = 0; i < cities.size(); i++) {
							if(cities.get(i).getState().equalsIgnoreCase(userState)) {
								stateCities.add(cities.get(i));
							}
						}
					}
					
					System.out.println("\nFifty most populous cities in " + userState + "\n");
					System.out.printf("%10s %21s %22s %20s\n", "State", "City", "Type", "Population");
					startMillisec = System.currentTimeMillis();
					sorter.mergeSortPopulation(stateCities);
					endMillisec = System.currentTimeMillis();
					for(int i = 0; i < Math.min(50, stateCities.size()); i++) {
						System.out.printf("%3d: %s\n", i+1, stateCities.get(i));
					}
					System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds\n");
					break;
				case 6:
					String userCity = Prompt.getString("\nEnter city name");
					List<City> validCities = new ArrayList<>();
					for(int i = 0; i < cities.size(); i++) {
						if(cities.get(i).getName().equalsIgnoreCase(userCity)) {
							validCities.add(cities.get(i));
						}
					}
					while(validCities.size() <= 0) {
						System.out.println("ERROR: " + userCity + " is not valid");
						userCity = Prompt.getString("Enter state name (ie. Alabama)");
						validCities = new ArrayList<>();
						for(int i = 0; i < cities.size(); i++) {
							if(cities.get(i).getName().equalsIgnoreCase(userCity)) {
								validCities.add(cities.get(i));
							}
						}
					}
					
					System.out.println("\nCity " + userCity + " by population\n");
					System.out.printf("%10s %21s %22s %20s\n", "State", "City", "Type", "Population");
					startMillisec = System.currentTimeMillis();
					sorter.mergeSortPopulation(validCities);
					endMillisec = System.currentTimeMillis();
					for(int i = 0; i < validCities.size(); i++) {
						System.out.printf("%3d: %s\n", i+1, validCities.get(i));
					}
					System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds\n");
					break;
			}
			printMenu();
			input = Prompt.getInt("Enter selection");
		} 

		System.out.println("\nThanks for using Population!");
	}
	
}
