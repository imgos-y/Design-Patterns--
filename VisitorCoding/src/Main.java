import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Company c = new Company();
        c.addEmployee(new FullTimeEmployee("İmge" , 40000, 5));
        c.addEmployee(new PartTimeEmployee("Deniz" ,20000, 3 ));

        c.accept(new SalaryVisitor());
        c.accept(new VacationVisitor());

    }
}

interface EmployeeVisitor {
    public void PartTimevisit(PartTimeEmployee partTimeEmployee);
    public void FullTimeVisit(FullTimeEmployee fullTimeEmployee);

}

class SalaryVisitor implements EmployeeVisitor {

    @Override
    public void PartTimevisit(PartTimeEmployee e) {
        double increase = e.getSalary() * 0.05; // %5 zam
        e.setSalary(e.getSalary() + increase);
        System.out.println(e.getName() + " new salary: " + e.getSalary());

    }

    @Override
    public void FullTimeVisit(FullTimeEmployee e) {
        double increase = e.getSalary() * 0.10; // %10 zam
        e.setSalary(e.getSalary() + increase);
        System.out.println(e.getName() + " new salary: " + e.getSalary());

    }
}

class VacationVisitor implements EmployeeVisitor {

    @Override
    public void PartTimevisit(PartTimeEmployee e) {
        int holidays = e.getVacationDays() *2;
        e.setVacationDays(e.getVacationDays() + holidays);
        System.out.println(e.getName() + " new vacation days: " + e.getVacationDays());

    }

    @Override
    public void FullTimeVisit(FullTimeEmployee e) {
        int holidays = e.getVacationDays() *4;
        e.setVacationDays(e.getVacationDays() + holidays);
        System.out.println(e.getName() + " new vacation days: " + e.getVacationDays());

    }
}
interface Employee {

    public void Accept(EmployeeVisitor visitor);
}


class FullTimeEmployee implements Employee{
    private String name;
    private double salary;
    private int vacationDays;

    public FullTimeEmployee(String name, double salary, int vacationDays) {
        this.name = name;
        this.salary = salary;
        this.vacationDays = vacationDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    @Override
    public void Accept(EmployeeVisitor visitor) {
        visitor.FullTimeVisit(this);

    }
}

class PartTimeEmployee implements Employee{
    private String name;
    private double salary;
    private int vacationDays;


    public PartTimeEmployee(String name, double salary, int vacationDays) {
        this.name = name;
        this.salary = salary;
        this.vacationDays = vacationDays;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }



    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public void Accept(EmployeeVisitor visitor) {
        visitor.PartTimevisit(this);

    }
}

class Company {
    private ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public void accept(EmployeeVisitor visitor) {
        for (Employee e : employees) {
            e.Accept(visitor);
        }

    }
}


