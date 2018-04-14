package com.hlzj.util;

public class TrimUtil {
    public static String[] dataTrim(char[] data) {
        return String.valueOf(data).split(" ");
    }

    public static String[] dataTrim(String str) {
        return str.split(" ");
    }
}
