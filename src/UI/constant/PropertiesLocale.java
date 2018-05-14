package UI.constant;

import java.io.*;
import java.util.Properties;
/**
 * 通过调用配置文件改变内容
 * @author Frankel.Y
 * Created in 20:08 2018/4/30
 * Modified by
 */
public class PropertiesLocale {
    public static int locale;

    /**
     * 初始化配置文件以选择语言
     * created in 20:39 2018/4/30
     */
    public static void initialize(){
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(UIConstants.CONFIG_PROPERTY));
            pps.load(in);
            if(pps.getProperty("LANGUAGE").equals("CN")){
                locale = 1;
            }
            else if(pps.getProperty("LANGUAGE").equals("EN")){
                locale = 2;
            }
            else{
                locale = 1;
                changeLanguage("CN");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取配置文件中键值
     * created in 20:40 2018/4/30
     */
    public static String getProperty(String key){
        Properties pps = new Properties();
        if(locale==2){
            try {
                InputStream in = new BufferedInputStream(new FileInputStream(UIConstants.EN_PATH_PROPERTY));
                pps.load(in);
                String value = pps.getProperty(key);
                return value;
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        else{
            try {
                InputStream in = new BufferedInputStream(new FileInputStream(UIConstants.CN_PATH_PROPERTY));
                pps.load(in);
                String value = pps.getProperty(key);
                return value;
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 改变界面语言，修改配置文件
     * created in 20:48 2018/4/30
     */
    public static void changeLanguage(String language){
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(UIConstants.CONFIG_PROPERTY));
            props.load(in);
            OutputStream fos = new FileOutputStream(UIConstants.CONFIG_PROPERTY);
            props.setProperty("LANGUAGE", language);
            props.store(fos, "Update value");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error in updating language config files");
        }
    }
}
