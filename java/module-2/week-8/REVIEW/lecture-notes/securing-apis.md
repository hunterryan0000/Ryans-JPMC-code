# Week 8 Review - Securing APIs

> Note: It's important to let students know that, in most cases, they're likely joining a team that already has authentication and authorization implemented. Only on rare occasions are junior developers likely to implement security for a company's API or web application.

## Primary objectives

- **Define and differentiate between the terms "authentication" and "authorization" as they pertain to a client-server or Web application**
- Provide examples of multiple forms of authentication used by software applications (e.g. username/password, biometric, API key, etc)
- **Describe the general mechanics and workflow of how to use JSON Web Tokens (JWTs) to authenticate users of a client-server (including Web) application**
- Use a common tool to decode an encoded JWT to inspect its contents
- Recognize and interpret the HTTP response status codes commonly associated with authentication and authorization failures (i.e. 401 and 403)
- Write client code in Java or C# that can authenticate with an authentication server to retrieve a JWT, and then use the JWT to authenticate subsequent requests to a Web API.
- **Utilize the auth features of an application framework (Spring Boot)**
    - Specify a particular resource with anonymous access, that doesn't require authentication
    - Specify a particular resource that requires authentication for access
    - Apply simple authorization rules for resources
    - Obtain the identity of an authenticated user


## Objective 1: Define and differentiate between the terms "authentication" and "authorization" as they pertain to a client-server or Web application

> Note: Interviewers often ask students to define and differentiate between authentication and authorization.

### Opening

Authentication and authorization are fundamental to securing APIs. As developers, it's essential to understand how to protect sensitive information and limit who has access to it.

Focus on ensuring that students use the correct terminology. When it comes to **authentication** they need to realize there are multiple ways of identifying oneself. Some examples that they might be familiar with include:

* Using a password, PIN, or security question (authentication)
* Using a one-time code sent as SMS or email (authentication)
* Relying on a fingerprint or face scan (authentication)

Students need to know that **authorization**is about determining a user's permissions to access a resource or function.

A restaurant analogy might resonate. All employees can take a customer's order, but only the cook can make it. 

### Example

The Module 2 Bookmark Manager application includes endpoints requiring authentication and specific authorization levels. You can demonstrate the types of responses a client might receive when invoking an API that implements authentication and authorization.

You'll find the Postman collection for the sample application at `bookmark-server/postman`.

Before authenticating, try submitting the "Get all users" request. Have the students note the status code returned is HTTP 401. 

Try submitting the request again after logging in with a non-admin user, and note that the status code is now HTTP 403.


## Objective 2: Describe the general mechanics and workflow of how to use JSON Web Tokens (JWTs) to authenticate users of a client-server or Web application

### Opening

Before going far into JSON Web Tokens (JWT), explain the pattern of generating a token to be presented later.

A stretch analogy that students might relate to is a coat check. The guest receives a token, which they must present at a later date to claim their coat.

Common misconceptions related to JWTs include:

* the client can generate or manipulate the token
* the token gets encrypted (it's usually signed with public/private key pairs)
* when and how to include the token as an authentication header in subsequent requests
* the difference between the header, payload, and signature

### Example

Use [jwt.io](https://jwt.io) to encode/decode a few JWTs. You can have students decode the following tokens:

**Jane (Student)**
```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKYW5lIChTdHVkZW50KSIsImF1dGgiOlsiSkFWQV9TVFVERU5UIiwiUEFUSFdBWV9TVFVERU5UIl0sImV4cCI6MTY1OTU2MTE0Nn0.ILf4nWzM_vYThG5njI9MA9JuKC0cVZdgQdvNE0QZk_2cj8cEK7BjpSoCvvqFHnbhSyVFOAnDeRW0oNh3QXkKNw
```

**Sandy (Instructor)**
```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJTYW5keSAoSW5zdHJ1Y3RvcikiLCJpZCI6IjMzNzE0NSIsImF1dGgiOlsiSkFWQV9JTlNUUlVDVE9SIiwiRU1QTE9ZRUUiXSwiZXhwIjoxNjU5NTYxMTQ2fQ.uIJp9-fZkJ1dJbc3lO1ybNHSG19Lq1rM6epLSoTFY7DY0mV-v0McT4dKlatc9ttLuBVtc52HwnUsrbPlOU06nw
```

After looking at these JWTs, try running the sample application and inspecting the token that gets returned from the server.

### Next Steps

There are a few different options that you can consider with the class if there is time:

* show students how to apply a Bearer token for all requests in a collection
* discuss the role that the application secret plays in verifying that the token is authentic

## Objective 3: Utilize the auth features of an application framework (Spring Boot)

### Opening

Open by making it clear that most web applications students work on require auth. Most frameworks provide a process or pattern for enforcing auth. Using framework-provided approaches ensures that auth rules get applied consistently throughout the application. Spring Security gives the means to enforce auth with the `@PreAuthorize` annotation.

The `@PreAuthorize` annotation provides class-level or method-level security. When you use `@PreAuthorize`, the auth process runs before entering the handler method. `@PreAuthorize` makes it possible to check if the caller has authenticated and if they have the appropriate role to access this endpoint. **Ask the students to brainstorm ideas where one's auth status might determine if they can invoke an API endpoint.**

`@PreAuthorize` doesn't cover all situations. There may be instances that require resource-level enforcement. **Continue the discussion and ask students for cases in which knowing one's role isn't enough to determine if the caller can invoke an API endpoint.**

Some common misconceptions or areas that typically trip students up include:

* not realizing that adding `@PreAuthorize` introduces a new step in the request process
* terminology like *Principal*, *Identity*, *roles*, and *claims*
* what argument to pass to `@PreAuthorize`
* precedence when adding class-level and method-level annotations

### Example

The sample application includes many examples of `@PreAuthorize` (applied at the class level and method level) and `Principal` (used at the code level).

In their own words, have students explain what auth checks each controller and handler method check against.

### Next Steps

Some next steps might include discussion or additional code demonstration around the following topics:

* where in the `AuthenticationController` the caller's credentials get verified so that the server can invoke a JWT
* reasons why it's a bad practice to accept user identifiers as request parameters, and it's better to rely on `Principal`