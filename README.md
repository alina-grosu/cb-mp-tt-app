 Spring Boot based web app, uses JDK facilities to implement encryption/decryption.
 Secret Key for encryption is obtained from KeyStore which is available through classpath.
 Secret Key and Key Store generated using keytool with next command:  
 keytool -genseckey -alias cb-mp-tt-mk -keyalg AES -keysize 256 -storetype jceks -keystore cb-mp-tt-ks.jks  

 Basic exception handling used, unit tests provided only for critical functionality.  

 build: mvn clean install  
 if build and deployment to Tomcat succeeds (use cd-mp-tt-1.0-SNAPSHOT.war) the endpoints should be available on following URLs:  
 http://localhost:8080/cd-mp-tt-1.0-SNAPSHOT/userdetails/getencrypted  
 http://localhost:8080/cd-mp-tt-1.0-SNAPSHOT/userdetails/getdecrypted  

 /getencrypted request body example:  
 {
     "id": 1
 }

 /getdecrypted request body example:  
 {
     "encryptedUserDetails": "ZVgifp3gSvL4qNiYs1DNACggOSLfF3vVWznjyIGNfv8="
 }

 logs should be written to $TOMCAT_HOME/logs/cb-mp-tt-app.log e.g. standard Tomcat log directory.

 Sample database is populated with 3 users e.g.:  
 INSERT INTO user (user_name) VALUES
   ('User One'),
   ('User Two'),
   ('User Three');