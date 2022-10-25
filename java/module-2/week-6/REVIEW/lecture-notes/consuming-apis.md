# Week 6 Review - Consuming APIs

## Primary objectives

- Explain the purpose of a DNS and IP Addresses.
- Identify and explain parts of a URL.
- Identify and explain the purpose of the main components of HTTP Requests and Responses.
- **Explain the steps of a typical HTTP request between a web browser and a server.**
- Explain what JSON is and how it is used in a Java / C# program.
- Identify resources in a RESTful API, describe the relationship between a resource and a URL.
- Explain how the different HTTP methods are used to interact with RESTful resources.
- **Use Postman to make a GET, PUT, POST, or DELETE request and inspect the result**
- **Make HTTP GET, POST, PUT, an DELETE requests to a RESTful web service using Java / C# and process the response.**
- Handle an error from RESTful API requests 


## Objective 1: Explain the steps of a typical HTTP request between a web browser and a server

> Note: As an interview question, students are often asked to describe what happens behind the scenes when a browser loads a web page. Some employers are looking for just an understanding of HTTP requests and responses, while others want to know the candidate understands the complete picture, including DNS lookup and network handling. 

### Opening

Understanding how to work with HTTP requests and responses is fundamental to web application development. It's essential to prepare students to discuss this topic conceptually in interviews and use the correct technical terms while doing so. 

Some common points of confusion with this topic might be:

- Using terminology correctly - for example, interviewers aren't looking to hear that a browser *downloads* a web page.
- The idea of one HTTP request and response per resource. The browser makes many requests to load a single web page.
- HTTP requests are stateless. There is no connection or relationship maintained between requests. All information needed is on the request itself.
- Separating the conceptual ideas of the client and the server - This can get missed when everything is running on the same computer. 

### Example

The LMS reading includes an animated image showing HTTP Communication between a client and a server. Following this is a more in-depth walk-through of using the browser developer tools to see how a small web page ([TE Local Weather](https://te-weather.netlify.app/)) loads through a series of HTTP requests and responses. Talk through this or similar examples, preparing the students to describe this process in their own words.

As the next objectives focus on REST API requests, you might call attention to the API request to get the weather data as a lead in to the discussion of APIs.

### Next steps

You can bring the discussion back to web addressing and how DNS servers get used to add depth to the topic. While it may come up in interviews, having a solid understanding of this piece isn't critical to success in the program. However, this can be a good opportunity to dive deeper into this topic if there is additional interest. For example, you could use the `ping` command in the terminal to show a website's IP address.
 

## Objective 2: Use Postman to make a GET, PUT, POST, or DELETE request and inspect the result

> Note: This week's pair exercise uses Postman extensively. Make sure students are comfortable using the tool before they start the exercise. Remind them that there are videos walking through this content in the LMS Intro to Tools section. 

### Opening

An understanding of how a browser gets a web page from a server is a great foundation for students, but working with the browser address bar is limited to only HTTP GET requests. A tool like Postman is needed to make other types of requests and provides better support for examining all types of requests and responses in more detail. 

Ensure students are comfortable:

- changing the HTTP request method
- adding both query string and URL parameters
- adding a body to a request

### Example

Note: It's recommended *not* to use the module 2 sample application for this lecture, so that the same examples used here can also get used when discussing the following objective. Much of the module 2 sample application requires Auth. While this is relatively transparent when using the Postman collection, the client code uses the `exchange` method which gets taught in a later unit. 

Use examples from the Consuming RESTful_APIs_Part_2 tutorial. Use the `tutorial-final` project and start the `json-server`. 

The URLs to use for the Postman requests are:

- GET all - http://localhost:3000/locations/
- GET by id - http://localhost:3000/locations/{id} 
- POST - http://localhost:3000/locations/ 
- PUT - http://localhost:3000/locations/{id}
- DELETE - http://localhost:3000/locations/{id}

Where needed, get valid id values and a template to use for the body from previous results.

Terminology regarding URLs, such as ports and resource paths, is important as students move forward working with servers and REST APIs. As you work through examples, make sure to call-out these parts of the URLs as you use them.

### Next steps

To further the discussion, you may look at a third-party API, showing how to use their documentation to learn how to use endpoints and making requests to the API through Postman. To do this, use either an API that doesn't require an API key for use, or one where it's fast to get a key to use. 

Two APIs that you might use that don't require a key are:
 - the PokéAPI - an educational Pokémon API: https://pokeapi.co/
 - SWAPI - the Star Wars API: https://swapi.dev/

An alternative if you don't want to look at a different API would be to demonstrate creating, importing, and exporting Postman collections. There is an example collection in the Module 2 Sample application. 

## Objective 3: Make HTTP GET, POST, PUT, an DELETE requests to a RESTful web service using Java / C# and process the response

### Opening

This unit expands the use of the Spring framework into RESTful Web Services. Students use the `RestTemplate` class in these units to consume an API. This continues to build over the subsequent units as students learn to use Spring to build their REST APIs.

> Note: This unit focuses on command-line client applications, which may feel less real-world to students. It's essential to help students keep this in perspective and see that they're building toward real-world usage scenarios. For example, it would not be uncommon to see `RestTemplate` used in server-side code, making requests to another REST API server. Students do this later, after learning to write code to build REST API servers.

Ensure students are comfortable with:

- which HTTP method types use a request body and/or URL parameters. 
- which RestTemplate method to use `getForEntity`, `getForObject`, `postForObject`, `put`, `delete`. It's fine to ignore `getForEntity` in favor of `getForObject`. (Note: The Auth unit later teaches the `exchange` method to send the auth header, which *replaces* the use of these methods.)
- what type these methods return, and how to get the response body from that returned type

### Example

Use examples from the Consuming RESTful_APIs_Part_2 tutorial, `LocationService` class. (Use the `tutorial-final` project to show the code). 

Walk through each method, focusing on:
- the request URL, specifically where it's REST best practice to send URL parameters
- the request body, calling out where one gets sent and not sent
- the response body, focusing on the RestTemplate method parameter that communicates the type the body gets converted to when returned.

### Next steps

Two options for furthering the discussion:

- Show or write code to make requests to a third-party API (examples listed in the previous objective). This example would likely focus on making GET requests and would require some advance prep and setup for some starter code. 
- Introduce the `exchange` method without getting into headers and Auth. This paves the way for that method later, and offers one method to use for all types of requests. There are examples in the Module 2 Sample Application. In the Admin Client, look at the TagService or BookmarkService classes. 