# Week 8 Review - File input and output

## Primary objectives

- **Open a file for reading and read line by line**
- Use `try-with-resources` to properly open and close a file handle
- **Open a file for writing**
- Handle errors in file reading and writing properly
- **Write tests around file IO errors and process code**


## Objective 1: Open a file for reading and read line by line

### Opening

Review basics related to file systems like hierarchical directory structure and absolute vs. relative paths. Also make sure students know what text files are and how to open a text file and see how many lines it contains.

Some common difficulties with reading files are:
* Knowing which folder is the current directory when identifying files with relative paths
* Mistakenly creating a `Scanner` with a `String` rather than a `File` object
* Structuring a loop correctly to avoid reading too many or too few lines

### Example

In the Vacation Expense Estimator project, search for `//Example: reading a file with a Scanner`.

Point out the try-with-resources block and discuss how it differs from a normal `try` block. You might temporarily replace the code that's there with a normal `try` and a call to `Scanner.close()` in a `finally` block so they can see what try-with-resources is doing.

Make sure students understand how the loop is iterating through the file one line at a time. Discuss what changes you'd need to make if you needed to work with two or three lines at a time rather than just one.

### Next steps

The line by line approach isn't the only way to read a file. Students are likely to encounter code that reads an entire file at a time, and the Java standard library contains methods like `Files.readString()` to facilitate the whole file approach. Discuss situations when the line by line approach is preferable. For example, when processing very large files or files of unknown size, or only needing to process a portion of a file.

## Objective 2: Open a file for writing

### Opening

Discuss the concept of buffering. It's helpful for students to understand that when they write to a file using Java, the data is typically kept in a buffer until it reaches a certain size (a bucket that gets emptied when it's full is a good analogy). This approach helps improve performance since writing to disk is relatively slow.

Some common difficulties with writing to files are:
* Confusing input and output files when reading from one file and writing to another
* Knowing how to append to a file rather than overwriting it
* Not seeing data in output file while debugging

### Example

There is an example of writing to a file in the Vacation Expense Estimator that you can find by searching for `//Example: writing to a file with a PrintWriter`. It's a very minimal example, however. The tutorial for today is a more complex example, including reading and writing in the same try-with-resources block and appending to a file.

If you want to dive into something more hands-on, you could work with the students to add a basic logging class to the Vacation Expense Estimator. Here's an example of what a class like that might look like:

```java
public class BasicLogger {
    public static final String FILENAME = "log.txt";
	private static PrintWriter pw = null;
	
	public static void log(String message) {
        if (pw == null) {
            try { // not using try-with-resources to keep the log file open
                pw = new PrintWriter(new FileOutputStream(FILENAME, true));
            } catch (FileNotFoundException e) {
                // could use a custom exception here, but should be unchecked so every call
                // to BasicLogger.log() doesn't need to be wrapped in a try block
                throw new RuntimeException("Failed to open log file: " + e.getMessage());
            }
        }
        pw.println(LocalDateTime.now() + " " + message);
        pw.flush(); // flush the buffer to ensure the message is written to disk
	}
}
```

### Next steps

In addition to creating files, replacing files, and appending to files, there are other operations related to output like creating folders, checking if files exist, renaming files, and deleting files. Discuss what changes you'd make to the logging class if you wanted to keep a separate log file for each day and only retain the last week's worth, and keep all those files in their own folder. 

## Objective 3: Write tests around file IO errors and process code

### Opening

In the LMS, show students [the chart that lists various types of testing](
https://lms.techelevator.com/content_link/gitlab.com/te-curriculum/unit-testing-java-lms/01_Unit_Testing/03-other-types-of-testing.md).

A test that interacts with the file system is an example of an integration test. There are various approaches to doing this kind of testing. For example, you can create specially prepared text files for the tests to use (stored under the `src/test/resources` folder) or you can create temporary files from within the tests. The Java standard library includes methods for creating temporary files with unique names stored in an appropriate location (that varies depending on the operating system).

### Example

The Vacation Expense Estimator project contains examples of tests that involve file IO. You can find them by searching for:
* `//Example: testing reading with a temporary file`
* `//Example: testing writing with a temporary file`

Point out to students that these tests still follow the Arrange-Act-Assert pattern they're familiar with.

Some of the code in these tests that handles file IO may look unfamiliar to students. This is a good opportunity to discuss that Java includes a variety of ways to interact with files. Make sure they understand that the approach they've been using would also work in these tests. It's important for them to be aware that there are other possibilities, however, since they're likely to encounter them when looking at examples on the internet or at their future workplace.

### Next steps

To dig into Java's "old" vs. "new" file IO systems further, you can show students this page that includes a table that matches up corresponding methods:
https://docs.oracle.com/javase/tutorial/essential/io/legacy.html
