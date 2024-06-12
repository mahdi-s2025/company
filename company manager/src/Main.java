import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Admin admin = Admin.getAdmin("Mahdi", "09102244741");

        Employee employee1 = admin.newEmployee("test1", "09100000001", new Date(), 2100, EmployeeType.FULL_TIME_EMPLOYEE);
        Employee employee2 = admin.newEmployee("test2", "09100000002", new Date(), 2200, EmployeeType.FULL_TIME_EMPLOYEE);
        Employee employee3 = admin.newEmployee("test3", "09100000003", new Date(), 100, EmployeeType.PART_TIME_EMPLOYEE);
        Employee employee4 = admin.newEmployee("test4", "09100000004", new Date(), 110, EmployeeType.PART_TIME_EMPLOYEE);
        Employee employee5 = admin.newEmployee("test5" , "09100000005", new Date(), 700, EmployeeType.PROJECT_EMPLOYEE);

        for (Employee employee : admin.getEmployees()) {
            employee.work(50);
            int salary = 0;
            int amount;
            do {
                amount = employee.getSalary();
                salary += amount;
            } while (amount != 0);
            System.out.println(salary);
        }
    }
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

class Admin extends User {
    private static Admin admin;
    private final ArrayList<Employee> employees;

    private Admin(String fullName, String phoneNumber) {
        super(fullName, phoneNumber);
        employees = new ArrayList<>();
    }

    public static Admin getAdmin(String fullName, String phoneNumber) {
        if (admin == null) {
            admin = new Admin(fullName, phoneNumber);
        }
        return admin;
    }

    public static Admin getAdmin() {
        return admin;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public String getEmployeesInfo() {
        StringBuilder employeesInfo = new StringBuilder();
        for (Employee employee : employees) {
            employeesInfo.append(employee.getBasicInformation()).append("\n");
        }
        return employeesInfo.toString();
    }

    public Employee newEmployee(String fullName, String phoneNumber, Date employmentDate, int salaryRate, EmployeeType employeeType) {
        switch (employeeType) {
            case FULL_TIME_EMPLOYEE -> {
                FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(fullName, phoneNumber, employmentDate, salaryRate);
                employees.add(fullTimeEmployee);
                return fullTimeEmployee;
            }
            case PART_TIME_EMPLOYEE -> {
                PartTimeEmployee partTimeEmployee = new PartTimeEmployee(fullName, phoneNumber, employmentDate, salaryRate);
                employees.add(partTimeEmployee);
                return partTimeEmployee;
            }
            case PROJECT_EMPLOYEE -> {
                ProjectEmployee projectEmployee = new ProjectEmployee(fullName, phoneNumber, employmentDate, salaryRate);
                employees.add(projectEmployee);
                return projectEmployee;
            }
            default -> {
                return null;
            }
        }
    }
}

enum EmployeeType {
    FULL_TIME_EMPLOYEE, PART_TIME_EMPLOYEE, PROJECT_EMPLOYEE
}