# Layered Architecture Project

Welcome to the Layered Architecture project! This project is developed for educational purposes, specifically to teach Layered Architecture concepts to students in the GDSE program at IJSE.

## Overview

This project is designed to demonstrate the principles of layered architecture, a common architectural pattern used in software development. The project showcases the separation of concerns and modularity by dividing the application into distinct layers.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Database Setup](#database-setup)
  - [Installation](#installation)
- [Project Structure](#project-structure)
- [Usage](#usage)
- [License](#license)

## Features

- **Layered Architecture:** The project follows a layered architecture pattern, emphasizing the separation of concerns.
- **Educational Purpose:** Developed to help GDSE students understand and apply layered architecture concepts.
- **Modularity:** Each layer is designed to be modular and independent, facilitating ease of maintenance and scalability.

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit 11 (JDK) installed.
- Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.
- Git for version control.
- MySQL or another relational database management system installed.

### Database Setup

1. **Create Database:**
   Open your preferred MySQL client and execute the following SQL command to create the "company" database:

   ```sql
   CREATE DATABASE IF NOT EXISTS company;
   ```

2. **Run SQL Script:**
   Locate the SQL file named `init_database.sql` in the `resources` folder. Run this script in your MySQL client to initialize the database with required tables and data.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/udarasan/layered-architecture.git
   ```

2. Open the project in your IDE.
3. Build and run the application.

## Project Structure (Initial)

```
layered-architecture/
|-- src/
|   |-- main/
|       |-- java/
|           |-- com/
|               |-- example/
|                   |-- layeredarchitecture/
|                       |-- controller/      
|                       |-- db/    
|                       |-- view/         
|                       |-- model/               
|-- resources/
|   |-- company-db.sql                        # SQL script to initialize the "company" database
|-- README.md
|-- .gitignore
|-- LICENSE
|-- ...
```

## Usage

To run the project, follow these steps:

1. Open the project in your IDE.
2. Build and run the application.

For detailed usage instructions or examples, refer to the documentation provided in each layer's package.


## License

This project is licensed under the [MIT License](LICENSE).
