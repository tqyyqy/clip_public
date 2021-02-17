package xyz.tqyyqy.clip_board.utils;

import java.security.MessageDigest;
import java.util.List;
import java.util.TreeSet;

public class Md5 {
    public static void main(String[] args) {
        System.out.println(getMD5String("18280349102"));
    }
//    public static String getMD5String(String str) {
//        try {
//            // 生成一个MD5加密计算摘要
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            // 计算md5函数
//            md.update(str.getBytes());
//            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
//            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
//            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
//            return new BigInteger(1, md.digest()).toString(16);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//private static final String slat = "&%5123***&&%%$$#@";
    public static String getMD5String(String dataStr) {
        try {
//            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

//    public static String getMD5String(String data) {
//
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        byte[] array = new byte[0];
//        try {
//            array = md.digest(data.getBytes("UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        StringBuilder sb = new StringBuilder();
//
//        for (byte item : array) {
//
//            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
//
//        }
//
//        return sb.toString().toUpperCase();
//
//    }

//去重
    public static List repeatListWayThird(List<String> list){
        TreeSet set = new TreeSet(list);
        list.clear();
        list.addAll(set);
        return list;
    }
}
