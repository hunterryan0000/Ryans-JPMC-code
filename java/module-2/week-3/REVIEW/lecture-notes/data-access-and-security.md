# Week 3 Review - Data access objects and data security

## Primary objectives

* **Define Data Access Object and explain why it's good for decoupling code**
* Write a DAO for accessing data in a database
* Explain CRUD in terms of DAO
* Identify which code belongs in a DAO (and which doesn't)
* **Describe the SQL injection vulnerability and the process of exploiting it in vulnerable code**
* **Write code in Java that implements data access in a safe way**
* Compare and contrast the features of hashing and encryption for purposes of securing data


## Objective 1: Define Data Access Object and explain why it's good for decoupling code

### Opening

The students have learned about storing and retrieving data with databases. Many applications use a database for data storage. So how does one access a database from code? There are many different ways to interact with a database in code, but one common way is the **DAO (Data Access Object) pattern**.

Explain to the students that the DAO pattern lets developers decouple data persistence code from the rest of the application. "Data persistence code" refers to the code that interacts with the SQL database, or with any means of saving information while the application isn't running. For example, a program could use the DAO pattern to read from and write to files stored on the hard drive.

#### Models and interfaces

Explain to the students that there are two key components of the DAO pattern—models and interfaces.
> Note: technically there's a third component, the implementation, but that's covered later in this lecture.

Models are *plain old Java object* (POJO) classes like those that the students have been working with in the curriculum—a class with properties or members.

Interfaces define the public methods that DAO classes must implement. Remind students that interfaces don't have method logic—they're a "contract." When a class implements an interface, it agrees to that contract, committing to providing definitions to the methods. By using interfaces, you decouple the code specific to a particular data storage mechanism, like a database or file, from the rest of the application. Your other application code "doesn't need to know" what data storage mechanism a DAO *class* uses, just that there's a DAO *interface* that provides data access methods.

Some common difficulties that students have with understanding DAOs:

* The importance or necessity of interfaces with DAOs.
* Understanding how the DAO pattern facilitates testing.
* Keeping acronyms straight and knowing how they fit together (DAO, JDBC, CRUD).

### Example

Start with opening the `Tag` class in the `com.techelevator.bookmark.model` package. The `Tag` class looks like a regular Java class: private members, public getters/setters, and constructors. Students might recognize the two members—`id` and `name`—from the two fields in the `tag` table—`tag_id` and `name`. This is the model.

Next open the `TagDao` interface in the `com.techelevator.bookmark.dao` package. Show the students that some of the methods use the `Tag` class as a parameter or return type. Reiterate that interfaces don't contain method bodies, it's up to the class implementing the interface to define the method logic.

> Note: don't open the implementation class `JdbcTagDao` yet, but show it later in the lecture. Not showing the implementation right away may help reinforce the importance of the interface.

Point out that most DAOs usually implement some or all of the four CRUD functions (Create, Retrieve, Update, Delete). In the `TagDao` interface, you see:

* One "Create" method—`create()`
* Two "Retrieve" methods—`getAll()` and `getById()`
* No "Update" method—it's not always necessary to implement every CRUD method
* One "Delete" method—`delete()`

Open `TagController` in the `com.techelevator.bookmark.controller` package. Show the students that the `tagDao` variable is of type `TagDao`, which is the interface. Each method of `TagController` contains a call to a method of `tagDao`. This exemplifies the "decoupled" code mentioned earlier—by writing your code using the interface, it doesn't matter what the underlying data storage is, whether a database, a file, or something else.

> Note: you don't need to go into any of the controller code and annotations. If students have questions about the code besides `tagDao`, tell them you'll cover it in a future lecture.

### Next steps

Explore `BookmarkDao` or `UserDao` interfaces with the students. Note the similarities and differences with `TagDao`. These models, slightly more complex than `Tag`, may prove a helpful extension of today's lesson.


## Objective 2: Describe the SQL injection vulnerability and the process of exploiting it in vulnerable code

### Opening

Begin by recapping string concatenation in Java:

```java
String greeting = "Hello " + "world!"
```

If you were to incorporate user input, or any other variable, it would look like:

```java
String name = "Robert"; // user input
String greeting = "Hello " + name + "!";
```

So it may be natural to think you can concatenate strings to form SQL statements in Java:

```java
String username = "rob23"; // user input
String sql = "SELECT * FROM app_user WHERE username = '" + username + "';";
// sql -> "SELECT * FROM app_user WHERE username = 'rob23';"
```

Which is okay until someone comes along and enters not a username, but a certain string of characters:

```java
String username = "' OR 1=1; --"; // user input
String sql = "SELECT * FROM app_user WHERE username = '" + username + "';";
// sql -> "SELECT * FROM app_user WHERE username = '' OR 1=1; --';"
```

When the database runs that statement, it returns data where the username is an empty string (`''`) or where `1=1`. Since `1=1` is true in all cases, the statement returns all the rows in the table. The `--` is the beginning of a SQL comment, so the server doesn't run anything after the `--`.

If a malicious user were to figure out the names of your tables, perhaps through error messages leaked to the front-end, they could also destroy your data:

```java
String username = "'; DROP TABLE app_user;--"; // user input
String sql = "SELECT * FROM app_user WHERE username = '" + username + "';";
// sql -> "SELECT * FROM app_user WHERE username = ''; DROP TABLE app_user;--';"
```

The server would first run the `SELECT` statement, which doesn't return anything, then the `DROP TABLE` statement, which deletes the table and data within.

### Example

Go through the SQL injection demo at https://www.hacksplaining.com/exercises/sql-injection. It gives you a step-by-step demonstration of SQL injection with an example web login form. It also provides "server logs" that show what the server might see, as well as how the concatenated SQL string forms as you type.

### Next steps

Discuss with the students thoughts they may have on ways to mitigate SQL injection issues. Some answers may be outside the scope of the curriculum—such as using stored procedures or establishing a database user with limited permissions. Try to steer the conversation towards parameterized queries, which is part of the next learning objective.


## Objective 3: Write code in Java that implements data access in a safe way

### Opening

Now it's time to show the students the actual implementation of data access. While parameterized queries is your primary focus, you'll also want to mention the use of the `RETURNING` clause, looping through a `SqlRowSet`, and mapping a SQL record to a Java object.

Some common difficulties student experience:

* Using the `RETURNING` clause to get the id of a new record.
* Forgetting to call `next()` before reading from `SqlRowSet`.
* Converting SQL data types to Java data types, like `LocalDate`.

### Example

Continuing with tags, show the students the `JdbcTagDao` class in the `com.techelevator.bookmark.dao` package.

Start by identifying the class that implements the `TagDao` interface you looked at earlier. The name `JdbcTagDao` reflects this is the JDBC implementation of the `TagDao` interface.

You may also want to point out the private `JdbcTemplate` variable declared, and its initialization in the `JdbcTagDao` constructor.

Work your way through the methods, noting these points along the way:

* `getAll()`: multiple rows (looping through row set), no parameters
* `getById()`: single row, single parameter
* `create()`: `INSERT` statement, single parameter, use of `RETURNING`, use of `jdbcTemplate.queryForObject()`
* `delete()`: multiple statements, two parameters with the same value, use of `jdbcTemplate.update()`

You'll want to also look at `mapRowToTag()` when discussing one of the `get` methods or after reviewing all the other methods. This DAO only has `getInt()` and `getString()`, but if you want more data types, in particular `LocalDate`, look at `mapRowToBookmark()` in `JdbcBookmarkDao`.

### Next steps

You can review the slightly more complex methods in `JdbcBookmarkDao` and `JdbcUserDao`. The `create()` and `addTagToBookmark()` methods from `JdbcBookmarkDao` offer helpful examples of an `INSERT` statement with more than one parameter.

Alternatively, you can have the students help you write create, update, or delete methods for `JdbcSaleDao` or `JdbcCustomerDao` of the "Data Access and DAO" tutorial.
