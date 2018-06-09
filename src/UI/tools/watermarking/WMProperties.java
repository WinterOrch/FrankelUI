package UI.tools.watermarking;


import UI.constant.UIConstants;
import UI.tools.ToolConstants;

import java.io.*;
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

    /**
     * Change Config of Algorithm selected
     * created in 18:08 2018/6/9
     */
    public static void changeAlgorithm(String type, String name){
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(ToolConstants.CONFIG_PROPERTY));
            props.load(in);
            OutputStream fos = new FileOutputStream(ToolConstants.CONFIG_PROPERTY);
            props.setProperty(type, name);
            props.store(fos, "Update value");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error in updating language config files");
        }
    }
}
