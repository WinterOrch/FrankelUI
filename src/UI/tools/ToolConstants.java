package UI.tools;

import java.io.File;

/**
 * Constant Class of tools Package
 * @author Frankel.Y
 * Created in 22:36 2018/5/29
 */
public class ToolConstants {

    /**
     * Current Path
     */
    public final static String CURRENT_DIR = System.getProperty("user.dir");

    /**
     * Properties Path
     */
    public final static String CONFIG_PROPERTY = CURRENT_DIR + File.separator + "config" + File.separator
            + "wongWaterMark.properties";

    /**
     * Length of Encryption Key
     */
    public final static int AES_KEYSIZE = 128;
    public final static int RSA_KEYSIZE = 1024;

    /**
     * Extension File Name of Key File
     */
    public final static String PUBLIC_KEY_FILE = "puk";
    public final static String PRIVATE_KEY_FILE = "prk";

    /**
     * Standard Size of Watermark after Compressing
     */
    public final static int WATERMARK_SIZE = 128;
}
