# Verspaetung Transport Service
This project is an API service offering information of Verspaetung Transport System. You can use this service to search for vehicles and check delay information.

## Getting Started
This API is created with Spring-boot 2.1.3 and support HTTP requests. Currently the API support GET only. Response is available in JSON only.

### Environment Setup  
You need to following installed on your machine to get the project started.
```
Java 1.8
Maven
```

### Start API server locally  
Execute the following commands from project root folder
```
cd transport
spring-boot:run
```
The endpoint will served on port 8081. You can change the default port by change parameter in "application.properties" file.
## Tests
### Automatic Test
Test cases are not created yet.

### Send Request to local API server  
#### 1. Find a vehicle for a given time and X & Y coordinates
Note that this API will search based on original schedule, delay is not considered when search is performed.

Request example:
```
curl -v 'http://localhost:8081/lines:time={time}&posX={x_coordinate}&posY={y_coordinate}'  

```

Response example:  

200
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 17 Feb 2019 12:58:58 GMT

* Connection #0 to host localhost left intact
{"line_id":0,"line_name":"M4"}
```

404

```
HTTP/1.1 404 
Content-Type: text/plain;charset=UTF-8
Content-Length: 73
Date: Sun, 17 Feb 2019 13:19:09 GMT

* Connection #0 to host localhost left intact
Could not find employee with search criteria: time=10:02:00&posX=1&posY=1
```

#### 2. Return the vehicle arriving next at a given stop
This API will search based on original schedule, delay is not considered when search is performed.

Request example:
```
curl -v 'http://localhost:8081/lines:stopId={stopId}'
```

Response example:

200

```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 17 Feb 2019 14:01:58 GMT

* Connection #0 to host localhost left intact
{"line_id":2,"line_name":"S75"}
```

404
```
HTTP/1.1 404 
Content-Type: text/plain;charset=UTF-8
Content-Length: 54
Date: Sun, 17 Feb 2019 14:02:16 GMT

* Connection #0 to host localhost left intact
Could not find employee with search criteria: stopId=1
```

#### 3. Indicate if a given line is currently delayed
You can indicate a line with either line_id or line_name, but not both

Request example:
```
curl -v 'http://localhost:8081/lines:lineId={lineId}'
curl -v 'http://localhost:8081/lines:lineName={lineName}'
```
Response example:  

200
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 17 Feb 2019 13:16:29 GMT

* Connection #0 to host localhost left intact
{"line_id":0,"line_name":"200","isDelayed":true,"delayMinutes":2}
```
404 
```
HTTP/1.1 404 
Content-Type: text/plain;charset=UTF-8
Content-Length: 56
Date: Sun, 17 Feb 2019 13:18:00 GMT

* Connection #0 to host localhost left intact
Could not find employee with search criteria: Line Id: 4
```

### Limitations
The API has the following limitations with the given development resources.

#### Data storage
The project is built using H2 in-memory database, for the convenience of initialization with data from provided csv files. When application is started, database and will gets installed and data will be available in the current application process lifetime. 

This could be improved by storing static data in dedicated relational DB server, and dynamic "delays" data shall be stored in KVS with defined lifetime.

The test data is stored under src/resources/data folder.

#### Search Service
As mentioned above, search related to time is not taking delay into consideration. This could be improved with consideration of real-time delay.