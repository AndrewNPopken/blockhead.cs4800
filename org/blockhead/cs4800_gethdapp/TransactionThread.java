/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blockhead.cs4800_gethdapp;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author krady
 */
public class TransactionThread extends Thread {

    StringStorage contract;
    boolean live;
    private final Object waitlock;

    public TransactionThread(StringStorage contract) {
        this.live = true;
        this.contract = contract;
        this.waitlock = new Object();
    }

    public void die() {
        this.live = false;
    }

    private static ArrayList<String> parseTransactions(String transactions) {
        ArrayList<String> transactionArray = new ArrayList<>();
        String transaction = transactions.substring(transactions.indexOf("{", 1) + 1, transactions.indexOf("]"));
        String[] temp = (transaction.split("\\{"));
        for (String str : temp) {
            transactionArray.add("{" + str.substring(0, str.lastIndexOf("}") + 1));
        }
        return transactionArray;
    }

    @Override
    public void run() {
//        synchronized (waitlock) {
//            try {
//                waitlock.wait(1000);
//            } catch (InterruptedException ex) {
//                //shouldn't happen, but continue operation if it does
//            }
//        }
        while (live) {
            String transactions = HTTPHandler.getTransactions();
            if (transactions.contains("\"status\": \"not ok\"") || transactions.length() < 50) {
//                System.out.println("TransactionThread: no transactions: " + transactions);
                synchronized (waitlock) {
                    try {
                        waitlock.wait(1000);
                    } catch (InterruptedException ex) {
                        //shouldn't happen, but continue operation if it does
                    }
                }
            } else {
                try {
                    ArrayList<String> transactionArr = parseTransactions(transactions);
                    Iterator<String> transactionItr = transactionArr.iterator();
                    for (; transactionItr.hasNext();) {
                        String temp = transactionItr.next();
                        System.out.println("TransactionThread: transaction: \n" + temp);
                        contract.add(temp).send();
                    }
                } catch (Exception ex) {
                    System.out.println("Blockchain is not running.");
                }
            }
        }
    }
}
