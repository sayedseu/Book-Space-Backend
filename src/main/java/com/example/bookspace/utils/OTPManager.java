package com.example.bookspace.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public final class OTPManager {

    private static char[] OTP(int len) {

        String numbers = "0123456789";
        Random rndm_method = new Random();

        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }

    public static String send(String baseUrl, String username, String password, String number) {

        int otpLength = 4;

        char[] OTP = OTP(otpLength);
        String OTPcode = new String(OTP);
        String smsMessage = "Your OTP is: " + OTPcode.trim();
        String urlParameters = "username=" + username + "&password=" + password + "&number=" + number + "&message=" + smsMessage;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        URL myurl = null;
        HttpURLConnection con = null;
        try {
            myurl = new URL(baseUrl);
            con = (HttpURLConnection) myurl.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }
            StringBuilder content;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                }

                String response = content.toString();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    sb.append(response.charAt(i));
                }

                int result = Integer.parseInt(sb.toString());
                if (result == 1101) {
                    return OTPcode.trim();
                } else {
                    return null;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return null;
    }
}
