package fr.manu.kata.mutation.domain;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class CompanyTest {
    private Company company;

    @Before
    public void setUp() {
        this.company = new Company("Megadyne, Inc.");
    }

    @After
    public void tearDown() {
        this.company = null;
    }

    @Test
    public void companyRenamed() {
        String proposedName = "Cybertron Unlimited, Ltd.";

        Company aCompany = spy(this.company);
        aCompany.setName(proposedName);
        verify(aCompany).setName(proposedName);

        aCompany.getName();
    }

    @Test
    public void leadingTrailingSpacesRemovedFromEmployeeName() {
        Employee employee1 = new Employee("001", " Bob", 100_000.00);
        Assertions.assertThat(employee1.getName()).isEqualTo("Bob");
        Employee employee2 = new Employee("002", "Alice  ", 100_000.00);
        Assert.assertEquals("Alice", employee2.getName());
        Assertions.assertThat(employee2.getName()).isEqualTo("Alice");
    }

    @Test
    public void employeeAdded() {
        this.company.addEmployee(new Employee("123", "Dave", 100_000.00));
        Assertions.assertThat(this.company.numberOfEmployees()).isPositive();

        this.company.addEmployee(new Employee("456", "Bob", 50_000.00));
        Assertions.assertThat(this.company.numberOfEmployees()).isPositive();
    }

    @Test
    public void everybodyGetsRaise() {
        double increaseBy = 0.1; // everybody's salary should go up by this fraction

        double davesOriginalSalary = 100_000.00;

        this.company.addEmployee(new Employee("123", "Dave", davesOriginalSalary));
        this.company.addEmployee(new Employee("456", "Alice", 120_000.00));
        this.company.addEmployee(new Employee("789", "Bob", 110_000.00));

        this.company.everybodyGetsRaiseBy(increaseBy);

        Employee dave = this.company.findEmployeeById("123");

        Assertions.assertThat(dave.getSalary()).isEqualTo(davesOriginalSalary * increaseBy, Assertions.offset(0.0001));
    }

    @Test
    public void findEmployeeById() {
        this.company.addEmployee(new Employee("123", "Dave", 100_000.00));
        this.company.addEmployee(new Employee("456", "Alice", 100_000.00));
        this.company.addEmployee(new Employee("789", "Bob", 100_000.00));

        Employee hopefullyDave = this.company.findEmployeeById("123");
        Employee hopefullyNoOne = this.company.findEmployeeById("999");
    }

    @Test
    public void employeeNameChanged() {
        this.company.addEmployee(new Employee("123", "Dave", 100_000.00));
        this.company.addEmployee(new Employee("456", "Alice", 100_000.00));
        this.company.addEmployee(new Employee("789", "Bob", 100_000.00));

        Employee employee = this.company.findEmployeeById("123");
        employee.setName("Tommy Lee");
        employee = this.company.findEmployeeById("123");
        System.out.println(employee.getName().equals("Tommy Lee") ? "PASSED" : "FAILED");
    }
}
