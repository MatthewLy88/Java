import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyHashTable {

	// ATTRIBUTES

	// buckets is an array of ArrayList. Each item in an ArrayList is an
	// EmployeeInfo object.
	private ArrayList<EmployeeInfo>[] buckets;

	// CONSTRUCTOR

	public MyHashTable(int howManyBuckets) {
		// Construct the hash table (open hashing/closed addressing) as an array of
		// howManyBuckets ArrayLists.

		// Instantiate an array to have an ArrayList as each element of the array.
		buckets = new ArrayList[howManyBuckets];

		// For each element in the array, instantiate its ArrayList.
		for (int i = 0; i < howManyBuckets; i++) {
			buckets[i] = new ArrayList(); // Instantiate the ArrayList for bucket i.
		}
	}

	// METHODS

	public int calcBucket(int keyValue) {

		// Returns the bucket number as the integer keyValue modulo the number of
		// buckets for the hash table.

		return (keyValue % buckets.length);
	}

	public boolean addEmployee(EmployeeInfo theEmployee) { // ADD AN EMPLOYEE

		// Add the employee to the hash table. Return true if employee is added
		// successfully,
		// return false otherwise.

		if (theEmployee == null) {
			return (false);
		} else {

			// Determine the target bucket for this employee's empNum.

			int x = calcBucket(theEmployee.getEmpNum());
			boolean addStatus = buckets[x].add(theEmployee);
			return (addStatus);
		}
	}

	public EmployeeInfo removeEmployee(EmployeeInfo removeEmp) { // REMOVE AN EMPLOYEE

		// Remove the employee from the hash table and return the reference to that
		// employee.
		// If the employee is not in the hash table, return null.

		EmployeeInfo removeEmployee = removeEmp;
		int x = calcBucket(removeEmp.getEmpNum());
		for (int i = 0; i < buckets[x].size(); i++) {
			if (buckets[x].get(i).getEmpNum() == removeEmp.getEmpNum()) {
				// removeEmployee = buckets[x].get(i);
				buckets[x].remove(i);
				System.out.println("\nEmployee: " + removeEmployee.getEmpNum() + " has been removed");
			}
		}
		return removeEmployee;
	}

	public int searchByEmployeeNumber(int empNum) { // SEARCH AND LIST EMPLOYEE INFO

		// Determine the position of the employee in the ArrayList for the bucket that
		// employee hashes to.
		// If the employee is not found, return -1.

		int position = -1;
		EmployeeInfo empInfo;
		boolean real = false;
		int x = calcBucket(empNum);
		for (int i = 0; i < buckets[x].size(); i++) {
			if (buckets[x].get(i).getEmpNum() == empNum) {
				position = i;
				empInfo = buckets[x].get(i);
				employeeData(empInfo);
				real = true;
			}

		}
		if (real = false) {
			System.out.println("\nEmployee " + empNum + " doest not exist");
		}
		return position;
	}

	public void employeeData(EmployeeInfo myEmp) { // USED BY SEARCH FUNTION PRINTS OUT EMPLOYEE INFO 
		
		System.out.println("\nEmployee Number: " + myEmp.getEmpNum());
		System.out.println("First Name: " + myEmp.getFirstName());
		System.out.println("Last Name: " + myEmp.getLastName());
		System.out.println("Sex: " + myEmp.getSex());
		System.out.println("Work Location: " + myEmp.getWorkLoc());
		System.out.println("Deduct Rate: " + myEmp.getDeductRate());
		if (myEmp instanceof PartTimeEmployee) {
			PartTimeEmployee partTimeEmp = (PartTimeEmployee) myEmp;
			System.out.println("Hourly wage is " + partTimeEmp.getHourlyWage());
		} else if (myEmp instanceof FullTimeEmployee) {
			FullTimeEmployee fullTimeEmp = (FullTimeEmployee) myEmp;
			System.out.println("Yearly Salary is " + fullTimeEmp.getYearlyWage());
		}
	}
	
	public void writeToFile() {
		// This method is called every time the hash table is modified.
		// Replaces all old contents with new contents.
		try {
			FileWriter writer = new FileWriter("Database");
			for (int i = 0; i < buckets.length; i++) {
				// For the current bucket, print out the empNum for each item in its ArrayList.
				int listSize = buckets[i].size();
				if (listSize != 0) {
					for (int j = 0; j < listSize; j++) {
						EmployeeInfo theEmployee = buckets[i].get(j);
						if (theEmployee instanceof FullTimeEmployee) {
							writer.write("FT");
							writer.write(System.getProperty("line.separator"));
						} else if (theEmployee instanceof PartTimeEmployee) {
							writer.write("PT");
							writer.write(System.getProperty("line.separator"));
						}
						writer.write("" + theEmployee.getEmpNum());
						writer.write(System.getProperty("line.separator"));
						writer.write(theEmployee.getFirstName());
						writer.write(System.getProperty("line.separator"));
						writer.write(theEmployee.getLastName());
						writer.write(System.getProperty("line.separator"));
						writer.write(theEmployee.getSex());
						writer.write(System.getProperty("line.separator"));
						writer.write(theEmployee.getWorkLoc());
						writer.write(System.getProperty("line.separator"));
						writer.write("" + theEmployee.getDeductRate());
						writer.write(System.getProperty("line.separator"));
						if (theEmployee instanceof FullTimeEmployee) {
							writer.write("" + ((FullTimeEmployee) theEmployee).getYearlyWage());
							writer.write(System.getProperty("line.separator"));
						} else if (theEmployee instanceof PartTimeEmployee) {
							writer.write("" + ((PartTimeEmployee) theEmployee).getHourlyWage());
							writer.write(System.getProperty("line.separator"));
							writer.write("" + ((PartTimeEmployee) theEmployee).getHoursPerWeek());
							writer.write(System.getProperty("line.separator"));
							writer.write("" + ((PartTimeEmployee) theEmployee).getWeeksPerYear());
							writer.write(System.getProperty("line.separator"));
						}
						writer.write("-----------");
                                                writer.write(System.getProperty("line.separator"));
					}
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFromFile() {
		// Call this at the beginning of the test program after the hash table is
		// instantiated.
		// Read from text file and add all contents to the hash table.
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Database"));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.equals("FT")) {
					line = reader.readLine();
					int empNum = Integer.parseInt(line);
					line = reader.readLine();
					String firstName = line;
					line = reader.readLine();
					String lastName = line;
					line = reader.readLine();
					String sex = line;
					line = reader.readLine();
					String workLoc = line;
					line = reader.readLine();
					Double deductRate = Double.parseDouble(line);
					line = reader.readLine();
					Double yearlySalary = Double.parseDouble(line);
					line = reader.readLine();
					EmployeeInfo theEmployee = new FullTimeEmployee(empNum, firstName, lastName, sex, workLoc,
							deductRate, yearlySalary);
					addEmployee(theEmployee);
				} else if (line.equals("PT")) {
					line = reader.readLine();
					int empNum = Integer.parseInt(line);
					line = reader.readLine();
					String firstName = line;
					line = reader.readLine();
					String lastName = line;
					line = reader.readLine();
					String sex = line;
					line = reader.readLine();
					String workLoc = line;
					line = reader.readLine();
					Double deductRate = Double.parseDouble(line);
					line = reader.readLine();
					Double hourlyWage = Double.parseDouble(line);
					line = reader.readLine();
					Double hoursPerWeek = Double.parseDouble(line);
					line = reader.readLine();
					Double weeksPerYear = Double.parseDouble(line);
					line = reader.readLine();
					EmployeeInfo theEmployee = new PartTimeEmployee(empNum, firstName, lastName, sex, workLoc,
							deductRate, hourlyWage, hoursPerWeek, weeksPerYear);
					addEmployee(theEmployee);
				}
                                /*else if (line.equals("-----------")){
                                        Database.nextLine();
                                }*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayContents() { // LIST ALL EMPLOYEES IN THE SYSTEM

		// Print the employee numbers for the employees stored in each bucket's
		// ArrayList,
		// starting with bucket 0, then bucket 1, and so on.

		for (int i = 0; i < buckets.length; i++) {

			// For the current bucket, print out the emp num for each item
			// in its ArrayList.

			System.out.println("\nExamining the ArrayList for bucket " + i);
			int listSize = buckets[i].size();
			if (listSize == 0) {
				System.out.println("  Nothing in its ArrayList!");
			} else {
				for (int j = 0; j < listSize; j++) {
					int theEmpNum = buckets[i].get(j).getEmpNum();
					String theEmpFName = buckets[i].get(j).getFirstName();
					String theEmpLName = buckets[i].get(j).getLastName();
					System.out.println("\n  Employee #: " + theEmpNum);
					System.out.println("  First Name: " + theEmpFName);
					System.out.println("  Last Name: " + theEmpLName);
				}
			}

		}

	} // end displayContents

} // end class MyHashTable
