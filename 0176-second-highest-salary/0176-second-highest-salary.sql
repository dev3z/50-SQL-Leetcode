
SELECT max(e1.salary) AS SecondHighestSalary 
From Employee e1 inner join Employee e2
 on e1.salary < e2.salary
 
