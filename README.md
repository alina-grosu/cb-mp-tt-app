 Spring Boot based web app, basic exception handling used, unit tests provided only for critical functionality.
 Secret Key  and Key Store generated using keytool with below command:
 keytool -genseckey -alias cb-mp-tt-mk -keyalg AES -keysize 256 -storetype jceks -keystore cb-mp-tt-ks.jks
 
 build: mvn clean install
