-- Write your PostgreSQL query statement below
select 
p.product_name,
sum(o.unit) as unit
from Products p left join Orders o
on p.product_id = o.product_id 

where 
extract(year from o.order_date ) = 2020 
and 
extract(month from o.order_date ) = 02

group by product_name
having sum(o.unit) >= 100