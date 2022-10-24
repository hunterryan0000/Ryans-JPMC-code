# Week 4 Review - DAO Testing

## Primary objectives

- **Understand the difference between unit tests and integration tests.**
- **Arrange a minimal data set to use in a DAO integration test.**
- **Write, execute, and interpret results of DAO Integration tests.**
- Understand and avoid common pitfalls of writing integration tests that connect to a datastore

## Objective 1: Understand the difference between unit tests and integration tests

### Opening

An integration test is a test that interacts with another system, such as a file store or database. A unit test is a self-contained test that has no external dependencies. The key difference is that unit tests do not have dependencies on outside data or systems. 

Review with students [the similarities and differences between unit & integration tests](https://lms.techelevator.com/content_link/gitlab.com/te-curriculum/data-persistence-using-spring-jdbc-lms/02_DAO_Testing/02-integration-testing.md). 

| Note: This topic was also briefly introduced earlier with File IO in module 1, week 8. 

Some common points of confusion with this topic might be:

- Identifying what is and what isn't an external dependency. 
- It may seem to students that class variables and method arguments are external dependencies, as they get values from *outside* the method itself.
- Students may feel overwhelmed by understanding how to test everything. Clarify that Tech Elevator only expects students to write integration tests specifically for testing DAOs.

### Example

Share examples of both unit tests and integration tests. Call attention to how unit tests are self-contained but integration tests have external dependencies. Start by showing the code that requires testing, identifying the outside dependency. 

Good *unit test* examples include: 

- The `getTotalCost()` method from the `MealPlan` class from the Vacation Expense Estimator (Module 1 sample application). This method performs a calculation using internal class properties. It has no dependence on outside system data.
- `Lucky13`, `StringBits` and other methods from the Module 1 Week 7 exercises. Since the method arguments are values passed into the method directly, these also have no outside dependencies. 


Good *integration test* examples include:

- The `TextFileStorage` class methods from the Vacation Expense Estimator (Module 1 sample application). These methods utilize the file system to read and write to files. The tests manage and control for the file system dependency by creating and using files specifically for testing. This allows the tests to know and rely on the location, existence, and data in the files. 
- The `VacationExpenseEstimatorView` class (also from Vacation Expense Estimator). `VacationExpenseEstimatorView` contains methods which display and prompt for data using the console. Here the tests utilize a `ConsoleMock` class to manage that dependency. 
- The Module 2 Bookmark Manager project also contains DAO tests, which are the main focus for this week. The `JdbcTagDao` is the most straightforward example and supports full CRUD. Here the dependency is on a database, and the tests handle that dependency by using an in-memory database for the tests. 

> Note: These examples all have tests provided, but at this point avoid diving too deeply into the setup for various integration tests. Focus on identifying where a dependency exists that requires integration testing. 

### Next steps
 
There are many ways to set up the conditions of the test and the test data. These vary based on the type of dependency, but with any approach, the tests must be able to predict exactly how the dependency must behave and respond. 

When testing DAOs, the dependency is on a database. One approach used to handle this is to create a database designed specifically for testing, populated with a set of mock data. Tech Elevator uses this DAO testing approach repeatedly throughout the program. Though the course materials always provide the proper setup, it may prove helpful to walk students through the process.

The Module 2 Bookmark Manager sample application's server project provides an example of this. The `TestingDatabaseConfig` class sets up the in-memory database, running a SQL script to create tables and insert sample data. All of the DAO test classes extend the `BaseDaoTests` class. This makes the test datasource available as a class instance variable and sets up an `@After` method to rollback, or reset, any changes to the database after each test method runs.


## Objective 2: Arrange a minimal data set to use in a DAO integration test

### Opening

Some common difficulties with test data preparation are:

- Knowing how much data is enough
- Providing enough variety in the data to meet test requirements
- Adding the data in a way that allows verification of the sort order

### Example

The `JdbcTagDaoTest` class contains an example of setting up data for testing. Point out that the data set up as constants in the `JdbcTagDaoTest` class matches the data inserted in the `test-data.sql` file. The test data set is intentionally small and simple. It contains only 4 tags, including one that lacks any associations to user bookmarks. This is a small number, but it is enough to allow testing of the sort order, and getting user bookmarks both with and without tag associations. 

Things to note on the test data set:
- The tests make no attempt to guess real world ids or values
- When setting up dependencies between tables it's helpful to choose specific ids for inserted values. 
- Starting id for different tables at different numeric ranges makes it easier to read and understand.
- The data provides a create date for the bookmarks, even though the table script could default the value. This is because it's difficult to verify an unknown value, and for these values it's not important that the dates are recent.
- The data allows for testing of sort order. For example, the tag data is not both ascending by tag id and alphabetical by name. 

### Next steps

The `JdbcBookmarkDaoTest` class provides opportunities to talk about testing and setting up data with table joins. 

Users and Bookmarks have a one-to-many relationship. The test data contains three users, two with bookmarks and one without. The method `getByUserId_returns_correct_list_sorted_by_title()` tests the `getByUser()` method from `JdbcBookmarkDao`. It checks that each user id generates the appropriate list of bookmarks, and confirms that the id of a user with zero bookmarks correctly gets an empty List.

Bookmarks and Tags have a many-to-many relationship. The test data includes bookmarks that have tags as well as those that don't. The tests for all of the *get* methods verify that both types of bookmarks are in the results. To support this, the `SELECT` statements in the `JdbcBookmarkDao` use a `LEFT JOIN` to the `bookmark_tag` and `tag` tables. To demonstrate this you might remove the `LEFT` from the join in the `getByUserId()` method. This change causes the `getByUserId_returns_correct_list_sorted_by_title()` method to fail. You might also modify the `test-data.sql` and `JdbcBookmarkDaoTest` constants to have tag associations for all bookmarks and then show that the tests incorrectly pass. 


## Objective 3: Write, execute, and interpret results of DAO Integration tests

### Opening

Review the three A's of unit testing: Arrange, Act, Assert. Integration testing also follows this pattern. However, in integration testing the  Arrange step may be complicated by the dependence on external systems or data. The previous objective discussed test data setup, which is the main part of Arrange for DAO tests. Therefore in Objective 3 focus your attention on Act and Assert.

Some common points of confusion are:
- What to assert
- When to break tests into multiple methods
- Understanding why a test has failed
- Determining whether the test setup or the code caused a test to fail. 

### Example

The Module 2 Bookmark Manager classes `JdbcTagDao` and `JdbcTagDaoTest` contain the most straight forward examples of integration tests. Select a few methods to review the pattern of Arrange, Act, Assert. 

Review common Assert methods, highlighting the arguments passed in each method call. Encourage students to add messages to Assert statements that clarify which part of a test has failed and why. You might also cause some tests to fail to show how messaging from the test runner helps identify the cause of a failure. Note that a failed test doesn't always indicate flawed code. For example, students might alter the test data sql script without also modifying the corresponding data constants in the test code, causing tests to fail. 


### Next steps

Discussing common patterns for each type of DAO method may help students determine what type of things to test and assert. 

__Read__ methods return data from the database, so familiarity with the test data proves critical when verifying the correctness of the results.
- Make sure to test for correct subsets of results. It's common to be able to get all items, or a single item by id. But requirements vary. For example the bookmark class requires methods that retrieve all public bookmarks, all flagged bookmarks, and all bookmarks associated with a specific user. 
- Test for no results found. Common patterns are to return an empty array or List when many results are possible, or null for a single result. If results are always expected, another pattern might be to throw an exception.
- Verify the correct order when requirements call for sorting by a particular value. 

__Create__ and __Update__ methods typically return an id or an object representing the new data. 
- If the method returns an id, confirm that the id retrieves the correct data from the database. 
- If the method returns an object, check that the object has an id and that its values are as expected. Then confirm that the  retrieves the correct data from the database. This could be in one test or broken out into two different tests. 
- If objects receive default values confirm they are the values expected. 
- Test constraints with bad data, verifying that the DAO method fails. 

__Delete__ methods typically return void and throw an exception on failure. 
- Make sure that a deleted item is no longer retrievable.
- Confirm the correct response to a delete request for an item that's not in the database. 
- Test deletes with associations.  
    - Sometimes the requirements allow such a delete, also removing any associated data. For example, the Bookmark Manager permits a bookmark's deletion even if it has associated tags. It first removes the tag associations, thus ensuring the database maintains referential integrity.
    - Other times associated data causes a delete request to fail. For example, the Bookmark Manager blocks  the deletion of any tag listed by existing Bookmarks. Testing under these circumstances requires the use of the `Assert.fail()` method and a `try/catch` block. 

This might also be a good opportunity to discuss how writing tests can help developers write better code.
