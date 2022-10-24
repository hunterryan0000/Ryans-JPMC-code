# Week 9 Group Code Review

**Note:** There are no planned exercises this day because the students didn't have new materials in the LMS.

## Primary objectives

- Review the significance of code reviews
- Code review individual projects

## Objective 1: Review the significance of code reviews

### Opening

Open the discussion about the significance code reviews provide to professional software development teams. You can ask students to share specific things they've learned from one-on-one code reviews or by working with each other.

Some examples they might cite include:
* spotting issues they didn't see in their code
* improving the readability of their code 
* ensure the code follows a standard
* sharing working knowledge about their code with someone else
* learning something new from reading someone else's code

Tell them that code reviews vary from team to team and project to project. Some happen synchronously, and others happen asynchronously. Some occur with peers, and others happen with lead developers.

## Objective 2: Code review individual projects

### Opening

Use the remaining class time to help students experience receiving and giving code reviews on their projects. Students demonstrated the following programming skills on this project:

* Using the DAO pattern to access data in a SQL database
* Writing code to get data from a REST API
* Implementing server-side code to create a RESTful API
* Using Auth to control access to REST API endpoints

### Code review

Conduct a series of short code reviews with the class:

1. Ask someone in the class to share their screen and walk the rest of the group through a user story. They should demonstrate their feature using Postman.
2. After demonstrating the feature, ask the student to switch to the IDE and explain the code behind that feature.
3. Pause and give everyone three to four minutes to read the code. Ask the class to write down any comments that they may have.
4. Provide one positive and one constructive piece of feedback about the code.
5. Ask one other student in the class to share a comment they observed. 

> Note: If you notice that your class or some students have difficulty coming up with meaningful critiques, tell them to ask questions. Then they get to learn something new as a reviewer.

### Next steps

Have students split up into groups of three or four and take turns demonstrating a feature in their code while the rest of the group asks questions or provides feedback. Before you break into groups, tell the students they can use some of the following prompts if they're not sure what to look for:

* Are the dependencies directly instantiated or handled through dependency injection?
* How was validation implemented?
* Are the correct HTTP status codes returned?
* Is the code that calls the Tax API self-contained?
* Is it possible to act on resources that don't belong to the user? Is it possible to manage another user's cart? 
* How did you model the shopping cart when there wasn't a table?
* How do you get all the information required for the user's cart?
* Are the queries implemented using parameters, or are they susceptible to SQL injection?
* Are there large blocks of commented-out code?
* What's one thing you learned while reading this code?