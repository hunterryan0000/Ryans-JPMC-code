# Week 7 Review - Server-side APIs (Parts 1 & 2)

## Primary objectives

- **Describe the MVC pattern and why programmers use it**
- Describe the purpose and scope of the Spring Boot / ASP.NET Web API framework
- Run a REST API locally and connect to it with Postman or another client application
- **Understand the high-level architectural elements of the REST architecture**
  - **Dependency injection**
  - **Model binding**
- Explain CRUD in terms of HTTP methods and APIs
- Perform request validation and handle errors in back-end code.
- Implement a RESTful web service with full CRUD features, conforming to REST best practices

> Note: One of the biggest challenges with this content is that students struggle with understanding that the Spring framework does most of the work behind the scenes.

## Objective 1: Describe the MVC pattern and why programmers use it

> Note: As an interview question, students are often asked to describe and provide examples of MVC.

### Opening 
The MVC design pattern gets widely used in software development. In large applications it's important to separate code into loosely coupled components to keep the code clean and maintainable. The model represents the data, the view is the format or presentation of that data, while a controller is a mediator facilitating communication between a model and a view. 

Use some analogies to help illustrate this concept. The reading uses Twitter as an example:

- A single tweet or a list of tweets is the model.
- A Twitter timeline, a single tweet page, and a Twitter thread are different ways to display model data.
- If you ask the Twitter API for a list of tweets, the controller returns the data in JSON format. It's up to each view to display the data.


### Example

Use the Module 2 Bookmark Manager sample application, focusing on the server-side REST API code, to illustrate the MVC pattern. The Tag and User classes provide the most straightforward examples.

### Next Steps

While the main focus of this week is on the REST API, the MVC pattern is also used in the command line applications for both Module 1 and Module 2. To broaden the discussion, you could compare and contrast how MVC is used in a different context - a command-line application. 

It's probably sufficient to use one of these applications as an example, but they both follow this pattern similarly. The module 1 and module 2 sample command-line client applications share a similar structure:

| Module 1                                          | Module 2 |
|:--------------------------------------------------|:----------------------------------------|
| com.techelevator.vee                              | com.techelevator.bookmark               |
|   - Application.java                              |   - Application.java                   |
|   - **VacationExpenseEstimatorController.java**   |   - **BookmarkAdminController.java**   |
|   - **VacationExpenseEstimatorView.java**         |   - **BookmarkAdminView.java**         |
|   - **model** (package)                           |   - **model** (package)                |
|                                                    |  - services (package)                 |

The model classes define the objects (data) used by the application. The *View* class gives user feedback and gets user input. The *Controller* class manages the program flow, passing data to the view to display.

In module 2, the service classes support the controller by facilitating communication with the server.

## Objective 2: Understand dependency injection

### Opening

Dependency Injection is a design pattern that promotes creating dependent objects outside of a class and then *injecting* them in. This allows the class to focus on *what* the dependent classes do - expressed through an interface, not *how* they do it - determined by the implementation. 

Note: The class learned about interfaces in Module 1. This is an opportunity to review and reinforce the value they provide.

Some points where misconceptions and confusion arise with this topic include:
- Understanding what's wrong with instantiating dependencies in a class.
- Identifying when to inject a dependency and when it's okay just to create an instance within the class. 
- Understanding where instances get created and how the specific instance type was chosen. **This is especially true in terms of injection performed by the Spring framework.**
- How to recognize and troubleshoot errors from dependency injection.
- Students may bump into other dependency injection methods, specifically Spring's `@Autowired` annotation for property injection. Students may not understand this is a different pattern and mix the approaches.


### Example

While the current focus is on REST APIs, it may be helpful to first show dependency injection outside of Spring. This can facilitate discussion around:
- what are class dependencies 
- the code mechanics - both the constructor and the code that calls it passing in the dependencies.

Both the Module 1 and 2 sample applications have examples of constructor dependency injection in the command line applications: 

- In the Bookmark Manager admin client (Module 2), the `BookmarkAdminController` constructor has dependencies on both the REST API base URL and a `BasicConsole`. The `Application` class creates these dependencies and calls the `BookmarkAdminController` constructor. 
- Similarly, the Module 1 Vacation Expense Estimator `VacationExpenseEstimatorController` has dependencies on both a `BasicConsole` class and a `BasicFileStorage` class. 

The Spring framework supports dependency injection in various ways, but the preferred method is constructor injection, as it's both more clear for instruction and widely preferred in the industry. Dependencies get passed in as arguments to the constructor and resolved by the Spring framework at runtime. 

The Bookmark Manager Server project contains good examples in the `TagController` and `UserController` classes. These classes follow the pattern students saw in the reading and exercises - a DAO class injected into the controller class constructor. 

> Make sure to point out the `@Component` annotation on the injected class. Spring looks for *annotated* classes of the correct type to fulfill the dependency. Students frequently miss adding the annotation in the DAO implementation classes. 

### Next steps
 
Some options to further the discussion include:

- Troubleshooting - Remove the `@Component` annotation from one of the injected classes and walk through identifying the problem. 
- Property injection using the `@Autowired` - While the curriculum covers constructor injection, students may find many examples and references that use this pattern. It's also used in the unit test setup for exercises and the sample application. There is no reason to discuss this in-depth, but it can be helpful to clarify this as an alternate approach. - not to get mixed together.
- Service layer - It's often preferred to keep the controller layer as more of a pass-through, splitting business logic into a service layer class. The Bookmark Manager sample application has an example of this in the `BookmarkController`, which uses a `BookmarkService` class to handle the more complex business rules around bookmarks. This is also a good way to show that any class can get injected as a dependency. It doesn't need to be a DAO class.


## Objective 3: Understand model binding 

### Opening

Model binding (or data binding) connects Java objects to non-object content. It has a specific focus on format and data types. 

For example, you need to bind the Java object model properties to database table columns to store Java objects in a database. (This is what an ORM would do, similar to the *mapRowToObject* methods in the JDBC DAO classes.) 

In a REST API, the binding is typically between Java objects and JSON. Within the Spring framework, the Jackson library handles the binding between Java objects and JSON. 

Some common difficulties around model binding include:
- Understanding how the Spring framework translates between Java objects and JSON
  - Handling differences in naming between Java and JSON property names
  - Correctly matching types between Java and JSON 
- Handling nested objects and arrays
- Working with Date types can be particularly tricky.

### Example

Look at examples of JSON serialized from Java model classes in the Bookmark Manager sample application. This can be done by using the Postman collection to make requests to the API.

Focus on the translation of different data types. For example, from the `/login` endpoint, the `LoginResponseDto` includes:
- a `String` token 
- a `User` nested object which includes:
  - an int for the id
  - a string for the username
  - a nested array of authorities
  - Note that the password isn't included in the JSON due to the `@JsonIgnore` annotation

Dates are also very common and are often a source of issues in data binding. The `Bookmark` class contains a `LocalDate`. Show that this is a formatted date string in JSON. 

### Next steps

Validation is a natural progression from discussing the model. Students generally don't struggle with the mechanics of adding the annotations within the model class. However, understanding when and how the validation runs can be less clear. Validation annotations don't prevent invalid values from getting set in model objects. The Validation Exceptions get thrown later when the validation process runs. 

In a REST Controller handler method, validation gets triggered by the `@Valid` annotation on the method arguments. This causes validation to run *after* the model class gets created but *before* the object gets passed into the controller handler method. 

The `Bookmark` model class is the only class in the Bookmark Manager project that uses validation annotations. The `BookmarkController` class methods `addBookmark` and `removeBookmark` perform validation on the body using `@Valid`.

