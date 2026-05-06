# Shared Message Board (ServletContext)

## Student Details

| Field         | Details                          |
|---------------|----------------------------------|
| Name          | Vinayak S Patil                 |
| USN           | 2BL23CS187      |
| Branch        | Computer Science & Engineering   |
| Semester      | VI Semester                      |
| Subject       | Advanced Java Programming        |
| Problem No.   | Problem 45               |

## Problem Statement

This is a Shared Message Board application built using Java Servlets. It allows any user to post a short message through an HTML form, which is then stored globally in the `ServletContext`. All users can see the same list of messages, displayed in reverse chronological order (latest first), simulating a public notice board.

## Technologies Used

- Java (Servlets)
- HTML, CSS (inline)
- Apache Tomcat 10
- Eclipse IDE

## How to Run This Project

1. Clone this repository or download the ZIP.
2. Import the project into Eclipse as a Dynamic Web Project.
3. Add Apache Tomcat as the server in Eclipse.
4. Right-click project → Run As → Run on Server.
5. Open browser and go to: http://localhost:8080/SharedMessageBoard/index.html

## Screenshots

### Input Form
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/de520b4b-a449-4738-88d6-15c41edfe8ff" />


### Output / Result Page
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/1d5f526e-d134-4c2a-a751-deed0ecab473" />


## Servlet Concept Practiced

This project practices the use of **ServletContext** to store and share data across all users of the application. It also demonstrates handling form data via **doPost** and displaying information via **doGet**, while ensuring thread-safety with synchronization.
