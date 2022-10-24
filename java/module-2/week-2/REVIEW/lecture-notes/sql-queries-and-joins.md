# Week 2 Review - Database Fundamentals

**Note:** There is no lecture code for this week because all the examples are in the Bookmark Database project. If you need more straightforward examples to address student questions, you can use this week's tutorials.

## Primary objectives

- Define primary and foreign key and explain their use in a relational database
- **Define common database relationships, like one-to-many, many-to-many and one-to-one**
- **Create statements that use the JOIN clause to select data from more than one table**
- Describe the use of INNER JOIN, OUTER JOIN, RIGHT JOIN and LEFT JOIN
- Use subqueries to select information from more than one table
- Use an INSERT statement to add a row to a database
- Use an UPDATE statement to modify a row in a database
- Use a DELETE statement to delete rows from a database
- Use transactions to group statements into atomic operations
- Test WHERE clauses in UPDATE and DELETE before running them on a database
- Define constraints on data using features of the database to verify integrity of the data and referential integrity
- Define normalization in context of a relational database
- **Use CREATE statements to define the tables of a new database**
- Use constraint statements to ensure data integrity of a database
- Describe how to use database user accounts and permissions to limit access to a database, its tables, and actions

## Objective 1: Define common database relationships, like one-to-many, many-to-many and one-to-one

### Opening

Ask students to identify the three types of relationships and offer an example of each type. Help them develop examples by offering the prompt of a school (or a different example of your choosing). You might use:

* a one-to-one relationship (teacher to classroom)
* a one-to-many relationship (teacher to student)
* a many-to-many relationship (student to club) 

Discuss how databases manage their relationships using keys to ensure **referential integrity**.

Some common difficulties students have include:
* knowing whether a real-world relationship is one-to-many or many-to-many
* determining which table in a relationship contains the foreign key
* understanding how referential integrity impacts the order of `INSERT` or `DELETE` statements
* using a linking table to enforce a many-to-many relationship

### Example

Reference the ERD from the Bookmark Database project and have students describe the nature of its relationships.

Draw attention to the following points:
* the one-to-many relationship between users and bookmarks
* the many-to-many relationship between bookmarks and tags
* the **crow's foot notation** illustrating the relationship
* the primary and foreign keys in each relationship and how they maintain referential integrity
* the `bookmark_tag` table **links** two tables together to create a many-to-many relationship

You might ask some of the following questions:
* what happens if multiple rows in the `bookmark` table have the same `user_id`
* what happens if multiple rows in the `bookmark_tag` table have the same `bookmark_id` or `tag_id`
* why it isn't possible for two rows in the `bookmark_tag` table to have the same `bookmark_id` and `tag_id`
* what challenges would occur if the `bookmark` table had a column for tags

If students ask about one-to-one relationships, you can explain that most are best modeled as attributes within a single table. 

### Next steps

As an additional challenge, place students into small working groups and ask them to model out how they'd modify the Bookmark Database to support a new requirement, community groups. Give them these requirements:
1. A community has a name and a moderator, who is a user.
2. Users belong to communities.
3. A user can belong to many communities and a community can have many users.

## Objective 2: Create statements that use the JOIN clause to select data from more than one table

### Opening

Explain to students that while relationships are best managed over multiple tables, they'll often need to show it together. `JOIN` statements make it possible to access values from multiple tables.

Some common mistakes students experience when working with `JOIN`:
* `JOIN` and `INNER JOIN` are the same
* how `NULL` works when it appears as a foreign key value in an `INNER JOIN`
* the difference between a `LEFT/RIGHT JOIN` join and an `INNER JOIN`
* remembering that the table in the `FROM` clause is the left-side table
* the difference between a subquery and a join

### Example

Show students the ERD of the Bookmark Database and ask them to help you write some of the following queries:
1. List all bookmarks (title and URL) along with the user who saved them.
2. List all bookmarks (title and URL) with a *Technology* tag.
3. List each user and the number of bookmarks that they have.
4. List each tags and the number of bookmarks associated with it.

For the last two some students may suggest using an inner join which only returns results for users who have bookmarked a site or have a tag associated to them. If they do, remind them that this is a common problem most developers have happen.

Either way, be sure to show the query using a left join and an inner join so that they can see the difference.

### Next steps

The database includes opportunities for other types of queries that can leverage past learning objectives.

Ask students how they'd write a query that:
* lists the names of users with no bookmarks
* lists the tags with no bookmarks associated with them

To do this, they'll likely need to use a subquery.

## Objective 3: Use CREATE statements to define the tables of a new database

### Opening

While tools such as pgAdmin4 can support the process of table creation, students need to know how to write scripts to generate tables. They'll need to know this so that they can quickly rebuild their database or share it with a colleague.

Review the terms **Data Definition Language (DDL)** and **Data Manipulation Language (DML)**, as students may encounter these during the interview process.

* DDL includes the creation and manipulation of structures of a database.
* DML is syntax used to access, retrieve, or modify data from the database.

### Example

Before proceeding, acknowledge that students are less likely to write `CREATE TABLE` or `ALTER TABLE` statements. They're more often writing `SELECT`, `INSERT`, `UPDATE`, or `DELETE` statements. It's acceptable to use a reference when writing DDL.

Open the `BookmarkDB.sql` file and walk through each of the `CREATE TABLE` statements. You can ask students some of the following questions:
* why is `serial` useful for primary key columns
* why might `varchar` be more prevalent instead of `char`
* what might happen if the data size is too small
* what constraints are on each table and why is it important to define them

### Next steps

Revisit the challenge from the first objective and have the class guide you through the implementation of the new community requirement.

You can ask different students to provide you with the `CREATE TABLE` statements needed for a `community` table and a `community_app_user` table (to reflect members).

Once you've written `CREATE TABLE` statements you can have the class participate by suggesting various `INSERT` statements to populate the two tables.