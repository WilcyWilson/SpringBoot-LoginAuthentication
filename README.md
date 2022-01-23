Terms to Learn
- Learn Angular, Spring Boot and MySQL
- Learn about Jenkins and Kubsphere
- Jenkins = Create ISO Image of Project
- Kubsphere = Deploy
- UAT = User Acceptance Testing during deployement

Things to do
- Insert = Create
- View = Fetch -> Map -> List, ByID, DTO
- Modify = Edit -> Future Table, Audit Log
- Future Table => Table before modification 
- Maker/Checker Concept => Maker can't modify, Other people need to modify
- MySQL: Join, Query, Left, Right, Inner, Outer, Foreign Key

Angular
- Register new userRegister (save)
- Login (Fetch By Username, id)
- Join Query (foreign key)
- SHA 256 Encryption for password
- After Login Welcome
- Edit information then audit log, Future Table
- Add Flag to true or false for Maker/Checker Concept

Concepts and Definition

- Checker Maker

We use approval and destination user and change the status if destination user exists.

- Edit User

We just edit fixed set of non important field and update the field.

- Audit Log

Saving previous data

- Meta Table

Saving configuration data, constants for easy change without restarting the server

- application.properties constants

No change without restarting the server, @Value

- Password Validation

Using Meta Table, changing password constraints on the fly without restarting the server

- @CrossOrigin annotation
When JSON data is coming from different port 
say localhost:4200 of angular