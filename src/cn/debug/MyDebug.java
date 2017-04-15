package cn.debug;

public class MyDebug {
	static boolean debug=true;
public static void println(Object object,String con)
{
	if(debug)
		System.out.println(object.getClass().getName()+": "+con);
}
}
