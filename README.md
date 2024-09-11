This is the backend of a group demo project of a car leasing application. My main contribution to the backend was coding the logic for queries to an 
external API that would fetch data about cars (make, model, model variant, year, fuel type, engine power, etc.) that the backend would return to the frontend as a user was filling in information about their car. For example, when a user selects a make "Toyota", the frontend would execute a get query that would fetch all Toyota models, and when a user selects a model, data about that model would be fetched.

I also wrote authentication logic, as the external API requires a JWT token to be included in queries.

Contributions:

https://github.com/andriusLedas/acad-demo-back/graphs/contributors

Some of the files that I wrote code for:

https://github.com/andriusLedas/acad-demo-back/blob/main/src/main/java/com/example/sick/api/controller/CarInfoController.java
https://github.com/andriusLedas/acad-demo-back/blob/main/src/main/java/com/example/sick/service/CarAPILoginService.java
https://github.com/andriusLedas/acad-demo-back/blob/main/src/main/java/com/example/sick/service/CarInfoService.java
https://github.com/andriusLedas/acad-demo-back/blob/main/src/main/java/com/example/sick/repository/CarAPIJwtRepository.java
https://github.com/andriusLedas/acad-demo-back/blob/main/src/main/java/com/example/sick/repository/CarInfoRepository.java
https://github.com/andriusLedas/acad-demo-back/blob/main/src/test/java/com/example/sick/CarInfoServiceTest.java
