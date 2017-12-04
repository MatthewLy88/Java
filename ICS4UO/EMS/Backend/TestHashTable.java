public class TestHashTable {

	public static void main(String[] args) {

		MyHashTable theHT = new MyHashTable(2);

		FullTimeEmployee someEmp = new FullTimeEmployee(757442, "Daffy", "Duck", "male", "Mississauga", 0.23, 100000.08);
		theHT.addEmployee(someEmp);

		FullTimeEmployee someEmp2 = new FullTimeEmployee(907263, "Leghorn", "Foghorn", "male", "Toronto", 0.19, 208300);
		theHT.addEmployee(someEmp2);

		FullTimeEmployee someEmp3 = new FullTimeEmployee(186484, "Lola", "Bunny", "female", "Seattle", 0.20, 301000);
		theHT.addEmployee(someEmp3);

		PartTimeEmployee anotherEmp = new PartTimeEmployee(888885, "Bugs", "Bunny", "male", "Brampton", 0.21, 11.40, 40, 46);
		theHT.addEmployee(anotherEmp);

		PartTimeEmployee anotherEmp2 = new PartTimeEmployee(795306, "Marvin", "Martian", "male", "Africa", 0.15, 12.35, 35, 48);
		theHT.addEmployee(anotherEmp2);

		PartTimeEmployee anotherEmp3 = new PartTimeEmployee(390217, "Sylvester", "Cat", "male", "Kenya", 0.27, 20.05, 38, 52);
		theHT.addEmployee(anotherEmp3);
		
		theHT.displayContents();
		theHT.searchByEmployeeNumber(907263);
		theHT.searchByEmployeeNumber(795306);
		theHT.removeEmployee(someEmp);
		theHT.displayContents();
	}

}
