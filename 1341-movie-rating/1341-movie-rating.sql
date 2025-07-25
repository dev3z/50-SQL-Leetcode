-- Write your PostgreSQL query statement below
with mycte as(
select u.name 
from Users u 
 join  MovieRating m on
u.user_id  = m.user_id
group by u.user_id, u.name
    ORDER BY COUNT(*) DESC, u.name ASC
LIMIT 1),

mycte2 as(
select u.title
from Movies u 
 join MovieRating m on 
 u.movie_id  = m.movie_id
  where extract(year from m.created_at ) = 2020 and 
     extract(month from m.created_at) = 02
group by u.movie_id, u.title
    ORDER BY AVG(m.rating) DESC, u.title ASC
   

LIMIT 1)

SELECT name AS results FROM mycte
UNION ALL
SELECT title FROM mycte2;