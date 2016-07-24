Mbta API
---

The project requires:

- Java 8
- Gradle 2.14 +

####Import CSV data

Here a sample command to run the import

```
gradle importCsv -s \
	-Ddb.url=jdbc:mysql://mysql_host/database \
	-Ddb.driver=com.mysql.jdbc.Driver \
	-Ddb.user=test \
	-Ddb.password=test

 ```
 
CSV is loaded from `src/main/resources/departures.csv` 
 
####Run the project
 
 The project is implemented on Spring Boot. 
 
 ```

gradle bootRun 

 ```
 
Application configuration file is inside `config/` 
 