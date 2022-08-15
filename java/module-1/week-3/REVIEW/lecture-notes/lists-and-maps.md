# Week 3 Review - Lists and Maps

**Note:** There is no lecture code for this week, because all the examples are in the Vacation Expense Estimator project. If you need simpler examples to address student questions, you can use this week's tutorials.

## Primary objectives

- Familiar with the commonly used collections classes from the standard library
- Choose between a `List`, `Set`, or `Map` for modeling real-world data
- Describe the differences between a `List` and an array
- **Declare and instantiate instances of `List` and `Map`**
- **Add, retrieve, delete, and modify values in a collection**
- **Iterate over the values of a collection**


## Objective 1: Declare and instantiate instances of `List` and `Map`

### Opening

Ask students to describe some differences between an array and a `List`, then ask about the differences between a `List` and a `Map`. Discuss various collections of data for which each of these would be an appropriate choice. For example:
* the answers for a multiple-choice question that always has four options (array)
* a pair of related primitive values (array)
* expense amounts to total up (`List`)
* text messages (`List`)
* student ids and grades (`Map`)
* state names and populations (`Map`)

Some common difficulties with declaring and instantiating a `List` or `Map` are:
* the mismatch between the declared type (`List`) and the instantiated type (`ArrayList`)
* the generic syntax--where to put the angle brackets and what to put inside them
* trying to create a collection of primitives rather than the appropriate wrapper class

### Example

Since students haven't learned about interfaces and polymorphism yet, their understanding can't go very deep regarding the use of the `List` and `Map` interfaces when declaring variables. Reassure them that they'll learn more about interfaces later, and that for now it's a good pattern to start getting used to, even if they don't completely understand why they're using it.

The Vacation Expense Estimator contains several examples of declaring and instantiating `List`s and `Map`s, which you can find by searching for:
* `//Example: instantiating an ArrayList of Strings`
* `//Example: instantiating an ArrayList of Expenses`
* `//Example: instantiating a HashMap with String keys and Activity values`
* `//Example: instantiating a LinkedHashMap with String keys and String values`

(`LinkedHashMap` differs from a `HashMap` in keeping the entries in the same order they're added in.)

### Next steps

Challenge students to think about how they would declare more complex collections like a `List` of arrays, a `List` of `List`s, or a `Map` in which each value is a `List`.

If students ask about `List` and `Map` implementations other than `ArrayList` and `HashMap`, let them know those are the most commonly used, but the full list is available in the Java documentation at:
* https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html
* https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html


## Objective 2: Add, retrieve, delete, and modify values in a collection

### Opening

Discuss this table with students to review how to work with an array, a `List`, and a `Map`. (An image version of this named `operations.png` is in the same folder as these notes to make it easier to display for students.)

* `myArray` is an array containing 3 `String` elements: "a", "b", "c"
* `myList` is a `List<String>` containing the values: "a", "b", "c"
* `myMap` is a `Map<Integer, String>` containing the key-value pairs: 100 = "a", 200 = "b", 300 = "c"

|                      | array            | `List`             | `Map`                  |
|----------------------|------------------|--------------------|------------------------|
| set x to "c"         | x = myArray[2]   | x = myList.get(2)  | x = myMap.get(300)     |
| replace "a" with "z" | myArray[0] = "z" | myList.set(0, "z") | myMap.put(100, "z")    |
| add "d"              | (need new array) | myList.add("d")    | myMap.put(400, "d")    |
| delete "b"           | (need new array) | myList.remove(1)   | myMap.remove(200)      |


Some common difficulties when working with collections are:
* forgetting to instantiate the collection before calling one of its methods
* converting an array to a `List` or a `List` to an array
* unnecessarily searching through a `Map` for a value rather than using the value's key

### Example

The Vacation Expense Estimator contains examples of working with a `List` and a `Map` which you can find by searching for:
* `//Example: working with a List`
* `//Example: working with a Map` (note that there's no need to iterate through all the values)

The method that includes the comment `//Example: converting a Set to an array` is an example of the `keySet()` and `toArray()` methods. Make sure students understand that the keys of a `Map` are a `Set` since each is unique, and note that a `List` also has a `toArray()` method for converting it to an array.

To go the other way, from an array to a `List`:
```java
String[] myArray = {"a", "b", "c"};
//Arrays.asList returns a List, but it is immutable.
List<String> myUnmodifiableList = Arrays.asList(myArray);
//Make a copy by passing it to the constructor of a new ArrayList.
List<String> myModifiableList = new ArrayList<>(myUnmodifiableList);
```

### Next steps

Beyond the basic operations, the Java standard library contains many methods for working with collections, like `reverse()`, `sort()`, and `shuffle()`. These are part of the `Collections` class:
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collections.html

Students also encountered two other collections in their reading this week: `Stack` and `Queue`. There's no need to dive into the details of working with those interfaces, but it's valuable for students to understand them conceptually, and when they might be appropriate choices rather than the much more commonly used `List`.


## Objective 3: Iterate over the values of a collection

### Opening

Ask students what the advantages and disadvantages are of a for loop vs. a foreach loop. Some possible answers:
* for loop
    * hard to use if items aren't accessible by index (like items in a `Set`)
    * can go backwards through items
    * can skip items while iterating
    * facilitates referencing other items (`i + 1` or `i - 1`, for example)
* foreach loop
    * more succinct syntax
    * saves a step of retrieving the current value
    * only for iterating one by one, first to last

Some common difficulties with iterating through a collection are:
* choosing between a for and foreach loop
* trying to add or remove items from a collection while iterating through it
* working with `Map.Entry`

### Example

The Vacation Expense Estimator has examples of iterating with for and foreach loops. Make sure students understand why the kind of loop that's used is appropriate for the situation. You can find the examples by searching for:
* `//Example: iterating with foreach`
* `//Example: iterating through an array`

Search for `//Example: iterating with Map.Entry` for an example of iterating through a `Map`.

### Next steps

If you only want to iterate through the keys or values of a `Map`, you can use the `keySet()` or `values()` method rather than the `entrySet()` method.

When iterating through a set (including the entry set of a `Map`), the the type of set determines the order:
* `HashSet` (and entries of `HashMap`): sorted by hash (which usually appears random)
* `LinkedHashSet` (and entries of `LinkedHashMap`): kept in order added to the set
* `TreeSet` (and entries of `TreeMap`): sorted "naturally" (alphabetically, numerically, etc.) depending on data type

You can demonstrate this using the tutorial and changing the type of `Map`.
