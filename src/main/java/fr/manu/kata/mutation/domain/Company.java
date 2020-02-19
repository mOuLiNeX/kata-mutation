package fr.manu.kata.mutation.domain;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;

    private List<Employee> employees = new ArrayList<>();

    public Company(String newName) {
        this.setName(newName);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void addEmployee(Employee newEmployee) {
        this.employees.add(newEmployee);
    }

    /**
     * Increase every employee's salary by the specified fraction
     *
     * @param incrementAsFraction salary increase as a fraction of the original salary. e.g. if the value of the
     *                            parameter is 0.1, everyone at the company gets a 10% raise
     */
    public void everybodyGetsRaiseBy(double incrementAsFraction) {
        this.employees.forEach(e -> e.setSalary(e.getSalary() * (1 + incrementAsFraction)));
    }

    /**
     * Finds an employee by their id
     *
     * @param id the id of the employee to be found
     * @return the employee with the id passed as the parameter or null if no such employee exists
     */
    public Employee findEmployeeById(String id) {
        return this.employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public int numberOfEmployees() {
        return this.employees.size();
    }
}
