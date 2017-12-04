
public class FullTimeEmployee extends EmployeeInfo {

	private double yearlyWage;

	public FullTimeEmployee(int empNum, String firstName, String lastName, String sex, String workLoc, double deductRate,
			double yearlyWage) {
		super(empNum, firstName, lastName, sex, workLoc, deductRate);
		this.yearlyWage = yearlyWage;
	}

	public double getYearlyWage() {
		return yearlyWage;
	}

	public void setYearlyWage(double yearlyWage) {
		this.yearlyWage = yearlyWage;
	}

	public double calcAnnualGrossIncome() {
		return this.yearlyWage;
	}

	public double AnnualNetIncome() {
		return calcAnnualGrossIncome() * (1 - deductRate);
	}

}
