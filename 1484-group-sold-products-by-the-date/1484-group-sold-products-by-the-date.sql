select 

sell_date,
count(distinct product) as num_sold,
STRING_AGG(distinct product, ',' ORDER BY product) AS products

from Activities 
GROUP BY sell_date
ORDER BY sell_date;  