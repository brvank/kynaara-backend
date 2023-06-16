package com.retail.kynaara.utility;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AppSecurity {
    @Value("${secret_key}")
    private static final String SECRET_KEY = "Bg7ycxFDdv5QovWhcDzFj";
    @Value("${salt}")
    private static final String SALT = "dXT82rXvt8G4OFCqHA4m3";

    private final static int GCM_TAG_LENGTH = 16;

    public static String encrypt(String strToEncrypt){
        try{
            byte[] iv = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey temp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(temp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String strToDecrypt){
        try{
            byte[] iv = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey temp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(temp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
