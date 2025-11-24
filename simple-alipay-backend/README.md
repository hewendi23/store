# simple-alipay-backend

Spring Boot 3.4.12 + JDK21 demo backend implementing simplified payment, collection QR, and travel code modules.

How to run:
- Build with Maven: `mvn clean package`
- Run: `java -jar target/simple-alipay-backend-0.0.1-SNAPSHOT.jar`
- H2 console: http://localhost:8080/h2-console (jdbc url: jdbc:h2:mem:alipay)

APIs (examples):
- POST /api/pay/execute  -> {"fromUser":"alice","toMerchant":"bob","amount":"10.00","method":"balance"}
- POST /api/collect/create -> {"merchantId":"bob","validSeconds":"120"}
- POST /api/collect/refresh/{id}
- POST /api/collect/parseFromBase64 -> {"imageBase64":"<base64 png>"}
- POST /api/travel/open -> {"username":"alice","city":"Beijing","line":"Line1","payment":"balance"}
- POST /api/travel/entry -> {"username":"alice","city":"Beijing","line":"Line1"}
- POST /api/travel/exit/{recordId}
- GET /api/travel/records/{username}

Note: This project is intentionally minimal and NOT production-ready. Security and advanced error handling are omitted as requested.
