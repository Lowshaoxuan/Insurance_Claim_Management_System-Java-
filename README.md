# Insurance Claim Management System

A console-based Insurance Claim Management System developed using **Java**. The application simulates an insurance management workflow with multiple user roles, vehicle registration, policy management, and file-based data persistence.

## 📌 Project Overview

The Insurance Claim Management System is a Java application designed to manage basic insurance operations through a command-line interface.

The system separates functionalities based on user roles, allowing administrators, claims officers, and customers to access different operations.

This project was developed to strengthen my understanding of Java programming, object-oriented programming (OOP), file handling, and application architecture.

## 🛠️ Technologies Used

- **Java** – Core application development.
- **Object-Oriented Programming (OOP)** – Classes, objects, inheritance, encapsulation, and polymorphism.
- **Java File I/O** – Reading and writing application data using text files.
- **Java Collections** – Managing application data.
- **Exception Handling** – Handling invalid inputs and runtime errors.
- **IntelliJ IDEA** – Development environment.

## ✨ Key Features

### 1. User Management
- User authentication and login.
- Role-based access control.
- Separate user roles for administrators, claims officers, and customers.
- User information management.

### 2. Customer Management
- Customer account management.
- Customer identification and ID tracking.
- Access to customer-related insurance information.

### 3. Vehicle Management
- Vehicle registration and information management.
- Associate registered vehicles with customer accounts.
- Store and retrieve vehicle records.

### 4. Insurance Policy Management
- Insurance policy record management.
- Policy information storage.
- Policy status tracking.

### 5. Claims Officer Management
- Dedicated claims officer role.
- Access to claims-related management operations.

### 6. File-Based Data Persistence
- Store user information in `users.txt`.
- Store vehicle records in `vehicles.txt`.
- Store policy records in `policy.txt`.
- Maintain customer and claims officer ID sequences.
- Retrieve previously stored information when the application runs again.

## 🏗️ Application Structure

The application follows an object-oriented design, separating responsibilities into individual Java classes.

| Class | Responsibility |
|---|---|
| `Main.java` | Application entry point and main program flow |
| `User.java` | Base user class |
| `Customer.java` | Customer-related functionality |
| `Administrator.java` | Administrative functionality |
| `ClaimsOfficer.java` | Claims officer functionality |
| `Vehicle.java` | Vehicle information and management |
| `policy.java` | Insurance policy information and management |

## 📂 Project Structure

```text
Insurance_Claim_Management_System/
│
├── src/
│   ├── Main.java
│   ├── User.java
│   ├── Customer.java
│   ├── Administrator.java
│   ├── ClaimsOfficer.java
│   ├── Vehicle.java
│   └── policy.java
│
├── users.txt                 # User records
├── vehicles.txt              # Vehicle records
├── policy.txt                # Insurance policy records
├── customerid.txt            # Customer ID tracking
├── officerid.txt             # Claims officer ID tracking
│
├── .gitignore
└── README.md
```

## ⚙️ Installation and Setup

### Prerequisites

- Java Development Kit (JDK)
- IntelliJ IDEA or another Java IDE

### Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/Lowshaoxuan/Insurance_Claim_Management_System-Java-.git
   ```

2. Open the project in IntelliJ IDEA.

3. Make sure the JDK is properly configured.

4. Navigate to `src/Main.java`.

5. Run the `Main` class to start the application.

### Data Storage

This application uses local text files for data persistence instead of a relational database.

The text files are used to store user information, vehicle records, insurance policies, and ID tracking information.

## 🎓 Learning Outcomes

Through this project, I gained practical experience in:

- Applying object-oriented programming principles using Java.
- Designing a multi-role application with separate user responsibilities.
- Implementing inheritance and class relationships.
- Using Java File I/O for persistent data storage.
- Organizing application logic into reusable classes.
- Managing application data and user interactions.
- Understanding the fundamental workflows of an insurance claim management system.

## 👨‍💻 Author

**Low Shao Xuan**

- GitHub: [@Lowshaoxuan](https://github.com/Lowshaoxuan)

---

*Developed as a Java programming project.*
