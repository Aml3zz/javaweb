package cn.edu.swu.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.edu.swu.app.beans.FileUploadBeans;

public class UploadFileDao extends DAO<FileUploadBeans>{
	
	@SuppressWarnings("unchecked")
	public List<FileUploadBeans> getfiles(){
		Connection conn = null;
		
		try {
			conn = JDBCUtils.getConnection();
			String sql = "SELECT id,file_name fileName,file_path filePath,"+
						"file_desc fileDesc FROM upload_files";
			return getForList(conn, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(conn);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public  void  save(List<FileUploadBeans> uploadFiles) {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "INSERT INTO upload_files (file_name,file_path,file_desc) VALUES"
					+ "(?,?,?)";
			for(FileUploadBeans file : uploadFiles) {
				update(conn, sql, file.getFileName(),file.getFilePath(),file.getFileDesc());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(conn);
		}
	}
}
