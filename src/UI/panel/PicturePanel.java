package UI.panel;

import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;

import javax.swing.*;
import java.awt.*;

public class PicturePanel extends JPanel {
    /**
     * 构造
     * created in 23:25 2018/5/3
     */
    public PicturePanel() {
        initialize();
        addComponent();
        addListener();
    }

    /**
     * 初始化
     * created in 23:26 2018/5/3
     */
    private void initialize() {
        this.setBackground(UIConstants.MAIN_BACK_COLOR);
        this.setLayout(new BorderLayout());

    }

    /**
     * 添加组件
     * created in 23:28 2018/5/3
     */
    private void addComponent() {
        this.add(getUpPanel(), BorderLayout.NORTH);
        this.add(getCenterPanel(), BorderLayout.CENTER);
    }

    /**
     * 上方横条
     * created in 23:29 2018/5/3
     */
    private JPanel getUpPanel() {
        JPanel panelUp = new JPanel();
        panelUp.setBackground(UIConstants.MAIN_BACK_COLOR);
        panelUp.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.MAIN_H_GAP, 5));

        JLabel labelTitle = new JLabel(PropertiesLocale.getProperty("UI.PICTURE.TITLE"));
        labelTitle.setFont(UIConstants.FONT_TITLE);
        labelTitle.setForeground(UIConstants.NAVI_BAR_BACK_COLOR);
        panelUp.add(labelTitle);

        return panelUp;
    }

    /**
     * 中部主界面
     * created in 23:32 2018/5/3
     */
    private JPanel getCenterPanel() {
        // 中间面板
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(UIConstants.MAIN_BACK_COLOR);
        panelCenter.setLayout(new BorderLayout());
        //TODO
        return panelCenter;
    }

    /**
     * 添加监听器
     * created in 23:34 2018/5/3
     */
    private void addListener() {
        //TODO
    }
}
