package org.example;
import javax.swing.*;
import java.awt.*;

public class gamepage extends JFrame {
    public void create(String title) {
//窗口代码如下
        JFrame jf = new JFrame(title);
        jf.setSize(1260, 840);
        jf.setResizable(false);
        jf.setLocation(150, 15);
        background mp = new background();
        jf.add(mp);
        jf.setBackground(Color.black);
//窗口结束代码
        mp.setLayout(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
class background extends JPanel {
    @Override
    public void paint(Graphics g) {
    }
}