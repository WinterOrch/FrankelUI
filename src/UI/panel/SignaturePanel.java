package UI.panel;

import UI.MainWindow;
import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SignaturePanel extends FunctionPanel {
    private static JPanel listPanelBmpEncode;
    private static JPanel listPanelBmpDecode;
    private static JPanel SignaturePanelMain;
    private static Signature_BMPEncodePanel signaturePanelBmpEncode;
    private static Signature_BMPDecodePanel signaturePanelBmpDecode;

    /**
     * 构造
     * created in 23:25 2018/5/3
     */
    public SignaturePanel() {
        super("signature");
    }

    /**
     * 中部主界面
     * created in 23:32 2018/5/3
     */
    public JPanel getCenterPanel() {
        // 初始化
        signaturePanelBmpEncode = new Signature_BMPEncodePanel();
        signaturePanelBmpDecode = new Signature_BMPDecodePanel();
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

        JLabel labelBmpEncode = new JLabel(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE"));
        JLabel labelBmpDecode = new JLabel(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE"));
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
        SignaturePanelMain = new JPanel();
        SignaturePanelMain.setBackground(UIConstants.MAIN_BACK_COLOR);
        SignaturePanelMain.setLayout(new BorderLayout());
        SignaturePanelMain.add(signaturePanelBmpEncode);
        //TODO
        panelCenter.add(panelList, BorderLayout.WEST);
        panelCenter.add(SignaturePanelMain, BorderLayout.CENTER);


        return panelCenter;
    }

    /**
     * 添加监听器
     * created in 23:34 2018/5/3
     */
    public void addListener() {
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
                    SignaturePanel.SignaturePanelMain.removeAll();
                    SignaturePanel.SignaturePanelMain.add(signaturePanelBmpEncode);
                    MainWindow.signaturePanel.updateUI();
                }
            }
        });

        listPanelBmpDecode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!listPanelBmpDecode.getBackground().equals(UIConstants.PRESSED_BACK_COLOR)){
                    listPanelBmpEncode.setBackground(UIConstants.NAVI_BAR_BACK_COLOR);
                    listPanelBmpDecode.setBackground(UIConstants.PRESSED_BACK_COLOR);
                    SignaturePanel.SignaturePanelMain.removeAll();
                    SignaturePanel.SignaturePanelMain.add(signaturePanelBmpDecode);
                    MainWindow.signaturePanel.updateUI();
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
    }
}
