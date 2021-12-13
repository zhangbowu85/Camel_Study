package bw.study.examples.camel

import groovy.json.JsonOutput
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContexts

import java.time.Instant

class apachehttpclient {

    public static void main(args) {
        System.setProperty("javax.net.ssl.trustStore", "c:\\Users\\zhangbow\\client.keystore")
        System.setProperty("javax.net.ssl.trustStorePassword", "changeme")

        javax.net.ssl.SSLContext sslContext = SSLContexts.createDefault()

        SSLConnectionSocketFactory sslsf =
                new SSLConnectionSocketFactory(sslContext,
                        new String[] {"TLSv1.2", "TLSv1.3"}, null,
                        MySSLContext.hostnameVerifier()
        )
        CloseableHttpClient client =
                HttpClients.custom().setSSLSocketFactory(sslsf).build()

        HttpPost post = new HttpPost("https://localhost:18080/bw/test/example")
        post.addHeader('Content-Type', "application/json;charset=utf-8")
        post.setEntity(new StringEntity(JsonOutput.toJson(['test' : 'apach http client' + Instant.now().toString()])))

        CloseableHttpResponse httpResponse = client.execute(post)
        InputStream is = httpResponse.getEntity().getContent()
        InputStreamReader isr = new InputStreamReader(is)
        BufferedReader br = new BufferedReader(isr)
        String line
        while ((line = br.readLine()) != null) {
            System.out.println(line)
        }


    }
}
