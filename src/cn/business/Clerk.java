package cn.business;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clerk {
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
public static String scanTime(){
	return df.format(new Date());
}
public static int getRandom(int count) {
  return (int) Math.round(Math.random() * (count));
}

private static String string = "abcdefghijklmnopqrstuvwxyz"; 
public static String getRandomString(int length){
    StringBuffer sb = new StringBuffer();
    int len = string.length();
    for (int i = 0; i < length; i++) {
        sb.append(string.charAt(getRandom(len-1)));
    }
    return sb.toString();
}
}
