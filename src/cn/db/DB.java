package cn.db;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;
import java.sql.SQLException;

  
public class DB {  
    public static final String url = "jdbc:mysql://127.0.0.1/shangcheng";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "";  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public boolean connect() {  
        try {  
            Class.forName(name);//ָ����������  
            conn = DriverManager.getConnection(url, user, password);//��ȡ����
            if(conn==null)
            	return false;
            else
            	return true;
        } catch (Exception e) {  
            e.printStackTrace(); 
            return false;
        }  
    }  
    public ResultSet query(String sql) {  
        try {  
        	if(connect())
            pst = conn.prepareStatement(sql);//׼��ִ�����  
        	return pst.executeQuery();
        } catch (Exception e) {  
            e.printStackTrace();
        }
		return null;  
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