<div style="text-align:center"><img src="src/main/resources/static/images/logo.png"></div>


A web app to easily transfer money.

This app uses Java to run and MySQL to store data.

## Prerequisites  

What things we need for the project:

- Java 1.8
- Maven 4.0
- MySQL 8.0.27

## Technologies

- Eclipse IDE
- Spring Boot with starters:
    - Spring Web
    - Spring Data JPA
    - MySQL Driver
    - Thymeleaf
- Lombok for logs

## UML Diagram

<img src="src/main/resources/images/UML_Diagram.png">

## Relational Model

<img src="src/main/resources/images/relational_model.png">

## Preparation for the app running and testing

You will have to set up the tables and data in the data base.
For this, please run the sql commands present in the files `data.sql` and `populate.sql` under the `resources` folder in the code base.

## Connection to the database and the app

To connect to the database:
   - login: root
   - password: rootroot
	
To use the app:
   - login: fabien@email.com
   - password: 1234

## Limits of the version

This version only deals with a user authentication and the transaction between the user and a connection existing in the initialized database.
