package cn.data.database;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;

  
public class DB { 

//	public static final String url = "jdbc:mysql://sqld.duapp.com:4050/nSIomDplmakIMUlWNrdn?characterEncoding=utf-8";  
//    public static final String name = "com.mysql.jdbc.Driver";  
//    public static final String user = "0872f959c6994054bd8bfc1acab139f3";  
//    public static final String password = "bc52d1dd5d1e4a0faa9261e4d2acbbbf"; //bae

	
    public static final String url = "jdbc:mysql://127.0.0.1/shangcheng?characterEncoding=utf-8";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "2468li";
    
    
    //public static final String password = ""; 
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
    public boolean connect() {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接
            if(conn==null)
            	return false;
            else
            	return true;
        } catch (Exception e) {  
            e.printStackTrace(); 
            return false;
        }  
    }  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
