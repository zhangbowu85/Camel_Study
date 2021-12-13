package bw.study.examples.camel

import javax.net.SocketFactory
import javax.net.ssl.SSLParameters
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory
import java.time.Instant

class Tls13SocketClient {

    public static void main(args) {
        System.setProperty("javax.net.ssl.trustStore", "c:\\Users\\zhangbow\\client.jks")
        System.setProperty("javax.net.ssl.trustStorePassword", "changeme")

        String host = "localhost";
        int port = 18080;
        SocketFactory factory = SSLSocketFactory.getDefault();
        try (Socket connection = factory.createSocket(host, port)) {
            ((SSLSocket) connection).setEnabledCipherSuites(
                    new String[] { "TLS_AES_128_GCM_SHA256" });
            ((SSLSocket) connection).setEnabledProtocols(
                    new String[] { "TLSv1.3" });

            SSLParameters sslParams = new SSLParameters();
            sslParams.setEndpointIdentificationAlgorithm("HTTPS");
            sslParams.needClientAuth = false

            ((SSLSocket) connection).setSSLParameters(sslParams);

            BufferedReader is = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            OutputStream os = new BufferedOutputStream(connection.getOutputStream())
            os.write("Hello, Server ${Instant.now().toString()}" .getBytes())
            os.flush()
            byte[] data = new byte[1024]
            int len = is.read(data)
            System.out.println(Instant.now().toString() + ": received reply " + new String(data, 0, len))
        }

    }
}
