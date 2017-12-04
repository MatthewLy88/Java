
public class PartTimeEmployee extends EmployeeInfo {

	private double hourlyWage;
	private double hoursPerWeek;
	private double weeksPerYear;

	public PartTimeEmployee(int empNum, String firstName, String lastName, String sex, String workLoc, double deductRate,
			double hourlyWage, double hoursPerWeek, double weeksPerYear) {
		super(empNum, firstName, lastName, sex, workLoc, deductRate);
		this.hourlyWage = hourlyWage;
		this.hoursPerWeek = hoursPerWeek;
		this.weeksPerYear = weeksPerYear;
	}

	public double getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(double hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public double getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(double hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public double getWeeksPerYear() {
		return weeksPerYear;
	}

	public void setWeeksPerYear(double weeksPerYear) {
		this.weeksPerYear = weeksPerYear;
	}

	public double calcAnnualGrossIncome() {
		return hourlyWage * hoursPerWeek * weeksPerYear;
	}

	public double AnnualNetIncome() {
		return calcAnnualGrossIncome() * (1 - deductRate);
	}

}
