package com.kentung.kentungBlockChain.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

    public static String generateSHA256Hash(String input) {
        try {
            // Membuat instance MessageDigest untuk SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Meng-hash input (mengubah string ke dalam array byte)
            byte[] hashBytes = digest.digest(input.getBytes());

            // Mengubah byte array ke dalam bentuk string heksadesimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Jika algoritma SHA-256 tidak ditemukan
            throw new RuntimeException("SHA-256 algorithm not found.", e);
        }
    }

}
