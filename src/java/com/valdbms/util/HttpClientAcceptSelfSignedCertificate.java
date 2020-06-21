/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valdbms.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

/**
 *
 * @author Life
 */
public class HttpClientAcceptSelfSignedCertificate
{

    public static void bulkSmsApiConnection(String smsApiKey, String smsFrom, String smsTo, String smsMessage) throws Exception
    {
        try(CloseableHttpClient httpclient = createAcceptSelfSignedCertificateClient())
        {
            String safeUrl = URLEncoder.encode(smsMessage, "UTF-8");
            HttpGet newRequest = new HttpGet("https://www.bulksmsnigeria.com/api/v1/sms/create?api_token=" + smsApiKey + "&from=" + smsFrom + "&to=" + smsTo + "&body=" + safeUrl + "");
            HttpResponse newRes = httpclient.execute(newRequest);
            HttpEntity entity = newRes.getEntity();
        }
        catch(NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static CloseableHttpClient createAcceptSelfSignedCertificateClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException
    {

        // use the TrustSelfSignedStrategy to allow Self Signed Certificates
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustAllStrategy())
                .build();

        // we can optionally disable hostname verification.
        // if you don't want to further weaken the security, you don't have to include this.
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();

        // create an SSL Socket Factory to use the SSLContext with the trust self signed certificate strategy
        // and allow all hosts verifier.
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);

        // finally create the HttpClient using HttpClient factory methods and assign the ssl socket factory
        return HttpClients
                .custom()
                .setSSLSocketFactory(connectionFactory)
                .build();
    }
}
