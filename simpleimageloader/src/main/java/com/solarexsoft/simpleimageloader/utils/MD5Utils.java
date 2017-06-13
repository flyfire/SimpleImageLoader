package com.solarexsoft.simpleimageloader.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class MD5Utils {
    private static MessageDigest sDigest;

    static {
        try {
            sDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            L.d("MD5Utils", "MD5 not supported");
        }
    }

    public static String toMD5(String key) {
        if (sDigest == null) {
            return String.valueOf(key.hashCode());
        }
        sDigest.update(key.getBytes());
        return convert2HexString(sDigest.digest());
    }

    private static String convert2HexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


}
