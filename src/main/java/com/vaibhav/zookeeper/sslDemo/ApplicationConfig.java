package com.vaibhav.zookeeper.sslDemo;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.Ssl.ClientAuth;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Vaibhav Devekar
 *
 */
@Configuration
public class ApplicationConfig {

    
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
         
        // keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
        // keytool -list -v -keystore keystore.p12 -storetype pkcs12
         
        // curl -u user:password -k https://127.0.0.1:9000/greeting
         
        final String keystoreFile = "/Users/fudged/git/zk-app-config/dev/sslcert.jks";
        final String keystorePass = "zkpass";
        final String keystoreType = "JKS";
        final String truststoreFile = "/Users/fudged/git/zk-app-config/dev/cacerts_qa";
        final String truststorePass = "changeit";
     
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        
     
        Connector connector =  new Connector();
        connector.setScheme("https");
        connector.setSecure(true);        
        connector.setPort(8443);
        Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
        proto.setSSLEnabled(true);
        proto.setKeystoreFile(keystoreFile);
        proto.setKeystorePass(keystorePass);
        proto.setKeystoreType(keystoreType);
        proto.setKeyAlias("fudged");
        proto.setKeyPass(null);
        proto.setTruststoreFile(truststoreFile);
        proto.setTruststorePass(truststorePass);
        proto.setClientAuth(Boolean.TRUE.toString());
        proto.setSslProtocol("TLS");
        
        factory.addAdditionalTomcatConnectors(connector);
        
        return factory;
    }
}
