# LMS Project (Learning Management System) 📚

The LMS Project provides a comprehensive solution to manage students, books, and their associated transactions, such as issuing and returning books. Built with efficiency in mind, this system integrates features like Spring Security and Redis Caching.

> 💡 **Tip:** Ensure your MySQL and Redis servers are running when starting the application.

## 📌 Table of Contents

- [Features](#features)
- [API Endpoints](#api-endpoints)
  - [Student Controller](#student-controller)
  - [Book Controller](#book-controller)
  - [Transaction Controller](#transaction-controller)


## 🚀 Features

- **Student Management**: Streamlined creation of student profiles with role-based fetching.
- **Book Management**: Facilitates book creation, updates, and offers advanced search capabilities.
- **Transactions**: Efficiently handles the process of issuing and returning books.

## 🛠 API Endpoints

### Student Controller

- **Create a Student**:
  - `POST /student`
- **Get Student Info (for students)**:
  - `GET /student`
- **Get Student Info (for admins)**:
  - `GET /student_for_admin?studentId=<STUDENT_ID>`

### Book Controller

- **Create or Update a Book**:
  - `POST /book`
- **Search Books**:
  - `GET /books/search?filter=<FILTER_TYPE>&value=<VALUE>`
- **Enhanced Book Search**:
  - `GET /books/search/robust?filter=<FILTER_TYPE>&value=<VALUE>&bookSearchOperationType=<OPERATION_TYPE>`
- **Advanced Book Search**:
  - `GET /books/search/robust2?filter=<FILTER_LIST>&value=<VALUE_LIST>&bookSearchOperationType=<OPERATION_TYPE_LIST>`

### Transaction Controller

- **Issue a Book**:
  - `POST /transaction/issue?studentId=<STUDENT_ID>&bookId=<BOOK_ID>`
- **Return a Book**:
  - `POST /transaction/return?studentId=<STUDENT_ID>&bookId=<BOOK_ID>`

