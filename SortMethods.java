import java.util.ArrayList;
import java.util.List;
/**
 *	SortMethods - Sorts an array of Integers in ascending or decending order.
 *
 *	@author Jayden Lim
 *	@since	January 16, 2022
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		List of City objects to sort
	 */
	public void bubbleSort(List<City> arr) {
		for(int outer = arr.size() - 1; outer > 0; outer--) {
			for(int inner = 0; inner < outer; inner++) {
				if(arr.get(inner).compareTo(arr.get(inner+1)) > 0) {
					swap(arr, inner, inner+1);
				}
			}
		}
	}
	
	/**
	 *	Swaps two Integer objects in List arr
	 *	@param arr		List of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order by population
	 *	@param arr		List of City objects to sort
	 */
	public void selectionSortPopulation(List<City> arr) {
		int fat = 0; 
        for(int i = arr.size() - 1; i > 0; i--) {
			fat = i;
            for(int j = 0; j < i; j++) {
                if(arr.get(j).compareTo(arr.get(fat)) > 0) {
					fat = j;
				}
			}
			swap(arr, fat, i);
        }
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order by names
	 *	@param arr		array of City objects to sort
	 */
	public void insertionSortName(List<City> arr) {
		for (int outer = 1; outer < arr.size(); outer++)
		{
			for (int inner = outer-1; inner >= 0; inner--) {
				if (arr.get(inner+1).getName().compareTo(arr.get(inner).getName()) < 0)
					swap(arr, inner, inner+1);
				else
					inner = -1;
			}
		}
	}
	
	/**
	 *	Merge Sort algorithm - in decending order by population
	 *	@param arr		List of City objects to sort
	 */
	public void mergeSortPopulation(List<City> arr) {

		if(arr.size() == 1)
			return;
		else if(arr.size() == 2) {
			if(arr.get(0).compareTo(arr.get(1)) < 0)
				swap(arr, 0, 1);
			return;
		}

		int tmp = arr.size() / 2;
		if(arr.size() % 2 == 1)
			tmp += 1;

		List<City> arr1 = new ArrayList<>();
		for(int i = 0; i < tmp; i++)
			arr1.add(arr.get(i));
		mergeSortPopulation(arr1);

		List<City> arr2 = new ArrayList<>();
		for(int i = tmp; i < arr.size(); i++)
			arr2.add(arr.get(i));
		mergeSortPopulation(arr2);

		int c1 = 0;
		int c2 = 0;
		int c = 0;

		while(c1 < arr1.size() && c2 < arr2.size()) {
			if(arr1.get(c1).compareTo(arr2.get(c2)) > 0) {
				arr.set(c, arr1.get(c1));
				c1++;
			}
			else {
				arr.set(c, arr2.get(c2));
				c2++;
			}
			c++;
		}

		if(c1 == arr1.size())
			for(int i = c2; i < arr2.size(); i++) {
				arr.set(c, arr2.get(i));
				c++;
			}
		else {
			for(int i = c1; i < arr1.size(); i++) {
				arr.set(c, arr1.get(i));
				c++;
			}
		}

		return;
		
	}

	/**
	 *	Merge Sort algorithm - in acending order by name
	 *	@param arr		List of City objects to sort
	 */
	public void mergeSortName(List<City> arr) {

		if(arr.size() == 1)
			return;
		else if(arr.size() == 2) {
			if(arr.get(0).getName().compareTo(arr.get(1).getName()) < 0)
				swap(arr, 0, 1);
			return;
		}

		int tmp = arr.size() / 2;
		if(arr.size() % 2 == 1)
			tmp += 1;

		List<City> arr1 = new ArrayList<>();
		for(int i = 0; i < tmp; i++)
			arr1.add(arr.get(i));
		mergeSortName(arr1);

		List<City> arr2 = new ArrayList<>();
		for(int i = tmp; i < arr.size(); i++)
			arr2.add(arr.get(i));
		mergeSortName(arr2);

		int c1 = 0;
		int c2 = 0;
		int c = 0;

		while(c1 < arr1.size() && c2 < arr2.size()) {
			if(arr1.get(c1).getName().compareTo(arr2.get(c2).getName()) > 0) {
				arr.set(c, arr1.get(c1));
				c1++;
			}
			else {
				arr.set(c, arr2.get(c2));
				c2++;
			}
			c++;
		}

		if(c1 == arr1.size())
			for(int i = c2; i < arr2.size(); i++) {
				arr.set(c, arr2.get(i));
				c++;
			}
		else {
			for(int i = c1; i < arr1.size(); i++) {
				arr.set(c, arr1.get(i));
				c++;
			}
		}

		return;
		
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(List<City> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %4d", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr.get(a));
			else System.out.printf(", %4d", arr.get(a));
		}
		System.out.println(" )");
	}

	public static void main(String[]args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		// List<City> arr = new Integer.get(10);
		// Fill arr with random numbers
		 
		// for (int a = 0; a < 10; a++)
		// 	arr.get(a) = (int)(Math.random() * 100) + 1;
		// System.out.println("\nBubble Sort");
		// System.out.println("Array before sort:");
		// printArray(arr);
		// System.out.println();
		// bubbleSort(arr);
		// System.out.println("Array after sort:");
		// printArray(arr);
		// System.out.println();
		
	
		// for (int a = 0; a < 10; a++)
		// 	arr.get(a) = (int)(Math.random() * 100) + 1;
		// System.out.println("\nSelection Sort");
		// System.out.println("Array before sort:");
		// printArray(arr);
		// System.out.println();
		// selectionSort(arr);
		// System.out.println("Array after sort:");
		// printArray(arr);
		// System.out.println();

		
		// for (int a = 0; a < 10; a++)
		// 	arr.get(a) = (int)(Math.random() * 100) + 1;
		// System.out.println("\nInsertion Sort");
		// System.out.println("Array before sort:");
		// printArray(arr);
		// System.out.println();
		// insertionSort(arr);
		// System.out.println("Array after sort:");
		// printArray(arr);
		// System.out.println();

		
		// for (int a = 0; a < 10; a++)
		// 	arr.get(a) = (int)(Math.random() * 100) + 1;
		// System.out.println("\nMerge Sort");
		// System.out.println("Array before sort:");
		// printArray(arr);
		// System.out.println();
		// mergeSort(arr);
		// System.out.println("Array after sort:");
		// printArray(arr);
		// System.out.println();

	}
}
