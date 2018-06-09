package UI.tools;
import UI.component.ExcuteButton;
import UI.component.ProgressBar;
import UI.constant.UIConstants;

import javax.swing.*;

import java.awt.*;

import java.util.concurrent.TimeUnit;


public class UITest {
    public static void main(String[] args) {

        JFrame frame = new JFrame("JProgressBarDemo");
        frame.setSize(700, 400);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(new FlowLayout());

        final ProgressBar progressBar = new ProgressBar(0,100);

        progressBar.setOrientation(JProgressBar.HORIZONTAL);

        progressBar.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);

        frame.add(progressBar);

        ExcuteButton btn = new ExcuteButton("Start", 3F);
        btn.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        btn.addActionListener(e -> new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                progressBar.setValue(i * 10);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }).start());
        frame.add(btn);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
