# App ID Spring Boot Starter 

This is a spring boot starter that enables Spring boot developers to get started quickly to build authentication workflow for applications that use [App ID](https://www.ibm.com/cloud/app-id) and OAuth 2.0 to secure its back end. This starter is like an extension to the actual functionality of the spring security, that means you can do whatever you want to do with spring secuirty, plus you get additional functionality to configure App ID.

* [Installation and Usage](#installation-and-usage)
* [Getting Started](#getting-started)
* [Related Documentation](#related-documentation)
* [Development](#development)
    * [Contributing](CONTRIBUTING.md)

## Installation and Usage
    
Add the following dependency
    
Gradle:

```groovy
dependencies {
    compile group: 'com.appid', name: 'appid-spring-boot-starter', version: '0.0.1'
}
```

Maven:

~~~ xml
<dependency>
  <groupId>com.appid</groupId>
  <artifactId>appid-spring-boot-starter</artifactId>
  <version>0.0.1</version>
</dependency>
~~~

## Getting Started
This section contains examples of how to use App ID starter in your spring boot application. There are multiple ways to auto-configure App ID in your application by providing the App ID configuration details in the following ways in your application.yml file:

1.

```
spring:
  security:
    oauth2:
      client:
        registration:
          appid:
            region: <<region>>
            clientId: <<clientId>>
            clientSecret: <<clientSecret>>
            tenantID: <<tenantID>>
            version: <<version>>
```
* region - the region in which the App ID service is created in IBM Cloud.
* clientID - is the client id of the App ID service you created.
* clientSecret - is the client secret of the App ID service you created.
* tenantID - is the tenant id of the App ID service you created.
* version - is the App ID endpoints version, it is defaulted to 4, but if you would like to use a different version then this parameter should be set.

2.

```
spring:
  security:
    oauth2:
      client:
        registration:
          appid:
            clientId: <<clientId>>
            clientSecret: <<clientSecret>>
            issuerUri: <<issuerUri>> 
```
* issuerUri - URI that can either be an OpenID Connect discovery endpoint or an OAuth 2.0 Authorization Server Metadata endpoint defined by RFC 8414.


If you are not sure of the region, tenantID and version, use this configuration by just providing oAuthServerUri.

3.

```
spring:
  security:
   oauth2:
     client:
       registration:
         appid:
           provider: appid
           clientId: <<clientId>>
           clientSecret: <<clientSecret>>
       provider:
         appid:
           issuerUri: <<issuerUri>>         
```
This is the other way to configure App ID.


The starter also supports multiple oauth provider configuration, i.e., you can configure the application with more than one OAuth provider. For example, you can do something like this:

```
spring:
  security:
    oauth2:
      client:
        registration:
          appid:
            region: <<region>>
            clientId: <<clientId>>
            clientSecret: <<clientSecret>>
            tenantID: <<tenantID>>
          google:
            clientId: <<clientId>>
            clientSecret: <<clientSecret>>
         
```

## Related Documentation
* [Spring Boot documentation](https://projects.spring.io/spring-boot/)
* [App ID blogs](https://cloud.ibm.com/docs/services/appid?topic=appid-rellinks)
* [Spring Security](https://docs.spring.io/spring-security/site/docs/5.1.6.RELEASE/reference/htmlsingle/)

## Development
For information about contributing, building, and running tests see the [CONTRIBUTING.md](CONTRIBUTING.md).

