-- Write your PostgreSQL query statement below
        select p.project_id,
        round(AVG(experience_years),2) as average_years 
        from Project  p join Employee e on 
        p.employee_id  = e.employee_id 
        group by project_id  
        order by  project_id  

