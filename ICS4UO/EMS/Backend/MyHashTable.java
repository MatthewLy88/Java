import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
		int x = calcBucket(removeEmp.getEmpNum());
		for (int i = 0; i < buckets[x].size(); i++) {
			if (buckets[x].get(i).getEmpNum() == removeEmp.getEmpNum()) {
				// removeEmployee = buckets[x].get(i);
				buckets[x].remove(i);
				System.out.println("\nEmployee: " + removeEmp.getEmpNum() + " has been removed");
			}
		}
		return removeEmp;
                
	}

	public EmployeeInfo searchByEmployeeNumber(int empNum) { // SEARCH AND LIST EMPLOYEE INFO

		// Determine the position of the employee in the ArrayList for the bucket that
		// employee hashes to.
		// If the employee is not found, return -1.

		EmployeeInfo empInfo = null;
		boolean real = false;
		int x = calcBucket(empNum);
		for (int i = 0; i < buckets[x].size(); i++) {
			if (buckets[x].get(i).getEmpNum() == empNum) {
				empInfo = buckets[x].get(i);
				//employeeData(empInfo);
				real = true;
			}

		}
		if (real = false) {
			//System.out.println("\nEmployee " + empNum + " doest not exist");
		}
		return empInfo;
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
           try {
               PrintWriter writer = new PrintWriter ("Database.txt");
               for (int i = 0; i < buckets.length; i++) {
                   int listSize = buckets[i].size();
                   if (listSize == 0) {
			System.out.println("  Nothing in its ArrayList!");
                   } else if (listSize != 0) {
			for (int j = 0; j < listSize; j++) {
                           EmployeeInfo someEmployee = buckets[i].get(j);  //Gets employee
                            if (someEmployee instanceof PartTimeEmployee){
                              writer.println("Part Time Employee");  //write PART TIME EMPLOYEE
                           } else if (someEmployee instanceof FullTimeEmployee) {
                              writer.println("Full Time Employee");
                           }
                           writer.println(someEmployee.getEmpNum());  //Write EMPLOYEE NUMBER
                           writer.println(someEmployee.getFirstName()); //Writes FIRST NAME
                           writer.println( someEmployee.getLastName()); //Writes LAST NAME
                           writer.println( someEmployee.getDeductRate()); //Writes DEDUCT RATE
                           writer.println(someEmployee.getSex()); //Writes GENDER
                           writer.println( someEmployee.getWorkLoc());  //Writes LOCATION
                           if (someEmployee instanceof PartTimeEmployee){
                              writer.println(( (PartTimeEmployee) someEmployee).getHourlyWage()); //Writes HOURLY WAGE
                              writer.println( ( (PartTimeEmployee) someEmployee).getHoursPerWeek());  //Writes HOURS WORKED PER WEEK
                              writer.println( ( (PartTimeEmployee) someEmployee).getWeeksPerYear());  //Writes WEEKS WORKED PER WEEK
                           } else if (someEmployee instanceof FullTimeEmployee) {
                               writer.println(( (FullTimeEmployee) someEmployee).getYearlyWage());   //Writes YEARLY SALARY                    
                           }
                           writer.println("-----------");
                       }
                   } 
		}
           writer.close();
           } catch (Exception e) {
               //JOptionPane.showMessageDialog(null, "File Not Found"); //make sure
           }
       }
        
	/*
	public void writeToFile() {
		// This method is called every time the hash table is modified.
		// Replaces all old contents with new contents.
		try {
			FileWriter writer = new FileWriter("Database.txt");
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
	}*/

	public void readFromFile() {
		// Call this at the beginning of the test program after the hash table is
		// instantiated.
		// Read from text file and add all contents to the hash table.
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Database.txt"));
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
					Double YearlyWage = Double.parseDouble(line);
					line = reader.readLine();
					EmployeeInfo theEmployee = new FullTimeEmployee(empNum, firstName, lastName, sex, workLoc,
							deductRate, YearlyWage);
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
                System.out.println("These are all the employees in the database:");
                System.out.println("--------------------------------------------");
		for (int i = 0; i < buckets.length; i++) {

			// For the current bucket, print out the emp num for each item
			// in its ArrayList.

			int listSize = buckets[i].size();
			if (listSize == 0) {
				System.out.println("  Nothing in its ArrayList!");
			} else {
				for (int j = 0; j < listSize; j++) {
					int theEmpNum = buckets[i].get(j).getEmpNum();
					String theEmpFName = buckets[i].get(j).getFirstName();
					String theEmpLName = buckets[i].get(j).getLastName();
					String theEmpSex = buckets[i].get(j).getSex();
                                        String theEmpWorkLoc = buckets[i].get(j).getWorkLoc();
                                        Double theEmpDeductRate = buckets[i].get(j).getDeductRate();
                                        
                                        System.out.println("\n  Employee #: " + theEmpNum);
					System.out.println("  Their first name is " + theEmpFName);
					System.out.println("  Their last name is " + theEmpLName);
                                        System.out.println("  They are a " + theEmpSex);
                                        System.out.println("  They work in " + theEmpWorkLoc);
                                        System.out.println("  Their deduct rate is " + theEmpDeductRate + "%");
                                        //if (Type = "FTE"){
                                            ;
                                        //}
                                        //if (theEmployee instanceof FullTimeEmployee) {
                                            
                                        //}
				}
			}

		}

	} // end displayContents

} // end class MyHashTable
