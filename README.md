# Camel Overview

## What the camel looks like? - Related Concepts

The following picture show what camel looks like:

![Alt text](pictures/camel-like.png?raw=true "What camel looks like")

### Route
move data from place A to place B
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

, like Java-based Fulent API, Spring or Blueprint XML configuration files)

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
