# Week 2 Review - CSS Grid and Flexbox

## Primary Objectives

- Layout an HTML5 page using CSS Grid
    - Define a grid container using rows and columns
    - Define named grid template areas
    - Assign page elements to grid template areas for purposes of page layout
    - Apply content alignment to items within a grid container using `justify-items` and `align-items`
- **Describe what Responsive Design is and what Mobile First is**
    - Create a page with multiple layouts depending on screen width
- Use media queries (screen width) to define different dimensions for grid containers
- **Use the relevant dev tools available in Chrome to assist with developing grid layouts**
- Lay out a series of elements using Flexbox
    - Define a Flexbox container using a row or a column
    - Apply normal flow to Flexbox items using flex-wrap
    - Apply content alignment to flex items using justify-content and align-items
    - Arrange items within a Flexbox container using order
    - Size items within a Flexbox container using flex-basis, flex-grow, and flex-shrink
- Understand how to add Flexbox layouts to existing Responsive CSS Grid layouts
- **Understand when to use Flexbox or Grid or combine them both**

## Objective 1: Describe what Responsive Design is and what Mobile First is

### Opening
As more and more devices of different shapes and sizes can browse the web, webpages must render well and provide appropriate features on any screen.

Thus, Responsive Web Design was born, a set of practices that allows pages to alter their layout and appearance to suit different screen sizes and resolutions.

Mention the three key areas of Responsive Design:
- Flexible, or fluid, grid layouts
- Resizable images
- CSS media queries

> Note: Students may think of Responsive Design as a separate technology, but it's important to note that it isn't; it's a set of best practices.

A good approach to Responsive Web Design to think mobile-first. That is designing a page for the smallest screens—mobile devices—then working up to larger layouts.

An analogy to mobile-first as designing a building foundation-first may help illustrate the concept. You can relate adding more rooms and floors to the building to enhancing a webpage with more enhanced layouts and capabilities.

### Example
Visit a site you know that uses a grid layout like [NASA](https://www.nasa.gov), [Slack](https://www.slack.com), or [Medium](https://www.medium.com), and demonstrate how the layout of the site changes when you adjust the browser's size.

### Next Steps
While demonstrating a responsive site, you can segue into using Chrome DevTools by using them take a closer look at a site's grid layout.

## Objective 2: Use the relevant dev tools available in Chrome to assist with developing grid layouts

### Opening
> Note: Students will use a browser's dev tools (and later Vue dev tools) frequently, so becoming familiar with what they can do with them is important.

Browsers give you tools to help examine and debug issues with a webpage, including but not limited to its layout and styling.

### Example
Some notable features to show for Chrome DevTools:
- When an HTML element on your page has a display value of flex or grid, you can see a badge next to it in the Elements panel.
    - Clicking on the badge shows the element's flex or grid overlay on the page.
- Hovering over elements with the inspector to see their size, margin, padding, or grid/flex overlay.
- Use the device toolbar to view a site at pixel-specific dimensions or in an emulated device viewport and throttling performance.
- Checking the Styles tab to see an element's active and overridden CSS styling.

## Objective 3: Understand when to use Flexbox or Grid or combine them both

### Opening

1. Is the layout two-dimensional or one-dimensional?

You can expand on this question by noting that a CSS grid layout can provide symmetry to both columns and rows, while flexbox is best for organizing related content in either rows OR columns.

2. Is the design based on content or structure?

Grid is better when organizing the structure of the overarching page or component, while flexbox is better suited for aligning and adjusting related content.

The reading relates the function of a grid layout to an architect designing a house and laying out how different spaces relate to one another.

The reading relates the function of flexbox to an interior designer arranging and organizing the contents of a room.

It's good to explain that it's possible to use flexbox and grid layouts together and that it's common to use flexbox inside a grid area.

### Example
Using the following HTML and CSS you can demonstrate how it looks before and after adding CSS Grid, Flexbox.

To show flexbox you can first add this to a new `.html` file and open it in a browser. Then add the CSS to apply a flex layout:

```HTML
<main>
  <article>Article 1</article>
  <article>Article 2</article>
  <article>Article 3</article>
</main>
```
```CSS
main {
  background: pink;
  display: flex;
  flex-direction: row;
  align-items: center;
  flex-wrap: wrap;
  justify-content: space-between;
  padding: 10px;
}

article {
  background: red;
  border: 2px solid yellow;
  padding: 25px;
  margin: 8px;
  width: 40%;
}
```

To show grid you can first add this to a new `.html` file, show the HTML in the browser, then add the CSS to apply a grid layout:

```HTML
<div id="container">
  <header>Header</header>
  <aside>Aside</aside>
  <main>Main</main>
  <footer>Footer</footer>
</div>
```

```CSS
#container {
  width: 100%;
  height: 100vh;
  background: yellow;
  display: grid;
  grid-template-rows: 80px 600px 80px;
  grid-template-columns: 0.5fr 1fr;
  grid-template-areas:
    "header header"
    "aside main"
    "footer footer";
}

header {
  grid-area: header;
  background: blue;
}

aside {
  grid-area: aside;
  background: green;
}

main {
  grid-area: main;
  background: pink;
}

footer {
  grid-area: footer;
  background: yellow;
}
```

### Next Steps

In the grid HTML, you can replace the `main` tag with the flexbox HTML and append the CSS code layout to demonstrate using flexbox within a grid layout.

> Note: Students often ask if you can use grid inside of a flexbox layout which is possible, but uncommon.

Feel free to explain or show how you can leverage media queries to implement multiple layouts to a single page.
