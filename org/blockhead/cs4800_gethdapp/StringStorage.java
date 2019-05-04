package org.blockhead.cs4800_gethdapp;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class StringStorage extends Contract {
    private static final String BINARY = "6080604052600060015534801561001557600080fd5b50610630806100256000396000f3fe608060405260043610610051576000357c010000000000000000000000000000000000000000000000000000000090048063621b8c3214610056578063b0c8f9dc1461010a578063fb8cbced146101d2575b600080fd5b34801561006257600080fd5b5061008f6004803603602081101561007957600080fd5b8101908080359060200190929190505050610262565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100cf5780820151818401526020810190506100b4565b50505050905090810190601f1680156100fc5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561011657600080fd5b506101d06004803603602081101561012d57600080fd5b810190808035906020019064010000000081111561014a57600080fd5b82018360208201111561015c57600080fd5b8035906020019184600183028401116401000000008311171561017e57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050610312565b005b3480156101de57600080fd5b506101e7610350565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561022757808201518184015260208101905061020c565b50505050905090810190601f1680156102545780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60006020528060005260406000206000915090508054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561030a5780601f106102df5761010080835404028352916020019161030a565b820191906000526020600020905b8154815290600101906020018083116102ed57829003601f168201915b505050505081565b806000806001548152602001908152602001600020908051906020019061033a92919061055f565b5060016000815480929190600101919050555050565b6060806020604051908101604052806000815250905060008090505b60015481101561055757816000808381526020019081526020016000206040516020018083805190602001908083835b6020831015156103c1578051825260208201915060208101905060208303925061039c565b6001836020036101000a0380198251168184511680821785525050505050509050018280546001816001161561010002031660029004801561043a5780601f1061041857610100808354040283529182019161043a565b820191906000526020600020905b815481529060010190602001808311610426575b5050925050506040516020818303038152906040529150816040805190810160405280601281526020017fe29688e29684e2968ce29690e29680e2968800000000000000000000000000008152506040516020018083805190602001908083835b6020831015156104c0578051825260208201915060208101905060208303925061049b565b6001836020036101000a03801982511681845116808217855250505050505090500182805190602001908083835b60208310151561051357805182526020820191506020810190506020830392506104ee565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040529150808060010191505061036c565b508091505090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106105a057805160ff19168380011785556105ce565b828001600101855582156105ce579182015b828111156105cd5782518255916020019190600101906105b2565b5b5090506105db91906105df565b5090565b61060191905b808211156105fd5760008160009055506001016105e5565b5090565b9056fea165627a7a72305820f65f88828070b8ace61815d74c1778f3cf2b6f7898957028d10e21ea093cd7640029";

    public static final String FUNC_STR_ARR = "str_arr";

    public static final String FUNC_ADD = "add";

    public static final String FUNC_GETARR = "getArr";

    protected StringStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StringStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> str_arr(BigInteger param0) {
        final Function function = new Function(FUNC_STR_ARR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> add(String str) {
        final Function function = new Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(str)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getArr() {
        final Function function = new Function(FUNC_GETARR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<StringStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StringStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<StringStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StringStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static StringStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new StringStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static StringStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StringStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
