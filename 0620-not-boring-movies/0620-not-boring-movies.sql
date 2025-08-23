-- Write your PostgreSQL query statement below
select * from 
cinema 
WHERE description <> 'boring'
  AND id % 2 <> 0
  order by  rating  desc