package com.capstone.eLearning.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DataEncryptDecrypt {
  
  /**
   * Return encrypted value for a given String argument.
   * 
   * @param str
   * @return encrypted string value.
   * @throws Exception
   */
  public static String encryptData(String str) throws Exception {
    // obtaining byte array from string argument
    byte[] valuesBytes = str.getBytes();
    
    // Encrypting the data
    byte[] encrypted;
    try
    {
      encrypted = getCipher("ENCRYPT_MODE").doFinal(valuesBytes);
    } catch (Exception e)
    {
      throw new Exception("Error encrypting data " + e.getMessage());
    }
    // return encrypted value
    return new String(new BASE64Encoder().encode(encrypted));
  }
  
  /**
   * Return decrypted value for a given encrypted String argument.
   * 
   * @param str
   * @return decrypted string value.
   * @throws Exception
   */
  public static String decryptData(String str) throws Exception {
    try
    {
      // decrypting the data
      byte[] actualValue = getCipher("DECRYPT_MODE").doFinal(new BASE64Decoder().decodeBuffer(str));
      // return decrypted value
      return new String(actualValue, "UTF-8");
    } catch (Exception e)
    {
      throw new Exception("Error decrypting data " + e.getMessage());
    }
  }
  
  /*
   * Return the Cipher object.
   */
  private static Cipher getCipher(String mode) throws Exception {
    // encryption Key in byte array
    byte[] encryptKey = {
      (byte) 0x91, (byte) 0x01, (byte) 0x9, (byte) 0x44, (byte) 0x85, (byte) 0x66, (byte) 0x23, (byte) 0x08 };
    
    // initialization of vector (necessary for CBC mode)
    IvParameterSpec IvParameters = new IvParameterSpec(new byte[] { 92, 48, 36, 78, 91, 80, 05, 43 });
    
    // creating a DES key spec from the byte array key
    DESKeySpec spec = new DESKeySpec(encryptKey);
    
    // initializing secret key factory for generating DES keys
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    
    // creating a DES SecretKey object
    SecretKey theKey = keyFactory.generateSecret(spec);
    
    // obtaining a DES Cipher object
    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    
    if (mode.equalsIgnoreCase("ENCRYPT_MODE"))
    {
      // Initializing the cipher and put it into encrypt mode
      cipher.init(Cipher.ENCRYPT_MODE, theKey, IvParameters);
    }
    else if (mode.equalsIgnoreCase("DECRYPT_MODE"))
    {
      // Initializing the cipher and put it into decrypt mode
      cipher.init(Cipher.DECRYPT_MODE, theKey, IvParameters);
    }
    return cipher;
  }
  
  public static void main(String[] args) throws Exception {
    // String str = "npCTVb1c3vg=";
    // admin prod telglobe#89MCM
    String str = "deepthi";
    String encStr = encryptData(str);
    System.out.println("Encrypted -->" + encStr);
    System.out.println("Decrypted -->" + decryptData("cGFzc3dvcmQ="));
    System.out.println("Encrypted (decrpyt)  -->" + encryptData(decryptData(encStr)));
    System.out.println("Decrypted (encryp decrpyt-)->" + decryptData(encryptData(decryptData(encStr))));
  }
}
