

# LMS Project (Learning Management System)
The LMS Project is an application that aims to manage students, books, and the transactions between them, such as issuing and returning books. It comprises multiple modules, each serving specific functionalities within the system.

Table of Contents
Features
API Endpoints
Student Controller
Book Controller
Transaction Controller
Features
Student Management: Allows creation of student profiles and fetching details specific to students and admins.
Book Management: Enables book creation, updates, and advanced search features with robust filters.
Transactions: Manages the issuance and return of books between students and the library.
# API Endpoints
Student Controller
Create a Student:
POST /student: Accepts student details and creates a student profile.
Get Student Information (for students):
GET /student: Returns details of the logged-in student.
Get Student Information (for admins):
GET /student_for_admin?studentId=<STUDENT_ID>: Allows admins to fetch student details using a student's ID.
Book Controller
Create or Update a Book:
POST /book: Accepts book details and creates or updates a book profile.

Search Books:
GET /books/search?filter=<FILTER_TYPE>&value=<VALUE>: Search for books based on a specific filter type and value.

Enhanced Book Search:
GET /books/search/robust?filter=<FILTER_TYPE>&value=<VALUE>&bookSearchOperationType=<OPERATION_TYPE>: Offers a refined search considering both the filter type and a specific operation type.

Advanced Book Search:
GET /books/search/robust2?filter=<FILTER_LIST>&value=<VALUE_LIST>&bookSearchOperationType=<OPERATION_TYPE_LIST>: Supports search based on multiple filters and operation types.

Transaction Controller
Issue a Book:
POST /transaction/issue?studentId=<STUDENT_ID>&bookId=<BOOK_ID>: Facilitates the issuance of a book to a student.

Return a Book:
POST /transaction/return?studentId=<STUDENT_ID>&bookId=<BOOK_ID>: Manages the return of a book from a student.

License
The LMS Project is open-sourced under the MIT license.
