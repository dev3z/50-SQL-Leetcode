select 
query_name,
round(avg(1.0*rating / position), 2) as quality,

round(
100.0*count(case when rating  < 3 then rating else null end)/ count(rating),2
)
as poor_query_percentage 

from Queries
group by query_name 