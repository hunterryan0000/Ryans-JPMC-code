-- 3. List the publisher name and the highest star rating for each publisher (name the column 'highest_rating'). 
-- Order the results by rating, highest first.
-- (4 rows, starting with T&E Publishing)

SELECT publisher_name, MAX(star_rating) AS highest_rating
FROM book b
JOIN publisher p ON b.publisher_id = p.publisher_id
GROUP BY p.publisher_name
ORDER BY MAX(star_rating) DESC;
