package bw.study.examples.camel

import groovy.json.JsonParser
import groovy.json.JsonSlurper
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.snmp4j.PDU
import org.snmp4j.mp.SnmpConstants
import org.snmp4j.smi.OID
import org.snmp4j.smi.OctetString
import org.snmp4j.smi.VariableBinding

import java.time.Instant

class PDUMarshaller implements Processor {

    @Override
    void process(Exchange exchange) throws Exception {
        PDU trap = new PDU()
        trap.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.1'), new OctetString('localhost')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.2'), new OctetString('Test')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.3'), new OctetString('pod1')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.4'), new OctetString('snmpAgent')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.5'), new OctetString('new')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.6'), new OctetString('a new alarm')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.7'), new OctetString('a new alarm for test')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.8'), new OctetString('Major')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.9'), new OctetString(Instant.now().toString())))
        exchange.getIn().setBody(trap)



    }
}
