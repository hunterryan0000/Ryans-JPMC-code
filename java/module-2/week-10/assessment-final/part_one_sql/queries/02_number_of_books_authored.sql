-- 2. List the first and last name of all authors (name the column 'author') and the number of books they've written (name the column 'num_books').
-- Order the results by the number of books in descending order (highest first), then by alphabetical order of the author's first name.
-- Tip: make sure to add a space between the author's first and last names.
-- (16 rows)

SELECT (first_name || ' ' || last_name) AS author, COUNT(person_id) AS num_books
FROM person p
JOIN book_author ba ON p.person_id = ba.author_id
GROUP BY p.person_id
ORDER BY COUNT(person_id) DESC, first_name;
