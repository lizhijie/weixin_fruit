package cn.business;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clerk {
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
public String scanTime(){
	return df.format(new Date());
}
}
