package com.vaibhav.zookeeper.sslDemo.server;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.curator.framework.CuratorFramework;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.Ssl.ClientAuth;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Vaibhav Devekar
 */
@Configuration
public class ApplicationConfig {

    @Autowired
    Environment environment;
    
    @Bean
    public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        
        return new RestTemplate(requestFactory);
    }


    @Bean
    public EmbeddedServletContainerFactory servletContainer() {

        // keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize
        // 2048 -keystore keystore.p12 -validity 3650
        // keytool -list -v -keystore keystore.p12 -storetype pkcs12

        // curl -u user:password -k https://127.0.0.1:9000/greeting

        final String keystoreFile = "/Users/fudgedassociate/Desktop/certificates/server/zkServer.keystore";
        final String keystorePass = "zkpass";
        final String keystoreType = "JKS";
        final String truststoreFile = "/Users/fudgedassociate/Desktop/certificates/server/cacerts_server";
        final String truststorePass = "changeit";

        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();

        Connector connector = new Connector();
        connector.setScheme("https");
        connector.setSecure(true);
        connector.setPort(
                environment.getRequiredProperty("sslPort", Integer.class));
        Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
        proto.setSSLEnabled(true);
        proto.setKeystoreFile(keystoreFile);
        proto.setKeystorePass(keystorePass);
        proto.setKeystoreType(keystoreType);
        proto.setKeyAlias("zookeeper");
        proto.setKeyPass(keystorePass);
        proto.setTruststoreFile(truststoreFile);
        proto.setTruststorePass(truststorePass);
        proto.setClientAuth(ClientAuth.NEED.toString());
        //proto.setClientAuth(Boolean.TRUE.toString());
        // proto.setSslProtocol("TLSv1");

        factory.addAdditionalTomcatConnectors(connector);

        return factory;
    }
}
