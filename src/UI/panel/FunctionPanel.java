package UI.panel;

import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * 功能面板抽象类
 * @author Frankel.Y
 * Created in 10:48 2018/5/19
 */
public abstract class FunctionPanel extends JPanel{
    private String upTitle;

    FunctionPanel(String title){
        upTitle = title.toUpperCase();
        initialize();
        addComponent();
        addListener();
    }

    /**
     * 初始化
     * created in 23:26 2018/5/3
     */
    public void initialize() {
        this.setBackground(UIConstants.MAIN_BACK_COLOR);
        this.setLayout(new BorderLayout());
    }

    /**
     * 添加组件
     * created in 23:28 2018/5/3
     */
    public void addComponent() {
        this.add(getUpPanel(), BorderLayout.NORTH);
        this.add(getCenterPanel(), BorderLayout.CENTER);
    }

    /**
     * 产生上方标题横条
     * created in 10:57 2018/5/19
     */
    private JPanel getUpPanel() {
        JPanel panelUp = new JPanel();
        panelUp.setBackground(UIConstants.MAIN_BACK_COLOR);
        panelUp.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.MAIN_H_GAP, 5));

        JLabel labelTitle = new JLabel(PropertiesLocale.getProperty("UI."+upTitle+".TITLE"));
        labelTitle.setFont(UIConstants.FONT_TITLE);
        labelTitle.setForeground(UIConstants.NAVI_BAR_BACK_COLOR);
        panelUp.add(labelTitle);

        return panelUp;
    }

    public abstract JPanel getCenterPanel();

    public abstract void addListener();

    /**
     * 改变语言，实时更新UI
     * created in 0:25 2018/5/5
     */
    public void refreshLocale(){
        this.removeAll();
        this.initialize();
        this.addComponent();
        this.addListener();
        this.updateUI();
    }
}
