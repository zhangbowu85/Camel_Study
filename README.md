# Camel Overview

## What the camel looks like? - Related Concepts

The following picture show what camel looks like:

![Alt text](pictures/camel-like.png?raw=true "What camel looks like")

### Route
move data from endpoint A to endpoint B
### Endpoint
in Camel, an endpoint represents any other external system to Camel.
### Components
A component is simply like a plug that allow your to connect to an external system (such as a file on disk, a mailbox, or an app like Dropbox)
### EIP :Enterprise Integration Patterns
An EIP defines a pattern according to which the system does special processing on messages.
examples:
|EIP name | What it does | java syntax|
|---------|---------------|-----------|
|Splitter |Splits a message into multiple parts | .split()
|Aggregator|Combines several messages into one message|.aggregate()
|Log|Writes a simple log message|.log()
|Marshal|Converts an object into a text or binary format|.marshal()
### Camel Context
A container to manage, run your routes
### DSL :domain-specific language
like Java-based Fulent API, Spring or Blueprint XML configuration files) and so on.
Java-base API:
```
from("file:src/data?noop=true")
            .choice()
                .when(xpath("/person/city = 'London'"))
                    .to("file:target/messages/uk")
                .otherwise()
                    .to("file:target/messages/others");
```
Spring XML configuration :
```
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://camel.apache.org/schema/spring http://camel.apache.org/schema/spring/camel-spring.xsd
    ">

  <camelContext id="camel-A" xmlns="http://camel.apache.org/schema/spring">
    <route>
      <from uri="seda:start"/>
      <to uri="mock:result"/>
    </route>
  </camelContext>

</beans>
```
Blueprint xml:
```
<blueprint xmlns="http://www.osgi.org/xmlns/blueprint/v1.0.0">

    <camelContext xmlns="http://camel.apache.org/schema/blueprint">
        <route>
            <from uri="timer:test" />
            <to uri="log:test" />
        </route>
    </camelContext>

</blueprint>
```
## What is camel?

- It is a **integration framework** Based on **EIP** (<Enterprise Integration Patterns>)
  - Simplifying Integration
  - Concrete implementations of all the widely used EIPs 
- Empower you to define **routing** and **mediation rules** in a variety of **DSL**
  - A routing engine: enable to define routing rules (from a source endpoint to other destination endpoint)
  - Message perhaps be processed in some way (perhaps by an EIP when from source endpoint to destination endpoint)
- Apache Camel uses **URIs** to work directly with **any kind of Transport or messaging model** such as HTTP, ActiveMQ, JMS, JBI, SCA, MINA or CXF, as well as pluggable Components and Data Format options.
  - Comes with huge library of components  
  - Camel connects to an endpoint through variou components
  - The URI is used to define the endpoint access

## Architecture

## Get started

## Examples
