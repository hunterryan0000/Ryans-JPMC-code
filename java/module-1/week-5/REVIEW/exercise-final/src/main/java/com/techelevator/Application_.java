package com.techelevator;

import java.text.NumberFormat;
import java.util.*;

public class Application_ {

    private final List<Department_> departments = new ArrayList<>();
    private final List<Employee_> employees = new ArrayList<>();
    private final Map<String, Project_> projects = new HashMap<>();

    /**
     * The main entry point in the application
     */
    public static void main(String[] args) {
        Application_ app = new Application_();
        app.run();
    }

    private void run() {

        // create some departments
        createDepartments();

        // print each department by name
        printDepartments();

        // create employees
        createEmployees();

        // give Angie a 10% raise, she is doing a great job!
        Employee_ angie = employees.get(1);
        angie.raiseSalary(10);

        // print all employees
        printEmployees();

        // create the TEams project
        createTeamsProject();
        // create the Marketing Landing Page Project
        createLandingPageProject();

        // print each project name and the total number of employees on the project
        printProjectsReport();

    }

    /**
     * Create departments and add them to the collection of departments
     */
    private void createDepartments() {
        Department_ marketing = new Department_(1,"Marketing");
        Department_ sales = new Department_(2,"Sales");
        Department_ engineering = new Department_(3, "Engineering");

        departments.add(marketing);
        departments.add(sales);
        departments.add(engineering);
    }

    /**
     * Print out each department in the collection.
     */
    private void printDepartments() {
        System.out.println("------------- DEPARTMENTS ------------------------------");
        for(Department_ department : departments) {
            System.out.println(department.getName());
        }
    }

    /**
     * Create employees and add them to the collection of employees
     */
    private void createEmployees() {
        Department_ engineering = departments.get(2);
        Department_ marketing = departments.get(0);

        //*** Part of bonus challenge solution:
        //Department engineering = getDepartmentByName("Engineering");
        //Department marketing = getDepartmentByName("Marketing");
        //***
        
        // Employee #1 - using no args constructor
        Employee_ dean = new Employee_();
        dean.setEmployeeId(1);
        dean.setFirstName("Dean");
        dean.setLastName("Johnson");
        dean.setEmail("djohnson@teams.com");
        dean.setSalary(Employee_.DEFAULT_STARTING_SALARY);
        dean.setDepartment(engineering);
        dean.setHireDate("08/21/2020");

        // Employee #2 - using all args constructor
        Employee_ angie = new Employee_(2,"Angie","Smith", "asmith@teams.com", engineering, "08/21/2020");
        // Employee #3 - using all args constructor
        Employee_ margaret = new Employee_(3,"Margaret","Thompson", "mthompson@teams.com", marketing, "08/21/2020");

        employees.add(dean);
        employees.add(angie);
        employees.add(margaret);
    }

    /**
     * Print out each employee in the collection.
     */
    private void printEmployees() {
        System.out.println("\n------------- EMPLOYEES ------------------------------");
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        for(Employee_ employee : employees) {
//            System.out.println(employee.getFullName() + " (" + currency.format(employee.getSalary()) + ") " + employee.getDepartment().getName());
            System.out.println(employee.getFullName() + " (" + employee.getSalary() + ") " + employee.getDepartment().getName());
        }
    }

    /**
     * Create the 'TEams' project.
     */
    private void createTeamsProject() {
        // Project #1 - TEams Project Management
        Project_ teams = new Project_("TEams", "Project Management Software", "10/10/2020", "11/10/2020");

        // add all employees where department is engineering to the teams project
        List<Employee_> engineeringTeam = new ArrayList<>();
        for(Employee_ employee : employees) {
            if( employee.getDepartment().getName().equals("Engineering")) {
                engineeringTeam.add(employee);
            }
        }
        teams.setTeamMembers(engineeringTeam);

        // the key should be the name of the project
        projects.put(teams.getName(),teams);
    }

    /**
     * Create the 'Marketing Landing Page' project.
     */
    private void createLandingPageProject() {
        // Project #2 - Marketing Landing Page
        Project_ landingPage = new Project_("Marketing Landing Page",
                "Lead Capture Landing Page for Marketing",
                "10/10/2020",
                "10/17/2020");


        // add all employees where department is marketing to the landing page project
        List<Employee_> marketingTeam = new ArrayList<>();
        for(Employee_ employee : employees) {
            if( employee.getDepartment().getName().equals("Marketing")) {
                marketingTeam.add(employee);
            }
        }
        landingPage.setTeamMembers(marketingTeam);

        projects.put(landingPage.getName(),landingPage);
    }

    /**
     * Print out each project in the collection.
     */
    private void printProjectsReport() {
        System.out.println("\n------------- PROJECTS ------------------------------");
//        for(Map.Entry<String, Project_> project : projects.entrySet()) {
//            System.out.println(project.getValue().getName() + ": " + project.getValue().getTeamMembers().size());
//    }
        for (Project_ project : projects.values())
            System.out.println(project.getName() + ": " + project.getTeamMembers().size());
        }


    
    /**
     * Bonus challenge: Find a department by name.
     */
    private Department_ getDepartmentByName(String departmentName)
    {
        for (Department_ department : departments)
        {
            if (department.getName().equals(departmentName))
            {
                return department;
            }
        }
        return null;
    }
}
