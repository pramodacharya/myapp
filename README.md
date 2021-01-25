# Web-app with redis cache for evaluated prime numbers

This is a simple web-app backed by redis as backend. This is for demonstrating docker-compose functionality where two services are started
The app returns whether a given integer is prime or not. In backend the given is first checked if it is present in redis. If it is present the result is returned otherwise it is evaluated and then cached in redis. 

## Requirements
* Java 8
* Apache Maven 3.6.3 or higher

## How to Run

- Clone the project and checkout the branch docker-compose
```
git clone https://github.com/pramodacharya/myapp.git
cd myapp
git checkout docker-compose
```
- Build the project   
```
mvn clean install
```
- Run the application using docker-compose
```
docker-compose up --build 
```

## How to validate?  
- After starting the containers, send a http request for any integer, try below
http://localhost:8080/checkPrime/104729

and do refersh and try other numbers. It will return true or false based on primeness of the numbers.
Then check the console in powershell, the logs should be something like this
```
myapp    | 104729 is not found in redis cache. Evalating it's primeness and chaching.....
myapp    | 104729 is found in redis cache
myapp    | 10472 is not found in redis cache. Evalating it's primeness and chaching.....
myapp    | 10472 is found in redis cache
myapp    | 113 is not found in redis cache. Evalating it's primeness and chaching.....
myapp    | 113 is found in redis cache
```
- We can also check the stored keys/values in redis with these steps
```
docker exec -it redis_runner sh
# redis-cli
127.0.0.1:6379> auth letmesee
OK
127.0.0.1:6379> keys *
```
      






 

