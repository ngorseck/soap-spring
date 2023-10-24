# Spring SOAP Web Client

SOAP is a messaging protocol. Messages (requests and responses) are XML documents over HTTP. The XML contract is defined by the WSDL (Web Services Description Language). It provides a set of rules to define the messages, bindings, operations, and location of the service.
There are two possible approaches when creating a web service: Contract-Last and Contract-First. When we use a contract-last approach, we start with the Java code, and generate the web service contract (WSDL) from the classes. When using contract-first, we start with the WSDL contract, from which we generate the Java classes.
Spring-WS only supports the contract-first development style. The aim of this project is to consume a soap web service with Spring Boot.

## Setup

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web-services</artifactId>
    </dependency>
</dependencies>
<build>
    <plugins>
        <plugin>
            <groupId>org.jvnet.jaxb2.maven2</groupId>
            <artifactId>maven-jaxb2-plugin</artifactId>
            <version>0.14.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>generate</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <schemaLanguage>WSDL</schemaLanguage>
                <generatePackage>com.spring.training.model</generatePackage>
                <schemas>
                    <schema>
                        <url>http://localhost:8080/ws/countries.wsdl</url>
                    </schema>
                    <schema>
                        <url>http://localhost:8080/ws/persons.wsdl</url>
                    </schema>
                </schemas>
            </configuration>
        </plugin>
    </plugins>
</build>
```


## Security

Spring WS-Security allows you to sign SOAP messages, encrypt and decrypt them, or authenticate against them. The WS-Security implementation of Spring Web Services provides integration with Spring Security.

```xml
<dependency>
    <groupId>org.springframework.ws</groupId>
    <artifactId>spring-ws-security</artifactId>
</dependency>
<dependency>
    <groupId>com.sun.xml.wss</groupId>
    <artifactId>xws-security</artifactId>
    <version>3.0</version>
    <exclusions>
        <exclusion>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
        </exclusion>
        <exclusion>
            <groupId>javax.xml.crypto</groupId>
            <artifactId>xmldsig</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
**Authentication**: 

This is the process of determining whether a principal is who they claim to be. In this context, a "principal" generally means a user, device or some other system which can perform an action in your application.

**Digital signatures**:  

The digital signature of a message is a piece of information based on both the document and the signer's private key. It is created through the use of a hash function and a private signing function (encrypting with the signer's private key).

**Encryption and Decryption**:  

Encryption is the process of transforming data into a form that is impossible to read without the appropriate key. 
It is mainly used to keep information hidden from anyone for whom it is not intended. Decryption is the reverse of encryption, it is the process of transforming of encrypted data back into an readable form. 

Two implementations of WS-Security, WSS4J and XWSS, are supported and the following authentication methods are used: 

- Plain password
- Signature
- Encryption

## Security activation

You need to run the project with the appropriate profile and you can read below the security configuration declared in the *application.yml* file.

```
ws:
  location: http://localhost:8080/ws
  security:
    password:
      policy: classpath:passwordSecurityPolicy.xml
    certificate:
      policy: classpath:certificateSecurityPolicy.xml
      keyStore: classpath:keyStore.jks
      password: changeit
      alias: thinktech
```
 
- Plain password

```
mvn spring-boot:run -Dspring-boot.run.profiles=password
```

- Signature

```
mvn spring-boot:run -Dspring-boot.run.profiles=signature
```

- Encryption 

```
mvn spring-boot:run -Dspring-boot.run.profiles=encryption
```

### REST endpoints

| HTTP verb | Resource  | Description
|----|---|---|
|  GET  | /persons  | retrieve list and information of persons  
|  GET |  /persons/{id} | retrieve information of a person specified by {id}
|  POST | /persons  | create a new person with payload  
|  PUT   |  /persons/{id} | update a person with payload   
|  DELETE   | /persons/{id}  |  delete a person specified by {id} 
|  GET  | /countries  | retrieve list and information of countries  
|  GET |  /countries/{name} | retrieve information of a country specified by {name} 
|  POST | /countries  | create a new country with payload  
|  PUT   |  /countries/{name} | update a country with payload   
|  DELETE   | /countries/{name}  |  delete a country specified by {name} 
