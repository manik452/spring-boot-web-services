package com.encryption.primebank;

import com.encryption.primebank.model.UserRequest;
import com.encryption.primebank.model.UserResponse;
import com.encryption.primebank.service.PBEncryptionService;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

public class PrimeBankKeyGenerator {
    private static final int NUMBER_OF_BYTE=256;
    public static void main(String args[]) throws Exception{

        PBEncryptionService pbEncryptionService = new PBEncryptionService();
        pbEncryptionService.getAuthenticate();





















        PBRestTemplate restTemplate = new PBRestTemplate();
        UserResponse response =restTemplate.getModel();
        System.out.println(response);

//        POST
        UserRequest userRequest = new UserRequest("1","12","Dash","this tis body");
       UserRequest userRequest1 = restTemplate.postUserModel(userRequest);
       System.out.println(userRequest1);
        //Creating a KeyGenerator object
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        //Creating a SecureRandom object
        SecureRandom secRandom = new SecureRandom();

        secRandom.generateSeed(NUMBER_OF_BYTE);
        //Initializing the KeyGenerator
        keyGen.init(secRandom);

        //Creating/Generating a key
        Key sessionKey = keyGen.generateKey();

        System.out.println("Session Key: "+sessionKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(cipher.ENCRYPT_MODE, sessionKey);

        String msg = new String("1234");
        byte[] bytes = cipher.doFinal(msg.getBytes());
        System.out.println(bytes);
    }
}