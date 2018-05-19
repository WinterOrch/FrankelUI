package UI.panel;

import javax.swing.*;
import UI.MainWindow;
import UI.component.*;
import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主窗口导航栏
 * @author Frankel.Y
 * Created in 0:46 2018/4/30
 * Modified by
 */
public class NaviBarPanel extends JPanel{
    private static NaviIconButton messageButton;
    private static NaviIconButton pictureButton;
    private static NaviIconButton signitureButton;
    private static NaviIconButton settingButton;

    /**
     * 构造方法
     * created in 0:55 2018/4/30
     */
    public NaviBarPanel() {
        initialize();
        addButton();
        addListener();
    }

    /**
     * 初始化
     * created in 0:56 2018/4/30
     */
    private void initialize(){
        Dimension preferredSize = new Dimension(UIConstants.NAVI_BAR_WIDTH, UIConstants.MAIN_WINDOW_HEIGHT);
        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);
        this.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
        this.setLayout(new GridLayout(2, 1));
    }
    
    /**
     * 添加按钮
     * created in 0:59 2018/4/30
     */
    private void addButton(){

        JPanel upPanel = new JPanel();
        upPanel.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
        upPanel.setLayout(new FlowLayout(FlowLayout.CENTER, -2, -4));
        JPanel downPanel = new JPanel();
        downPanel.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
        downPanel.setLayout(new BorderLayout(0, 0));

        messageButton = new NaviIconButton(UIConstants.ICON_MESSAGE, UIConstants.ICON_MESSAGE_PRESSED,
                UIConstants.ICON_MESSAGE_READY, PropertiesLocale.getProperty("UI.MESSAGE.TITLE"));
        pictureButton = new NaviIconButton(UIConstants.ICON_PICTURE, UIConstants.ICON_PICTURE_PRESSED,
                UIConstants.ICON_PICTURE_READY, PropertiesLocale.getProperty("UI.PICTURE.TITLE"));
        signitureButton = new NaviIconButton(UIConstants.ICON_SIGNITURE, UIConstants.ICON_SIGNITURE_PRESSED,
                UIConstants.ICON_SIGNITURE_READY, PropertiesLocale.getProperty("UI.PICTURE.TITLE"));
        settingButton = new NaviIconButton(UIConstants.ICON_SETTING, UIConstants.ICON_SETTING_PRESSED,
                UIConstants.ICON_SETTING_READY, PropertiesLocale.getProperty("UI.SETTING.TITLE"));

        upPanel.add(messageButton);
        upPanel.add(pictureButton);
        upPanel.add(signitureButton);

        downPanel.add(settingButton, BorderLayout.SOUTH);
        this.add(upPanel);
        this.add(downPanel);
    }

    /**
     * 添加监听器，实现主界面跳转
     * created in 1:00 2018/4/30
     */
    private void addListener(){
        messageButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageButton.setIcon(UIConstants.ICON_MESSAGE_PRESSED);
                //TODO
                pictureButton.setIcon(UIConstants.ICON_PICTURE);
                signitureButton.setIcon(UIConstants.ICON_SIGNITURE);
                settingButton.setIcon(UIConstants.ICON_SETTING);

                MainWindow.centerPanel.removeAll();
                //MessagePanel.getContent();
                MainWindow.centerPanel.add(MainWindow.messagePanel, BorderLayout.CENTER);
                MainWindow.centerPanel.updateUI();
            }
        });

        pictureButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageButton.setIcon(UIConstants.ICON_MESSAGE);
                //TODO
                pictureButton.setIcon(UIConstants.ICON_PICTURE_PRESSED);
                signitureButton.setIcon(UIConstants.ICON_SIGNITURE);
                settingButton.setIcon(UIConstants.ICON_SETTING);

                MainWindow.centerPanel.removeAll();
                //PicturePanel.getContent();
                MainWindow.centerPanel.add(MainWindow.picturePanel, BorderLayout.CENTER);
                MainWindow.centerPanel.updateUI();
            }
        });

        settingButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageButton.setIcon(UIConstants.ICON_MESSAGE);
                pictureButton.setIcon(UIConstants.ICON_PICTURE);
                signitureButton.setIcon(UIConstants.ICON_SIGNITURE);
                //TODO
                settingButton.setIcon(UIConstants.ICON_SETTING_PRESSED);

                MainWindow.centerPanel.removeAll();
                //SettingPanel.getContent();
                MainWindow.centerPanel.add(MainWindow.settingPanel, BorderLayout.CENTER);
                MainWindow.centerPanel.updateUI();
            }
        });

        signitureButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageButton.setIcon(UIConstants.ICON_MESSAGE);
                pictureButton.setIcon(UIConstants.ICON_PICTURE);
                signitureButton.setIcon(UIConstants.ICON_SIGNITURE_PRESSED);
                //TODO
                settingButton.setIcon(UIConstants.ICON_SETTING);

                MainWindow.centerPanel.removeAll();
                //SettingPanel.getContent();
                MainWindow.centerPanel.add(MainWindow.signiturePanel, BorderLayout.CENTER);
                MainWindow.centerPanel.updateUI();
            }
        });
    }

    /**
     * 用于变更语言时实时改变文字
     * created in 23:26 2018/4/30
     */
    public static void refreshLocale(){
        //TODO
        messageButton.setToolTipText(PropertiesLocale.getProperty("UI.MESSAGE.TITLE"));
        pictureButton.setToolTipText(PropertiesLocale.getProperty("UI.PICTURE.TITLE"));
        signitureButton.setToolTipText(PropertiesLocale.getProperty("UI.SIGNITURE.TITLE"));
        settingButton.setToolTipText(PropertiesLocale.getProperty("UI.SETTING.TITLE"));
    }
}
