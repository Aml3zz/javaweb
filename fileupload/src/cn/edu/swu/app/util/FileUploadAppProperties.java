package cn.edu.swu.app.util;

import java.util.HashMap;
import java.util.Map;

public class FileUploadAppProperties {
		private Map<String, String> properties = new HashMap<String, String>();
		private FileUploadAppProperties() {}
		private static FileUploadAppProperties instance =  new FileUploadAppProperties();
		
		public static FileUploadAppProperties getInstance() {
			return instance;
		}
		
		public void addProperty(String prpopertyName,String propertyValue) {
			properties.put(prpopertyName, propertyValue);
		}
		
		public String getProperty(String propertyName) {
			return properties.get(propertyName);
		}
}
