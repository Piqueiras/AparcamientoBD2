/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author alumnogreibd
 */
public class Hash {
    private static final MessageDigest SHA256_DIGEST;

    static {
        try {
            SHA256_DIGEST = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to initialize SHA256 digest", e);
        }
    }

    public static String sha256(String cadena) {
        byte[] encodedHash = SHA256_DIGEST.digest(cadena.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
