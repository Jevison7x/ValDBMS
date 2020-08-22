/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valdbms.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Jevison7x
 */
public class XyneexURL
{
    private String address;

    private URL url;

    private HashMap<String, String> parametersMap;

    private HashMap<String, String> propertiesMap;

    private HttpURLConnection connection;

    private String xml;

    private File file;

    private static final int BUFFER_SIZE = 1024;

    private volatile long currentBytesRead;

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";

    public XyneexURL(String address)
    {
        this.address = address;
        this.parametersMap = new HashMap<>();
        this.propertiesMap = new HashMap<>();
    }

    public void addParameter(String name, String value)
    {
        if(this.connection == null)
            this.parametersMap.put(name, value);
        else
            throw new UnsupportedOperationException("The connection is already open.");
    }

    public void addXML(String xml)
    {
        this.xml = xml;
    }

    public void addFile(File file)
    {
        this.file = file;
    }

    public void addProperty(String name, String value)
    {
        if(this.connection == null)
            this.propertiesMap.put(name, value);
        else
            throw new UnsupportedOperationException("The connection is already open.");
    }

    public void setGetRequest() throws IOException
    {
        Set<Map.Entry<String, String>> parameters = this.parametersMap.entrySet();
        if(!parameters.isEmpty())
        {
            this.address += "?";
            Iterator<Map.Entry<String, String>> iterator = parameters.iterator();
            while(iterator.hasNext())
            {
                Map.Entry<String, String> parameter = iterator.next();
                String name = parameter.getKey();
                String value = parameter.getValue();
                this.address += name + "=" + URLEncoder.encode(value, "UTF-8");
                if(iterator.hasNext())
                    this.address += "&";
            }
        }
        this.url = new URL(address);
        this.connection = (HttpURLConnection)this.url.openConnection();
        //this.setRequestProperties();
        this.connection.setRequestMethod(GET);
    }

    public void setPostRequest() throws IOException
    {
        this.url = new URL(address);
        this.connection = (HttpURLConnection)this.url.openConnection();
        //this.setRequestProperties();
        this.connection.setRequestMethod(POST);
        Set<Map.Entry<String, String>> parameters = this.parametersMap.entrySet();
        if(!parameters.isEmpty())
        {
            String paramsStr = "";
            Iterator<Map.Entry<String, String>> iterator = parameters.iterator();
            while(iterator.hasNext())
            {
                Map.Entry<String, String> parameter = iterator.next();
                String name = parameter.getKey();
                String value = parameter.getValue();
                paramsStr += name + "=" + URLEncoder.encode(value, "UTF-8");
                if(iterator.hasNext())
                    paramsStr += "&";
            }
            this.connection.setDoOutput(true);
            DataOutputStream dos = null;
            try
            {
                dos = new DataOutputStream(this.connection.getOutputStream());
                dos.writeBytes(paramsStr);
            }
            finally
            {
                if(dos != null)
                {
                    dos.flush();
                    dos.close();
                }
            }
        }
    }

    public void setPutRequest() throws IOException
    {
        this.url = new URL(address);
        this.connection = (HttpURLConnection)this.url.openConnection();
        //this.setRequestProperties();
        this.connection.setRequestMethod(PUT);
        this.connection.setRequestProperty("Content-Type", "application/octet-stream");
        Set<Map.Entry<String, String>> properties = this.propertiesMap.entrySet();
        if(!properties.isEmpty())
        {
            Iterator<Map.Entry<String, String>> iterator = properties.iterator();
            while(iterator.hasNext())
            {
                Map.Entry<String, String> property = iterator.next();
                String name = property.getKey();
                String value = URLEncoder.encode(property.getValue(), "UTF-8");
                this.connection.setRequestProperty(name, value);
            }
        }
        this.connection.setDoOutput(true);
        this.connection.connect();
        try(InputStream inputStream = new ByteArrayInputStream(this.xml.getBytes(Charset.forName("UTF-8")));)
        {
            try(BufferedInputStream bis = new BufferedInputStream(inputStream, BUFFER_SIZE);)
            {
                try(BufferedOutputStream out = new BufferedOutputStream(this.connection.getOutputStream(), BUFFER_SIZE);)
                {
                    byte[] bytes = new byte[BUFFER_SIZE];
                    int bytesRead;
                    while((bytesRead = bis.read(bytes)) != -1)
                        out.write(bytes, 0, bytesRead);
                }
            }
        }
        catch(NullPointerException xcp)
        {
            throw new RuntimeException("You must set an xml String.", xcp);
        }
    }

    public void setUploadRequest() throws IOException
    {
        this.currentBytesRead = 0;
        this.url = new URL(address);
        this.connection = (HttpURLConnection)this.url.openConnection();
        //this.setRequestProperties();
        this.connection.setRequestMethod(PUT);
        this.connection.setRequestProperty("Content-Type", "application/octet-stream");
        Set<Map.Entry<String, String>> properties = this.propertiesMap.entrySet();
        if(!properties.isEmpty())
        {
            Iterator<Map.Entry<String, String>> iterator = properties.iterator();
            while(iterator.hasNext())
            {
                Map.Entry<String, String> property = iterator.next();
                String name = property.getKey();
                String value = URLEncoder.encode(property.getValue(), "UTF-8");
                this.connection.setRequestProperty(name, value);
            }
        }
        this.connection.setDoOutput(true);
        this.connection.connect();
        try(InputStream inputStream = new FileInputStream(this.file);)
        {
            try(BufferedInputStream bis = new BufferedInputStream(inputStream, BUFFER_SIZE);)
            {
                try(BufferedOutputStream out = new BufferedOutputStream(this.connection.getOutputStream(), BUFFER_SIZE);)
                {
                    byte[] bytes = new byte[BUFFER_SIZE];
                    int bytesRead;
                    while((bytesRead = bis.read(bytes)) != -1)
                    {
                        this.currentBytesRead += bytesRead;
                        System.out.println("Current Bytes Read: " + this.currentBytesRead);
                        out.write(bytes, 0, bytesRead);
                    }
                }
            }
        }
        catch(NullPointerException xcp)
        {
            throw new RuntimeException("You must set a file.", xcp);
        }
    }

    public long getCurrentBytesRead()
    {
        return this.currentBytesRead;
    }

    public void executeRequest(String requestType) throws IOException
    {
        switch(requestType)
        {
            case GET:
                this.setGetRequest();
                break;
            case POST:
                this.setPostRequest();
                break;
            case PUT:
                this.setPutRequest();
                break;
            default:
                this.setGetRequest();
        }
    }

    public String getResponse() throws IOException
    {
        if(this.connection != null)
            try(InputStream inputStream = this.connection.getInputStream())
            {
                String response = IOUtils.toString(inputStream, "UTF-8");
                return response;
            }
        else
            throw new UnsupportedOperationException("There is no connection.");
    }

    public int getStatusCode() throws IOException
    {
        return this.connection.getResponseCode();
    }

    public String getResponseMessage() throws IOException
    {
        if(this.connection != null)
            return this.connection.getResponseMessage();
        else
            throw new UnsupportedOperationException("There is no connection.");
    }

    public String getResultingAddress()
    {
        if(this.connection != null)
            return this.address;
        else
            throw new UnsupportedOperationException("There is no connection.");
    }
}
