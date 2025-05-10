# Employee Management Service

This is a Spring Boot REST API project developed for an interview assessment at **Sitech**.

## Features

- Create new employee
- Get employee by ID
- Search employees with filters:
  - Name (partial match on first or last name)
  - Salary range (`fromSalary`, `toSalary`)
- Pagination support (`page`, `size`)
- Duplicate check on create
- Exception handling with custom responses

## 📁 File-Based Storage

## How To Run
 - you can run it Direct without profile . or you can add below on the VM Parameters 
   1 - Dspring.profiles.active = dev  : The different the page size . 
 - You can run using docker 
     - docker build -t employee-management-service . 
     - docker run -d -p 8080:8080 --name employee-app employee-management-service
