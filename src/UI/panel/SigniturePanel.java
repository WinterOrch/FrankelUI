package UI.panel;

import UI.constant.UIConstants;

import javax.swing.*;
import java.awt.*;

public class SigniturePanel extends FunctionPanel {
    /**
     * 构造
     * created in 23:25 2018/5/3
     */
    public SigniturePanel() {
        super("signiture");
    }

    /**
     * 中部主界面
     * created in 23:32 2018/5/3
     */
    public JPanel getCenterPanel() {
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
    public void addListener() {
        //TODO
    }
}
