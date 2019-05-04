/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blockhead.cs4800_gethdapp;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author krady
 */
public class QueryThread extends Thread {

    StringStorage contract;
    boolean live;
    private final Object waitlock;

    public QueryThread(StringStorage contract) {
        this.live = true;
        this.contract = contract;
        this.waitlock = new Object();
    }

    public void die() {
        this.live = false;
    }

    private static HashMap<String, String> parseQueries(String queries) {
        HashMap<String, String> queryMap = new HashMap<>();
        while (queries.length() > 50) {
            String queryID = queries.substring(
                    queries.indexOf("\"_id\"") + 6,
                    queries.indexOf("\"blockchainID\"")
            ).trim().substring(1, 10);
            String query = queries.substring(
                    queries.indexOf("\"query\""),
                    queries.indexOf("}") + 1).trim();
            queries = queries.substring(queries.indexOf("}", queries.indexOf("}") + 1) + 1);
            queryMap.put(queryID, query);
        }
        return queryMap;
    }

    @Override
    public void run() {
        while (live) {
//            synchronized (waitlock) {
//                try {
//                    waitlock.wait(1000);
//                } catch (InterruptedException ex) {
//                    //shouldn't happen, but continue operation if it does
//                }
//            }
            String queries = HTTPHandler.getQueries();
            if (queries.contains("\"status\": \"not ok\"") || queries.length() < 50) {
//                System.out.println("QueryThread: no queries: " + queries);
                synchronized (waitlock) {
                    try {
                        waitlock.wait(1000);
                    } catch (InterruptedException ex) {
                        //shouldn't happen, but continue operation if it does
                    }
                }
            } else {
                try {
                    String data[] = contract.getArr().send().split("█▄▌▐▀█");
                    HashMap<String, String> queryMap = parseQueries(queries);
                    Iterator<String> queryIDs = queryMap.keySet().iterator();
                    for (; queryIDs.hasNext();) {
                        String tempID = queryIDs.next();
                        System.out.println("QueryThread: query: \n" + tempID + "\n" + queryMap.get(tempID));
//                        System.out.println(tempID);
//                        System.out.println(queryMap.get(tempID));
                        //String results = SearchMethod(queryMap.get(tempID), data);
                        //HTTPHandler.postQueryResults(tempID, results);   
                    }
                } catch (Exception ex) {
                    System.out.println("Blockchain is not running.");
                }
            }
        }
    }
}
