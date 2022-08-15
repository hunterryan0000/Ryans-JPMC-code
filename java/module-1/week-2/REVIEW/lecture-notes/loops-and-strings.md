# Week 2 Review - Loops and Strings

**Note:** As the instructor, familiarize yourself with both the provided examples listed for each objective and the relevant example code in the sample project (pointed out at the end of this document). Don't try to work through all of this material during class (there won't be time). Instead, get an idea of how your students are doing and what they're finding most difficult, and then use whichever pieces seem most helpful.

## Primary objectives

- Declare an array
- Get and set the values of array elements
- **Use for loops for iterative logic including sequentially processing values in an array**
- Increment and decrement values using shorthand assignment
- Describe the key features of objects and how they are used by computer programs to model real world problems
- Explain the difference between objects and classes
- Describe how objects are created and used
- Describe how objects are stored in the computer and how objects are different than primitives
- **Use methods of the String class for text processing and manipulation**
- Parse strings into numeric data types
- **Accept user input from `stdin`**


## Objective 1: Use for loops for iterative logic including sequentially processing values in an array

### Opening

A loop allows a program to do something repeatedly. This is sometimes referred to as iteration. Brainstorm a list of familiar activities that involve loops/iteration, such as:
* counting
* searching through a deck of cards
* sorting a stack of mail into piles
* iterating through traffic light states
* listening to new voicemail messages

Some common difficulties with loops and arrays are:
* thinking loops must always involve an array
* stopping before going out of bounds when iterating through an array
* confusing the iterator variable with the array element at that index

### Example

The `Example1` class has three methods (and tests) you can work together with the students to complete by using loops. The first one iterates without an array, the second involves a single array, and the third involves two arrays. This is also a good opportunity to demonstrate using the debugger to trace through loop execution.

### Next steps

Challenge students to modify the behavior of a loop in one of the example methods by making it count down instead of up, or using a `while` loop rather than a `for` loop.

## Objective 2: Use methods of the String class for text processing and manipulation

### Opening

Programs frequently have to work with text, and Java's `String` class includes many methods to facilitate this. Show students how to look up the documentation for the `String` class (for example, do a Google search for "Java SE String") and find the list of all the methods it includes.

Some common difficulties with strings are:
* confusing `char` primitives with single character strings
* understanding the difference between an empty string and null
* remembering to compare strings with the `equals()` method instead of `==`
* determining which indices to use with `substring()`
* remembering that invoking methods on strings won't change their state due to their immutability

### Example

The `Example2` class has three methods to complete with students using a variety of string methods. The third example is also an opportunity to reinforce the idea of iterative logic by iterating through strings. 

### Next steps

Regular expressions are too much to cover in depth, but you could introduce them. An alternative approach for the first method is to use the `matches()` method with a regexp.

Discuss refactoring the third method to move the repeated loop into a separate method.

There are more challenges like these at https://codingbat.com in the "String-1," "String-2," and "String-3" sections.

## Objective 3: Accept user input from `stdin`

### Opening

While most computer users don't use command-line programs these days, most developers still work with at least a few command-line based tools. For example, the students have already been using git from the command line. A command-line interface is also a quick way to provide basic interaction while working on a program that may later have a more advanced user interface. A command-line interface can send output to the user with just `System.out.println()`, but handling the user's input is more involved.

Tell the students about some command-line programs you're familiar with or have worked on, or show them a useful command-line program like JShell.

Some common difficulties with user input and command-line programs are:
* closing the `Scanner` attached to `System.in`
* using `Scanner` methods like `nextInt()` that don't move to the next line of input like `nextLine()`
* trying to split a string on a character that needs backslashes before it like `|`

### Example

The `DiscountCalculator` class is a complete command-line program. Demonstrate what it does (or have the students run it), and then walk through the code together. Make a change or two to the program such as:
* change the separator in the list of prices to something other than a space
* add some basic error handling (that doesn't involve exceptions) like re-prompting if nothing is input
* when prompting the user about continuing, allow more responses like capital "Y" or "yes" or "Yes"
* display individual discounted prices rather than only discounting the total
* refactor some piece of the program into a separate method

### Next steps

Discuss how to create a program to provide a command-line interface for one of the methods you worked on earlier. For example, a program that prompts the user for a list of numbers, passes them to the `halveAll()` method from `Example1`, and then displays the results. Students could work together to do that in breakout rooms or you could challenge them to do it independently after class, depending on time.

Encourage students to try writing more complex command-line programs during the coming week, like a rock-paper-scissors game or a program that can encode and decode using a "Caesar cipher" (https://en.wikipedia.org/wiki/Caesar_cipher).

## Reviewing with the Vacation Expense Estimator

You can find code examples to highlight in the sample application that relate to this week's learning objectives by searching for:
* `//Example: iterating through an array`
* `//Example: while loop`
* `//Example: string indices and substring`
* `//Example: string equality`

In their reading this week, students were also introduced to `BigDecimal` and `LocalDate` which are both used throughout the VEE. You can find examples by searching for:
* `//Example: primitive multiplication and BigDecimal multiplication`
* `//Example: BigDecimal addition`
* `//Example: LocalDate comparison`
* `//Example: counting days between LocalDates`
* `//Example: LocalDate formatting`

The `SystemInOutConsole` class has several examples of accepting user input (of a variety of data types, including `BigDecimal` and `LocalDate`). You could demonstrate those methods by temporarily replacing the contents of the `main()` method of the `Application` class with calls to `SystemInOutConsole` methods.
