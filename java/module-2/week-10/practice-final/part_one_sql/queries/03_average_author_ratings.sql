-- 3. List the first and last name of all authors, separated by a space, (name the column 'author') and the average star rating of their books (name the column 'average_rating').
-- Round average_rating to 2 decimal places.
-- Order by the average rating, lowest first.
-- (16 rows)

SELECT first_name || ' ' || last_name AS author, ROUND(AVG(star_rating), 2) AS average_rating 
FROM book
INNER JOIN book_author ON book.book_id = book_author.book_id
INNER JOIN person ON book_author.author_id = person.person_id
GROUP BY author
ORDER BY average_rating
