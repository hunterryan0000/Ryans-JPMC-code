# Week 7 Review - Testing and Exceptions

## Primary objectives

- Describe the pros and cons of manual versus automated testing
- Describe the differences between Unit, Integration and Acceptance testing
- **Write, execute, and interpret results of Unit tests**
- Identify boundary cases in a piece of code and select the correct asserts and tests to test for errors around the boundary
- Throw exceptions from code to delegate errors to code that can handle it
- Describe scenarios where code can't handle an error it has found and where code can handle an error triggered in another part of the application
- **Explain the parts and uses of the `try-catch-finally` block**
- Create custom exceptions for control flow and error handling in specialized code
- **Write tests to test proper exception handling and throwing**


## Objective 1: Write, execute, and interpret results of Unit tests

### Opening

A big part of programming is finding and fixing problems. The compiler alerts you to problems with the syntax of your code, but it can't catch problems with the logic of your code. That's where unit testing comes in. Discuss the difference between errors in syntax and errors in logic, asking the students for several examples of each.

Some common difficulties with unit tests are:
* identifying edge cases to test
* structuring code in a way that makes it testable
* knowing which test code is part of the "arrange" step vs. the "act" step
* understanding the delta that's required when using `assertEquals()` with `double` values
* assertions involving arrays and objects rather than primitive values

### Example

The Vacation Expense Estimator project contains more than a hundred unit tests, so there are plenty you can use as examples. The tests for the classes under `com.techelevator.vee.model` are a good place to start.

Introduce an error into the `getTotalCost()` method of one of the model classes and demonstrate how the tests help you catch the problem.

Vacation activities can have a fixed cost, a cost per person, or both. The tests in the `ActivityTest` class currently test for the first two scenarios, but there's no test for an activity with both a fixed cost and a cost per person. You can discuss why the current tests are insufficient and work together to add that additional test.

### Next steps

Show students how to use the code coverage features of the IDE to find untested code, but make sure they understand the limitations of code coverage. A class with less than 100% of its methods tested may not be a problem, since very basic methods (like getters and setters) don't need tests. Also, code coverage can't identify issues like the inadequate testing of the `Activity` class you fixed earlier.

Discuss how interfaces are useful when testing. For example, because of the `BasicConsole` interface, the `VacationEstimatorViewTest` class can use the `ConsoleMock` for testing rather than actually writing to and reading from the console. The same approach is a possibility for testing the `VacationExpenseEstimatorController` (which currently doesn't have any tests), but it would require refactoring to add additional interfaces implemented by the view and storage.


## Objective 2: Explain the parts and uses of the `try-catch-finally` block

### Opening

As a programmer, you can't prevent every problem. When your program interacts with external entities (like a user, the operating system, or a network resource), you also have to consider the problems those entities can cause. Using `try` blocks to catch exceptions is a common way to handle those kind of errors. Show the students a diagram of a `try` block like this:

![try-catch-finally diagram](try-catch-finally.drawio.png)

Starting with the first diagram, ask students which colored blocks run in various scenarios:
* no exceptions
* an exception occurs in the blue block
* an exception occurs in the blue block and the yellow block contains a `return` statement

Then switch to the second diagram and discuss how those scenarios change with `finally` included.

Some common difficulties with exception handling are:
* wrapping too much code in a single `try` block
* mistakenly catching exceptions caused by errors within the program (bugs)
* putting catch blocks in the wrong order (a more general exception prior to a more specific one, for example)

### Example

You can find examples of try-catch blocks in the Vacation Expense Estimator by searching for:
* `//Example: try with one catch block`
* `//Example: try with two catch blocks`

It might be helpful to walk through those examples with the debugger. Make sure students understand where the exceptions are coming from. Discuss what's included in the try block and what the potential problems would be if the try block encompassed more of the code. For the example with multiple catch blocks, discuss if the order they're in makes a difference in this case (and in what situations it would matter).

### Next steps

Discuss checked vs. unchecked exceptions in Java. `FileStorageException` is a checked exception, while `BasicXmlException` is an unchecked exception. Point out the difference that makes in regard to `throws` clauses, etc.

## Objective 3: Write tests to test proper exception handling and throwing

### Opening

Normally, a method ends by returning a value, but it's also possible for a method to end by throwing an exception. A method doesn't have to throw an exception when it encounters an error. It might handle the error and return a value like -1 or null to indicate something went wrong. Discuss scenarios in which throwing an exception is the best option. For example, if you need to let the caller know an error occurred but the method doesn't return a value. Or, if a returned value would be ambiguous (does false indicate a valid boolean result or an error?).

When testing a method that's designed to throw an exception in certain cases, it's important to include tests to verify that behavior.

### Example

A commonly used exception in the Java standard library is `IllegalArgumentException`, which you can find an example of in the Vacation Expense Estimator by searching for `//Example: throwing an IllegalArgumentException`.

You can then find a test that checks for this exception by searching for `//Example: unit test expecting an exception to be thrown`. It looks like this test is missing the "assert" step of the "arrange-act-assert" pattern, so make sure the students understand that the `expected` parameter of the `@Test` annotation is filling that role in this case.

The `addElement()` method of the `BasicXmlBuilder` class can throw a `BasicXmlException` in certain situations. The `BasicXmlBuilderTest` class doesn't have any tests that check for that. You could work together with the students to add a test or two.

### Next steps

To go further with unit testing, you could discuss how to use unit tests for Test Driven Development. If there's time at the end of class, you could demonstrate using this approach for solving a coding kata or for making a modification to the Vacation Expense Estimator.
