# Week 3 Review - Intro to JavaScript

**Note:** After two weeks of work on static web design, students are likely excited to develop dynamic web sites. Begin class with a discussion of what it means for a web site to be "dynamic" and inform students that JavaScript is what makes these features possible.

## Primary objectives

- **Identify the similarities and key differences between JavaScript and another language (Java or C#)**
- Understand the difference of statically and dynamically typed languages
- Write procedural code in JavaScript that utilizes
    - Variables and data types
    - Logical branching
    - Looping and iteration
- **Use the collections capability of JavaScript arrays**
- Define named functions in JavaScript
- Define anonymous functions in JavaScript and assign them to a variable
- **Describe the difference between an anonymous function and a named function**
- Use default parameters in a function
- Take parameters in a function and return a value to end a function
- Add JSDoc to a function to document what it takes, what it does and what it returns

## Objective 1: Identify the similarities and key differences between JavaScript and another language (Java or C#)

### Opening

With the word "Java" in both their names, students might suspect there is a close relationship between Java and JavaScript and use "Java" interchangeably to refer to either language. While both languages share a similar history, using the word "Java" to refer to JavaScript leaves a very novice impression with recruiters and interviewers.

> Note: It's not necessary to go into the timeline or history of Java or JavaScript unless you believe your students have interest in that background. 

Some similarities:

- JavaScript syntax shares some syntactical similarities with both C# and Java, as all three languages were heavily influenced by C and C++. Reassure students that their previous experience facilitates the learning of JavaScript, as well as any other language they may need to pick up on the job. 
- JavaScript, Java and C# are all very versatile languages. Though JavaScript is primarily used for front-end web applications, it's also capable of building server-side code and APIs. Conversely, Java and C#, primarily used for server-side code and APIs, are also capable of building front-end applications. 

Some differences: 

- Both Java and C# are first compiled into an intermediary language, Java bytecode or C# IL, which is then interpreted at run-time. JavaScript is strictly an interpreted scripting language; JavaScript files are never compiled. JavaScript files run through an interpreter, often located in a web browser. 
- Java and C# are both **strongly typed** languages. You need to declare variables to be of a particular type before using them. JavaScript is a **weakly typed** language. JavaScript variables themselves don't have a data type, and they can store values of any type.
- Both Java and C# are class based, object oriented programing languages. While JavaScript has objects, it is procedural, not object based. Procedural programming is about writing procedures or methods that perform operations on the data, while object-oriented programming is about creating objects that contain both data and methods. 
- JavaScript functions are **first-class citizens**, meaning that they can be:
    * passed as an argument
    * returned from a function
    * assigned to a variable


### Example  

Use the `tutorial_final/tutorial.js` files from the Intro to JavaScript and JavaScript Functions units to highlight syntactical similarities and differences between Java and JavaScript. Use your discretion to select which of the following items to focus on or pass over.

Using the `01_Intro_to_JavaScript/tutorial_final/tutorial.js`, note:
- the declaration of variables without the explicit naming of their data type. Review the use of `let` and `const` in variable declaration.
- similarities and differences in array syntax 
- the common `if-else` structure  
- the use of `===` in JavaScript for strict, typed equality and `==` for looser non-typed equality checks. Optionally, contrast this with the `==` behavior in Java which is more strict, and the `equals` method which allows for more flexible equality checking. 
- similarities in the `for of` syntax. Optionally re-write this to show the standard `for` loop syntax. 
- JavaScript object literal syntax
- similarities in a the use of dot notation to call methods on objects

The `02_JavaScript_functions/tutorial_final/tutorial.js` file provides examples of similarities and differences in function declaration and use.

- Functions don't need to be inside a class.
- There is no return type declared.
- Function parameters aren't typed.
- Parameters are optional by default, and can have default values. 
- There is no error given if a function argument is missing or if there are extras given in a function call.


### Next steps

Some options for furthering this discussion include:

- JavaScript *gotchas* that can be the source of bugs. For example, you can create a variable by just assigning a value without the use of `let` or `const`. While you *should not* do this intentionally, know that this sometimes occurs unintentionally due to typos. Similarly, typos may also result in the creation of new object properties.
- Discussing similarities between a JavaScript object and a Java Collections Map.

## Objective 2: Use the collections capability of JavaScript arrays

### Opening

In Java, arrays are primitive data types. But in JavaScript, an array is an object. There are many similarities to the Java Collection Lists, which have methods for performing common array operations. 

> Note: As the curriculum moves toward Vue, there is diminishing focus on array processing logic in exercises. However, if students are comfortable working with JavaScript array functions, there can be some advantages in using JavaScript over Java or C# in solving whiteboard style coding challenges or interview questions.

### Example

For this topic, there are very limited examples from the existing tutorial code. You may either create your own examples or work with the [W3Schools Array Methods](https://www.w3schools.com/js/js_array_methods.asp) page which includes an interactive "Try it Yourself" feature. 

> Note: Consider using a browser based JavaScript IDE such as [Replit](https://replit.com/) which may used in interviewing scenarios. 

Begin with examples that review the use of common array methods that don't include callback functions, such as `push`, `pop`, `includes`, `indexOf`, and `join`. Draw attention to similarities to Java Collections methods.

### Next steps

Continue the discussion of array functions with those that use a callback function such as `filter`, `find`, `map`, `reduce`, `slice`, and `sort`. 

> Note: Students aren't introduced to the term **callback** from the LMS reading, but this is a very commonly used term in JavaScript programming. A **callback** is just a function that's passed into another function and called later. It's often an anonymous function, but it can also be a named function.

Use declared functions for this discussion to separate the understanding of a callback function from the topic of anonymous functions. 

For example:
```JavaScript
function doubleTheValue(num) {
  return num * 2;
}

let array = [1, 2, 3, 4, 5];
let doubles = array.map(doubleTheValue);
console.log('Original array', array);
console.log('Doubles array', doubles);
```


## Objective 3: Describe the difference between an anonymous function and a named function

### Opening

On the surface, an anonymous function is just a function without a name. They're often used when a function is only needed for use in a single, specific place an application. Anonymous functions frequently appear as **callback functions**, serving as arguments in another function call. 

### Example

Rewrite the examples used for the previous topic using anonymous functions. If students are struggling with arrow syntax, you may want to introduce an intermediate solution featuring the conventional (non-arrow) syntax.

For example, begin with the previous example of `map`:
```JavaScript
function doubleTheValue(num) {
  return num * 2;
}

let array = [1, 2, 3, 4, 5];
let doubles = array.map(doubleTheValue);
console.log('Original array', array);
console.log('Doubles array', doubles);
```

Next move the function syntax into the map function, removing the name:
```JavaScript
let doubles = array.map( function (num) {
        return num * 2;
    }
);
```

Then convert to arrow notation, removing the word `function` and adding the `=>`:
```JavaScript
let doubles = array.map( (num) => {
        return num * 2;
    }
);
```

Remove the parentheses around the parameters. These are optional if there is only one parameter, but required otherwise:
```JavaScript
let doubles = array.map( num => {
        return num * 2;
    }
);
```

Remove the curly braces around the body and the word `return`. Braces are optional if there if the body consists of a single return statement:
```JavaScript
let doubles = array.map( num => num * 2 );
```

### Next steps

Optionally, discuss the terms **lambda** and/or **closure** which can also come up interviews. 