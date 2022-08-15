# Week 9 Group Code Review

**Note:** There are no planned exercises this day because the students didn't have new materials in the LMS.

## Primary objectives

- Review the significance of code reviews
- Code review individual projects

## Objective 1: Review the significance of code reviews

### Opening

It's been four weeks since the last code review, and besides needing a refresher, they might not all have had the same experience. Open the discussion and revisit the significance code reviews provide. Ask students what they've learned overall when working with each other and the academic support team.

Some things they might cite include:
* spotting issues they didn't see in their code
* finding ways to make their code more readable
* improving their code so that it follows best practices
* sharing working knowledge about their code with someone else
* learning something new from someone else's code

Remind them that it's okay to feel code reviews are intimidating but reinforce how they'll get better with positive and constructive feedback. It's also important to remind them of the following:
* code reviews aren't just about spotting mistakes
* code reviews aren't performance reviews
* they can conduct code reviews with anyone on the team

## Objective 2: Code review individual projects

### Opening

Use the remaining class time to help students experience receiving and giving code reviews on their projects. Students implemented the following requirements on this independent project:

1. Implement the provided class design and interface methods.
2. Implement the `FileStorageService` to read and write to a file.
3. Process and transform the contents of the `.data` files into the catalog.
4. **CHALLENGE:** Write unit tests for the `Book`, `Movie`, and `Tool` classes. 

### Code review

Conduct a series of short code reviews with the class so that students have an example they can work from:

1. Ask someone in class to share their screen and demonstrate a feature of their application. 
2. After demonstrating the feature, ask the student to switch to their IDE and explain a portion of the code they wrote for that feature.
   * As an example, ask, "Can you explain each of your classes in the `model` folder/package?
3. Pick a spot in the code, pause, and give the class four to five minutes to read the code. Ask them to write down any comments and observations they may have.
4. As the instructor, provide at least one piece of positive and constructive feedback.
5. Ask another student in the class to share a comment that they observed.

Repeat these steps a few times, asking a different student to demonstrate and explain another project requirement. Encourage the rest of the class to note something that they might've learned.

**Note:** If you notice that your class or some students are having difficulty providing meaningful critiques, tell them to ask questions instead. Then they get to learn something new as a reviewer.

### Next steps - group activity

Have students split up into groups of three or four and take turns demonstrating a feature in their code while the rest of the group asks questions or provides feedback. 

Before you break into groups, help the students know what types of things to look for:
* Does the code meet the requirements? 
* Are there any missed scenarios where the code may fail?
* Are there any opportunities to refactor the code to increase readability?
  * poorly-named variables
  * significantly long methods
  * stale comments  
  * redundant code fragments
  * hard-coded primitive values (instead of constants)
* Are there any _code smells_?    
  * large unused (or commented out) blocks of code
  * reassigned parameter values  
  * uncaught exceptions
  * are there `PrintWriter` or `Scanner` objects left open
* Does the code follow a consistent style?
* Are there any parts of the code that need more comments?
* What's one thing you learned reading this code?
