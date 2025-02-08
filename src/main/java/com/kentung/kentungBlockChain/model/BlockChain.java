package com.kentung.kentungBlockChain.model;

import com.kentung.kentungBlockChain.Exception.InvalidBlockException;
import com.kentung.kentungBlockChain.Exception.InvalidNode;
import com.kentung.kentungBlockChain.model.dto.NewBlockDTO;
import com.kentung.kentungBlockChain.utils.Mapper;
import lombok.Builder;

import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.kentung.kentungBlockChain.utils.Mapper.mapToStringAlltransactions;

@Builder
@Getter
@Setter
@Component
@NoArgsConstructor
public class BlockChain {
    private static String name;
    private static String difficulty = "11";
    private static List<Block> blocks = new ArrayList<>();
    private static String reward = "10";




    private static List<Node> nodes = new ArrayList<>();
    //todo : create node feature

    //todo : swab block
    public static void setBlocks(ArrayList<Block> newBlocks){
        blocks =  newBlocks;
    }

    public static Node addNode(String address){
        Node node = Node.builder()
                .id(UUID.randomUUID().toString())
                .index(nodes.size())
                .url(address)
                .build();

        nodes.add(node);

        return node;
    }



    public static Node removeNode(int index){
        if(nodes.isEmpty()){
            throw new InvalidNode("node is empty");
        }

        int sizeNodeBeforeRemove = nodes.size();
        Node savedNode = nodes.get(index);
        nodes.remove(savedNode);
        int sizeNodeAfterRemove = nodes.size();
        if(sizeNodeBeforeRemove == sizeNodeAfterRemove){
            throw new InvalidNode("fail remove node");
        }



        System.out.println("berhasil menghapus node " + savedNode.getId());
        return savedNode;
    }

    public static List<Node> readAllNode(){
        return nodes;
    }




    public static void initGenesisBlock() {
        System.out.println("ini jalan");

        String previouseBlockHash = "0";
        TransactionsListTemoprary.addTransaction(new Transaction(
                "0xDeveloper",
                "0xhudzaifah",
                LocalDateTime.now().toString(),
                2000000000000L
        ));

        byte[] previousBlockHash = "0".getBytes();
        NewBlockDTO genesisNonceEtc = generateNonce(previousBlockHash, TransactionsListTemoprary.getAllTransaction(), "0");
        byte[] genesisNonceWithHash = genesisNonceEtc.getHashWithNonce();
        String genesisNonce = genesisNonceEtc.getNonce();
        String genesisDiff = genesisNonceEtc.getDifficulty();
        String genesisNonceTimeStamp = genesisNonceEtc.getTimeStamp();
        String transactionsString = genesisNonceEtc.getTransactionsString();

        String transactions = Mapper.mapToStringAlltransactions(TransactionsListTemoprary.getAllTransaction());

        //create genesis block
        Block genesisBlock = new Block(previouseBlockHash.getBytes(), genesisNonceWithHash, genesisNonce, transactionsString , "0", genesisDiff, genesisNonceTimeStamp);
        blocks.add(genesisBlock);
        TransactionsListTemoprary.clearAllTransaction();
    }


    public static NewBlockDTO generateNonce(byte[] previoesBlockHasWithNonce, List<Transaction> transaction, String blockIndex) {
        String transactionsString = mapToStringAlltransactions(transaction);
        String timeStamp = LocalDateTime.now().toString();
        int nonce = 0;
        String nonceString = String.valueOf(nonce);
        String hashWIthNonce = "";
        byte[] hashTrueWithNonce = null;

        while (!hashWIthNonce.startsWith(difficulty)) {
            nonceString = String.valueOf(nonce);
            String dataForNewBlock = new String(previoesBlockHasWithNonce) + transactionsString + blockIndex + nonceString;

            hashTrueWithNonce = DigestUtils.sha256(dataForNewBlock);
            hashWIthNonce = new String(hashTrueWithNonce);;
//            System.out.println(nonce);
//            System.out.println(hashWIthNonce);
            nonce++;
        }

        int validNonceString = nonce -1;

        System.out.println("nonce di temukan : " + validNonceString);
        System.out.println("hash with nonce dengan tingkat kesulitan tertentu: " + hashWIthNonce);



        return NewBlockDTO.builder()
                .hashWithNonce(hashTrueWithNonce)
                .nonce(String.valueOf(validNonceString))
                .difficulty(difficulty)
                .timeStamp(timeStamp)
                .transactionsString(transactionsString)
                .build();
    }

    public static Block minningBlock(List<Transaction> transactions, String addressMinner) {
        //give reward
        transactions.add(new Transaction("0xDeveloperReward", addressMinner, LocalDateTime.now().toString(), Long.parseLong(reward)));
        String lastBlockIndex = String.valueOf (blocks.size() - 1);
        String index = String.valueOf(blocks.size());


        Block lastBlock = blocks.get(Integer.valueOf(lastBlockIndex));
        byte [] previousHashBlockWithNonce = lastBlock.getBlockHashWithNonce();

        NewBlockDTO newNonceEtc = generateNonce(
                previousHashBlockWithNonce,
                transactions,
                index
        );

        byte[] nonceWithHash = newNonceEtc.getHashWithNonce();
        String nonce = newNonceEtc.getNonce();
        String diff = newNonceEtc.getDifficulty();
        String nonceTimeStamp = newNonceEtc.getTimeStamp();
        String transactionsString = newNonceEtc.getTransactionsString();
        String blockIndex = String.valueOf (Integer.valueOf(lastBlockIndex) + 1);

        Block newBlock = new Block(previousHashBlockWithNonce, nonceWithHash, nonce, transactionsString, blockIndex, diff, nonceTimeStamp);
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

    public static void validate(List<Block> blocks){
        if(blocks.isEmpty()){
            throw new InvalidBlockException("block is empty");
        }
        System.out.println("do verify all block hash");

        int index = 0;
        int nextIndex = 1;

        // todo : validate genesis block
        String dataForCheckGenesisBlock = "0" + blocks.get(index).getTransactions() + String.valueOf(index) + blocks.get(index).getNonce();
        byte[] hashGenesisCheck = DigestUtils.sha256(dataForCheckGenesisBlock);
        byte [] savedHashGenesis = blocks.get(index).getBlockHashWithNonce();

        System.out.println("this block index  genesis - > " + index );
        System.out.println("saved hash gensis -> " + new String(savedHashGenesis));
        System.out.println("verify hash genesis -> " + new String(hashGenesisCheck));

        System.out.println(savedHashGenesis);
        System.out.println(hashGenesisCheck);
        boolean isValidGenesis = Arrays.equals(savedHashGenesis, hashGenesisCheck);
        System.out.println(isValidGenesis);
        System.out.println();

        if(!isValidGenesis){
            throw new InvalidBlockException("invalid genesis block");
        }

        if(blocks.size() == 1){
            return;
        }




        // todo : validate all block
        for (Block block : blocks) {

            byte[] byteArray1 = blocks.get(index).getBlockHashWithNonce();
            byte[] byteArray2 = blocks.get(nextIndex).getPreviousBlockHashWithNonce();
            System.out.println(byteArray1);
            System.out.println(byteArray2);

            String base64String1 = Base64.encodeBase64String(byteArray1);
            String base64String2 = Base64.encodeBase64String(byteArray2);

            if(!base64String1.equals(base64String2)) {
                System.out.println(blocks.get(index).getBlockHashWithNonce());
                System.out.println(blocks.get(nextIndex).getPreviousBlockHashWithNonce());
                throw new InvalidBlockException("invalid block");
            }

            byte [] prevHash = blocks.get(index).getBlockHashWithNonce();

            String dataForCheck =  new String(prevHash) + blocks.get(nextIndex).getTransactions() + String.valueOf(nextIndex) + blocks.get(nextIndex).getNonce();
            byte[] hash = DigestUtils.sha256(dataForCheck);

            byte [] savedHash = blocks.get(nextIndex).getBlockHashWithNonce();
            System.out.println("this block index  - > " + nextIndex );
            System.out.println("saved hash -> " + new String(savedHash));
            System.out.println("verify hash -> " + new String(hash));

            System.out.println(savedHash);
            System.out.println(hash);
            boolean isValidBlock = Arrays.equals(savedHash, hash);
            System.out.println(isValidBlock);
            System.out.println();

            if(!isValidBlock){
                throw new InvalidBlockException("invalid block with index " + nextIndex);
            }

            index++;
            nextIndex++;



            if(index == blocks.size() - 1) break;
        }






    }


}
