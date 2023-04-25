/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.Beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author julie
 */
public class Hachage {
    
    public static String hachage(String string) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        md.update(string.getBytes());  
  
        byte byteData[] = md.digest();  

        //convertir  bits -> hexadécimal 
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < byteData.length; i++) {  
           sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));  
        }  
        return sb.toString();
    }
    
}
