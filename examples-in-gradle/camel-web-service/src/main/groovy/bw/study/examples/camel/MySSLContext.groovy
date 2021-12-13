package bw.study.examples.camel

import org.apache.camel.support.jsse.FilterParameters
import org.apache.camel.support.jsse.KeyManagersParameters
import org.apache.camel.support.jsse.KeyStoreParameters
import org.apache.camel.support.jsse.SSLContextClientParameters
import org.apache.camel.support.jsse.SSLContextParameters
import org.apache.camel.support.jsse.TrustManagersParameters
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.X509HostnameVerifier

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLException
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocket
import java.security.cert.X509Certificate

class MySSLContext {

    static SSLContextParameters initServerSSLContext() {
        KeyStoreParameters ksp = new KeyStoreParameters();
        ksp.setResource("c:\\Users\\zhangbow\\server.jks");
        ksp.setPassword("changeme");

        KeyManagersParameters kmp = new KeyManagersParameters();
        kmp.setKeyStore(ksp);
        kmp.setKeyPassword("changeme");

        FilterParameters filter = new FilterParameters();
        filter.getInclude().add("TLS_AES_128_GCM_SHA256");

        SSLContextParameters scp = new SSLContextParameters();
        scp.setKeyManagers(kmp);
/*
        SSLContextClientParameters sccp = new SSLContextClientParameters();
        sccp.setCipherSuitesFilter(filter)*/


        scp.setSecureSocketProtocol('TLSv1.3')
        scp.setCipherSuitesFilter(filter)

        return scp
    }

    static SSLContextParameters initClientSSLContext() {


        SSLContextParameters scp = new SSLContextParameters();
        scp.setTrustManagers(new TrustManagersParameters().tap {
            keyStore = new KeyStoreParameters().tap {
                resource = 'c:\\Users\\zhangbow\\client.jks'
                password = 'changeme'
            }
        })

        scp.setSecureSocketProtocol('TLSv1.3')
        FilterParameters filter = new FilterParameters();
        filter.getInclude().add("TLS_AES_128_GCM_SHA256");
        scp.setCipherSuitesFilter(filter)

        return scp

        /*KeyStoreParameters ksp = new KeyStoreParameters();
        ksp.setResource("c:\\Users\\zhangbow\\sever.keystore");
        ksp.setPassword("changeme");

        KeyManagersParameters kmp = new KeyManagersParameters();
        kmp.setKeyStore(ksp);
        kmp.setKeyPassword("changeme");


        SSLContextParameters scp = new SSLContextParameters();
        scp.setKeyManagers(kmp);


        scp.setSecureSocketProtocol('TLSv1.2')

        return scp*/
    }

    static X509HostnameVerifier hostnameVerifier() {
        return new X509HostnameVerifier() {
            @Override
            void verify(String host, SSLSocket ssl) throws IOException {

            }

            @Override
            void verify(String host, X509Certificate cert) throws SSLException {

            }

            @Override
            void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {

            }

            @Override
            boolean verify(String s, SSLSession sslSession) {
                return true
            }
        }
    }
}
