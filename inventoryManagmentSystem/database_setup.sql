-- Database setup script for Inventory Management System
-- Run this script in PostgreSQL to create the database

-- Create database (run this as superuser)
-- CREATE DATABASE inventory_db;

-- Connect to the database and run the following:

-- The application will automatically create tables using JPA/Hibernate
-- This script is for reference only

-- Note: The application uses Spring Boot with JPA/Hibernate
-- which will automatically create the following tables:
-- - users
-- - products  
-- - cart
-- - orders
-- - order_items
-- - inventory

-- Sample data will be automatically inserted by the DataInitializer class
-- when the application starts for the first time.

-- To manually create the database:
-- 1. Install PostgreSQL
-- 2. Open psql or pgAdmin
-- 3. Run: CREATE DATABASE inventory_db;
-- 4. Update application.properties with your database credentials
-- 5. Start the Spring Boot application

-- The application will handle table creation and sample data insertion automatically.