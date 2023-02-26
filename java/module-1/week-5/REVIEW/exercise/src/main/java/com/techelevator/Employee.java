package com.techelevator;

import java.math.BigDecimal;

public class Employee {

    private static final BigDecimal DEFAULT_SALARY = new BigDecimal("60000.00");
    private long employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    private Department department;
    private String hireDate;

    public Employee() {
    }

    public Employee(long employeeID, String firstName, String lastName, String email, Department department, String hireDate) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.hireDate = hireDate;
        this.salary = DEFAULT_SALARY;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getFullName(){return lastName + ", " + firstName;}

    public void raiseSalary(double percent){
        salary = salary.multiply(BigDecimal.valueOf(percent));
    }
}
