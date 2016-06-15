# sslDemo
Connecting java client with zookeeper with SSL


This is a spring boot so you can directly run ServiceApplication


Zookeeper SSL setup:
http://confluence.staples.com/display/SEAR/ZooKeeper+SSL+Server+Configuration
Essentially add client and server JVM FLAGS to java.env in zookeeper/conf
Also add secureClientPort in zoo.cfg
