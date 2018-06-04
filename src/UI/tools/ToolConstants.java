package UI.tools;

import java.io.File;

/**
 * 工具包常量类
 * @author Frankel.Y
 * Created in 22:36 2018/5/29
 */
public class ToolConstants {

    /**
     * 系统当前路径
     */
    public final static String CURRENT_DIR = System.getProperty("user.dir");

    /**
     * properties路径
     */
    public final static String CONFIG_PROPERTY = CURRENT_DIR + File.separator + "config" + File.separator
            + "wongWaterMark.properties";
}