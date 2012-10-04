package org.training.dcharnavoki.issuetracker.start.preparing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.ConstErr;

public final class LoadConfig {
	private static final String KEY_IMPL = "dataBase";

	private static final String IMPL_VALUE1 = "xml";
//	private static final String IMPL_VALUE2 = "sql";

	private final static String EXT_XML = ".xml".toUpperCase();
	private final static String EXT_PROPERTY = ".property".toUpperCase();
	
	private static Map<String,String> mapAllowedValues = new HashMap<String, String>();
	
	static{
		mapAllowedValues.put(IMPL_VALUE1, Constant.DAO_XML_IMPLEMENTATION);
	}


	public static ConfigApp getConfig(String file) throws IOException {

		Properties properties = new Properties();
		InputStream is = LoadConfig.class.getResourceAsStream(file);
		if (is == null) {
			throw new FileNotFoundException("file not found:" + file);
		}
		try {
			if (file.toUpperCase().endsWith(EXT_PROPERTY.toUpperCase())) {
				properties.load(is);
			} else if (file.toUpperCase().endsWith(EXT_XML.toUpperCase())) {
				properties.loadFromXML(is);
			} else {
				throw new IllegalArgumentException(
						"this is file is not correct extensions");
			}
			
			String value_impl = properties.getProperty(KEY_IMPL).toLowerCase().trim();
			String s = mapAllowedValues.get(value_impl);
			if (mapAllowedValues.get(value_impl) == null){
				throw new IllegalArgumentException(ConstErr.CONFIG_APP_ILLEGAL);
			}
	
			return new ConfigApp(mapAllowedValues.get(value_impl));
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(ConstErr.CONFIG_NOT_FIND);
		} catch (IOException e) {
			throw new IOException();
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

}
