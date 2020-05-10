package com.encryption.primebank;

import org.omg.CORBA.OBJ_ADAPTER;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.util.Base64;

public class ThreeDES {

    private static String key= "UVeznRX6m8Xb/AG5IpSJQXftf4wSnHVRdAUV7Y5SKjZTZtXzgbWq7HnD60o//2YQ=";
    private static String iv="12367656";
    private static void p(Object o){
        System.out.println(o);
    }
    public static void main(String[] agr){

        //String key = generateKey();
        Encrypter encrypter = new Encrypter(key,iv);
        String pin = "1234";
        String encrypted = encrypter.encrypt(pin);
        String decrypted = encrypter.decrypt(encrypted);

        p(pin+" after encryption: "+encrypted);
        p(encrypted+" after decrypted: "+decrypted);
    }

    public static String generateKey() {
        KeyGenerator generator = null;
        Key keyToBeWrapped = null;
        String key = null;
        try {
            generator = KeyGenerator.getInstance("AES", "BC");
            generator.init(256);
            keyToBeWrapped = generator.generateKey();
            key = new String(Base64.getEncoder().encode(keyToBeWrapped.getEncoded()));
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return key;
    }
}
