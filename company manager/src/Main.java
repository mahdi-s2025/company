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
        uncountedWorkingDays = 0;
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

final class PartTimeEmployee extends Employee {
    private int uncountedWorkingHours;
    private final int dailySalaryRate;

    public PartTimeEmployee(String fullName, String phoneNumber, Date employmentDate, int dailySalaryRate) {
        super(fullName, phoneNumber, employmentDate);
        uncountedWorkingHours = 0;
        this.dailySalaryRate = dailySalaryRate;
    }

    @Override
    public int getSalary() {
        if (uncountedWorkingHours < 24) return 0;
        uncountedWorkingHours -= 24;
        return dailySalaryRate;
    }

    @Override
    public void work(int amount) {
        uncountedWorkingHours += amount;
    }

    public int getUncountedWorkingHours() {
        return uncountedWorkingHours;
    }

    public void setUncountedWorkingHours(int uncountedWorkingHours) {
        this.uncountedWorkingHours = uncountedWorkingHours;
    }

    public int getDailySalaryRate() {
        return dailySalaryRate;
    }
}

final class ProjectEmployee extends Employee {
    private int uncountedCompleteProjects;
    private final int projectSalaryRate;

    public ProjectEmployee(String fullName, String phoneNumber, Date employmentDate, int projectSalaryRate) {
        super(fullName, phoneNumber, employmentDate);
        this.uncountedCompleteProjects = 0;
        this.projectSalaryRate = projectSalaryRate;
    }

    @Override
    public int getSalary() {
        if (uncountedCompleteProjects < 1) return 0;
        uncountedCompleteProjects--;
        return projectSalaryRate;
    }

    @Override
    public void work(int amount) {
        uncountedCompleteProjects += amount;
    }

    public int getUncountedCompleteProjects() {
        return uncountedCompleteProjects;
    }

    public void setUncountedCompleteProjects(int uncountedCompleteProjects) {
        this.uncountedCompleteProjects = uncountedCompleteProjects;
    }

    public int getProjectSalaryRate() {
        return projectSalaryRate;
    }
}