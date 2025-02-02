package com.kentung.kentungBlockChain.model;

import com.kentung.kentungBlockChain.utils.SHA256;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.kentung.kentungBlockChain.utils.Mapper.mapToStringAlltransactions;

@Builder
@Getter
public class BlockChain {
    private static String name;
    private static String difficulty = "1111";
    private static List<Block> blocks = new ArrayList<>();
    private static String reward = "10";

    public static void initGenesisBlock() {
        System.out.println("ini jalan");
        String previouseBlockHash = "0";
        TransactionsListTemoprary.addTransaction(new Transaction(
                "0xDeveloper",
                "0xhudzaifah",
                LocalDateTime.now().toString(),
                2000000000000L
        ));

        TransactionsListTemoprary.addTransaction(new Transaction(
                "0xhudzaifah",
                "0xpipit",
                LocalDateTime.now().toString(),
                10000L
        ));

        List<String> genesisNonceEtc = generateNonce(previouseBlockHash, TransactionsListTemoprary.getAllTransaction(), "0");
        String genesisNonceWithHash = genesisNonceEtc.get(0);
        String genesisNonce = genesisNonceEtc.get(1);
        String genesisDiff = genesisNonceEtc.get(2);
        String genesisNonceTimeStamp = genesisNonceEtc.get(3);

        //create genesis block
        Block genesisBlock = new Block(previouseBlockHash, genesisNonceWithHash, genesisNonce, TransactionsListTemoprary.getAllTransaction(), 0, genesisDiff, genesisNonceTimeStamp);
        blocks.add(genesisBlock);
        TransactionsListTemoprary.clearAllTransaction();
    }


    public static List<String> generateNonce(String previoesBlockHas, List<Transaction> transaction, String blockIndex) {
        String transactionsString = mapToStringAlltransactions(transaction);
        String timeStamp = LocalDateTime.now().toString();
        int nonce = 0;
        String hashWIthNonce = "";
        while (!hashWIthNonce.startsWith(difficulty)) {
            hashWIthNonce = SHA256.generateSHA256Hash(previoesBlockHas + transactionsString + timeStamp + blockIndex + nonce);
            nonce++;
        }

        System.out.println("nonce di temukan : " + nonce);
        System.out.println("hash with nonce dengan tingkat kesulitan tertentu: " + hashWIthNonce);

        return List.of(hashWIthNonce, String.valueOf(nonce), difficulty, timeStamp);
    }

    public static Block minningBlock(List<Transaction> transactions, String addressMinner) {
        //give reward
        transactions.add(new Transaction("0xDeveloperReward", addressMinner, LocalDateTime.now().toString(), Long.parseLong(reward)));
        String lastBlockIndex = String.valueOf (blocks.size() - 1);

        Block lastBlock = blocks.get(Integer.valueOf(lastBlockIndex));
        String previousHashBlockWithNonce = lastBlock.getBlockHashWithNonce();

        List<String> newNonceEtc = generateNonce(
                previousHashBlockWithNonce,
                transactions,
                lastBlockIndex
        );

        String nonceWithHash = newNonceEtc.get(0);
        String nonce = newNonceEtc.get(1);
        String diff = newNonceEtc.get(2);
        String nonceTimeStamp = newNonceEtc.get(3);
        String blockIndex = String.valueOf (Integer.valueOf(lastBlockIndex) + 1);

        Block newBlock = new Block(previousHashBlockWithNonce, nonceWithHash, nonce, transactions, Integer.valueOf(blockIndex), diff, nonceTimeStamp);
        System.out.println("new block created");

        blocks.add(newBlock);

        return newBlock;
    }

    public static List<Block> GetAllBlock() {
        for (int i = 0; i < blocks.size(); i++) {
            System.out.println(blocks.get(i));
        }
        return blocks;
    }


}
