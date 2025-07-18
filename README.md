# SQL Cheat Sheet

---

## 1. Basic SQL Syntax
- **SELECT**: Retrieve data from a table.
  ```sql
  SELECT column1, column2 FROM table_name WHERE condition;
  ```
- **DISTINCT**: Remove duplicates.
  ```sql
  SELECT DISTINCT column_name FROM table_name;
  ```
- **LIMIT/OFFSET**: Limit the number of rows returned.
  ```sql
  SELECT * FROM table_name LIMIT 10 OFFSET 5;
  ```

---

## 2. Filtering Data
- **WHERE Clause**: Filter rows.
  ```sql
  SELECT * FROM table_name WHERE condition;
  ```
- **Operators**:
  - Comparison: `=`, `!=`, `<`, `>`, `<=`, `>=`
  - Logical: `AND`, `OR`, `NOT`
  - Pattern Matching: `LIKE`, `ILIKE` (case-insensitive), `%` (wildcard)
  - NULL: `IS NULL`, `IS NOT NULL`

---

## 3. Aggregate Functions
- Functions applied to groups of data:
  - `COUNT()`: Number of rows.
  - `SUM()`: Sum of values.
  - `AVG()`: Average value.
  - `MIN()`, `MAX()`: Minimum and maximum values.
  ```sql
  SELECT COUNT(*), AVG(column_name) FROM table_name WHERE condition;
  ```

---

## 4. Grouping and Aggregation
- **GROUP BY**: Group rows sharing a property.
  ```sql
  SELECT column1, SUM(column2) FROM table_name GROUP BY column1;
  ```
- **HAVING**: Filter groups (used with `GROUP BY`).
  ```sql
  SELECT column1, SUM(column2) 
  FROM table_name
  GROUP BY column1
  HAVING SUM(column2) > 100;
  ```

---

## 5. Sorting Data
- **ORDER BY**: Sort results.
  ```sql
  SELECT * FROM table_name ORDER BY column_name ASC|DESC;
  ```

---

## 6. Joining Tables
- **INNER JOIN**: Matching rows in both tables.
  ```sql
  SELECT a.column1, b.column2
  FROM table1 a
  INNER JOIN table2 b ON a.id = b.id;
  ```
- **LEFT JOIN**: All rows from the left, matching from the right.
  ```sql
  SELECT * FROM table1 LEFT JOIN table2 ON table1.id = table2.id;
  ```
- **RIGHT JOIN**: All rows from the right, matching from the left.
- **FULL OUTER JOIN**: All rows from both tables.

---

## 7. Subqueries
- **Subquery in SELECT**:
  ```sql
  SELECT column1, (SELECT MAX(column2) FROM table2) AS max_value FROM table1;
  ```
- **Subquery in WHERE**:
  ```sql
  SELECT * FROM table1 WHERE column1 IN (SELECT column2 FROM table2);
  ```

---

## 8. Common Table Expressions (CTEs)
- Temporary result set for reuse.
  ```sql
  WITH cte_name AS (
    SELECT * FROM table_name WHERE condition
  )
  SELECT * FROM cte_name WHERE condition2;
  ```

---

## 9. Window Functions
- Perform calculations across a set of rows related to the current row.
  ```sql
  SELECT column1, ROW_NUMBER() OVER (PARTITION BY column2 ORDER BY column3) AS row_num
  FROM table_name;
  ```
- Common window functions:
  - `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()`
  - `SUM()`, `AVG()`, `COUNT()` (used with `OVER`)

---

## 10. Modifying Data
- **INSERT**: Add new rows.
  ```sql
  INSERT INTO table_name (column1, column2) VALUES (value1, value2);
  ```
- **UPDATE**: Modify existing rows.
  ```sql
  UPDATE table_name SET column1 = value1 WHERE condition;
  ```
- **DELETE**: Remove rows.
  ```sql
  DELETE FROM table_name WHERE condition;
  ```

---

## 11. Advanced SQL
- **Case Statements**: Conditional logic.
  ```sql
  SELECT column1,
         CASE 
           WHEN condition1 THEN result1
           WHEN condition2 THEN result2
           ELSE result3
         END AS alias_name
  FROM table_name;
  ```
- **UNION**: Combine results of two queries.
  ```sql
  SELECT column1 FROM table1
  UNION
  SELECT column1 FROM table2;
  ```
- **EXISTS**: Check for existence of rows in a subquery.
  ```sql
  SELECT * FROM table1 WHERE EXISTS (SELECT 1 FROM table2 WHERE condition);
  ```

---

## 12. Indexes and Performance
- Use indexes to speed up queries.
- Avoid using `SELECT *` in production.
- Analyze query plans for optimization: `EXPLAIN` or `EXPLAIN ANALYZE`.

---

## 13. Practice Patterns for LeetCode SQL
- **Self-Joins**: Compare rows in the same table.
  ```sql
  SELECT a.id, b.id
  FROM table_name a, table_name b
  WHERE a.column1 > b.column1;
  ```
- **Ranking Problems**: Use `ROW_NUMBER()` or `RANK()`.
- **Top-N Queries**: Use `LIMIT` with `ORDER BY`.
- **Gaps and Islands**: Use window functions and `GROUP BY`.

---

## 14. Business Analyst-Specific SQL
- Revenue analysis:
  ```sql
  SELECT SUM(revenue) AS total_revenue, product_category
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

---

### Tips for LeetCode SQL Challenges
1. Read the problem carefully, focusing on constraints.
2. Use CTEs for clarity.
3. Test subqueries separately for debugging.
4. Always check edge cases (e.g., NULL values).
