package bw.study.examples.camel

import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.ssl.SSLContexts

import javax.net.ServerSocketFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLServerSocket
import javax.net.ssl.SSLServerSocketFactory
import javax.net.ssl.SSLSocketFactory
import java.time.Instant

class Tls13SocketSever {

    public static void main(args) {
        System.setProperty("javax.net.ssl.keyStore", "c:\\Users\\zhangbow\\server.jks")
        System.setProperty("javax.net.ssl.keyStorePassword", "changeme")
        System.setProperty("javax.net.ssl.keyPassword", "changeme")
        int port = 18080

        ServerSocketFactory factory = SSLServerSocketFactory.getDefault()

        try (ServerSocket listener = factory.createServerSocket(port)) {
            SSLServerSocket sslListener = (SSLServerSocket) listener;
            sslListener.setNeedClientAuth(false);
            sslListener.setEnabledCipherSuites(
                    new String[] { "TLS_AES_128_GCM_SHA256" });
            sslListener.setEnabledProtocols(
                    new String[] { "TLSv1.3" });
            while (true) {
                System.out.println("Waiting for request")
                try (Socket socket = sslListener.accept()) {
                    System.out.println("receive request")
                    OutputStream os = new BufferedOutputStream(socket.getOutputStream())
                    os.write("Received request at ${Instant.now().toString()}".getBytes())
                    os.flush()
                }
            }
        }
    }
}
