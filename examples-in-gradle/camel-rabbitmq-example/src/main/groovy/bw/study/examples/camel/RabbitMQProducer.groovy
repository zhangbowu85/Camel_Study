/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package bw.study.examples.camel


import groovy.util.logging.Slf4j
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext

import java.time.Instant

@Slf4j
class RabbitMQProducer {

    static void main(String[] args) {
        CamelContext camel = new DefaultCamelContext()
        camel.getShutdownStrategy().tap {
            it.setShutdownRoutesInReverseOrder(true)
            it.setShutdownNowOnTimeout(true)
            it.setTimeout(1)
        }
        camel.getGlobalOptions().put(Exchange.LOG_EIP_NAME, "bw.study.examples");
        RouteBuilder.addRoutes(camel, {
            it
            .from("timer:sendMessage?period=60000&repeatCount=-1")
            .routeId('rabbitmq.client.producer')
            .process {
                it.in.body = 'Test Message ' + Instant.now()
            }
            .to("log:bw.study.examples?showAll=true&multiline=true")
            .to("rabbitmq:mytest?addresses=localhost:5672&username=guest&password=guest")
        })
        camel.addShutdownHook {
            System.out.println('Exiting')
        }
        camel.start()
        Thread.sleep(300000)
        camel.stop()
    }
}
