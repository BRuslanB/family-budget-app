# Family Budget Tracker Application
This is a Java-based application backed by a REST API and an Angular frontend, designed to help families track their expenses and manage their budget.

Note: This Family Budget Tracker application is built for training and educational purposes only. It is not intended for commercial or production use.

## Features
The Family Budget Tracker application allows users to:

* Register for an account and login securely
* Add and manage family members
* Add and manage expense categories
* Record and track expenses for each family member and category
* View and filter expense reports by date range, family member, and category
* Set and monitor budget limits for each category

* ## Technologies Used
The Family Budget Tracker application is built using the following technologies:

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Angular 14
* HTML5/CSS3

# Prerequisites
Before running the application, you will need to have the following software installed on your machine:

* Java Development Kit (JDK) 17 or higher
* Node.js
* Angular CLI
* Docker

# Getting Started
To run the application locally, follow these steps:

1. Clone the repository to your local machine:
```bash
git clone https://github.com/BRuslanB/family-budget.git
```
2. Create a new MySQL database for the application:
```bash
docker-compose up
```
3. Run the application using Graddle:
```bash
cd family-budget
./gradlew bootRun
```
4. Open a new terminal window and navigate to the frontend directory:
```bash
cd family-budget/frontend
```
5. Install the required Node.js packages:
```bash
npm install
```
6. Start the Angular development server:
```bash
ng serve --open
```
7. Open your web browser and navigate to http://localhost:4200

## Contributing
If you would like to contribute to the development of this application, please submit a pull request or open an issue on the GitHub repository.

## License
This application is licensed under the MIT License. See the LICENSE file for more details.

test test
