# Cartesian Widget API

#### Swagger UI url : http://localhost:9011/swagger-ui (Default port is 9011 but it can be changed during boot)
#### API Doc url : http://localhost:9011/v2/api-docs (Default port is 9011 but it can be changed during boot)

#### Repository implementation
There are two ways in which repository is implements. One using SQL database (H2) and other using stardard in memory classes
##### To enable SQL database based repository set the application proper sql.repository=true. By default, it runs with stardard in memory classes
Example: mvn spring-boot:run -Dspring-boot.run.arguments=--sql.repository=true
  
