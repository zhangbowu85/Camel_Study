package bw.study.examples.camel

import groovy.util.logging.Slf4j
import org.snmp4j.CommandResponder
import org.snmp4j.CommandResponderEvent
import org.snmp4j.CommunityTarget
import org.snmp4j.MessageDispatcherImpl
import org.snmp4j.PDU
import org.snmp4j.Snmp
import org.snmp4j.TransportMapping
import org.snmp4j.mp.MPv3
import org.snmp4j.mp.SnmpConstants
import org.snmp4j.security.SecurityModel
import org.snmp4j.security.SecurityModels
import org.snmp4j.security.SecurityProtocols
import org.snmp4j.security.USM
import org.snmp4j.smi.Address
import org.snmp4j.smi.OID
import org.snmp4j.smi.OctetString
import org.snmp4j.smi.TcpAddress
import org.snmp4j.smi.VariableBinding
import org.snmp4j.transport.DefaultTcpTransportMapping
import org.snmp4j.transport.TransportStateEvent
import org.snmp4j.transport.TransportStateListener

import java.time.Instant
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

@Slf4j
class SNMP4jTrapListener {
    static AtomicInteger counter = new AtomicInteger(0)
    static Map State = [1:'STATE_CONNECTED', 2:'STATE_CLOSED']
    public static void main(args) {

        Snmp snmp = null;
        TransportMapping<? extends Address> transport = null;

        try {
            transport = new DefaultTcpTransportMapping(new TcpAddress('0.0.0.0/10162'))
            transport.addTransportStateListener(new TransportStateListener() {
                 @Override
                 void connectionStateChanged(TransportStateEvent change) {
                     System.out.println(State[change.newState])
                 }
             })
            snmp = new Snmp(transport);

            OctetString engineId = new OctetString(MPv3.createLocalEngineID(new OctetString('testEngine')))
            log.info(engineId.toString())

            USM usm = new USM(SecurityProtocols.getInstance(), engineId, 0);
            SecurityModels.getInstance().addSecurityModel(usm);


            CommandResponder responder = new CommandResponder() {
                @Override
                void processPdu(CommandResponderEvent event) {
                    System.out.println('\n' + counter.incrementAndGet() + '\n' + event.getPDU().toString())
                    event.setProcessed(true)
                    sleep(500)
                }
            }
            snmp.addCommandResponder(responder)
            snmp.listen()
            System.out.println("listening......")
            Thread.sleep(5 * 60 * 1000)

        } finally {
            /*try {
                transport.close();
            } catch (Exception e) {
            }
            try {
                snmp.close();
            } catch (Exception e) {
            }*/
        }
    }

}
