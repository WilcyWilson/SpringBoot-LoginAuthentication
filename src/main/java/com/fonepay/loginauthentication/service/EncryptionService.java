package com.fonepay.loginauthentication.service;

public interface EncryptionService {

    void setKey(String myKey);

    String encrypt(String strToEncrypt, String secret);

    String decrypt(String strToDecrypt, String secret);
}
