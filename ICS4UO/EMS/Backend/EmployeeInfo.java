
public class EmployeeInfo {

	protected int empNum;
	protected String firstName;
	protected String lastName;
	protected String sex; // encode e.g. 0 for M, 1 for F, etc.
	protected String workLoc; // encode e.g. 0 for Mississauga, etc.
	protected double deductRate; // e.g. 0.21 for 21%

	public EmployeeInfo(int empNum, String firstName, String lastName, String sex, String workLoc, double deductRate) {
		this.empNum = empNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.workLoc = workLoc;
		this.deductRate = deductRate;
	}

	public int getEmpNum() {
		return empNum;
	}

	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWorkLoc() {
		return workLoc;
	}

	public void setWorkLoc(String workLoc) {
		this.workLoc = workLoc;
	}

	public double getDeductRate() {
		return deductRate;
	}

	public void setDeductRate(double deductRate) {
		this.deductRate = deductRate;
	}

}