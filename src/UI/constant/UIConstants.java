package UI.constant;

import UI.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Description 与UI构建相关的常量定义
 * @author Frankel.Y
 * Created in 0:01 2018/4/29
 * Modified by
 */
public class UIConstants {

    /**
     * 软件名称,版本
     */
    public final static String APP_NAME = "Malice";
    public final static String APP_VERSION = "v_1.60_160511";

    /**
     * 主窗口大小
     */
    public final static int MAIN_WINDOW_X = 240;
    public final static int MAIN_WINDOW_Y = 100;
    public final static int MAIN_WINDOW_WIDTH = 900;
    public final static int MAIN_WINDOW_HEIGHT = 636;

    /**
     * 系统当前路径
     */
    public final static String CURRENT_DIR = System.getProperty("user.dir");

    /**
     * properties路径
     */
    public final static String CONFIG_PROPERTY = CURRENT_DIR + File.separator + "config" + File.separator
            + "config.properties";
    public final static String CN_PATH_PROPERTY = CURRENT_DIR + File.separator + "config" + File.separator
            + "zh-cn.properties";
    public final static String EN_PATH_PROPERTY = CURRENT_DIR + File.separator + "config" + File.separator
            + "en-ww.properties";

    /**
     * 主窗口图标
     */
   public final static Image IMAGE_ICON = Toolkit.getDefaultToolkit()
            .getImage(MainWindow.class.getResource("/icon/icon.png"));

    /**
     * 主窗口背景色
     */
    public final static Color MAIN_BACK_COLOR = Color.WHITE;

    /**
     * 工具栏尺寸及颜色
     */
    public final static Color NAVI_BAR_BACK_COLOR = new Color(33, 150, 243);
    public final static Color PRESSED_BACK_COLOR = new Color(38, 157, 243);
    public final static Color LIST_BACK_COLOR = new Color(62, 62, 62);
    public final static Color ROLL_OVER_COLOR = new Color(97, 176, 239);
    public final static int NAVI_BAR_WIDTH = 48;

    /**
     * 字体
     */
    // 标题字体
    public final static Font FONT_TITLE = new Font(PropertiesLocale.getProperty("UI.FONT.FAMILY"), Font.PLAIN, 27);
    // 普通字体
    public final static Font FONT_NORMAL = new Font(PropertiesLocale.getProperty("UI.FONT.FAMILY"), Font.PLAIN, 13);
    //按钮字体
    public final static Font FONT_BUTTON = new Font(PropertiesLocale.getProperty("UI.FONT.FAMILY"), Font.PLAIN, 13);

    /**
     * 工具栏图标
     */
    public final static ImageIcon ICON_SETTING = new ImageIcon(MainWindow.class.getResource("/icon/settings-normal.png"));
    public final static ImageIcon ICON_SETTING_READY = new ImageIcon(MainWindow.class.getResource("/icon/settings-ready.png"));
    public final static ImageIcon ICON_SETTING_PRESSED = new ImageIcon(MainWindow.class.getResource("/icon/settings-pressed.png"));

    public final static ImageIcon ICON_PICTURE = new ImageIcon(MainWindow.class.getResource("/icon/picture-normal.png"));
    public final static ImageIcon ICON_PICTURE_READY = new ImageIcon(MainWindow.class.getResource("/icon/picture-ready.png"));
    public final static ImageIcon ICON_PICTURE_PRESSED = new ImageIcon(MainWindow.class.getResource("/icon/picture-pressed.png"));

    public final static ImageIcon ICON_MESSAGE = new ImageIcon(MainWindow.class.getResource("/icon/message-normal.png"));
    public final static ImageIcon ICON_MESSAGE_READY = new ImageIcon(MainWindow.class.getResource("/icon/message-ready.png"));
    public final static ImageIcon ICON_MESSAGE_PRESSED = new ImageIcon(MainWindow.class.getResource("/icon/message-pressed.png"));
    /**
     * 表格线条背景色
     */
    public final static Color TABLE_LINE_COLOR = new Color(229, 229, 229);
    /**
     * 样式布局相关
     */
    // 主面板水平间隔
    public final static int MAIN_H_GAP = 25;
    // 主面板Label 大小
    public final static Dimension LABLE_SIZE = new Dimension(1300, 30);
    // Item Label 大小
    public final static Dimension LABLE_SIZE_ITEM = new Dimension(93, 28);
    // Item text field 大小
    public final static Dimension TEXT_FIELD_SIZE_ITEM = new Dimension(400, 24);
    public final static Dimension REVERSED_TEXT_FIELD_SIZE_ITEM = new Dimension(320,24);
    // radio 大小
    public final static Dimension RADIO_SIZE = new Dimension(1300, 60);
    // 高级选项面板Item 大小
    public final static Dimension PANEL_ITEM_SIZE = new Dimension(1300, 40);
    /**
     * 可选语言
     */
    public final static String [ ] languageList={"中文 (zh-CN)","English (en-WW)"};

}
