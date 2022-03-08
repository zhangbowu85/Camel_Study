package bw.study.examples.camel

import groovy.util.logging.Slf4j
import org.snmp4j.CommunityTarget
import org.snmp4j.PDU
import org.snmp4j.Snmp
import org.snmp4j.TransportMapping
import org.snmp4j.mp.MPv3
import org.snmp4j.mp.SnmpConstants
import org.snmp4j.security.SecurityModel
import org.snmp4j.security.SecurityModels
import org.snmp4j.security.SecurityProtocols
import org.snmp4j.security.USM
import org.snmp4j.smi.*
import org.snmp4j.transport.DefaultTcpTransportMapping
import org.snmp4j.transport.TransportStateEvent
import org.snmp4j.transport.TransportStateListener

import java.time.Instant

@Slf4j
class SNMP4JTrapSender {

    public static void main(args) {
        int number = 1
        int index
        for (index = 0; index < number; index ++) {
            sendPdu(index)
            System.out.println("message ${index} sent")
        }
        /*trap.add(new VariableBinding(new OID('1.3.6.1.2.1.1.3.0'), new TimeTicks(1100)))
        snmp.send(trap, target)*/
        //if to exit immediately, the transport will be closed by sender,
        // then listener will lost the message have not read
        Thread.sleep(1 * 60 * 1000)

    }

    static void sendPdu(int index) {
        Snmp snmp = null;
        TransportMapping<? extends Address> transport = null;

        try {


           SecurityModels.getInstance().addSecurityModel(
                    new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0));
            SecurityModels.get
            transport = new DefaultTcpTransportMapping()
            transport.addTransportStateListener(new TransportStateListener() {
                @Override
                void connectionStateChanged(TransportStateEvent change) {
                    System.out.println(change.newState)
                }
            })
            /* transport.addTransportStateListener(new TransportStateListener() {
                 @Override
                 void connectionStateChanged(TransportStateEvent change) {
                     System.out.println(change.newState)
                 }
             })*/
            snmp = new Snmp(transport);


            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString('public'));
            target.setVersion(SnmpConstants.version2c)
            target.setAddress(new TcpAddress('localhost/10162'))
            snmp.send(newTrap("1.1.1.${index}"), target);
        } catch (Exception e) {
            e.printStackTrace()
        } finally {
           /* snmp.
            try {
               transport.close();
           } catch (Exception e) {
           }
           try {
               snmp.close();
           } catch (Exception e) {
           }*/
        }

    }



    static PDU newTrap(String trapOid) {
        PDU trap = new PDU()
        trap.setType(PDU.TRAP);
        trap.add(new VariableBinding(SnmpConstants.sysUpTime, TimeTicks.newInstance()))
        trap.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(trapOid)))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.1'), new OctetString('localhost')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.2'), new OctetString('Test snmp4j')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.3'), new OctetString('pod1')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.4'), new OctetString('snmpAgent')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.5'), new OctetString('new')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.6'), new OctetString('a new alarm')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.7'), new OctetString('a new alarm for test')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.8'), new OctetString('Major')))
        trap.add(new VariableBinding(new OID('1.3.6.1.4.1.54373.1.1.5.1.1.1.9'), new OctetString(Instant.now().toString())))
        return trap
    }
}
