# Week 1 Review - HTML and CSS

## Primary objectives

* Understand the role of HTML and CSS to create web pages
* Use common HTML tags to create web pages
* Create HTML forms and some of the common form controls
* **Understand the concept of semantic HTML**
* **CSS rules and selectors**
    * Use a CSS element selector
    * Declare CSS properties and values
    * Use CSS to manipulate fonts
    * Use CSS to manipulate colors
    * Units of measurement in CSS
* Default HTML element positioning and how to change it
* The Box Model of HTML layout
* **Cascading of stylesheets and specificity**

All examples use `public/about.html`, `public/css/about.css`, and `public/css/global.css` in the Module 3 Sample Application (Bookmark Manager Web).

You'll want to have the rendered page in a browser available to show students. You may choose to run it by `npm` (see sample application `README.md`) or by Live Server (right-click `about.html` and select **Open with Live Server**).


## Objective 1: Understand the concept of semantic HTML

### Opening

Semantic HTML is HTML that conveys meaning through the markup. Tags like `<header>` and `<main>` define the type of content within. This is in contrast to tags like `<b>` that change the presentation, or tags like `<div>` and `<span>` that don't carry any special meaning.

Semantic HTML brings with it several benefits. Search engines can scan web pages and index accordingly to semantic HTML—for example, it may give more importance to the content in `<main>` tags, or know that it can find links to other pages to scan in `<nav>` tags. Screen readers assist visually impaired users browse the web, and it can save time by not reading out repeated or irrelevant information if the HTML was correctly marked up.

### Example

Take a moment with the students to explore `about.html` and it's HTML structure. Point out tags like `<head>`, `<title>`, and `<body>` and discuss their importance.

Next, turn the focus to the `<header>`, `<nav>`, `<main>`, `<section>`, and `<footer>` tags. These are semantic HTML elements. Point out that you can just look at the tag and know what kind of content it has:

* `<header>` contains the logo and title, content you'd expect to find at the top of a webpage.
* `<nav>` has links—these are special links that point to sections in the same page.
* `<main>` contains the main content of the webpage—which is grouped by `<section>` tags. You can flip between the code and the browser to show how they match up and displayed from top to bottom.
* `<footer>` has copyright information and a link to contact, content that you normally find at the bottom of a webpage.

Show how the elements, in particular the `section` elements in `main`, follow the same order they're written in the HTML and display on the webpage. This is called "normal flow" and is how elements look and behave by default.

### Next steps

Point out the `id` and `class` attributes of the elements shown earlier, focusing on the `section` elements. Make sure to mention `id` attributes must be unique for every page, but `class` attributes can be reusable like `about-section`. This helps dove-tail into the next objective.


## Objective 2: CSS rules and selectors

### Opening

Cascading Style Sheets, or CSS, gives a developer the ability to define the look and layout of a webpage, independent of the actual content. This is an example of the design principle of "separation of concerns" or "separation of presentation and content." (You can draw another example of "separation of concerns" from encapsulating similar logic in a Java or C# class.) Also, CSS rules are reusable across multiple webpages, allowing you to also follow the DRY or "Don't Repeat Yourself" principle.

CSS rules consist of a **selector**, and one or more **style declarations**:
* A selector says what elements you want to target, or *select*, for the set of style declarations that follow. A selector can target any HTML element in the `<body>`, including `<body>` itself. You can target every instance of a tag, only certain ones, or a specific one. You make these more narrow selections with `class` and `id` attributes on the HTML elements.
* The style declarations has a "property" which is the particular characteristic that you're changing, and a "value" which defines how that characteristic looks.

### Example

The `about.html` page imports styles from several CSS files through `<link>` tags in the `<head>` of the HTML. Comment out or remove them, and save the file to reload in the browser. Show the students how much the page changed without the CSS. Un-comment or add back and save. Everything that changed was because of CSS.

The `about.css` contains styles used only on the about page. You can use this to show examples of:

* Basic element selectors: `li` and `figure`
* Class selectors: `.about-section` and `.screenshot` (note the beginning dot, show how these match up to some `class` attributes in `about.html`)
* Multiple combinator: `p, ul, ol`
* Descendant combinator: `figure figcaption`

The `global.css` contains styles for the entire web application. You can use this if you want more examples of styles, but be aware that some styles aren't used on the About page, and some use CSS Grid and Flexbox which are for future lessons. Some styles that might be useful to point out or use for examples:

* `h2` - Each `section` of the About page leads off with an `h2`. Try changing the `font-size` property.
* `#app-header a` (and `:hover`) - This is an example of an ID selector (and Descendant, also pseudo-class for `:hover`). Try changing `color` or removing `text-decoration`.
* `.nav-list` - Try changing values for the `border-right` property like `10px` or `dashed`.
* `.nav-link a` - Try changing values for `background-color` or `padding`.

### Next steps

Write some CSS rules in front of the class. Some ideas you can use:

* An example of an ID selector using one of the `section` elements like `app-features`. Try setting `background-color` or `border` and show how the styles only apply to the first box.
* Another example of an ID selector is `main-nav` and set `font-size`. Since the default size of links are `14px` (see `body` rule in `global.css`), you can show how `font-size: 21px` and `font-size: 150%` produce the same results (14 * 1.5 = 21).
* Create another `li` rule (you'll change it in a minute) and set `list-style-type: none` and show how it removes the dots for `li` elements in `ul` elements, and the numbers for `li` elements in `ol` elements. Say you want to actually keep the numbers so you change the selector to `ul > li` (`ul li` works too). If you want to have a little fun, try `list-style-type: upper-roman` (or `lower-roman`).


## Objective 3: Cascading of stylesheets and specificity

### Opening

The "C" in CSS stands for "Cascading" but what does that mean? It means how the browser reads styles and chooses the one or ones to apply.

First, it's important to understand that browsers read styles from top to bottom in each file, and if there are multiple CSS files referenced for an HTML page, first to last.

Why is that important? Well, you have to be able to declare multiple rules for the same selector. Maybe you have a large project, but you want to specify some different rules in one case—like the About page of the sample application (`about.css`). Or you may be working on a large project and not aware of every single rule in your CSS files. If you declare two different values for the same property, in two different locations, which one takes precedence? The browser takes the last rule it read.

However, that's only one part of it—the other part is **specificity**. The more *specific* a rule is, it's given more weight by the browser to choose that style to apply. If a selector uses an ID—such as `#app-features`—since only one element can have an ID, that's more specific than a class selector—like `.about-section`—or element selector like `section`. Similarly, class is more specific than elements. The order goes in **ID** -> **Class** -> **Element**.

### Example

To demonstrate cascading precedence, write two rules using `#app-features` or `.about-section`. Set a property like `border` or `background-color`, in the second rule write a different value for the same property. Demonstrate that the second rule "overrides" the first. You can also add a different property in one to show that it's only the duplicate property affected, not the entire rule.

To demonstrate specificity, write some rules that use `#app-features` and `.about-section`. First use the class selector to set a property like `background-color` of all boxes (reload and show), then use the ID selector to set a different value for the first box. The first box has the value set in the `#app-features` rule because that's more specific than the class selector `.about-section`.

### Next steps

You can also demonstrate precedence by file, by adding a rule to `global.css` then add another rule to `about.css` that takes precedence over the first. You can even swap their `<link>` tags in `about.html` and show that it's whatever file is last gets precedence.
