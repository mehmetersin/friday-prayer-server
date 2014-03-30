package com.keymb.fps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {

	static Properties configProp = null;

	public String getConfig(String param) {

		if (configProp == null) {

			configProp = new Properties();

			InputStream in = Util.class.getResourceAsStream("config.properties");
			try {
				configProp.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return configProp.getProperty(param);
	}

}
