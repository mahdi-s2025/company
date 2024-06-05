import java.util.Date;

public class Main {

}

abstract class User {
    private String fullName;
    private String phoneNumber;

    public User(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getBasicInformation() {
        return "Full Name: " + fullName + "\t Phone Number: " + phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

abstract class Employee extends User {
    private final Date employmentDate;   // it can change.

    public Employee(String fullName, String phoneNumber, Date employmentDate) {
        super(fullName, phoneNumber);
        this.employmentDate = employmentDate;
    }

    abstract public void work(int amount);

    abstract public int getSalary();

    final public String getBasicInformation() {
        return "Full Name: " + this.getFullName() + "\t Phone Number: " + this.getPhoneNumber() +
                "\t Employment Date " + employmentDate;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }
}

final class FullTimeEmployee extends Employee {
    private int uncountedWorkingDays;
    private final int monthlySalaryRate;

    public FullTimeEmployee(String fullName, String phoneNumber, Date employmentDate, int monthlySalaryRate) {
        super(fullName, phoneNumber, employmentDate);
        this.monthlySalaryRate = monthlySalaryRate;
    }

    @Override
    public int getSalary() {
        if (uncountedWorkingDays < 30) return 0;
        uncountedWorkingDays -= 30;
        return monthlySalaryRate;
    }

    @Override
    public void work(int amount) {
        uncountedWorkingDays += amount;
    }

    public int getUncountedWorkingDays() {
        return uncountedWorkingDays;
    }

    public void setUncountedWorkingDays(int uncountedWorkingDays) {
        this.uncountedWorkingDays = uncountedWorkingDays;
    }

    public int getMonthlySalaryRate() {
        return monthlySalaryRate;
    }
}


