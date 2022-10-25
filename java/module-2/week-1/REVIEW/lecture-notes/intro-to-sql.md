# Week 1 Review - Introduction to SQL

**Note:** Students studying the full-stack curriculum may find this more straightforward than learning Java. Tell students that although their work this week focuses exclusively on SQL, soon, they'll write Java code that uses SQL. Remind students to focus additional effort on remaining current with Java.

## Primary objectives

- Define what a Relational Database Management System is
- Use a GUI application to connect to a local database
- **Write and execute simple select statements using `SELECT`, `FROM`, and `WHERE`**
- Define the terms "table", "row" and "column" in relation to a database
- Look up metadata information about a database in the GUI application
- Use functions like `CONCAT` and `ABS` to perform simple operations on selected data
- **Use aggregate information using `GROUP BY` to group rows together**
- Use aggregate functions like `AVG`, `SUM`, `COUNT`, `MIN`, `MAX`
- **Use the `ORDER BY` clause to order results from the database**
- Use the `LIMIT` clause to limit the number of rows returned by the database


## Objective 1: Write and execute simple select statements using `SELECT`, `FROM`, and `WHERE`

### Opening

Introduce the module by tying some key terms from this unit together.

* Database management system (DBMS)
* Database client
* Database server
* Postgres
* Tables, rows, and columns
* SQL

Students may experience some of the following difficulties when first learning to work with databases:

* using new software for working with the DBMS, for example, pgAdmin
* importing and connecting to a new database
* choosing between `SELECT *` and `SELECT table.column_name`
* identifying partial matches with `LIKE` and `%`
* eliminating duplicate rows with `SELECT DISTINCT`

### Example

Introduce students to the Bookmark Manager sample application they'll study throughout the module. Without showing any code, demonstrate its features such as: 

* log in as a user
* view a list of public bookmarks
* review any flagged bookmarks
* manage a list of tags that users associate with a bookmark

**Note:** This application represents a sample administration interface and isn't the same one that users would use. They'll learn how to build one in the web application development module.

Focus on the database and display the ERD for the Bookmark Manager application. Discuss some of the relationships (without going deep into one-to-many or many-to-many) between entities, such as:

* users can have many bookmarks that they're associated with
* users may assign a bookmark many different tags
* users may use the same tag for many bookmarks

Using the `app_user` or `bookmark` table, ask students to engage and come up with the query syntax for the following examples:

* list the `username` of all users in an Admin role
* show the `username` and `user_id` of all users who don't have a display name
* list the unique `title` and `url` of all bookmarks
* list the `title` and `url` of all bookmarks with the word "recommend" in their description
* list the `title` and `url` of all bookmarks belonging to a user with a specific id (2, 3, or 4)

### Next steps

Challenge students with a gentle introduction to **subqueries**. Subqueries are often tricky for students, so ease in by first asking if anyone notices anything about the last query. Is it possible to get bookmarks knowing only the username?

Subqueries are more approachable when broken down:
* write a query to return the `user_id` for a given `username`
* write another query to return all bookmarks based on a hard-coded `user_id`, making sure to use `IN`
* combine the two queries to return bookmarks for a user with a given username

## Objective 2: Use the `ORDER BY` clause to order results from the database

### Opening

Ask students to think about how they see data represented in the real world and online. Some examples might include:

* an employee directory sorted alphabetically
* a social media timeline feed sorted in reverse chronological order
* an online shopping site sorted by price (least to greatest)
* a roster of baseball players sorted by batting average

Many DBMS display row data arbitrarily, and it often needs sorting. The `ORDER BY` clause allows you to dictate the arrangement of rows returned by a query.

Some common difficulties that students encounter with `ORDER BY` include:

* placement of the `ORDER BY` clause
* choosing between `ASC` and `DESC` when ordering strings or dates

### Example

While it can be unpredictable, you can try demonstrating that random order occurs by default with an unsorted query. 

Have students help you write the following queries:
* list all users in alphabetical order (with and without `ASC`)
* list all users in reverse alphabetical order
* list all bookmarks sorted by user id, then title

## Objective 3: Create aggregate information using `GROUP BY` to group rows together

### Opening

You can open by indicating that while detailed data gives a complete picture, many users often like to view the information in aggregate. Some examples might include:

* the average review rating per product
* the number of reviews per product
* the total number of products sold

In the bookmark database, this might be:

* the average number of bookmarks per user
* the total number of bookmarks per user

Some common difficulties that students have with `GROUP BY` queries include:

* placing the `GROUP BY` clause in the correct position
* knowing which columns to `GROUP BY`
* knowing which attributes to use inside the `COUNT` function

### Example

Using the bookmark database, ask students how they might group the data and show it in aggregate. Some examples might include:
* grouping by `url` to see the number of users per bookmark
* grouping by `user_id` to see the number of bookmarks per user 

> Note: Remember that JOIN is not covered, so you'll need to use just the `user_id` in these examples

Ask students to help you write queries that retrieve the following results:

* how many bookmarks exist in the database
* how many bookmarks each user has
* the average number of bookmarks a user has
* the smallest/largest number of bookmarks saved by any given user


### Next steps

If you feel students are in a good spot and want to keep going, you can show how `HAVING`. `HAVING` is a natural progression, although the curriculum doesn't cover it. 

The keyword `HAVING` filters the results of a `GROUP BY` clause, similar to `WHERE` in that it filters the results from a `SELECT` statement. 

It's probably best done with a demonstration. You can show:
* only users that have more than five bookmarks
    ```
    SELECT user_id, COUNT(*)
    FROM bookmark 
    GROUP BY user_id
    HAVING count(*) > 5;
    ```
* only bookmarks saved by two or more users
* only users with more bookmarks than the average user

