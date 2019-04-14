package cn.edu.swu.mvcapp.dao;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;



import cn.edu.swu.mvcapp.db.JdbcUtils;


public class DAO<T> {
	
	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;
	
	public DAO() {
		//Type supreClass = getClass().getGenericSuperclass();
		Type superClass = getClass().getGenericSuperclass();
		if(superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType)superClass;
			
			Type [] typeArgs = parameterizedType.getActualTypeArguments();
			if(typeArgs != null && typeArgs.length > 0) {
				if(typeArgs[0] instanceof Class) {
					clazz = (Class<T>)typeArgs[0];
				}
			}
		}
	}
	
	/*
	 * 返回某一个字段的值，例如返回某一条记录的customerName，或返回数据表中有多少条记录等。
	 * */
		public <E> E getForValue(String sql,Object ... args){
			Connection connection =null;
			try {
				connection = JdbcUtils.getConnection();
				return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtils.releaseConnection(connection);
			}
			
			return null;
	}
	
	public List<T> getForList(String sql,Object ... args){
		Connection connection =null;
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
		
		
		return null;
	}
	
	
	
		
		public T get(String sql,Object ... args) {
			Connection connection =null;
			try {
				connection = JdbcUtils.getConnection();
				return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtils.releaseConnection(connection);
			}
			
			return null;
		}
		
		public void update(String sql,Object ... args) {
			Connection connection = null;
			
			try {
				connection = JdbcUtils.getConnection();
				queryRunner.update(connection, sql, args);
		} catch (Exception e) {
					e.printStackTrace();
			}finally {
				JdbcUtils.releaseConnection(connection);
			}
		}
}

