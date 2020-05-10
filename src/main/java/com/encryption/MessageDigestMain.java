package com.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static org.opensaml.xmlsec.algorithm.AlgorithmDescriptor.AlgorithmType.MessageDigest;

public class MessageDigestMain {
    private static void p(Object o){
        System.out.println(o);
    }
    public static void main(String arg[]) throws NoSuchAlgorithmException {
        p("Hello");
/*        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();*/

        /*3Des Implementation*/

        String value= "Hello World";
        String encodeValue= ThreeDES.encode(value);
        p(value+" Encoded Result: "+encodeValue);
        String decodeValue = ThreeDES.decode(encodeValue);
        p(encodeValue +" Converted Value: "+decodeValue);


        /*java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-256");
        messageDigest.update(message.getBytes());
        byte[] result = messageDigest.digest();
        p(result);
        StringBuffer stringBuffer = new StringBuffer();
        for(int i =0; i<result.length; i++)
            stringBuffer.append(Integer.toHexString(0xFF & result[i]));
        p(stringBuffer.toString());*/

    }
}
