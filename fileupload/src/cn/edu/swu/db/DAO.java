package cn.edu.swu.db;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.lang.reflect.ParameterizedType;


public class DAO<T> {

	public static QueryRunner runner = new QueryRunner();
	
	private Class<T> clazz;
	@SuppressWarnings("unchecked")
	public DAO() {
		
		Type type = getClass().getGenericSuperclass();
		
		if(type instanceof ParameterizedType)	{
			ParameterizedType pt = (ParameterizedType) type;
			
			Type [] parameterArgs = pt.getActualTypeArguments();
			
			if(parameterArgs != null && parameterArgs.length > 0) {
				if(parameterArgs[0] instanceof Class) {
					clazz = (Class<T>) parameterArgs[0];
				}
			}
			
		}		
	}
	
	@SuppressWarnings("unchecked")
	protected void update(Connection conn, String sql,Object ... args) throws SQLException {
		runner.update(conn,sql,args);
	}
	
	@SuppressWarnings("unchecked")
	protected T get(Connection conn,String sql,Object ... args) throws SQLException {
		return runner.query(conn, sql, new BeanHandler<>(clazz),args);
		
		
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> getForList(Connection conn,String sql,Object ... args) throws SQLException {
		return runner.query(conn, sql, new BeanListHandler<>(clazz),args);
	}
	
	@SuppressWarnings("unchecked")
	protected <E> E getValue(Connection conn, String sql, Object ... args) throws SQLException{
		E result = null;
		result = (E) runner.query(conn, sql, new ArrayHandler(), args)[0];
		return result;
	}
	
}
