package com.fonepay.loginauthentication.service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Arrays;
import java.util.Base64;

public interface EncryptionService {

    void setKey(String myKey);

    String encrypt(String strToEncrypt, String secret);

    String decrypt(String strToDecrypt, String secret);
}
