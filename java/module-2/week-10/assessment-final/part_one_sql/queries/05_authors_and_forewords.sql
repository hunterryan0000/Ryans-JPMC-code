-- 5. List all book titles and the first and last name of the person who wrote the foreword (name the column 'foreword_author') for books that Moishe Reiling was an author.
-- Order by book title (A-Z).
-- Tip: make sure to add a space between the author's first and last names.
-- (5 rows)

SELECT book_title, (pf.first_name || ' ' || pf.last_name) AS foreword_author
FROM book b
JOIN book_author ba ON b.book_id = ba.book_id
JOIN person pa ON ba.author_id = pa.person_id
JOIN person pf ON b.foreword_by = pf.person_id
WHERE pa.first_name = 'Moishe' AND pa.last_name = 'Reiling'
ORDER BY book_title;
