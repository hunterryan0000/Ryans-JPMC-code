-- 1. List the titles of all books, the name of the publisher, and the date published. 
-- Order the results by published date, earliest first.
-- (33 rows)

SELECT book_title, publisher_name, published_date
FROM book b
JOIN publisher p ON b.publisher_id = p.publisher_id
ORDER BY published_date;
