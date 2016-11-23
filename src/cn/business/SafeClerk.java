package cn.business;

import java.sql.Connection;
import java.sql.SQLException;

import cn.data.database.DB;

public class SafeClerk extends Clerk {
	private DB db1 = null;
	private Connection con = null;
	protected String userId;

	public SafeClerk(String userId) {
		this.userId = userId;
	}

	protected Connection dbGateOpen() {
		if (db1 == null)
			db1 = new DB();
		if (con == null) {
			db1.connect();
			con = db1.conn;
		}
		try {
			if (con.isClosed())
				db1.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}

	protected void dbGateClose() {
		if (db1 != null) {
			if (con != null) {

				try {
					if (!con.isClosed())
						db1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	protected boolean isSafeUser() {
		// if(userId==null)
		// return false;
		return true;
	}

	protected void finalize() {

		this.dbGateClose();

	}

	public boolean checkString(String string) {
		if (string == null)
			return false;
		if (string == "")
			return false;
		return true;
	}

	public boolean checkInt(int num) {
		if (num < 1)
			return false;
		if (num > 200000000)
			return false;
		return true;
	}
}
