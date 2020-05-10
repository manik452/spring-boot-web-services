package com.encryption;

public interface FIMIPasswordEncryptor {

    String encrypt(String nextChallenge, String clearPassword);

}
