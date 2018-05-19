package UI.panel;

import UI.MainWindow;
import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SettingPanel extends FunctionPanel {
    private static JPanel panelOption;
    private static JPanel panelAbout;
    private static JPanel settingPanelMain;
    private static Setting_OptionPanel settingPanelOption;
    private static JPanel settingPanelAbout;

    /**
     * 构造
     * created in 21:02 2018/4/30
     */
    public SettingPanel() {
        super("setting");
    }

    /**
     * 中间选择列表
     * created in 21:37 2018/4/30
     */
    public JPanel getCenterPanel() {
        // 初始化
        settingPanelOption = new Setting_OptionPanel();
        settingPanelAbout = new JPanel();
        // 中间面板
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(UIConstants.MAIN_BACK_COLOR);
        panelCenter.setLayout(new BorderLayout());

        // 列表Panel
        JPanel panelList = new JPanel();
        Dimension preferredSize = new Dimension(245, UIConstants.MAIN_WINDOW_HEIGHT);
        panelList.setPreferredSize(preferredSize);
        panelList.setBackground(UIConstants.LIST_BACK_COLOR);
        panelList.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        panelOption = new JPanel();
        panelOption.setBackground(UIConstants.PRESSED_BACK_COLOR);
        panelOption.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        Dimension preferredSizeListItem = new Dimension(245, 48);
        panelOption.setPreferredSize(preferredSizeListItem);
        panelAbout = new JPanel();
        panelAbout.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
        panelAbout.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        panelAbout.setPreferredSize(preferredSizeListItem);

        JLabel labelOption = new JLabel(PropertiesLocale.getProperty("UI.SETTING.OPTION"));
        JLabel labelAbout = new JLabel(PropertiesLocale.getProperty("UI.SETTING.ABOUT"));
        Font fontListItem = new Font(PropertiesLocale.getProperty("UI.FONT"), Font.PLAIN, 15);
        labelOption.setFont(fontListItem);
        labelAbout.setFont(fontListItem);
        labelOption.setForeground(Color.white);
        labelAbout.setForeground(Color.white);
        panelOption.add(labelOption);
        panelAbout.add(labelAbout);

        panelList.add(panelOption);
        panelList.add(panelAbout);

        // 设置Panel
        settingPanelMain = new JPanel();
        settingPanelMain.setBackground(UIConstants.MAIN_BACK_COLOR);
        settingPanelMain.setLayout(new BorderLayout());
        settingPanelMain.add(settingPanelOption);

        panelCenter.add(panelList, BorderLayout.WEST);
        panelCenter.add(settingPanelMain, BorderLayout.CENTER);

        return panelCenter;
    }

    /**
     * 添加监听器
     * created in 21:39 2018/4/30
     */
    public void addListener(){
        panelOption.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(!panelOption.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    panelOption.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!panelOption.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    panelOption.setBackground(UIConstants.ROLL_OVER_COLOR);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(!panelOption.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    panelOption.setBackground(UIConstants.PRESSED_BACK_COLOR);
                    panelAbout.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
                    SettingPanel.settingPanelMain.removeAll();
                    SettingPanel.settingPanelMain.add(settingPanelOption);
                    MainWindow.settingPanel.updateUI();
                }
            }
        });

        panelAbout.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!panelAbout.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    panelOption.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
                    panelAbout.setBackground(UIConstants.PRESSED_BACK_COLOR);
                    SettingPanel.settingPanelMain.removeAll();
                    SettingPanel.settingPanelMain.add(settingPanelAbout);
                    MainWindow.settingPanel.updateUI();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(!panelAbout.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    panelAbout.setBackground(UIConstants.ROLL_OVER_COLOR);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!panelAbout.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    panelAbout.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
                }
            }
        });
    }

}
