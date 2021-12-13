package bw.study.examples.camel

import groovy.json.JsonOutput
import org.apache.http.client.methods.HttpPost
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.conn.ssl.X509HostnameVerifier
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContexts

import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLException
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocket
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.security.cert.X509Certificate

class SimpleJavaHttpClient {
    public static void main(args) {

        String body = JsonOutput.toJson(['test' : 'testmethod'])

        System.setProperty("javax.net.ssl.trustStore", "c:\\Users\\zhangbow\\client.keystore")
        System.setProperty("javax.net.ssl.trustStorePassword", "changeme")
        System.setProperty("https.protocols", "TLSv1.3")

        javax.net.ssl.SSLContext sslContext = SSLContexts.createDefault()

        HttpClient httpClient = HttpClient.newHttpClient()
        HttpRequest httpRequest = HttpRequest
                .newBuilder(URI.create("https://localhost:18080/bw/test/example"))
                .header('Content-Type', "application/json;charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build()

        HttpResponse<String> response =  httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
        System.out.println(response.statusCode())
        System.out.println(response.body())

        Thread.sleep(5 * 60 * 1000)
/*

        URL url = new URL("http://localhost:18080/bw/test/example")
        HttpURLConnection connection = (HttpURLConnection) url.openConnection()
      *//*  connection.setHostnameVerifier(new NoopHostnameVerifier())
        connection.setSSLSocketFactory(sslContext.getSocketFactory())*//*
        connection.setRequestMethod('POST')
        connection.setRequestProperty('')

        InputStream is = connection.getInputStream()
        InputStreamReader isr = new InputStreamReader(is)
        BufferedReader br = new BufferedReader(isr)
        String inputline
        while ((inputline = br.readLine()) != null) {
            System.out.println(inputline)
        }

        br.close()*/




    }
}
