# sslDemo
Connecting java client with zookeeper with SSL


This is a spring boot so you can directly run ServiceApplication


Zookeeper SSL setup:
https://cwiki.apache.org/confluence/display/ZOOKEEPER/ZooKeeper+SSL+User+Guide
Essentially add client and server JVM FLAGS to java.env in zookeeper/conf
Also add secureClientPort in zoo.cfg


How to:
1. Start server. 
For eg.  mvn spring-boot:run -Djavax.net.debug=ssl:handshake
2. Start client. For eg. mvn spring-boot:run 
-Djavax.net.ssl.keyStore=/Users/fudgedassociate/Desktop/certificates/client/zkClient.keystore
-Djavax.net.ssl.keyStorePassword=clientpass 
-Djavax.net.ssl.trustStore=/Users/fudgedassociate/Desktop/certificates/client/cacerts_client
-Djavax.net.ssl.trustStorePassword=changeit
-Djavax.net.debug=ssl:handshake
3. Go to browser and hit http://localhost:4000/client/send?msg=vaibhav