package cn.edu.swu.db;

public class DBException extends RuntimeException{
	public DBException() {
		
	}
	
	public DBException(String msg) {
		super(msg);
	}
	public DBException(String msg,Exception ex) {
		super(msg,ex);
	}
}
