package com.encryption.primebank.service;

import com.encryption.primebank.model.RequestModel;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;

public class PBEncryptionService {
    private static final int NUMBER_OF_BYTE=256;
    private static final String FIS_PUBLIC_KEY="FISPUBLICKEY";
    private static final String SESSION_KEY="FISPUBLICKEY";
    private static final String KEY_ALIAS="FISPUBLICKEY";
    private static final String INSTITUTION_ID="INSTITUTEiD";


    public void getAuthenticate() throws Exception {
        String key = this.generateKey();
        String encryptRSAsessionKey= this.encryptRSA(SESSION_KEY,FIS_PUBLIC_KEY);
        RequestModel requestModel = new RequestModel();
        requestModel.setKeyAlias(this.encrypt(KEY_ALIAS,encryptRSAsessionKey));
        requestModel.setInstitutionId(this.encrypt(INSTITUTION_ID,encryptRSAsessionKey));
//        TODO: Encryption dycription
        requestModel.setSignature("");
        requestModel.setSessionKey(encryptRSAsessionKey);
    }

    /*private String getKeyAlias() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        //Creating a SecureRandom object
        SecureRandom secRandom = new SecureRandom();

        secRandom.generateSeed(NUMBER_OF_BYTE);
        //Initializing the KeyGenerator
        keyGen.init(secRandom);

        //Creating/Generating a key
        Key sessionKey = keyGen.generateKey();
        return sessionKey;
    }*/

    public String encrypt(final String plainValue, final String key) throws Exception {
        Cipher cipher = this.getEncryptionCipher(key);
        final byte[] encryptedBytes = cipher.doFinal(plainValue.getBytes("UTF-8"));
        byte[] iV = cipher.getIV();
        return new String(Base64.getEncoder().encode(encryptedBytes)) + "." + new
                String(Base64.getEncoder().encode(iV));
    }

    private Cipher getEncryptionCipher(final String key) throws Exception {
        final Key aesKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
        final Cipher encryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(generateIv());
        encryptionCipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec);
        return encryptionCipher;
    }

    public String generateKey() {
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

    private byte[] generateIv() {
        SecureRandom random = new SecureRandom();
        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        return ivBytes;
    }

    private KeyStore generatePrivateKey() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//Jks file path
        FileInputStream fis = new FileInputStream("<Jks file path>\\KeyStore.jks");
        keyStore.load(fis, "<Password>".toCharArray()); //jks file password
        PrivateKey privateKey = (PrivateKey) keyStore.getKey("<Keyalias>", "<Password>".toCharArray());
        return keyStore;
    }

    public String encryptRSA(final String plainValue, final String key) throws Exception {
        Cipher cipher = this.getRSAEncryptionCipher(key);
        final byte[] encryptedBytes = cipher.doFinal(plainValue.getBytes("UTF-8"));
        byte[] iV = cipher.getIV();
        return new String(Base64.getEncoder().encode(encryptedBytes)) + "." + new
                String(Base64.getEncoder().encode(iV));
    }

    private Cipher getRSAEncryptionCipher(final String key) throws Exception {
        final Key rsaKey = new SecretKeySpec(Base64.getDecoder().decode(key), "RSA");
        final Cipher encryptionCipher = Cipher.getInstance("SHA256withRSA", "BC");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(generateIv());
        encryptionCipher.init(Cipher.ENCRYPT_MODE, rsaKey, ivParameterSpec);
        return encryptionCipher;
    }


}
