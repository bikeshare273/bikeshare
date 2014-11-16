--- Bike Share --- 

REST API for Location. 

1. POST - Location
http://localhost:8080/api/v1/location 

2. GET - Location
http://localhost:8080/api/v1/location/{location_id}

3. PUT - Location
http://localhost:8080/api/v1/location/{location_id}



REST API for Bike.

1. POST - Bike (To store the bikes at a particular location)
http://localhost:8080/api/v1/location/{location_id}/bike

2. GET - Bike (To get a list of available bikes at a particular location)
http://localhost:8080/api/v1/location/{location_id}/bike/available

3. GET - Bike (To get a list of reserved bikes at a particular location)
http://localhost:8080/api/v1/location/{location_id}/bike/reserved
 
