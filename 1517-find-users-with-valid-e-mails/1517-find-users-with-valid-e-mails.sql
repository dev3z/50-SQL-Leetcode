-- Write your PostgreSQL query statement below
SELECT *
FROM Users
where mail ~ '^[a-zA-Z][a-zA-Z0-9_.-]*@leetcode\.com$'