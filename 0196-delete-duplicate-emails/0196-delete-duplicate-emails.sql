-- Write your PostgreSQL query statement below
DELETE from Person
where id not in 
(
select min(id) from Person group by email
)