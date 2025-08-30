package com.coursetrackr.util;

import java.security.MessageDigest;

public class PasswordUtil{
    public static String sha256(String s){
        try{
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[] b=md.digest(s.getBytes("UTF-8"));
            StringBuilder sb=new StringBuilder();
            for(byte x:b) sb.append(String.format("%02x",x));
            return sb.toString();
        }catch(Exception e){throw new RuntimeException(e);}
    }
}
