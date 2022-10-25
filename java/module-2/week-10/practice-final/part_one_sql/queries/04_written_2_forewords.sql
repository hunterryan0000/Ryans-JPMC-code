-- 4. List the first and last name of all persons, separated by a space, (name the column 'full_name') who have written at least 2 forewords.
-- Include the count of forewords written by each person (name the column 'foreword_count').
-- Order by full_name ascending.
-- (7 rows)

SELECT first_name || ' ' || last_name AS full_name, foreword_count 
FROM (
    SELECT foreword_by, COUNT(foreword_by) AS foreword_count 
    FROM book 
    GROUP BY foreword_by
) AS sub
    INNER JOIN person ON sub.foreword_by = person.person_id
    WHERE foreword_count >= 2
ORDER BY full_name ASC;
