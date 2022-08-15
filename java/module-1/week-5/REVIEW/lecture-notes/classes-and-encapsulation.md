# Week 5 Review - Classes and Encapsulation

**Note:** There is no lecture code for this week because all the examples are in the Vacation Expense Estimator project. If you need more straightforward examples to address student questions, you can use this week's tutorials.

## Primary objectives

- Declare a new class
- **Describe the object oriented principle of Encapsulation**
- **Identify the key elements of a class declaration (name, member variables, constructors, methods)**
- Identify the components of a method signature
- Declare a method
- Declare a constructor
- **Use the private and public access modifiers appropriately to hide or expose elements of a class**


## Objective 1: Describe the object oriented principle of Encapsulation

### Opening

Technical interviewers often ask about the principles of Object Oriented Programming (OOP). Give students a quick introduction to them with brief, informal definitions like this:

- **encapsulation** - combining related methods and data and limiting access to them
- **inheritance** - deriving new classes from existing ones and arranging them into a hierarchy
- **polymorphism** - representing multiple types (classes) with a single symbol (variable)

Let them know that they'll learn more about inheritance and polymorphism in the coming week and that the focus today is on encapsulation.

### Example

Walk students through the structure of the Vacation Expense Estimator project without diving into the details of particular classes. Point out that the classes in the `com.techelevator.vee.model` package encapsulate various aspects of a vacation and that the classes in the `com.techelevator.util` package encapsulate general capabilities like reading and writing to files and parsing XML. The `Vacation` class, for example, can include the cost of lodging when calculating the total cost of the vacation without including any knowledge or details about how to calculate the cost of lodging because the `Lodging` class encapsulates those details.

### Next steps

Students are likely to encounter articles that list (or interviewers that ask about) **four** principles of OOP. When describing OOP using three principles, encapsulation includes the concepts of abstraction and information hiding. It's also common, however, to split off abstraction and list it as a fourth principle.

Encourage students to practice describing OOP principles in their own words rather than memorizing and repeating definitions.


## Objective 2: Identify the key elements of a class declaration

### Opening

Classes are a way to keep code organized. Encapsulating data and logic together makes it easier to maintain and extend complex programs. Ask students to list some reasons why writing a program as one giant method that does everything isn't a good idea. For example:
* variables become hard to keep track of
* too much to hold in your head at one time
* doesn't all fit on the screen
* changing how one thing works might accidentally break something else
* deeply nested if statements and loops become confusing
* difficult for multiple programmers to work on at the same time

Some common difficulties with the key elements of a class declaration are:
* knowing when to use `this`
* creating unnecessary variables for derived properties
* understanding static variables and methods

### Example

In the Vacation Expense Estimator project, open the `TravelGroup` class, and work with the students to identify its key elements. Some important points to note:
* let students know they'll learn about the `implements` clause in the coming week
* naming conventions: camel case for variables and methods and Pascal case for the class name
* each of the private member variables has a getter and setter
* there are two constructors for creating with default values vs. specified values
* `this` shows up whenever a parameter and member variable have the same name
* the `getTravelerCount()` method has no associated `travelerCount` member variable (it's a derived property)

You can find examples of a static variable and a static method by searching for:
* `//Example: declaring a constant`
* `//Example: calling a static method`

### Next steps

There are plenty more classes in the VEE that you could have students identify key elements of. You might also show them a class in the Java Standard Library such as `LocalDate` or `BigDecimal` (you can access the class definition in IntelliJ by right-clicking on a class name and selecting **Go To > Implementation**).


## Objective 3: Use the private and public access modifiers appropriately to hide or expose elements of a class

### Opening

Access modifiers are a way to implement the "information hiding" aspect of encapsulation. Make sure they understand that this isn't about security, and discuss why it isn't a good idea for every part of a program to be able to access every other part of a program. Without information hiding, a program quickly turns into a tangled web of dependencies (spaghetti code), which is a lot like writing a program as one giant method, with many of the same problems, like:
* variables become hard to keep track of
* changing how one thing works might accidentally break something else
* difficult for multiple programmers to work on at the same time

Some common difficulties with access modifiers are:
* getting into the habit of making everything public
* unintentionally using the default access modifier by forgetting to specify one
* mistakenly considering `static` or `final` to be access modifiers

### Example

Reinforce the meaning of `public` and `private` by pointing out various variables and methods with those access modifiers in the Vacation Expense Estimator and then asking students if you can access that variable or call that method from various other places in the program.

In the `Vacation` class, point out the `Map` used for activities, which is private. Note that there is no associated getter or setter, which keeps that member variable hidden from every other class in the program. Since none of them can access that `Map`, you can be confident they don't contain code that depends on it. Instead, they work with activities through the public methods `addActivity()`, `removeActivity()`, `hasActivityWithDescription()`, and `getActivityDescriptions()`. This means you could replace the `Map` with a completely different data structure (updating those methods accordingly), and wouldn't have to make changes anywhere outside the class.

### Next steps

Advise students that when they're creating their own classes, it's better to default to using `private` and then switch to `public` when necessary rather than using `public` by default for everything.

You might also mention that `public` and `private` are the most commonly used access modifiers, but aren't the only ones, and that they'll be learning about the other two options in the coming week.
