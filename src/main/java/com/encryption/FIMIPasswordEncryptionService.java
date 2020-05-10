package com.encryption;


import com.logger.PBLogger;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
@Qualifier("fimiPasswordEncryptionService")
public class FIMIPasswordEncryptionService implements FIMIPasswordEncryptor {

    private PBLogger logger;

    private final static String CHAR_SET = "UTF-8";
    private final static String TRIPPLE_DES_ALGORITHM = "DESede";
    private final static String TRIPPLE_DES_TRANSFORMATION = "DESede/ECB/PKCS5Padding";
    private final static String DES_ALGORITHM = "DES";
    private final static String DES_TRANSFORMATION = "DES/ECB/PKCS5Padding";

    public FIMIPasswordEncryptionService(PBLogger logger) {
        this.logger = logger;
    }

    private String doPadding(String str, int length, char delimiter){
        String padding = "";
        for(int i=0;i<length;i++){
            padding = padding + delimiter;
        }
        return str.concat(padding);
    }


    private String padTrail(String plainStr, String plainKeyStr){
        int clearPassLength = 8;
        int clearPassPaddingLength =  clearPassLength - plainStr.length();
        plainStr = this.doPadding(plainStr, clearPassPaddingLength, ' ');

        int key2Length = 16;
        int paddingLength = key2Length - plainKeyStr.length();
        String strPadded = this.doPadding(plainKeyStr, paddingLength, ' ');

        return strPadded.concat(plainStr);

    }

    private String getEncryptedToHexString(byte [] keyBytes, String password, String algorithm, String transformation) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        SecretKey secretKey = new SecretKeySpec(keyBytes, algorithm);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] plainTextBytes = password.getBytes(CHAR_SET);
        byte[] buf = cipher.doFinal(plainTextBytes);
        return Hex.encodeHexString(buf)
                .substring(0,16)
                .toUpperCase();
    }

    private String encrypt3DES(String clearPassword)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        clearPassword = clearPassword.toUpperCase();
        String plainPass = clearPassword.length() > 8 ? clearPassword.substring(0,8) : clearPassword;
        String plainKey = clearPassword.length() > 16 ? clearPassword.substring(0,16) : clearPassword;

        String keyFor3Des = padTrail(plainPass, plainKey);

        byte [] keyBytes = keyFor3Des.getBytes(CHAR_SET);
        return this.getEncryptedToHexString(keyBytes, plainPass, TRIPPLE_DES_ALGORITHM, TRIPPLE_DES_TRANSFORMATION);
    }

    private String encryptDES(String nextChallenge, String passHash)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte [] keyBytes = DatatypeConverter.parseHexBinary(passHash);
        return this.getEncryptedToHexString(keyBytes, nextChallenge, DES_ALGORITHM, DES_TRANSFORMATION);
    }

    /*private static void print(String label, String printable){
        System.out.println(label+printable+" LENGTH:"+printable.length());
    }*/

    public String encrypt(String nextChallenge, String clearPassword) {
        String encryptedPass = "";
        try {
            encryptedPass = this.encryptDES(nextChallenge, encrypt3DES(clearPassword));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return encryptedPass;
    }

    /*public static void main(String[] args) {
        try{
            FIMIPasswordEncryptionService encryptionService = new FIMIPasswordEncryptionService();

            String clearPass = "fimiprod";
            String nextChallenge = "06253765";

            String finalPass = encryptionService.encrypt(nextChallenge, clearPass);
            print("FINAL PASS:", finalPass);

        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

}