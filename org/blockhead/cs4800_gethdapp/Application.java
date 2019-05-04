/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blockhead.cs4800_gethdapp;

/**
 *
 * @author krady
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import java.math.BigInteger;
import java.util.Scanner;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static String address;

    public static void main(String[] args) throws Exception {
        System.out.println("Ensure Blockchain is running, then hit Enter...");
        Scanner sin = new Scanner(System.in);
        sin.nextLine();
        System.out.print("Enter existing contract address, or leave blank to create new contract\n?:");
        address = sin.nextLine().trim();
        //new Application().run();
    }

    private void run() throws Exception {
        Web3j web3j = Web3j.build(new HttpService());
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        Credentials credentials
                = WalletUtils.loadCredentials(
                        "password",
                        "C:\\Users\\krady\\Documents\\GethDappTutorial\\blockchain\\chaindata\\keystore\\UTC--2019-03-19T05-15-59.432129100Z--19cd29de55d30fa7bb27859284287ed8f14dda93");
        log.info("Credentials loaded");
        StringStorage contract;
        if (address == null || (address != null && address.length() < 2)) {
            log.info("Deploying smart contract");
            contract = StringStorage.deploy(
                    web3j, credentials,
                    BigInteger.valueOf(3000000), BigInteger.valueOf(3000000)).send();
            String contractAddress = contract.getContractAddress();
            log.info("Smart contract deployed to address " + contractAddress);
        } else {
            log.info("Loading smart contract");
            contract = StringStorage.load(address,
                    web3j, credentials,
                    BigInteger.valueOf(3000000), BigInteger.valueOf(3000000));
            String contractAddress = contract.getContractAddress();
            log.info("Smart contract loaded from address " + contractAddress);
        }
        QueryThread queryThread = new QueryThread(contract);
        TransactionThread transactionThread = new TransactionThread(contract);
        queryThread.start();
        transactionThread.start();
        System.out.println("Type \"quit\" without quotes and hit Enter at any time to signal the program to close once it finishes the current transactions or queries.");
        String input = "";
        Scanner sin = new Scanner(System.in);
        while (!input.equals("quit")) {
            input = sin.nextLine().toLowerCase().trim();
        }
        queryThread.die();
        transactionThread.die();
    }

}
