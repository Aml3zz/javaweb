package cn.edu.swu.app.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.edu.swu.app.util.FileUploadAppProperties;

public class FileUploadAppListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		InputStream in = getClass().getClassLoader()
				.getResourceAsStream("/upload.properties");
		
		Properties properties = new Properties();		
		try {
			properties.load(in);
			
			for(Map.Entry<Object, Object> prop : properties.entrySet()) {
				String propertyName = (String) prop.getKey();
				String propertyValue =  (String) prop.getValue();
				
				FileUploadAppProperties.getInstance().addProperty(propertyName, propertyValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
}
