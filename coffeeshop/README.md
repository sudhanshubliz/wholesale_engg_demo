Coffee Shop Demo APP Server
======================================

We can use the plugin to Generate a Changelog from an Existing Database:

mvn liquibase:generateChangeLog

Access h2-console db
http://localhost:8080/h2-console

Access Swagger
http://localhost:8080/swagger-ui.html#/

Create a schema for Coffee Shop app:
Change the spring.jpa.hibernate.ddl-auto=update -> spring.jpa.hibernate.ddl-auto=create
in application.properties file.

Run the command mvn clean install
Copy the jar D:\project\coffeeshop\target from the location for deploying in Docker.


