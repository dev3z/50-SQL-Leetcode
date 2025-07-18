# SQL Cheat Sheet with Examples (For Business Analysts)

---

## 1. Basic SQL Syntax
- **SELECT**: Retrieve data from a table.
  ```sql
  SELECT name, age FROM employees WHERE age > 30;
  ```
- **DISTINCT**: Remove duplicates.
  ```sql
  SELECT DISTINCT department FROM employees;
  ```
- **LIMIT/OFFSET**: Limit the number of rows returned.
  ```sql
  SELECT * FROM employees LIMIT 5 OFFSET 10;
  ```

---

## 2. Filtering Data
- **WHERE Clause**: Filter rows.
  ```sql
  SELECT * FROM employees WHERE salary > 50000 AND department = 'HR';
  ```
- **Operators**:
  - Comparison: `=`, `!=`, `<`, `>`, `<=`, `>=`
    ```sql
    SELECT * FROM employees WHERE age >= 30;
    ```
  - Logical: `AND`, `OR`, `NOT`
    ```sql
    SELECT * FROM employees WHERE (age > 30 AND department = 'IT') OR salary > 60000;
    ```
  - Pattern Matching: `LIKE`, `%` (wildcard)
    ```sql
    SELECT * FROM employees WHERE name LIKE 'A%'; -- Names starting with 'A'
    ```
  - NULL: `IS NULL`, `IS NOT NULL`
    ```sql
    SELECT * FROM employees WHERE manager_id IS NULL;
    ```

---

## 3. Aggregate Functions
- Functions applied to groups of data:
  - `COUNT()`: Number of rows.
    ```sql
    SELECT COUNT(*) AS total_employees FROM employees;
    ```
  - `SUM()`: Sum of values.
    ```sql
    SELECT SUM(salary) AS total_salary FROM employees;
    ```
  - `AVG()`: Average value.
    ```sql
    SELECT AVG(salary) AS average_salary FROM employees WHERE department = 'IT';
    ```
  - `MIN()`, `MAX()`: Minimum and maximum values.
    ```sql
    SELECT MIN(salary) AS min_salary, MAX(salary) AS max_salary FROM employees;
    ```

---

## 4. Grouping and Aggregation
- **GROUP BY**: Group rows sharing a property.
  ```sql
  SELECT department, AVG(salary) AS avg_salary 
  FROM employees 
  GROUP BY department;
  ```
- **HAVING**: Filter groups (used with `GROUP BY`).
  ```sql
  SELECT department, SUM(salary) AS total_salary 
  FROM employees 
  GROUP BY department
  HAVING SUM(salary) > 200000;
  ```

---

## 5. Sorting Data
- **ORDER BY**: Sort results.
  ```sql
  SELECT * FROM employees ORDER BY salary DESC, age ASC;
  ```

---

## 6. Joining Tables
- **INNER JOIN**: Matching rows in both tables.
  ```sql
  SELECT e.name, d.department_name
  FROM employees e
  INNER JOIN departments d
  ON e.department_id = d.department_id;
  ```
- **LEFT JOIN**: All rows from the left, matching from the right.
  ```sql
  SELECT e.name, d.department_name
  FROM employees e
  LEFT JOIN departments d
  ON e.department_id = d.department_id;
  ```
- **RIGHT JOIN**: All rows from the right, matching from the left.
- **FULL OUTER JOIN**: All rows from both tables.

---

## 7. Subqueries
- **Subquery in SELECT**:
  ```sql
  SELECT name, (SELECT MAX(salary) FROM employees) AS highest_salary FROM employees;
  ```
- **Subquery in WHERE**:
  ```sql
  SELECT * FROM employees WHERE department_id IN (SELECT department_id FROM departments WHERE location = 'NYC');
  ```

---

## 8. Common Table Expressions (CTEs)
- Temporary result set for reuse.
  ```sql
  WITH high_salary_employees AS (
    SELECT * FROM employees WHERE salary > 80000
  )
  SELECT * FROM high_salary_employees WHERE department = 'IT';
  ```

---

## 9. Window Functions
- Perform calculations across a set of rows related to the current row.
  ```sql
  SELECT name, department, salary,
         ROW_NUMBER() OVER (PARTITION BY department ORDER BY salary DESC) AS rank
  FROM employees;
  ```
- Common window functions:
  - `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()`
  - `SUM()`, `AVG()`, `COUNT()` (used with `OVER`)

---

## 10. Modifying Data
- **INSERT**: Add new rows.
  ```sql
  INSERT INTO employees (name, age, department_id, salary)
  VALUES ('John Doe', 35, 3, 75000);
  ```
- **UPDATE**: Modify existing rows.
  ```sql
  UPDATE employees 
  SET salary = salary * 1.10 
  WHERE department = 'HR';
  ```
- **DELETE**: Remove rows.
  ```sql
  DELETE FROM employees WHERE age < 25;
  ```

---

## 11. Advanced SQL
- **Case Statements**: Conditional logic.
  ```sql
  SELECT name, 
         CASE 
           WHEN salary > 80000 THEN 'High'
           WHEN salary BETWEEN 50000 AND 80000 THEN 'Medium'
           ELSE 'Low'
         END AS salary_category
  FROM employees;
  ```
- **UNION**: Combine results of two queries.
  ```sql
  SELECT name FROM employees WHERE department = 'HR'
  UNION
  SELECT name FROM employees WHERE salary > 60000;
  ```
- **EXISTS**: Check for existence of rows in a subquery.
  ```sql
  SELECT * 
  FROM employees 
  WHERE EXISTS (SELECT 1 FROM departments WHERE departments.department_id = employees.department_id AND location = 'NYC');
  ```
- **Recursive CTEs**: Example for hierarchical data.
  ```sql
  WITH RECURSIVE EmployeeHierarchy AS (
    SELECT employee_id, name, manager_id
    FROM employees
    WHERE manager_id IS NULL
    UNION ALL
    SELECT e.employee_id, e.name, e.manager_id
    FROM employees e
    INNER JOIN EmployeeHierarchy eh
    ON e.manager_id = eh.employee_id
  )
  SELECT * FROM EmployeeHierarchy;
  ```

---

## 12. Indexes and Performance
- Use indexes to speed up queries.
  ```sql
  CREATE INDEX idx_salary ON employees(salary);
  ```
- Avoid using `SELECT *` in production.
- Analyze query plans for optimization: `EXPLAIN` or `EXPLAIN ANALYZE`.

---

## 13. Practice Patterns for LeetCode SQL
- **Self-Joins**: Compare rows in the same table.
  ```sql
  SELECT a.id, b.id
  FROM employees a, employees b
  WHERE a.salary > b.salary AND a.department = b.department;
  ```
- **Ranking Problems**: Use `ROW_NUMBER()` or `RANK()`.
  ```sql
  SELECT name, salary, RANK() OVER (ORDER BY salary DESC) AS rank
  FROM employees;
  ```
- **Top-N Queries**: Use `LIMIT` with `ORDER BY`.
  ```sql
  SELECT name, salary FROM employees ORDER BY salary DESC LIMIT 5;
  ```
- **Gaps and Islands**: Use window functions and `GROUP BY`.
  ```sql
  SELECT department, COUNT(*) AS employee_count
  FROM employees
  GROUP BY department;
  ```

---

## 14. Business Analyst-Specific SQL
- Revenue analysis:
  ```sql
  SELECT product_category, SUM(revenue) AS total_revenue
  FROM sales_data
  GROUP BY product_category
  ORDER BY total_revenue DESC;
  ```
- Customer segmentation:
  ```sql
  SELECT customer_id, COUNT(*) AS purchase_count
  FROM purchases
  GROUP BY customer_id
  HAVING COUNT(*) > 5;
  ```
- Monthly trend analysis:
  ```sql
  SELECT DATE_TRUNC('month', sale_date) AS month, SUM(revenue) AS total_revenue
  FROM sales
  GROUP BY DATE_TRUNC('month', sale_date)
  ORDER BY month;
  ```

---

### Tips for LeetCode SQL Challenges
1. Read the problem carefully, focusing on constraints.
2. Use CTEs for clarity.
3. Test subqueries separately for debugging.
4. Always check edge cases (e.g., NULL values).
