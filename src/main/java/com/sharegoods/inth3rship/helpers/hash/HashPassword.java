package com.sharegoods.inth3rship.helpers.hash;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class HashPassword {
    // Available algorithms are: MD2, MD5, SHA-1, SHA-224, SHA-256, SHA-384, SHA-512
    public static String getPasswordHash(byte[] imputBytes, String algorithm) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(imputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        } catch (Exception e) {

        }
        return hashValue;
    }
}

