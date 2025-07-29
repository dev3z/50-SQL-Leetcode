-- Write your PostgreSQL query statement below
SELECT e.name  
FROM
Employee e 
where e.id in 
 (select managerId  from 
 Employee
 group by managerId 
having count(*) >= 5
)