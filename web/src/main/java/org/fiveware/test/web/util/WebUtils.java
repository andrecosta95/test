package org.fiveware.test.web.util;

import java.io.InputStream;
import java.util.Properties;

public final class WebUtils {

	private static final String VALIDATION_PROPERTIES = "validation.properties";
	
	private WebUtils() {
	}
	
	public static String getValidationProperty(String key) {
		
		Properties prop = new Properties();
    	InputStream is = null;
    	
    	try {
        
    		is = WebUtils.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES);
    		if(is != null) {
    			
    			prop.load(is);
    			
    			return prop.getProperty(key);
    		}
 
    	} catch (Exception ex) {
        } finally{
        	if(is != null){
        		try {is.close();}catch(Exception e){}
        	}
        }
    	
    	return null;
	}

}
