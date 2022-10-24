# Week 5 Group Code Review

**Note:** There are no planned exercises this day because the students didn't have new materials in the LMS.

## Primary objectives

- Review the significance of code reviews
- Code review individual projects

## Objective 1: Review the significance of code reviews

### Opening

It's been about eight weeks since the last code review so students can benefit from a refresher on code reviews. Open the discussion about the significance code reviews provide to professional software development teams. You can ask students to share specific things they've learned from one-on-one code reviews or by working with each other.

Some examples they might cite include:
* spotting issues they didn't see in their code
* improving the readability of their code 
* sharing working knowledge about their code with someone else
* learning something new from reading someone else's code

Let them know that it's okay to feel code reviews are intimidating but reinforce how important they're to their professional development and to the collective work of the team:
* mistakes get caught before production
* they and the team can collectively share responsibility for the code
* they can see and have code reviewed by a senior developer

## Objective 2: Code review individual projects

### Opening

Use the remaining class time to help students experience receiving and giving code reviews on their projects. Students implemented the following requirements on this independent project:

1. Implement a `ProductDao` interface to support product administration features (list products, view details, add, modify, and remove a product).
2. Implement a `SaleDao` interface to support sales administration features (list sales orders by customer and product, ship a sales order, and cancel a sales order)
3. Implement a `LineItemDao` interface to view the details of a sales order.

### Code review

> Note: Past code reviews focused on demonstrating the application's features. You can use this code review to get students to *talk about their code.*

Conduct a series of short code reviews with the class:

1. Ask someone in the class to share their screen and demonstrate a feature of their application.
2. After demonstrating the feature, ask the student to switch to the IDE and explain the code behind that feature.
3. Pause and give everyone three to four minutes to read the code. Ask the class to write down any comments that they may have.
4. Provide one positive and one constructive piece of feedback about the code.
5. Ask one other student in the class to share a comment they observed. 

> Note: If you notice that your class or some students have difficulty coming up with meaningful critiques, tell them to ask questions. Then they get to learn something new as a reviewer.

### Next steps

Have students split up into groups of three or four and take turns demonstrating a feature in their code while the rest of the group asks questions or provides feedback. Before you break into groups, tell the students they can use some of the following prompts if they're not sure what to look for:

* Are there instances where the code is susceptible to SQL Injection?
* How did they remove both the sale and line items at once?
* How were nullable columns handled?
* How did they get products that didn't have sales?
* Does the code follow a consistent style?
* Are there parts that might benefit from better variable names to make it more readable?
* Are there large blocks of commented-out code?
* What's one thing you learned while reading this code?