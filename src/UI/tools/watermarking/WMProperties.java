package UI.tools.watermarking;


import UI.tools.ToolConstants;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WMProperties {
    /**
     * 获取配置文件中键值
     * created in 20:40 2018/4/30
     */
    public static String getProperty(String key) {

        Properties pps = new Properties();

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(ToolConstants.CONFIG_PROPERTY));
            pps.load(in);
            String value = pps.getProperty(key);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
