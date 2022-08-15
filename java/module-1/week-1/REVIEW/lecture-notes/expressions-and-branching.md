# Week 1 Review - Expressions and Branching

## Primary objectives

- Declare and assign values to variables
- **Write arithmetic expressions and understand how they're evaluated in code**
- Identify the right primitive data types to model data in a real-world domain
- Understand and use casting
- Build boolean expressions using arithmetic, comparison, and logical operators
- **Read and understand complex code expressions**
- **Write conditional code using boolean expressions**
- Understand the use and implications of code blocks and variable scope

## Objective 1: Write arithmetic expressions and understand how they're evaluated in code

### Opening

Arithmetic expressions are a fundamental building block for all kinds of programs. Brainstorm with the students to create a list of examples of everyday computer interactions that depend on arithmetic. For example:
* adding up the total price of your Amazon shopping cart
* counting how many followers someone has on Twitter
* determining the remaining runtime of a YouTube video
* statistics displayed about weather or sports
* calculating the position of moving objects in a video game

Some common difficulties with arithmetic expressions for students are:
* understanding integer division
* knowing what data type an expression evaluates to
* encountering imprecision when an expression evaluates to a `double`
* using the mod (`%`) operator

### Example

The main method of the `Example1` class in the lecture code includes five questions you can write arithmetic expressions to answer. Print out the values those expressions evaluate to and address areas where students need a better understanding.

### Next steps

Once students understand the evaluation of arithmetic expressions, you can challenge them to create expressions for:
* adding sales tax to a price
* determining a batting average based on hits and at-bats
* calculating someone's BMI, given their height and weight

## Objective 2: Read and understand complex code expressions

### Opening

Boolean expressions are another fundamental building block for all kinds of programs. Complex boolean expressions can involve arithmetic, comparison, and logical operators. Review logical operators by showing the truth table in the LMS, and then having students help you fill in a truth table with more complicated expressions like `!(A || B)`, `!A || !B`, `A ^ !B`, and `(A && B) || !A`.

Some common difficulties with complex code expressions for students are:
* trying to use 2 comparison operators with the same value (`a > b < c`)
* misplaced, extra, or not enough parentheses in expressions
* ordering expressions to take advantage of short-circuit operators

### Example

The main method of the `Example2` class generates a random number and then outputs the results of evaluating five boolean expressions that involve that number. For each one, work with the class to break down the expression and understand its evaluation, and finish the description of what it means about the random number when that expression is true. (The final expression is also an example of how a short-circuit operator helps avoid errors.)

### Next steps

Suggest some additional checks for the random number and have students work together in small groups to write boolean expressions for those.

Demonstrate adding the ternary operator to the expressions in the example code to output "yes" or "no" instead of "true" and "false."

## Objective 3: Write conditional code using boolean expressions

### Opening

You use boolean expressions in `if` statements to control whether or not a block of code runs. Work with the students to arrange a flowchart for a process like deciding if an bid has won an online auction, checking conditions such as:
* is the bid the highest bid?
* is the auction over?
* did the auction specify a reserve price? (and if so, did the bid exceed it?)

Some common difficulties with conditional code for students are:
* when to use `else if` vs. independent `if` statements
* checking conditions in the right order
* following a program's flow through nested `if` statements

### Example

The `Example3` class contains three methods that need conditional code added before the tests pass. Work with the students to complete the methods. In addition to conditional code, this is a good opportunity to address questions students have about methods, and possibly discuss the pros and cons of having a single `return` statement vs. multiple `return` statements in a method.

### Next steps

Challenge students to write a method that implements the process you created a flowchart for.

With an understanding of conditional code and boolean expressions, students can (individually or in pairs/groups) start trying basic code katas such as those found at https://codingbat.com/java/Logic-1.

## Introducing the Vacation Expense Estimator

The Vacation Expense Estimator is a complete sample application that demonstrates everything students learn in module 1. Students won't be able to understand much of it today, but you can show them how to run it so they can play with it during the coming week and learn how to interact with it as a user.

If you have time, there are examples in the code related to today's learning objectives that you can highlight. Find them by searching for these comments:
* `//Example: complex boolean expression`
* `//Example: independent if statements` (make sure students understand why not to use `else if` in this situation)
* `//Example: if, else if, else`
* `//Example: nested if statements`
