package cn.edu.swu.app.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileUploadIOException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.edu.swu.app.beans.FileUploadBeans;
import cn.edu.swu.app.exception.InvalidExtNameException;
import cn.edu.swu.app.util.FileUploadAppProperties;
import cn.edu.swu.db.UploadFileDao;

public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String TEMP_DIR = "/tmp/tmpFactory";
	
	private static final String FILE_PATH = "/tmp/files";
	private UploadFileDao dao = new UploadFileDao();
	
	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String path = null;
		//获取 ServletFileUpload 对象.
		ServletFileUpload upload = getServletFileUpload();
		
		try {
			//把需要上传的 FileItem 都放入到该 Map 中
			//键: 文件的待存放的路径, 值: 对应的 FileItem 对象
			Map<String, FileItem> uploadFiles = new HashMap<String, FileItem>();
			
			//解析请求, 得到 FileItem 的集合.
			List<FileItem> items = upload.parseRequest(req);
			
			//1.构建FileUploadBean 的集合，同时填充uploadFiles
			List<FileUploadBeans>	beans = buildFileUploadBeans(items,uploadFiles);
			
			//2.校验扩展名
			vaidateExtName(beans);
			
			//3.校验文件的大小：在解析时已经校验过，只需要通过异常得到结果
			//4.进行文件的上传操作
			upload(uploadFiles);
			
			//5.把上传的文件信息保存在数据库中
			saveBeans(beans);
			
			
			path = "success.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			
			path = "upload.jsp";
			if(e instanceof FileUploadIOException) {
				req.setAttribute("msg","");
			}
			req.setAttribute("msg", e.getMessage());
		}
		req.getRequestDispatcher(path).forward(req, resp);
	}

	/**
	 * 文件上传前的准备工作. 得到 filePath 和 InputStream
	 * @param uploadFiles
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void upload(Map<String, FileItem> uploadFiles) throws IOException {
		for(Map.Entry<String, FileItem> uploadFile:uploadFiles.entrySet()) {
			String filePath = uploadFile.getKey();
			FileItem item = uploadFile.getValue();
			
			upload(filePath,item.getInputStream());
		}
		
	}

	/**
	 * 文件上传的 IO 方法.
	 * 
	 * @param filePath
	 * @param inputStream
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	private void upload(String filePath, InputStream inputStream) throws IOException {
		OutputStream out = new FileOutputStream(filePath);
		
		byte [] buffer = new byte[1024];
		int len = 0;
		
		while((len = inputStream.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		inputStream.close();
		out.close();
		System.out.println(filePath);
	}
	
	/**
	 * 利用传入的 FileItem 的集合, 构建 FileUploadBean 的集合, 同时填充 uploadFiles
	 * 
	 * FileUploadBean 对象封装了: id, fileName, filePath, fileDesc
	 * uploadFiles: Map<String, FileItem> 类型, 存放文件域类型的  FileItem. 键: 待保存的文件的名字 ,值: FileItem 对象
	 * 
	 * 构建过程:
	 * 1. 对传入 FileItem 的集合进行遍历. 得到 desc 的那个 Map. 键: desc 的 fieldName(desc1, desc2 ...). 
	 * 值: desc 的那个输入的文本值
	 * 
	 * 2. 对传入 FileItem 的集合进行遍历. 得到文件域的那些 FileItem 对象, 构建对应的 key (desc1 ....) 来获取其 desc.
	 * 构建的 FileUploadBean 对象, 并填充 beans 和 uploadFiles
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	private List<FileUploadBeans> buildFileUploadBeans(List<FileItem> items, Map<String, FileItem> uploadFiles) throws UnsupportedEncodingException {
		List<FileUploadBeans> beans = new ArrayList<FileUploadBeans>();
		
		//1.遍历fileitem 的集合，先得到desc  的 Map<String, String>
				//其中键：filedname（desc1，desc2...) 值：表单对应字段的值ֵ
		Map<String, String> descs = new HashMap<String, String>();
		
		for(FileItem item : items) {
			if(item.isFormField()) {
				String filedName = item.getFieldName();
				String desc = item.getString("UTF-8");
				descs.put(filedName,desc);
			}
		}
		
		//2.再遍历fileitem 集合，得到文件域的fileitem对象
				//每得到一个fileitem对象都创建一个FileUploadBean对象
				//得到fileName，构建filepath，从1中的map 得到当前fileitem对应的那个desc
				//使用filedName 后面的数字去匹配每个desc
		for(FileItem item : items) {
			if(! item.isFormField()) {
				String filedName = item.getFieldName();
				String index = filedName.substring(filedName.length() - 1);
	
				String fileName = item.getName();
				String desc = descs.get("desc" + index);
				String filePath = getFilePath(fileName);
				
				FileUploadBeans bean = new FileUploadBeans(fileName, filePath, desc);
				beans.add(bean);
				
				uploadFiles.put(filePath, item);
			}
		}
		
		return beans;
	}

	/**
	 * 根据跟定的文件名构建一个随机的文件名
	 * 1. 构建的文件的文件名的扩展名和给定的文件的扩展名一致
	 * 2. 利用 ServletContext 的 getRealPath 方法获取的绝对路径
	 * 3. 利用了 Random 和 当前的系统时间构建随机的文件的名字
	 */
	@SuppressWarnings("unchecked")
	private String getFilePath(String fileName) {

		String extName = fileName.substring(fileName.lastIndexOf("."));
		Random random = new Random();
		
		String filePath = FILE_PATH 
				+ "/" +System.currentTimeMillis() + random.nextInt(1000) + extName;
		
		return filePath;
	}
	
	@SuppressWarnings("unchecked")
	private void saveBeans(List<FileUploadBeans> beans) {
		dao.save(beans);
		
	}

	/**
	 * 校验扩展名是否合法
	 * @param beans: 
	 * @throws InvalidExtNameException 
	 */
	@SuppressWarnings("unchecked")
	private void vaidateExtName(List<FileUploadBeans> beans) throws InvalidExtNameException {
		String extString = FileUploadAppProperties.getInstance().getProperty("exts");
		List<String> exList = Arrays.asList(extString.split(","));
		System.out.println(exList);
		
		for(FileUploadBeans bean:beans) {
			String fileName = bean.getFileName();
			
			String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
			System.out.println(extName);
			if(!exList.contains(extName)) {
				throw new InvalidExtNameException(fileName + "文件扩展名不合法");
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	private ServletFileUpload getServletFileUpload() {
		
		String filemaxsize = FileUploadAppProperties.getInstance().getProperty("file.max.size");
		String totalfilesize = FileUploadAppProperties.getInstance().getProperty("total.file.max.size");
		
		//System.out.println(extString);
		//System.out.println(filemaxsize);
		//System.out.println(totalfilesize);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 500);
		
		File tempDirectory = new File(TEMP_DIR);
		factory.setRepository(tempDirectory);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		upload.setSizeMax(Integer.parseInt(totalfilesize));
		upload.setFileSizeMax(Integer.parseInt(filemaxsize));
		
		return upload;
	}
}
