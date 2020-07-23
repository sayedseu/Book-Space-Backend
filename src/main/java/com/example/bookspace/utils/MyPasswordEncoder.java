package com.example.bookspace.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MyPasswordEncoder {

    public String encodeBase64(String data) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data.getBytes());
    }

    public String decodeBase64(String data) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(data));
    }

    public String encode(String data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(data.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

}
