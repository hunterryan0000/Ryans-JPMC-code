# Week 6 Review - Vue.js data binding and event handling

## Primary objectives

* **Understand what Component-based JavaScript is and the benefits of using it**
* Define a new component and show it on a page
* **Implementing data binding in components (one-way, two-way, dynamic attributes, and with collections)**
    * Implement one-way data binding
    * Use `v-model` to perform two-way binding between values in HTML inputs and data properties
    * Use `v-bind` to bind a data property to HTML elements and their attributes
    * Use `v-for` to show an array or JS object as DOM elements
    * Use `v-if` and `v-show` for conditional display
* **Use `v-on` for event handling**
* **Using computed properties and methods**
    * Use computed properties to create dynamic or derived properties from data properties
    * Use methods to add behavior to your components
* Write inline and method event handlers
* Write event handler arguments

> Note: All examples use the Module 3 Sample Application (Bookmark Manager Web). You'll need to run `npm install` to install the dependencies and run the application with `npm run serve`. You'll also want have the Module 2 Sample Application (`bookmark-server`) running as well since it provides the API backend to this application. Refer to the `README.md` with the Module 2 application to set up the database and server.


## Objective 1: Understand what Component-based JavaScript is and the benefits of using it

First, it's good to take some time to help the students understand what component-based JavaScript is and the benefits of using it.

You can describe a component as a small feature that makes up a piece of the user interface. Bring up [NASA.gov](https://www.nasa.gov/) in front of the class and point out different parts of the website that could make good components:

* The main navigation bar and each nav item
    * Some of the nav items have drop-down menus
* The gray topics bar and each topic item
* The search bar
* The news cards

Note: In case the NASA website changes layouts or is unavailable, you can use this screenshot for demonstration:

![Screenshot of NASA.gov](./images/nasa_screenshot.png)

So why are components a good thing? Components encourage encapsulation, reusability, and single-responsibility:

* Encapsulation: all of the presentation (HTML and CSS) and behavior (JavaScript) for a component are together in the same file.
* Reusability: a component is reusable and can provide multiple instances of a feature with the same look and behavior, like nav items or news cards.
* Single-responsibility: a well-designed component has one purpose, like displaying a navigation link or news item. Having a limited scope helps allow reusability, and also makes it easier to test and maintain.

### Transitioning from "vanilla JS" to Vue.js

Some points to cover with students to help transition from "vanilla JavaScript" to Vue.js:

* Bundling HTML/CSS/JS together in one file may take some getting used to as it goes against the regular web development practice of separating these files. You can consider both ways as a "separation of concerns" but from a different viewpoint.
    * In regular web development, it's a best practice to separate presentation, style, and behavior.
    * In Vue.js, the HTML, CSS, and JavaScript of a component are of no concern to any other component or code in your application. This also keeps your components loosely coupled.
* DOM manipulation isn't necessary. You don't need to query DOM elements and update them to display some data or show some other application state. Vue handles this automatically.
* Event handling is easier and built-in. It's not necessary to wait for the element to be available in the DOM and attach an event listener.


## Objective 2: Implementing data binding in components

### Opening

Review with the students what data binding is and does. It's a way for your application to display data and update it with changes. It also allows you to dynamically change features and repeat entire sections of HTML based on the data provided.

Once you set up data binding, your application data flows and updates automatically. If you were to try to accomplish the same thing with vanilla JavaScript, you'd need to do one or more of the following:

* Write event listeners to capture data changes and assign them to variables and objects
* Update changes to displayed data either through the DOM or templates
* Change HTML attributes like `class` in response to data changes
* Add or remove DOM elements based on the size of your data and when it changes

### One-way and two-way data binding

Ask the group about the differences between one-way and two-way data binding. Which way does data flow in one-way data binding? Where can you write one-way data binding? Where can you write two-way data binding?

What does `v-bind` do? Is that one-way or two-way? How's it similar and how's it different from the other methods of data binding?
> Note: Students sometimes struggle with `v-bind`, so you may need to spend some extra time on this either as a discussion or demo. You don't need to go into props or loops at this time.

#### Examples

* One-way / `{{ }}`:
    * `src/components/UserProfile.vue`
        * `user.shortBio`
        * Note to the students how if `user.displayName` doesn't have a value it displays `user.username`
    * `src/components/EditProfileForm.vue`
        * `userProfile.username`
        * Can use this one to segue into two-way data binding.
* Two-way / `v-model`:
    * `src/components/EditProfileForm.vue`
        * The other `userProfile` properties. Straight-forward example of binding input fields to a data property.
        * You can use the Vue Dev Tools to show the data property updating as you change values.
    * `src/components/RegisterUserForm.vue`
        * Different example, has no default values (besides empty strings).
        * Make sure to point out this is still two-way data binding, even if it doesn't seem like values are getting assigned when it loads. You wouldn't want to have anything but blank values for a form like a user registration or contact page. When the user fills out the fields, Vue updates the data properties.
* Dynamic attributes / `v-bind`:
    * `src/components/UserProfile.vue`
        * `<img ... v-bind:src="user.profileImageUrl" ...>`
    * `src/components/MyBookmarkTable.vue`
        * `<a v-bind:href="bookmark.url" ... >`
        * Can use an expression to set an attribute too: `<button ... v-bind:class="bookmark.public ? '' : 'icon-button-inactive'">`
    * `src/components/AddEditBookmarkForm.vue`
        * The Visibility drop-down uses `v-bind:value` to set the option values.

### `v-if` and `v-show`

Students struggle with deciding between `v-if` and `v-show` because they seem to perform similar functions, but they're not intended to be interchangeable.

`v-if` is for conditionally *rendering* a block. If the condition isn't true, the block isn't rendered and isn't in the DOM.

`v-show` is for conditionally *hiding* a block. If the condition isn't true, the block *is* rendered and in the DOM, but hidden.

`v-if` usually gets used if the condition is unlikely to change. Vue actually destroys or recreates the elements and event listeners if the value changes during runtime.

`v-show` is better suited for situations where the user might toggle a value on and off, like displaying a modal window or hiding part of a form.

#### Examples

* `v-if`
    * `src/components/UserProfile.vue`
        * Profile image not shown if not set, `v-else` for default image.
    * `src/components/MyBookmarkTable.vue`
        * "No bookmarks to show" message, `v-else` for bookmark table.
* `v-show`
    * `src/views/MyBookmarksView.vue`
        * The **Add Bookmark Form** is visible or hidden based on the value of `showAddEditForm`.
        * Note: This example is in the **view** and not the similarly named **component**. The form is in both places to support Adding (view) and Editing (component). The Edit Bookmark Form uses `v-if` because the component can't get `selectedBookmark` unless it's destroyed and created each time—this nuance may be confusing to students, so you may want to just stick with the one in `MyBookmarksView`.

### Using `v-for` to generate elements

What data types and values can be data bound? Look back at what was just shown. There were mostly strings, and some usage of booleans. Numbers can also be data bound. What about arrays or objects—how do you display an array or an object's properties?

A typical programming task is to process or display the contents of an array of data. One way of doing so is with a for-each loop. A Vue loop repeats an element, including child elements, for each item in an array.

Students often struggle with the `v-bind:key` and on what element to put `v-for`.

#### Examples

* `src/components/MyBookmarkTable.vue`
    * `<tr v-for="bookmark in myBookmarks" v-bind:key="bookmark.bookmarkId">`
    * Each `bookmark` has its properties displayed in the table. Point out that it also has one-way data binding.
* `src/components/NotificationList.vue`
    * Example with `index`.
    * Point out other example of `v-bind:class`, uses an expression for string concatenation.
* `src/components/AddEditBookmarkForm.vue`
    * The Tags multi-select, `v-for` is on the `<option>` tag since that's what you want repeated
    * An example of one-way data binding is here too—`{{ tag.name }}`

### Next steps

This objective covers a lot of material. You can do a global search in the project for more examples if you need to, or dive deeper into one of the provided examples.


## Objective 3: Vue event handling, methods, and computed properties

Remind the students how event handling works in JavaScript. It involves multiple steps—selecting the DOM element on which the event triggers, the attachment of an event listener, and the function that handles the event. You also have to make sure the element exists before you declare the event listener.

Vue.js simplifies a lot of that by having you declare the event listener on the HTML element itself with `v-on`. Vue automatically takes care of the DOM selector and attaching the event handler, you just need to write the event handler method.

Students struggle with the distinction between methods and computed properties, when to use one over the other. While you can't use a computed property for event handling, this is an opportunity to show how they're distinct from methods and how they're similar to *derived properties* from Java/C#.

### Examples

* `src/components/EditProfileForm.vue`
    * The form and the cancel button both have events. You may find it easier to show the cancel button `click` first, before talking about the form `submit.prevent`.
    * Point out methods without parameters are usually written without parentheses as convention.
    * If you want to explain the code in the `submit()` and `cancel()` methods when you show them, keep it brief and high-level as the students haven't gotten to Vuex and Vue Router yet.
* `src/components/MyBookmarkTable.vue`
    * The `togglePublic()`, `editBookmark()`, `promptDeleteBookmark()` methods are all passed `bookmark`. Because this part of the HTML repeats for each bookmark, you must have a way to know which bookmark the method is to take action on.
    * There's a computed property on this component too—`bookmarkCounts`. Show the property declaration in the `computed` section and show the use of the property in `{{}}` in the HTML.
