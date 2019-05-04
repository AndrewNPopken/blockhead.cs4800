/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blockhead.cs4800_gethdapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krady
 */
public class HTTPHandler {

//    private static final String API_URL = "https://0f801d92-87d0-4f66-a4a5-af199969d23c.mock.pstmn.io/";
//    private static final String API_URL = "https://908e3ac5-7393-4ec9-a76c-bbcc9a9a449f.mock.pstmn.io/";
    private static final String API_URL = "https://blockchain-restful-api.herokuapp.com/";

    public static String getTransactions() {
        String url = API_URL + "api/transaction?blockchainID=0";
        String charset = java.nio.charset.StandardCharsets.UTF_8.name();
        try {
            URLConnection connection = new URL(url/* + query*/).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            try (Scanner scanner = new Scanner(response)) {
                String responseBody = scanner.useDelimiter("\\A").next();
//                System.out.println(responseBody);
                return responseBody;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(HTTPHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HTTPHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ERROR";
    }

    public static String getQueries() {
        String url = API_URL + "api/queries?blockchainID=0";
        String charset = java.nio.charset.StandardCharsets.UTF_8.name();
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            try (Scanner scanner = new Scanner(response)) {
                String responseBody = scanner.useDelimiter("\\A").next();
//                System.out.println(responseBody);
                return responseBody;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(HTTPHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HTTPHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ERROR";
    }

    public static String postQueryResults(String queryID, String results) {
        String url = API_URL + "api/queries";
        String charset = java.nio.charset.StandardCharsets.UTF_8.name();
        String postFormat = "queryID=" + queryID + "&data=" + results;
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            try (OutputStream output = connection.getOutputStream()) {
                output.write(postFormat.getBytes(charset));
            }
            InputStream response = connection.getInputStream();
            try (Scanner scanner = new Scanner(response)) {
                String responseBody = scanner.useDelimiter("\\A").next();
//                System.out.println(responseBody);
                return responseBody;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(HTTPHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HTTPHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ERROR";
    }
}
