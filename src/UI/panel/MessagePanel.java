package UI.panel;

import UI.MainWindow;
import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("Duplicates")
public class MessagePanel extends JPanel {
    private static JPanel listPanelBmpEncode;
    private static JPanel listPanelBmpDecode;
    private static JPanel messagePanelMain;
    private static Message_BMPEncodePanel messagePanelBmpEncode;
    private static Message_BMPDecodePanel messagePanelBmpDecode;

    /**
     * 构造
     * created in 23:25 2018/5/3
     */
    public MessagePanel() {
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
        messagePanelBmpEncode = new Message_BMPEncodePanel();
        messagePanelBmpDecode = new Message_BMPDecodePanel();
        //TODO

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

        JLabel labelTitle = new JLabel(PropertiesLocale.getProperty("UI.MESSAGE.TITLE"));
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

        // 列表面板
        JPanel panelList = new JPanel();
        Dimension preferredSize = new Dimension(245, UIConstants.MAIN_WINDOW_HEIGHT);
        panelList.setPreferredSize(preferredSize);
        panelList.setBackground(UIConstants.LIST_BACK_COLOR);
        panelList.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        listPanelBmpEncode = new JPanel();
        listPanelBmpEncode.setBackground(UIConstants.PRESSED_BACK_COLOR);
        listPanelBmpEncode.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        Dimension preferredSizeListItem = new Dimension(245, 48);
        listPanelBmpEncode.setPreferredSize(preferredSizeListItem);
        listPanelBmpDecode = new JPanel();
        listPanelBmpDecode.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
        listPanelBmpDecode.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        listPanelBmpDecode.setPreferredSize(preferredSizeListItem);

        JLabel labelBmpEncode = new JLabel(PropertiesLocale.getProperty("UI.MESSAGE.BMPENCODE"));
        JLabel labelBmpDecode = new JLabel(PropertiesLocale.getProperty("UI.MESSAGE.BMPDECODE"));
        Font fontListItem = new Font(PropertiesLocale.getProperty("UI.FONT"), Font.PLAIN, 15);
        labelBmpEncode.setFont(fontListItem);
        labelBmpDecode.setFont(fontListItem);
        labelBmpEncode.setForeground(Color.white);
        labelBmpDecode.setForeground(Color.white);
        listPanelBmpEncode.add(labelBmpEncode);
        listPanelBmpDecode.add(labelBmpDecode);

        panelList.add(listPanelBmpEncode);
        panelList.add(listPanelBmpDecode);

        //  消息隐藏panel
        messagePanelMain = new JPanel();
        messagePanelMain.setBackground(UIConstants.MAIN_BACK_COLOR);
        messagePanelMain.setLayout(new BorderLayout());
        messagePanelMain.add(messagePanelBmpEncode);
        //TODO
        panelCenter.add(panelList, BorderLayout.WEST);
        panelCenter.add(messagePanelMain, BorderLayout.CENTER);


        return panelCenter;
    }

    /**
     * 添加监听器
     * created in 23:34 2018/5/3
     */
    private void addListener() {
        listPanelBmpEncode.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(!listPanelBmpEncode.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    listPanelBmpEncode.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!listPanelBmpEncode.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    listPanelBmpEncode.setBackground(UIConstants.ROLL_OVER_COLOR);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(!listPanelBmpEncode.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    listPanelBmpEncode.setBackground(UIConstants.PRESSED_BACK_COLOR);
                    listPanelBmpDecode.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
                    MessagePanel.messagePanelMain.removeAll();
                    MessagePanel.messagePanelMain.add(messagePanelBmpEncode);
                    MainWindow.messagePanel.updateUI();
                }
            }
        });

        listPanelBmpDecode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!listPanelBmpDecode.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    listPanelBmpEncode.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
                    listPanelBmpDecode.setBackground(UIConstants.PRESSED_BACK_COLOR);
                    MessagePanel.messagePanelMain.removeAll();
                    MessagePanel.messagePanelMain.add(messagePanelBmpDecode);
                    MainWindow.messagePanel.updateUI();
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
                if(!listPanelBmpDecode.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    listPanelBmpDecode.setBackground(UIConstants.ROLL_OVER_COLOR);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!listPanelBmpDecode.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    listPanelBmpDecode.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
                }
            }
        });
        //TODO
    }

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
