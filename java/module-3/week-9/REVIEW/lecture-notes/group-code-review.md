# Week 9 Group Code Review

**Note:** There are no planned exercises this day because the students didn't have new materials in the LMS.

## Primary objectives

- Review the significance of code reviews
- Code review individual projects

## Objective 1: Review the significance of code reviews

### Opening

Students have experienced code reviews before, but it may have been some time since a student has been a part of a code review. A reminder of the purpose, benefits, and expectations of code reviews may be appropriate.

Open the discussion about the significance code reviews provide to professional software development teams. You can ask students to share specific things they've learned from one-on-one code reviews or by working with each other.

Some examples they might cite include:
* spotting issues they didn't see in their code
* improving the readability of their code 
* ensuring the code follows a standard
* sharing working knowledge about their code with someone else
* learning something new from reading another programmer's code

Tell them that code reviews vary from team to team and project to project. Some happen synchronously, and others happen asynchronously. Some occur with peers, and others happen with lead developers.

You may want to share some expectations and best practices you've picked up regarding code reviews. Here's a short list to get you started:

* Whether you are the author of the code or a reviewer, you must be actively engaged. The code review process is about sharing and learning, and both authors and reviewers can share and learn from one another.
* If you don't understand something you see in the code, it's your responsibility to bring it up. It may be that you're missing a key piece of information, or you may be uncovering an issue. In either case, the positive outcome can occur only if you bring it up.
* The words you choose and your tone of voice are important.
    * A code review must be a positive experience. Its tone must be collaborative, not combative. All participants must show respect and empathy to the other team members.
    * As a reviewer:
        * Don't be accusatory. Don't blame. Don't be condescending.
        * Ask questions and really listen to the answers. The author may have had a good reason to do what they did.
        * If you find something cool, or if you see some code that teaches you another way of doing something, bring this positive point up in the review. Make sure the other participants see it too.
    * As an author:
        * Feel free to explain what your code is doing, and why you chose to do it this way.
        * At the same time, try not to be overly defensive. Keep yourself open to suggestions.
* Most every review point is the start of a discussion. There's almost always subjectivity in a comment—it's seldom as simple as "you're missing a semi-colon." With subjectivity comes discussion, often about tradeoffs. These discussions among a team are invaluable.
* Remember, this is about making every individual better, and making the team better. Making the code better is a happy side-effect.

## Objective 2: Code review individual projects

### Opening

Use the remaining class time to help students experience receiving and giving code reviews on their projects. Students demonstrated the following programming skills on this project:

* Creating HTML and using semantic elements
* Styling with CSS and layout with grid and flexbox
* Updating the DOM using JavaScript
* Handling events in Vue
* Accessing data from an API
* Creating components in Vue
* Using Vue Router to create a single-page application (SPA)

### Code review

Conduct a series of short code reviews with the class:

1. Ask someone in the class to share their screen and walk the rest of the group through a user story demonstrating their feature using the running application. Candidate features from this project include:
    * As an anonymous or authenticated user, I can visit the Products page to see all the products in the SSGeek online store.
    * As an anonymous or authenticated user, on the Products page, I can view the product list as a tiled view or a table view, by clicking the appropriate icon.
    * As an anonymous or authenticated user, on the Products page, I can type a search term into a Search edit box, and the page displays only the products whose name contains the search term (ignoring case).
    * As an anonymous or authenticated user, from the Products page, I can click a product name to go to the Product Details page.
    * **Only as an authenticated user**, from the Products page, I can click a "shopping cart" icon next to the product, and the application adds the product to my cart.
    * **Only as an authenticated user**, from the Product Details page, I can click a "shopping cart" button on the page, and the application adds the product to my cart.
    * As an authenticated user, I can click the "Cart" link on the navigation bar to visit my shopping cart.
    * As an authenticated user, on the Shopping Cart page, I can click an "X" icon next to any item to remove that item from my cart.
    * As an authenticated user, on the Shopping Cart page, I can click a "Clear Cart" button to remove all items from my cart.
2. After demonstrating the feature, ask the student to switch to the IDE and explain the code behind that feature.
3. Pause and give everyone three to four minutes to read the code. Ask the class to write down any comments that they may have.
4. Provide one positive and one constructive piece of feedback about the code.
5. Ask one other student in the class to share a comment they observed. 

> Note: If you notice that your class or some students have difficulty coming up with meaningful critiques, tell them to ask questions. Then they get to learn something new as a reviewer.

### Next steps

Have students split up into groups of three or four and take turns demonstrating a feature in their code while the rest of the group asks questions or provides feedback. Before you break into groups, tell the students they can use some of the following prompts if they're not sure what to look for:

* When the Products page loads, what code runs to get a list of products and display them?
* What happens when you run the client, if the server isn't running? Is the user given a reasonable error message?
* What's the mechanism for switching between table and tiled views on the Products page? 
    * Is the logic for fetching products written just once in the parent, or is it in each component/view? 
    * If it's in the parent, how does the products list make it to each component?
* On the products page, how do you determine whether or not to show the "Add to cart" icon, and how do you show or hide it?
* On the products page, how's the link to the product details setup?
* On the product details page, how does the view determine which single product to fetch from the API?
* On the shopping cart page, when the user clicks the **X** next to an item, how does the code know which item to delete?
* Are all calls to the API consolidated into a service, or spread out among components?