# README #

### What is this repository for? ###

* __Quick summary__
  - Spring Boot application to surface restful API(s) for Smart Meter readings
* __Version__
  * 0.1

### How do I get set up? ###

* Start Spring application by running 'mvn clean install spring-boot:run

* On start-up, an H2 database is spun up locally and can be accessed via http://localhost:8080/h2/

* Once navigated to this location, simply hit connect (no password required)
  * __NOTE:__ For security, credentials can be supplied by including entries in _application.properties_ 
    * e.g
      * spring.datasource.username=username
      * spring.datasource.password=password
      
      
### How to use the in memory database? ###    

* Data has been inserted by default as part of the application build - see _/resources/data.sql_

* To insert additional data, simply navigate to H2 location (see above) and run SQL operations
  * e.g
  _insert into SMART_METER_READING
  values('AccountNum', 'ElecId', 'ElecSmartRead', 'GasId', 'GasSmartRead');_
  
* Alternatively, data can be inserting by enriching the _data.sql_ file and re-booting the application.