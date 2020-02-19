package fr.manu.kata.mutation.domain;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {

    private Company company;

    @Before
    public void setUp() {
        company = new Company("Megadyne, Inc.");
    }

    @After
    public void tearDown() {
        company = null;
    }

    @Test
    public void companyRenamed() {
        // Guard assert
        Assert.assertEquals("Megadyne, Inc.", company.getName());

        String proposedName = "Cybertron Unlimited, Ltd.";
        company.setName(proposedName);

        Assertions.assertThat(company.getName()).isEqualTo(proposedName);
    }

    @Test
    public void leadingTrailingSpacesRemovedFromEmployeeName() {
        Employee employee1 = new Employee("001", " Bob", 100_000.00);
        Assertions.assertThat(employee1.getName()).isEqualTo("Bob");

        Employee employee2 = new Employee("002", "Alice  ", 100_000.00);
        Assertions.assertThat(employee2.getName()).isEqualTo("Alice");

        Employee employee3 = new Employee("003", "Jimmy Don", 100_000.00);
        Assertions.assertThat(employee3.getName()).isEqualTo("Jimmy Don");
    }

    @Test
    public void employeeAdded() {
        company.addEmployee(new Employee("123", "Dave", 100_000.00));
        Assertions.assertThat(company.numberOfEmployees()).isEqualTo(1);

        company.addEmployee(new Employee("456", "Bob", 50_000.00));
        Assertions.assertThat(company.numberOfEmployees()).isEqualTo(2);

        Assert.assertEquals("Dave", company.findEmployeeById("123").getName());
        Assert.assertEquals("Bob", company.findEmployeeById("456").getName());
    }

    @Test
    public void everybodyGetsRaise() {
        double increaseBy = 0.1; // everybody's salary should go up by this fraction

        company.addEmployee(new Employee("123", "Dave", 100_000.00));
        company.addEmployee(new Employee("456", "Alice", 120_000.00));
        company.addEmployee(new Employee("789", "Bob", 110_000.00));

        company.everybodyGetsRaiseBy(increaseBy);

        Employee dave = company.findEmployeeById("123");

        Assertions.assertThat(dave.getSalary()).isEqualTo(110_000.00, Assertions.offset(0.0001));
    }

    @Test
    public void findEmployeeById() {
        company.addEmployee(new Employee("123", "Dave", 100_000.00));
        company.addEmployee(new Employee("456", "Alice", 100_000.00));
        company.addEmployee(new Employee("789", "Bob", 100_000.00));

        Employee hopefullyDave = company.findEmployeeById("123");
        Assertions.assertThat(hopefullyDave.getId()).isEqualTo("123");
        Assertions.assertThat(hopefullyDave.getName()).isEqualTo("Dave");
        Assertions.assertThat(hopefullyDave.getSalary()).isEqualTo(100_000.00, Assertions.offset(0.0001));

        Employee hopefullyNoOne = company.findEmployeeById("999");
        Assertions.assertThat(hopefullyNoOne).isNull();
    }

    @Test
    public void employeeNameChanged() {
        company.addEmployee(new Employee("123", "Dave", 100_000.00));
        company.addEmployee(new Employee("456", "Alice", 100_000.00));
        company.addEmployee(new Employee("789", "Bob", 100_000.00));

        Employee employee = company.findEmployeeById("123");
        employee.setName("Tommy Lee");
        Assertions.assertThat(company.findEmployeeById("123").getName()).isEqualTo("Tommy Lee");
    }
}
