# Week 4 Review - DOM and events

## Primary objectives

- **Describe the DOM and how it is different from HTML**
- Select elements from the DOM using `getElementById`, `querySelector`, `querySelectorAll`
- Set values on form elements
- Set `innerText` on HTML elements
- Create new DOM elements using `createElement()` and `insertAdjacentElement()`
- Traverse the DOM with `querySelector`, `querySelectorAll`,`children`,`childNodes`,`parentNode`, `nextElementSibling`, `previousElementSibling`
- Remove a DOM element with `removeChild()`
- **Select DOM objects and attach functions with `addEventListener()`**
- **Describe event bubbling and how it works**
- List possible event types and what elements get those events
- Describe how to add listeners to newly created DOM elements
- Remove an event listener with `removeEventListener()`

## Objective 1: Describe the DOM and how it is different from HTML

### Opening

The key here is that HTML is static, while the DOM is a dynamic, living structure created from the static HTML. Initially, it's the browser's interpretation of the HTML from the static file. An analogy could be a movie script, which is just text on paper, whereas the final movie is that script brought to life. The words from that script get tone, inflection, and perhaps even ad-libbed by the actors with input from the director.

Some common difficulties that students have around this topic include:

- Using the developer tools effectively when troubleshooting, particularly how to find a specific element on the page in the DOM tree view.
- Understanding that the browser may *fix* the HTML from the static file, for example adding a missing `tbody` element in a `table`. 

### Example

The Module 3, Bookmark Web sample application's `about.html` page intentionally includes some improper HTML to illustrate how the browser can *fix* static HTML. To show this, open the `about.html` page from VS Code using Live Server. Search for "Example: static HTML vs. DOM" in the file to locate the appropriate section. 

If you inspect this section of the document, the DOM shows that the `ul` isn't a child of the `p` as one might expect by just looking at the static HTML.

The previous example didn't use JavaScript at all. If a page uses JavaScript, the DOM may also change after the initial presentation of the page to the user. Use the tutorial's ToDo list example (`04_Event_Handling/tutorial-final/todo/index.html`) to illustrate this. The static HTML includes only an empty `div`. The `ul` and all of the `li` ToDo's are dynamically added to the DOM using JavaScript.

### Next steps
 
If there are any points you wish to review with the class regarding creating or adding elements to the DOM using JavaScript, you might take a deeper dive into the JavaScript file for the ToDo list tutorial. Consider using the debugger and adding a breakpoint to step through the addition of each `li` element one at a time.

This could also be an opportunity to spend additional time with other aspects of the developer tools. If there are areas that students have been struggling with, take some time build comfort with this tool now, before they move into Vue. (Come back to this at the end of lecture if there are concerns about running out of time.)


## Objective 2: Select DOM objects and attach functions with `addEventListener()`

### Opening

> Note: Students must understand DOM event handling conceptually as that concept carries over into work with a front-end framework or library.

The browser event model is what allows web applications to be interactive. To make an element interactive requires setting up one or more event listeners on that element. 

### Example

Use the ToDo list from the tutorial (`04_Event_Handling/tutorial-final/todo/index.html`) as an example to show the use of `addEventListener()`.

Note the different events:
- `DOMContentLoaded` is triggered once the browser has built the initial DOM from the static HTML
- `click` is used in the listener for both the Complete All Tasks button and the check-mark icon
- `dblclick` is used to undo the task completion, removing the strike-through

Use the browser debugger to illustrate the program flow by putting a breakpoint in the event handler functions.

### Next steps

In the most straightforward examples, the interactive elements exist on the DOM when it's first loaded. In the tutorial and exercise, everything is initially added to the DOM when it's first loaded either from the static HTML, or the JavaScript code. However, consider adding a button that would add a new ToDo item to the list. Doing so requires more thought about where and how to add the event listener to a new todo item that doesn't exist initially. 

> Note: This isn't covered in any curriculum exercises but is a logical extension point for students. It's not critical to teach this, but if there are questions around this, or if you feel the students are ready for more of a challenge, feel free to talk through how to do this. 


## Objective 3: Describe event bubbling and how it works

### Opening

Understanding event propagation is crucial to having a solid conceptual understanding of browser events. 

> Note: The reading and following example calls out the `event.stopPropagation()` method. However, it *should* rarely be used in practice. There is usually no need to stop the browser's natural event bubbling, and doing so is a potential source of bugs - if not now, in the future. (For additional information, see https://css-tricks.com/dangers-stopping-event-propagation/) 

### Example

Again, use the ToDo list from the tutorial (`04_Event_Handling/tutorial-final/todo/index.html`) as an example to discuss event bubbling.

To illustrate this, add a bit of CSS and JavaScript.

Add the CSS to the bottom of the CSS file:
```CSS
.red {
    background-color: red;
}

.yellow {
    background-color: yellow;
}
```

Add this JS to the end of the `DOMContentLoaded` handler function:
```JavaScript
  tasks.forEach((task) => {
    // Make the li toggle yellow when clicked 
    task.addEventListener('click', (event) => {
      event.stopPropagation();
      if (task.classList.contains('yellow')) {
        task.classList.remove('yellow');
      } else {
        task.classList.add('yellow');
      }
    });
  });

  let list = document.querySelector('ul');
  // Make the list toggle red when clicked
  list.addEventListener('click', () => {
    if (list.classList.contains('red')) {
      list.classList.remove('red');
    } else {
      list.classList.add('red');
    }
  });
```

When clicking on a ToDo item, both the task item `li` and the list `ul` change color. Clicking on an element also propagates that event up the DOM tree.

To stop event propagation, use `event.stopPropagation()`.

Update the code for the li toggle to stop propagation:
```JavaScript
  tasks.forEach((task) => {
    // Make the li toggle yellow when clicked 
    task.addEventListener('click', (event) => {
      event.stopPropagation();
      ...
```

Now, only the task item `li` color changes when you click a ToDo item.
