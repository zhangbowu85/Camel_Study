# Camel Overview

## Related Concepts

What camel looks like? The following picture show what camel looks like:

![Alt text](pictures/camel-like.png?raw=true "What camel looks like")

### Route
move data from endpoint A to endpoint B
### Endpoint
in Camel, an endpoint represents any other external system to Camel.
### Components
A component is simply like a pluggable adapter that allow your to connect to an external system (such as a file on disk, a mailbox, or an app like Dropbox)
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

Java-base API example:
```
from("file:src/data?noop=true")
            .choice()
                .when(xpath("/person/city = 'London'"))
                    .to("file:target/messages/uk")
                .otherwise()
                    .to("file:target/messages/others");
```

Spring XML configuration example :
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
Blueprint xml example:
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
For more, see [DSL](https://camel.apache.org/manual/latest/dsl.html)

### URI
Uniform Resource Identifier (URI) is a unique identifier used by web technologies. URIs may be used to identify anything, including real-world objects.

Camel use URI to plug a component to connect to a external system, processor.

Here are some example:
|Component | Purpose | Endpoint URI |
|---------|---------------|-----------|
HTTP|for creating or consuming web sites|http:xxxxx
File|for reading and writing fiels |file:xxxxx
JMS|for reading and writing to message queues|jms:xxxx
direct|for joing your camel routes together|direct:xxxx



## Then let's summarize what is camel?

### Description
- It is a **integration framework** Based on **EIP** (<Enterprise Integration Patterns>)
  - Simplifying Integration
  - Concrete implementations of all the widely used EIPs 
- Empower you to define **routing** and **mediation rules** in a variety of **DSL**
- Apache Camel uses **URIs** to work directly with **any kind of Transport or messaging model** such as HTTP, ActiveMQ, JMS, JBI, SCA, MINA or CXF, as well as pluggable Components and Data Format options.
  - Comes with huge library of components  
  - Camel connects to an endpoint through variou components

### So, when you develop with Camel, you are doing:
**Create routes that move data between endpoints, using components.**

**Each route starts with a from, configured with a uri, that defines the endpoint that the data is coming from.**

**A route can consist of multiple steps — such as transforming the data, or logging it. But a route usually ends with a to()
instruction, which describes where the data will be delivered to.**

### Architecture
![Alt text](pictures/Architecture.png?raw=true "Architecture")

## Why camel
- **Based on Enterprise Integration Patterns**: Camel supports most of the Enterprise Integration Patterns from the excellent book by Gregor Hohpe and Bobby Woolf, and newer integration patterns from microservice architectures to help you solve your integration problem by applying best practices out of the box.
- **Runs Everywhere**: Apache Camel is standalone, and can be embedded as a library within Spring Boot, Quarkus, Application Servers, and in the clouds. Camel subprojects focus on making your work easy.
- **Packed with Components**:  Packed with several hundred components that are used to access databases, message queues, APIs or basically anything under the sun. Helping you integrate with everything.

### Components
[See what components provided for Camel](https://camel.apache.org/components/latest/index.html)

## Get started

## Examples
[RabbitMQ Consumer and Producer](https://github.hpe.com/bo-wu-zhang/Camel-Study/tree/master/camel-rabbitmq-example)

## How To
### How to log exchange message through camel-log
[`.to("log:logger-name?showAll=true&multiline=true")`](https://github.hpe.com/bo-wu-zhang/Camel-Study/blob/e7dcaf3f8ddd66f7f76be4a581b9689a89c0fbea/camel-rabbitmq-example/src/main/groovy/bw/study/examples/camel/RabbitMQConsumer.groovy#L30)
