# RestApiProblem
REST service that will return geographic distance between two postal codes in UK.  A valid request returns a JSON document that contains the following
information:

 - For both locations, the postal code, latitude and longitude (in degrees)
 - The distance between the two locations (in kilometers)
 - A fixed string 'unit' that has the value "km"
 
## SQL for creating PostgreSQL  Database
    CREATE DATABASE ukpostcodes WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
      
    CREATE TABLE postcodelatlng (
      id integer PRIMARY KEY,
      postcode varchar(8) NOT NULL,
      latitude decimal(18,15) NOT NULL,
      longitude decimal(18,15) NOT NULL
    );

INSERTS: 
[ukpostcodesmysql.zip](https://www.freemaptools.com/download/full-postcodes/ukpostcodesmysql.zip)

CONNECTION CONFIG: 
ukpostcodes/src/main/resources/**application.properties**

## REST DEMO

> Without Authentication Example

`http://host:port/guest/...`

![guest](https://user-images.githubusercontent.com/15386676/47270675-a5876480-d56f-11e8-94d0-5dc8a14feadd.png)
---
> With Authentication Example

`http://host:port/secure/...`

![no credentials](https://user-images.githubusercontent.com/15386676/47270677-b0da9000-d56f-11e8-931b-051770802611.png)

![secured](https://user-images.githubusercontent.com/15386676/47270683-bafc8e80-d56f-11e8-90c2-1c4c8a7685b0.png)
